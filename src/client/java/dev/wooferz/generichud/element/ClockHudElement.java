package dev.wooferz.generichud.element;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.wooferz.generichud.config.BaseTextConfig;
import dev.wooferz.generichud.config.ClockHudConfig;
import dev.wooferz.hudlib.HudAnchor;
import dev.wooferz.hudlib.hud.HUDConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockHudElement extends BaseTextElement {

    public ClockHudElement() {
        super("Clock HUD", 5, 5 + 17 + 10 + 17 + 10, 55, 17, 1, "clock-hud", HudAnchor.HorizontalAnchor.LEFT, HudAnchor.VerticalAnchor.TOP);
    }

    @Override
    public void render(int i, int i1, int i2, int i3, DrawContext drawContext, float v) {

        ClockHudConfig config = (ClockHudConfig) getConfig();

        String pattern = "";

        if (!config.twelveHours) {
            pattern = "HH:mm";
        } else {
            pattern = "hh:mm";
        }

        if (config.includeSeconds) {
            pattern += ":ss";
        }
        if (config.twelveHours) {
            pattern += " a";
        }



        String timeStamp = new SimpleDateFormat(pattern).format(new Date());
        timeStamp.toUpperCase();

        renderText(timeStamp, i, i1, getWidth(), i3, drawContext, v, true);
        renderBox(drawContext, i, i1, getWidth(), i3);

    }

    @Override
    public Integer getWidth() {
        ClockHudConfig config = (ClockHudConfig) getConfig();

        int width = 55;
        if (config.twelveHours) {
            width += 12;
        }
        if (config.includeSeconds) {
            width += 12;
        }
        return width;
    }

    @Override
    public Class<?> getConfigType() {
        return ClockHudConfig.class;
    }

    @Override
    public HUDConfig getConfig() {
        if (config == null || !(config instanceof ClockHudConfig)) {
          config = new ClockHudConfig();
        }
        return config;
    }

    @Override
    public void setConfig(HUDConfig config) {
        if (config != null) {
            if (config instanceof ClockHudConfig) {
                this.config = (ClockHudConfig) config;
            }
        }
    }



    @Override
    public OptionGroup generateConfig() {

        ClockHudConfig config = (ClockHudConfig) getConfig();

        OptionGroup optionGroup = OptionGroup.createBuilder()
                .name(Text.of(displayName))
                .option(Option.<Color>createBuilder()
                        .name(Text.of("Text Color"))
                        .binding(Color.WHITE,
                                () -> config.color,
                                newColor -> config.color = newColor)
                        .controller(ColorControllerBuilder::create)
                        .build()
                )
                .option(Option.<Color>createBuilder()
                        .name(Text.of("Background Color"))
                        .binding(new Color(0x99000000, true),
                                () -> config.bgColor,
                                newColor -> config.bgColor = newColor)
                        .controller(opt -> ColorControllerBuilder.create(opt)
                                .allowAlpha(true))
                        .build()
                )
                .option(
                        Option.<Boolean>createBuilder()
                                .name(Text.of("Chroma"))
                                .binding(
                                        false,
                                        () -> config.chroma,
                                        newValue -> config.chroma = newValue
                                )
                                .controller(TickBoxControllerBuilder::create)
                                .build()
                )
                .option(
                        Option.<Boolean>createBuilder()
                                .name(Text.of("Include Seconds"))
                                .binding(
                                        false,
                                        () -> config.includeSeconds,
                                        newValue -> config.includeSeconds = newValue
                                )
                                .controller(TickBoxControllerBuilder::create)
                                .build()
                )
                .option(
                        Option.<Boolean>createBuilder()
                                .name(Text.of("12 Hour Time"))
                                .binding(
                                        false,
                                        () -> config.twelveHours,
                                        newValue -> config.twelveHours = newValue
                                )
                                .controller(TickBoxControllerBuilder::create)
                                .build()
                )
                .build();

        return optionGroup;
    }

}
