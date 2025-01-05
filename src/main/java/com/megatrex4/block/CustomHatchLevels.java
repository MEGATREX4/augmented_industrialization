package com.megatrex4.block;

import aztech.modern_industrialization.inventory.SlotPositions;
import aztech.modern_industrialization.machines.MachineBlockEntity;
import aztech.modern_industrialization.machines.init.MachineRegistrationHelper;
import com.megatrex4.CasingRegistry;
import aztech.modern_industrialization.api.energy.CableTier;
import aztech.modern_industrialization.inventory.ConfigurableFluidStack;
import aztech.modern_industrialization.inventory.ConfigurableItemStack;
import aztech.modern_industrialization.inventory.MIInventory;
import aztech.modern_industrialization.machines.blockentities.hatches.EnergyHatch;
import aztech.modern_industrialization.machines.blockentities.hatches.FluidHatch;
import aztech.modern_industrialization.machines.blockentities.hatches.ItemHatch;
import aztech.modern_industrialization.machines.gui.MachineGuiParameters;
import aztech.modern_industrialization.machines.models.MachineCasing;
import aztech.modern_industrialization.machines.models.MachineCasings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class CustomHatchLevels {
    public static void registerHatches() {
        registerCustomItemHatch("IV+", "iv_plus", MachineCasings.get("iv_plus"), 3, 9, 9, 16);
        registerCustomFluidHatch("IV+", "iv_plus", MachineCasings.get("iv_plus"), 1024);
        registerCustomEnergyHatch(CustomCableTiers.IV_PLUS);
    }

    // Register custom item hatch with 3x3 grid
    private static void registerCustomItemHatch(String englishPrefix, String prefix, MachineCasing casing, int rows, int columns, int xStart, int yStart) {
        for (int iter = 0; iter < 2; ++iter) {
            boolean input = iter == 0;
            String machine = prefix + "_item_" + (input ? "input" : "output") + "_hatch";
            String englishName = englishPrefix + " Item" + (input ? " Input" : " Output") + " Hatch";

            MachineRegistrationHelper.registerMachine(englishName, machine, (bet) -> {
                List<ConfigurableItemStack> itemStacks = new ArrayList<>();
                for (int i = 0; i < rows * columns; ++i) {
                    itemStacks.add(input ? ConfigurableItemStack.standardInputSlot() : ConfigurableItemStack.standardOutputSlot());
                }
                MIInventory inventory = new MIInventory(itemStacks, Collections.emptyList(), (new SlotPositions.Builder()).addSlots(xStart, yStart, columns, rows).build(), SlotPositions.empty());
                return new ItemHatch(bet, (new MachineGuiParameters.Builder(machine, true)).build(), input, true, inventory);
            }, (bet) -> MachineBlockEntity.registerItemApi(bet));

            MachineRegistrationHelper.addMachineModel(machine, "hatch_item", casing, true, false, true, false);
        }
    }

    private static void registerCustomFluidHatch(String englishPrefix, String prefix, MachineCasing casing, int bucketCapacity) {
        for (int iter = 0; iter < 2; ++iter) {
            boolean input = iter == 0;
            String machine = prefix + "_fluid_" + (input ? "input" : "output") + "_hatch";
            String englishName = englishPrefix + " Fluid" + (input ? " Input" : " Output") + " Hatch";

            MachineRegistrationHelper.registerMachine(englishName, machine, (bet) -> {
                List<ConfigurableFluidStack> fluidStacks = Collections.singletonList(input ? ConfigurableFluidStack.standardInputSlot((long) bucketCapacity * 81000L) : ConfigurableFluidStack.standardOutputSlot((long) bucketCapacity * 81000L));
                MIInventory inventory = new MIInventory(Collections.emptyList(), fluidStacks, SlotPositions.empty(), (new SlotPositions.Builder()).addSlot(80, 40).build());
                return new FluidHatch(bet, (new MachineGuiParameters.Builder(machine, true)).build(), input, true, inventory);
            }, (bet) -> MachineBlockEntity.registerFluidApi(bet));

            MachineRegistrationHelper.addMachineModel(machine, "hatch_fluid", casing, true, false, true, false);
        }
    }

    private static void registerCustomEnergyHatch(CableTier tier) {
        for (int iter = 0; iter < 2; ++iter) {
            boolean input = iter == 0;
            String machine = tier.name + "_energy_" + (input ? "input" : "output") + "_hatch";
            String englishName = tier.shortEnglishName + " Energy" + (input ? " Input" : " Output") + " Hatch";

            MachineRegistrationHelper.registerMachine(englishName, machine, (bet) -> {
                return new EnergyHatch(bet, machine, input, tier);
            }, (bet) -> EnergyHatch.registerEnergyApi(bet));

            MachineRegistrationHelper.addMachineModel(machine, "hatch_energy", tier.casing, true, false, true, false);
        }
    }

}
