package com.megatrex4;

import com.megatrex4.block.CustomCableTiers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.megatrex4.CasingRegistry.registerBlocks;
import static com.megatrex4.block.CustomHatchLevels.registerHatches;

public class AugmentedIndustrialization implements ModInitializer {
	public static final String MOD_ID = "augmented_industrialization";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Log initialization
		LOGGER.info("Initializing Augmented Industrialization Mod: " + MOD_ID);

		// Register the new cable tiers
		CustomCableTiers.registerCableTiers();
		registerBlocks();
		registerHatches();
		CreativeTabRegistry.register();
		// Log the successful registration of the cable tiers
		LOGGER.info("IVPlus CableTier added successfully!");
	}
}
