package com.rgb_ram.andvari;

import com.mojang.logging.LogUtils;
import com.rgb_ram.andvari.items.AndvariRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
//import com.rgb_ram.andvari.data.DataManager;
import se.mickelus.tetra.TetraRegistries;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.module.ModuleRegistry;
import net.minecraft.resources.ResourceLocation;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AndvariMod.MODID)
public class AndvariMod {
    public static final String MODID = "andvari";
    private static final Logger LOGGER = LogUtils.getLogger();
    private final IEventBus modEventBus;

    public AndvariMod() {
        this.modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        //MinecraftForge.EVENT_BUS.register(new DataManager());

        modEventBus.addListener(this::commonSetup);

       // moduleRegistry.registerModuleType(new ResourceLocation(AndvariMod.MODID, "fakebelt_module"), FakebeltModule::new);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }



    public void register(RegisterEvent event) {
        ModuleRegistry moduleRegistry = new ModuleRegistry();

        event.register(ForgeRegistries.Keys.BLOCKS,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "example_block_1"), new Block(...));
                    helper.register(new ResourceLocation(MODID, "example_block_2"), new Block(...));
                    helper.register(new ResourceLocation(MODID, "example_block_3"), new Block(...));
                    // ...
                }
        );
    }
//    @SubscribeEvent
//    public void registerModules(RegisterEvent event) {
//        LOGGER.info("DataManager instance: " + DataManager.instance);
//        ModuleRegistry moduleRegistry = new ModuleRegistry();
//        AndvariRegistries.init(event);
//        AndvariRegistries.registerItems(modEventBus);
//    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
