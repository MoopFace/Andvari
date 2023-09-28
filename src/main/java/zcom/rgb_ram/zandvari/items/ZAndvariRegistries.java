package zcom.rgb_ram.zandvari.items;

import zcom.rgb_ram.zandvari.ZAndvariMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ZAndvariRegistries {
    public static final DeferredRegister<Item> items;
    public ZAndvariRegistries(){}

    public static void init(IEventBus bus) {
        bus.register(ZAndvariRegistries.class);
        items.register(bus);
   //     ModularFakebeltItem.instance = items.register("modular_fakebelt", ModularFakebeltItem::new);
  //      ModularRingItem.instance = items.register("modular_ring", ModularRingItem::new);
    }
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZAndvariMod.MODID);

    public static final RegistryObject<Item> TESTICLE =
            ITEMS.register("testicle", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).build())));

    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    //public static final RegistryObject<Item> RING = ITEMS.register("modular_ring", ModularRingItem::new);


    static {
        items = DeferredRegister.create(ForgeRegistries.ITEMS, ZAndvariMod.MODID);
    }


}
