package dev.wooferz.generichud.element;

import dev.wooferz.generichud.helpers.CpsHelper;
import dev.wooferz.hudlib.HudAnchor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;

import java.util.List;

public class CpsHudElement extends BaseTextElement{

    public CpsHudElement() {
        super("CPS HUD", 5, 5 + 17 + 10 + 17 + 10 + 17 + 10, 55, 17, 1, "cps-hud", HudAnchor.HorizontalAnchor.LEFT, HudAnchor.VerticalAnchor.TOP);
    }

    @Override
    public void render(int i, int i1, int i2, int i3, DrawContext drawContext, float v) {


        renderText(String.valueOf(CpsHelper.getLeftCps()) + " CPS", i, i1, i2, i3, drawContext, v, true);
        renderBox(drawContext, i, i1, i2, i3);

    }


}
