package dev.wooferz.generichud;

import dev.wooferz.generichud.element.*;
import dev.wooferz.hudlib.HudManager;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericHUDClient implements ClientModInitializer {
	public static final String MOD_ID = "generic-hud";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		HudManager.registerHudElement(new FpsHudElement());
		HudManager.registerHudElement(new PingHudElement());
		HudManager.registerHudElement(new ClockHudElement());
		HudManager.registerHudElement(new CpsHudElement());
		HudManager.registerHudElement(new CoordinateElement());
	}
}