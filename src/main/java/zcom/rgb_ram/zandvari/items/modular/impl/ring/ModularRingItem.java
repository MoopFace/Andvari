
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zcom.rgb_ram.zandvari.items.modular.impl.ring;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ModularItem;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ModularRingItem extends ModularItem implements ICurioItem {
    public static final String identifier = "modular_ring";
    public static final String slot1Key = "ring/slot1";
    public static final String slot2Key = "ring/slot2";
    public static final String slot3Key = "ring/slot3";
    public static final String ringKey = "ring/band";
    public static final String slot1Suffix = "_slot1";
    public static final String slot2Suffix = "_slot2";
    public static final String slot3Suffix = "_slot3";
    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(new int[]{-14, 18, 4, 0, 4, 18});
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(new int[]{-13, 0});
    public static RegistryObject<ModularRingItem> instance;

    public ModularRingItem() {
        super((new Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC).fireResistant());
       // this.canHone = true;
        this.majorModuleKeys = new String[]{"ring/slot1", "ring/slot2", "ring/slot3"};
        this.minorModuleKeys = new String[]{"ring/band"};
        this.requiredModules = new String[]{"ring/band"};
    }

//    public void commonInit(PacketHandler packetHandler) {
//        DataManager.instance.synergyData.onReload(() -> {
//            this.synergies = DataManager.instance.getSynergyData("ring/");
//        });
//    }

    public void clientInit() {
        super.clientInit();
    }

    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowedIn(group)) {
            items.add(this.createStack("ring/rope"));
            items.add(this.createStack("ring/inlaid"));
        }

    }

    private ItemStack createStack(String beltMaterial) {
        ItemStack itemStack = new ItemStack(this);
        IModularItem.putModuleInSlot(itemStack, "ring/belt", "ring/belt", "ring/belt_material", beltMaterial);
        IModularItem.putModuleInSlot(itemStack, "ring/slot1", "ring/upgrade_slot1", "ring/upgrade_slot1_material", "upgrade1/leather");
        IModularItem.updateIdentifier(itemStack);
        return itemStack;
    }

    public Component getDisplayName() {
        return Component.literal(this.toString());
    }

    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMajorGuiOffsets() {
        return majorOffsets;
    }

    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMinorGuiOffsets() {
        return minorOffsets;
    }
}
