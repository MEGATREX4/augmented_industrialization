package com.megatrex4;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CreativeTabRegistry {
    public static final RegistryKey<ItemGroup> AUGMENTED_INDUSTRIALIZATION_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            new AMIIdentifier("augmented_industrialization")
    );

    public static final ItemGroup AUGMENTED_INDUSTRIALIZATION = ItemGroup.create(ItemGroup.Row.TOP, 0)
            .displayName(Text.translatable("itemGroup.augmented_industrialization"))
            .icon(() -> new ItemStack(Registries.ITEM.get(CasingRegistry.IV_PLUS_MACHINE_HULL.getValue()))) // Use the registered item
            .build();

    public static void register() {
        // Register the item group
        Registry.register(Registries.ITEM_GROUP, AUGMENTED_INDUSTRIALIZATION_KEY, AUGMENTED_INDUSTRIALIZATION);

        // Add items to the custom tab
        ItemGroupEvents.modifyEntriesEvent(AUGMENTED_INDUSTRIALIZATION_KEY).register(entries -> {
            entries.add(Registries.ITEM.get(CasingRegistry.IV_PLUS_MACHINE_HULL.getValue()));
            addItemByIdentifier(entries, "modern_industrialization:superconductor_iv_plus_transformer");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_superconductor_transformer");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_storage_unit");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_item_input_hatch");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_item_output_hatch");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_fluid_input_hatch");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_fluid_output_hatch");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_energy_input_hatch");
            addItemByIdentifier(entries, "modern_industrialization:iv_plus_energy_output_hatch");
        });
    }

    private static void addItemByIdentifier(FabricItemGroupEntries entries, String identifier) {
        Identifier id = new Identifier(identifier);
        if (Registries.ITEM.containsId(id)) {
            entries.add(Registries.ITEM.get(id));
            System.out.println("Item with identifier " + id + " added!");
        } else {
            System.err.println("Item with identifier " + id + " does not exist!");
        }
    }

}
