package com.megatrex4.block;

import com.megatrex4.CasingRegistry;
import aztech.modern_industrialization.api.energy.CableTier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.block.Block;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomCableTiers {
    public static final CableTier IV_PLUS;

    static {
        try {
            Constructor<CableTier> constructor = CableTier.class.getDeclaredConstructor(
                    String.class, String.class, String.class, long.class, RegistryKey.class);
            constructor.setAccessible(true);

            // Create IV_PLUS tier with a value between EV (8192) and SUPERCONDUCTOR (128000000)
            IV_PLUS = constructor.newInstance(
                    "iv_plus",
                    "IV+",
                    "Very High Voltage Plus",
                    (long) 8192 * 8,  // EU value between EV (8192) and SUPERCONDUCTOR (128000000)
                    CasingRegistry.IV_PLUS_MACHINE_HULL
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to create IV_PLUS tier", e);
        }
    }

    public static void injectIVPlusTier() throws NoSuchFieldException, IllegalAccessException {
        // Access the static tiers map
        Field tiersField = CableTier.class.getDeclaredField("tiers");
        tiersField.setAccessible(true);

        // Get the current list of CableTiers
        Map<String, CableTier> tiers = (Map<String, CableTier>) tiersField.get(null);

        // Create a list of the tiers
        List<CableTier> tierList = new ArrayList<>(tiers.values());

        // Ensure EV and SUPERCONDUCTOR exist in the tier list
        CableTier evTier = CableTier.getTier("ev");
        CableTier superconductorTier = CableTier.getTier("superconductor");

        // Find the position to insert the IV_PLUS tier
        int evIndex = tierList.indexOf(evTier);
        int superconductorIndex = tierList.indexOf(superconductorTier);

        // Ensure proper position by inserting IV_PLUS between EV and SUPERCONDUCTOR
        if (evIndex != -1 && superconductorIndex != -1 && evIndex < superconductorIndex) {
            tierList.add(evIndex + 1, IV_PLUS);
        } else {
            throw new IllegalStateException("Unable to properly order tiers for IV_PLUS.");
        }

        // Rebuild the tiers map with the new tier list
        tiers.clear();
        for (CableTier tier : tierList) {
            tiers.put(tier.name, tier);
        }
    }

}
