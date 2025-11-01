package dev.wooferz.generichud.element;

import dev.wooferz.hudlib.HudAnchor;
import dev.wooferz.hudlib.utils.TextUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class FpsHudElement extends BaseTextElement{

    public FpsHudElement() {
        super("FPS HUD", 5, 5, 55, 17, 1, "fps-hud", HudAnchor.HorizontalAnchor.LEFT, HudAnchor.VerticalAnchor.TOP);
    }

    @Override
    public void render(int x, int y, int width, int height, DrawContext context, float v) {

        int padding = 4;
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int currentFPS = MinecraftClient.getInstance().getCurrentFps();
        String text = currentFPS + " FPS";
        int argb = (config.bgColor.getAlpha() << 24) | (config.bgColor.getRed() << 16) | (config.bgColor.getGreen() << 8) | config.bgColor.getBlue();
        context.fill(x, y, x + width, y + 9 + (padding * 2), argb);

        //context.drawCenteredTextWithShadow(textRenderer, text, (width/2)+x, y + padding + 1, config.color.getRGB());

        TextUtils.drawText(context, textRenderer, text, (width/2)+x, y + padding + 1, config.color.getRGB(), true, true, config.chroma);

    }


}