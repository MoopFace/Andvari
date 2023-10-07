package com.rgb_ram.andvari.items;

import com.rgb_ram.andvari.AndvariMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import se.mickelus.tetra.module.ModuleRegistry;
import com.rgb_ram.andvari.items.modular.impl.fakebelt.ModularFakebeltItem;
import com.rgb_ram.andvari.items.modular.impl.fakebelt.FakebeltModule;


public class AndvariRegistries {
    public static final DeferredRegister<Item> items;

   ;
    public AndvariRegistries(){}

    public static void init(IEventBus bus) {
        bus.register(AndvariRegistries.class);
        items.register(bus);
        ModularFakebeltItem.instance = items.register("modular_fakebelt", ModularFakebeltItem::new);


//        event.register(ForgeRegistries.Keys.ITEMS, RegisterHelper -> {
//            RegisterHelper.register(new ResourceLocation(AndvariMod.MODID, "fakebelt_module"), new ModularFakebeltItem());
//        }
//        );


        //  ModularRingItem.instance = items.register("modular_ring", ModularRingItem::new);

    }
//    public static final DeferredRegister<Item> items =
//            DeferredRegister.create(ForgeRegistries.ITEMS, AndvariMod.MODID);
public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, AndvariMod.MODID);

    public static final RegistryObject<Item> TESTICLE =
            ITEMS.register("testitem", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).build())));

    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
        items.register(eventBus);
    }
    //public static final RegistryObject<Item> RING = ITEMS.register("modular_ring", ModularRingItem::new);
    static {
        items = DeferredRegister.create(ForgeRegistries.ITEMS, AndvariMod.MODID);
    }



}
