package dev.wooferz.generichud.element;

import dev.wooferz.hudlib.HudAnchor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;

import java.util.List;

public class PingHudElement extends BaseTextElement{

    public PingHudElement() {
        super("Ping HUD", 5, 5 + 17 + 10, 55, 17, 1, "ping-hud", HudAnchor.HorizontalAnchor.LEFT, HudAnchor.VerticalAnchor.TOP);
    }

    @Override
    public void render(int i, int i1, int i2, int i3, DrawContext drawContext, float v) {
        renderBox(drawContext, i, i1, i2, i3);
        renderText(String.valueOf(getPing(MinecraftClient.getInstance())) + " ms", i, i1, i2, i3, drawContext, v, true);
    }

    private int getPing(MinecraftClient mc) {
        // dirty way to get ping
        if (mc.player == null) return 0;
        List<PlayerListEntry> players = (List<PlayerListEntry>) mc.player.networkHandler.getPlayerList().stream().toList();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getProfile().equals(mc.player.getGameProfile())) {
                return players.get(i).getLatency();
            }
        }
        return 0;
    }
}
