package novamachina.novacore.data.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.material.FluidDefinition;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class TagProvider implements DataProvider {

  private final PackOutput output;
  private final String modId;
  private final CompletableFuture<HolderLookup.Provider> lookupProvider;
  private final ExistingFileHelper existingFileHelper;
  private final Map<
          ResourceKey<? extends Registry<?>>, Map<TagKey<?>, net.minecraft.tags.TagBuilder>>
      supportedTagTypes = new HashMap<>();
  private Set<Block> knownHarvestRequirements = new HashSet<>();

  protected TagProvider(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      String modId,
      @Nullable ExistingFileHelper existingFileHelper) {
    this.output = output;
    this.modId = modId;
    this.lookupProvider = lookupProvider;
    this.existingFileHelper = existingFileHelper;
  }

  @Override
  public @NonNull CompletableFuture<?> run(@NonNull CachedOutput cache) {
    return this.lookupProvider
        .thenApply(
            registries -> {
              supportedTagTypes.values().forEach(Map::clear);
              registerTags();
              return registries;
            })
        .thenCompose(
            registries -> {
              for (BlockDefinition<?> blockDefinition : getAllBlocks()) {
                Block block = blockDefinition.block();
                if (block.defaultBlockState().requiresCorrectToolForDrops()
                    && !knownHarvestRequirements.contains(block)) {
                  throw new IllegalStateException(
                      String.format(
                          "Missing harvest tool type for block %s that requires the correct tool for drops.",
                          blockDefinition.getId()));
                }
              }
              List<CompletableFuture<?>> futures = new ArrayList<>();
              supportedTagTypes.forEach(
                  (registry, tagTypeMap) -> {
                    if (!tagTypeMap.isEmpty()) {
                      futures.add(
                          new TagsProvider(
                              output, registry, lookupProvider, modId, existingFileHelper) {
                            @Override
                            protected void addTags(HolderLookup.@NonNull Provider lookupProvider) {
                              tagTypeMap.forEach(
                                  (tag, tagBuilder) -> builders.put(tag.location(), tagBuilder));
                            }
                          }.run(cache));
                    }
                  });
              return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
            });
  }

  private List<BlockDefinition<?>> getAllBlocks() {
    return Collections.emptyList();
  }

  protected abstract void registerTags();

  @Override
  public @NonNull String getName() {
    return String.format("Tags: %s", modId);
  }

  private <TYPE> Map<TagKey<?>, net.minecraft.tags.TagBuilder> getTagTypeMap(
      ResourceKey<? extends Registry<TYPE>> registry) {
    return supportedTagTypes.computeIfAbsent(registry, type -> new HashMap<>());
  }

  private <TYPE> net.minecraft.tags.TagBuilder getTagBuilder(
      ResourceKey<? extends Registry<TYPE>> registry, TagKey<TYPE> tag) {
    return getTagTypeMap(registry)
        .computeIfAbsent(tag, ignored -> net.minecraft.tags.TagBuilder.create());
  }

  protected <TYPE> TagBuilder<TYPE, ?> getBuilder(
      ResourceKey<? extends Registry<TYPE>> registry, TagKey<TYPE> tag) {
    return new TagBuilder<>(getTagBuilder(registry, tag), modId);
  }

  protected <TYPE> IntrinsicTagBuilder<TYPE> getBuilder(
      IForgeRegistry<TYPE> registry, TagKey<TYPE> tag) {
    return new IntrinsicTagBuilder<>(
        element -> registry.getResourceKey(element).orElseThrow(),
        getTagBuilder(registry.getRegistryKey(), tag),
        modId);
  }

  protected IntrinsicTagBuilder<Block> getBlockBuilder(TagKey<Block> tag) {
    return getBuilder(ForgeRegistries.BLOCKS, tag);
  }

  protected IntrinsicTagBuilder<Item> getItemBuilder(TagKey<Item> tag) {
    return getBuilder(ForgeRegistries.ITEMS, tag);
  }

  protected IntrinsicTagBuilder<Fluid> getFluidBuilder(TagKey<Fluid> tag) {
    return getBuilder(ForgeRegistries.FLUIDS, tag);
  }

  protected void addToTag(TagKey<Block> tag, Block... blocks) {
    getBlockBuilder(tag).add(blocks);
  }

  protected void addToTag(TagKey<Item> tag, ItemLike... items) {
    getItemBuilder(tag).addTyped(ItemLike::asItem, items);
  }

  protected void addToTag(TagKey<Fluid> tag, FluidDefinition<?, ?, ?>... fluids) {
    IntrinsicTagBuilder<Fluid> tagBuilder = getFluidBuilder(tag);
    for (FluidDefinition<?, ?, ?> fluid : fluids) {
      tagBuilder.add(fluid.getStillFluid(), fluid.getFlowingFluid());
    }
  }
}
