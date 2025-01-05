package com.megatrex4.block;

import aztech.modern_industrialization.api.energy.CableTier;
import aztech.modern_industrialization.materials.part.PartKey;
import aztech.modern_industrialization.materials.part.PartKeyProvider;
import aztech.modern_industrialization.materials.part.PartTemplate;
import aztech.modern_industrialization.pipes.MIPipes;

import static aztech.modern_industrialization.materials.property.MaterialProperty.MEAN_RGB;

public class CablePart implements PartKeyProvider {

    // Convert 128000000L to a more compact form using scientific notation
    public static final long CABLE_CAPACITY = 128 * 1000000L; // Equivalent to 128000000L

    public PartTemplate of(CableTier tier) {
        return new PartTemplate("Cable", key()).withoutTextureRegister()
                .withRegister((partContext, part, itemPath, itemId, itemTag, englishName) ->
                        MIPipes.INSTANCE.registerCableType(
                                englishName,
                                partContext.getMaterialName(),
                                partContext.get(MEAN_RGB) | 0xff000000,
                                tier));
    }

    @Override
    public PartKey key() {
        return new PartKey("cable");
    }
}
