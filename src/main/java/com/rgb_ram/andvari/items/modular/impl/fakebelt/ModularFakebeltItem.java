//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.rgb_ram.andvari.items.modular.impl.fakebelt;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.TetraItemGroup;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ModularItem;

@ParametersAreNonnullByDefault
public class ModularFakebeltItem extends ModularItem {
    public static final String identifier = "modular_fakebelt";
    public static final String slot1Key = "fakebelt/slot1";
    public static final String slot2Key = "fakebelt/slot2";
    public static final String slot3Key = "fakebelt/slot3";
    public static final String beltKey = "fakebelt/belt";
    public static final String slot1Suffix = "_slot1";
    public static final String slot2Suffix = "_slot2";
    public static final String slot3Suffix = "_slot3";
    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(new int[]{-14, 18, 4, 0, 4, 18});
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(new int[]{-13, 0});
    public static RegistryObject<ModularFakebeltItem> instance;

    public ModularFakebeltItem() {
        super((new Item.Properties()).stacksTo(1).tab(TetraItemGroup.instance).fireResistant());
        this.canHone = false;
        this.majorModuleKeys = new String[]{"fakebelt/slot1", "fakebelt/slot2", "fakebelt/slot3"};
        this.minorModuleKeys = new String[]{"fakebelt/belt"};
        this.requiredModules = new String[]{"fakebelt/belt"};
    }

//    public void commonInit(PacketHandler packetHandler) {
//        MinecraftForge.EVENT_BUS.register(new TickHandlerBooster());
//        RemoveSchematic.registerRemoveSchematics(this, "id");
//        DataManager.instance.synergyData.onReload(() -> {
//            this.synergies = DataManager.instance.getSynergyData("fakebelt/");
//        });
//    }

    public void clientInit() {
        super.clientInit();
    }

    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowedIn(group)) {
            items.add(this.createStack("belt/rope"));
            items.add(this.createStack("belt/inlaid"));
        }

    }

    private ItemStack createStack(String beltMaterial) {
        ItemStack itemStack = new ItemStack(this);
        IModularItem.putModuleInSlot(itemStack, "fakebelt/belt", "fakebelt/belt", "fakebelt/belt_material", beltMaterial);
        IModularItem.putModuleInSlot(itemStack, "fakebelt/slot1", "fakebelt/strap_slot1", "fakebelt/strap_slot1_material", "strap1/leather");
        IModularItem.updateIdentifier(itemStack);
        return itemStack;
    }

    public Component getDisplayName() {
        return Component.literal(this.toString());
    }

    @Nullable


    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMajorGuiOffsets() {
        return majorOffsets;
    }

    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMinorGuiOffsets() {
        return minorOffsets;
    }
}
