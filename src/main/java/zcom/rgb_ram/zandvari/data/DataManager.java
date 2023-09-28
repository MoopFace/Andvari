//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zcom.rgb_ram.zandvari.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.math.Quaternion;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.mickelus.mutil.data.DataDistributor;
import se.mickelus.mutil.data.DataStore;
import se.mickelus.mutil.data.deserializer.BlockDeserializer;
import se.mickelus.mutil.data.deserializer.BlockPosDeserializer;
import se.mickelus.mutil.data.deserializer.ItemDeserializer;
import se.mickelus.mutil.data.deserializer.ResourceLocationDeserializer;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.aspect.ItemAspect;
import se.mickelus.tetra.blocks.PropertyMatcher;
import se.mickelus.tetra.blocks.workbench.action.ConfigActionImpl;
import se.mickelus.tetra.craftingeffect.CraftingEffect;
import se.mickelus.tetra.craftingeffect.condition.CraftingEffectCondition;
import se.mickelus.tetra.craftingeffect.outcome.CraftingEffectOutcome;
import se.mickelus.tetra.data.*;
import se.mickelus.tetra.data.deserializer.AttributesDeserializer;
import se.mickelus.tetra.data.deserializer.EnchantmentDeserializer;
import se.mickelus.tetra.data.deserializer.GlyphDeserializer;
import se.mickelus.tetra.data.deserializer.ItemPredicateDeserializer;
import se.mickelus.tetra.data.deserializer.ModuleModelDeserializer;
import se.mickelus.tetra.data.deserializer.PropertyMatcherDeserializer;
import se.mickelus.tetra.data.deserializer.QuaternionDeserializer;
import se.mickelus.tetra.data.deserializer.ReplacementDeserializer;
import se.mickelus.tetra.data.deserializer.TransformationDeserializer;
import se.mickelus.tetra.data.deserializer.VectorDeserializer;
import se.mickelus.tetra.module.Priority;
import se.mickelus.tetra.module.ReplacementDefinition;
import se.mickelus.tetra.module.data.AspectData;
import se.mickelus.tetra.module.data.EffectData;
import se.mickelus.tetra.module.data.EnchantmentMapping;
import se.mickelus.tetra.module.data.GlyphData;
import se.mickelus.tetra.module.data.ImprovementData;
import se.mickelus.tetra.module.data.MaterialColors;
import se.mickelus.tetra.module.data.MaterialData;
import se.mickelus.tetra.module.data.ModuleData;
import se.mickelus.tetra.module.data.ModuleModel;
import se.mickelus.tetra.module.data.SynergyData;
import se.mickelus.tetra.module.data.ToolData;
import se.mickelus.tetra.module.data.TweakData;
import se.mickelus.tetra.module.data.VariantData;
import se.mickelus.tetra.module.improvement.DestabilizationEffect;
import se.mickelus.tetra.module.schematic.OutcomeDefinition;
import se.mickelus.tetra.module.schematic.OutcomeMaterial;
import se.mickelus.tetra.module.schematic.RepairDefinition;
import se.mickelus.tetra.module.schematic.requirement.CraftingRequirement;
import se.mickelus.tetra.module.schematic.requirement.CraftingRequirementDeserializer;
import se.mickelus.tetra.module.schematic.requirement.IntegerPredicate;
import se.mickelus.tetra.module.schematic.requirement.ModuleRequirement;

@ParametersAreNonnullByDefault
public class DataManager implements DataDistributor {
    public static final Gson gson;
    public static DataManager instance;
    public final DataStore<ResourceLocation[]> tierData;
    public final DataStore<TweakData[]> tweakData;
    public final MaterialStore materialData;
    public final DataStore<ImprovementData[]> improvementData;
    public final DataStore<ModuleData> moduleData;
    public final DataStore<RepairDefinition> repairData;
    public final DataStore<EnchantmentMapping[]> enchantmentData;
    public final DataStore<SynergyData[]> synergyData;
    public final DataStore<ReplacementDefinition[]> replacementData;
    public final SchematicStore schematicData;
    public final DataStore<CraftingEffect> craftingEffectData;
    public final DataStore<ConfigActionImpl[]> actionData;
    public final DataStore<DestabilizationEffect[]> destabilizationData;
    private final Logger logger = LogManager.getLogger();
    private final DataStore[] dataStores;

    public DataManager() {
        instance = this;
        this.tierData = new DataStore(gson, "andvari", "tiers", ResourceLocation[].class, this);
        this.tweakData = new DataStore(gson, "andvari", "tweaks", TweakData[].class, this);
        this.materialData = new MaterialStore(gson, "andvari", "materials", this);
        this.improvementData = new ImprovementStore(gson, "andvari", "improvements", this.materialData, this);
        this.moduleData = new ModuleStore(gson, "andvari", "modules", this);
        this.repairData = new DataStore(gson, "andvari", "repairs", RepairDefinition.class, this);
        this.enchantmentData = new DataStore(gson, "andvari", "enchantments", EnchantmentMapping[].class, this);
        this.synergyData = new DataStore(gson, "andvari", "synergies", SynergyData[].class, this);
        this.replacementData = new DataStore(gson, "andvari", "replacements", ReplacementDefinition[].class, this);
        this.schematicData = new SchematicStore(gson, "andvari", "schematics", this);
        this.craftingEffectData = new CraftingEffectStore(gson, "andvari", "crafting_effects", this);
        this.actionData = new DataStore(gson, "andvari", "actions", ConfigActionImpl[].class, this);
        this.destabilizationData = new DataStore(gson, "andvari", "destabilization", DestabilizationEffect[].class, this);
        this.dataStores = new DataStore[]{this.tierData, this.tweakData, this.materialData, this.improvementData, this.moduleData, this.enchantmentData, this.synergyData, this.replacementData, this.schematicData, this.craftingEffectData, this.repairData, this.actionData, this.destabilizationData};
    }

//    @SubscribeEvent(
//        priority = EventPriority.LOWEST
//    )
//    public void addReloadListener(AddReloadListenerEvent event) {
//        this.logger.debug("Setting up datastore reload listeners");
//        Stream var10000 = Arrays.stream(this.dataStores);
//        Objects.requireNonNull(event);
//        var10000.forEach(event::addListener);
//    }

    @SubscribeEvent
    public void tagsUpdated(TagsUpdatedEvent event) {
        this.logger.debug("Reloaded tags");
    }

    @SubscribeEvent
    public void playerConnected(PlayerEvent.PlayerLoggedInEvent event) {
        this.logger.info("Sending data to client: {}", event.getEntity().getName().getString());
        DataStore[] var2 = this.dataStores;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            DataStore dataStore = var2[var4];
            dataStore.sendToPlayer((ServerPlayer)event.getEntity());
        }

    }

    public void onDataRecieved(String directory, Map<ResourceLocation, String> data) {
        Arrays.stream(this.dataStores).filter((dataStore) -> {
            return dataStore.getDirectory().equals(directory);
        }).forEach((dataStore) -> {
            dataStore.loadFromPacket(data);
        });
    }

    public SynergyData[] getSynergyData(String path) {
        SynergyData[] data = (SynergyData[])this.synergyData.getDataIn(new ResourceLocation("tetra", path)).stream().flatMap(Arrays::stream).toArray((x$0) -> {
            return new SynergyData[x$0];
        });
        SynergyData[] var3 = data;
        int var4 = data.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            SynergyData entry = var3[var5];
            Arrays.sort(entry.moduleVariants);
            Arrays.sort(entry.modules);
        }

        return data;
    }

    public void sendToAll(String directory, Map<ResourceLocation, JsonElement> data) {
        TetraMod.packetHandler.sendToAllPlayers(new UpdateDataPacket(directory, data));
    }

    public void sendToPlayer(ServerPlayer player, String directory, Map<ResourceLocation, JsonElement> data) {
        TetraMod.packetHandler.sendTo(new UpdateDataPacket(directory, data), player);
    }

    static {
        gson = (new GsonBuilder()).registerTypeAdapter(ToolData.class, new ToolData.Deserializer()).registerTypeAdapter(AspectData.class, new AspectData.Deserializer()).registerTypeAdapter(ItemAspect.class, new ItemAspect.Deserializer()).registerTypeAdapter(EffectData.class, new EffectData.Deserializer()).registerTypeAdapter(GlyphData.class, new GlyphDeserializer()).registerTypeAdapter(ModuleModel.class, new ModuleModelDeserializer()).registerTypeAdapter(Priority.class, new Priority.Deserializer()).registerTypeAdapter(ItemPredicate.class, new ItemPredicateDeserializer()).registerTypeAdapter(PropertyMatcher.class, new PropertyMatcherDeserializer()).registerTypeAdapter(MaterialData.class, new MaterialData.Deserializer()).registerTypeAdapter(OutcomeMaterial.class, new OutcomeMaterial.Deserializer()).registerTypeAdapter(ReplacementDefinition.class, new ReplacementDeserializer()).registerTypeAdapter(BlockPos.class, new BlockPosDeserializer()).registerTypeAdapter(Block.class, new BlockDeserializer()).registerTypeAdapter(AttributesDeserializer.typeToken.getRawType(), new AttributesDeserializer()).registerTypeAdapter(VariantData.class, new VariantData.Deserializer()).registerTypeAdapter(ImprovementData.class, new ImprovementData.Deserializer()).registerTypeAdapter(OutcomeDefinition.class, new OutcomeDefinition.Deserializer()).registerTypeAdapter(MaterialColors.class, new MaterialColors.Deserializer()).registerTypeAdapter(CraftingEffectCondition.class, new CraftingEffectCondition.Deserializer()).registerTypeAdapter(CraftingEffectOutcome.class, new CraftingEffectOutcome.Deserializer()).registerTypeAdapter(CraftingRequirement.class, new CraftingRequirementDeserializer()).registerTypeAdapter(ModuleRequirement.class, new ModuleRequirement.Deserializer()).registerTypeAdapter(IntegerPredicate.class, new IntegerPredicate.Deserializer()).registerTypeAdapter(Item.class, new ItemDeserializer()).registerTypeAdapter(Enchantment.class, new EnchantmentDeserializer()).registerTypeAdapter(ResourceLocation.class, new ResourceLocationDeserializer()).registerTypeAdapter(Vector3f.class, new VectorDeserializer()).registerTypeAdapter(Quaternion.class, new QuaternionDeserializer()).registerTypeAdapter(Transformation.class, new TransformationDeserializer()).create();
    }
}
