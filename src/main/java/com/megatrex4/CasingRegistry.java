package com.megatrex4;

import aztech.modern_industrialization.MIIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class CasingRegistry {

    // Define RegistryKeys for each block (casing, hull, etc.)
    public static final RegistryKey<Block> IV_PLUS_MACHINE_HULL = block("iv_plus_machine_hull");

    // Create a list to automatically register multiple blocks
    public static final List<RegistryKey<Block>> BLOCKS = Arrays.asList(IV_PLUS_MACHINE_HULL);

    // Register blocks and block items automatically
    public static void registerBlocks() {
        BLOCKS.forEach(CasingRegistry::registerBlock);
    }

    public static void registerBlock(RegistryKey<Block> blockKey) {
        String path = blockKey.getValue().getPath();  // Get the path from the RegistryKey
        Block block = new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));  // Create your block (customize settings)

        Registry.register(Registries.BLOCK, new AMIIdentifier(path), block);

        // Register the block item
        registerBlockItem(path, block);
    }

    private static void registerBlockItem(String name, Block block) {
        Item blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, new AMIIdentifier(name), blockItem);
    }

    private static RegistryKey<Block> block(String path) {
        return RegistryKey.of(RegistryKeys.BLOCK, new AMIIdentifier(path));
    }
}
