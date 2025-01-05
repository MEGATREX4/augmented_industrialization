package com.megatrex4.block;

import com.megatrex4.CasingRegistry;
import aztech.modern_industrialization.api.energy.CableTier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.block.Block;

import java.lang.reflect.Constructor;

public class CustomCableTiers {
    public static final CableTier IV_PLUS;

    static {
        try {
            Constructor<CableTier> constructor = CableTier.class.getDeclaredConstructor(
                    String.class, String.class, String.class, long.class, RegistryKey.class);
            constructor.setAccessible(true);
            IV_PLUS = constructor.newInstance(
                    "iv_plus",
                    "IV+",
                    "Very High Voltage Plus",
                    (long)128000000 * 16,
                    CasingRegistry.IV_PLUS_MACHINE_HULL
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to create IV_PLUS tier", e);
        }
    }

    public static void registerCableTiers() {
        CableTier.addTier(IV_PLUS);
    }
}

