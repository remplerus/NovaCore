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
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.material.FluidDefinition;

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
      ExistingFileHelper existingFileHelper) {
    this.output = output;
    this.modId = modId;
    this.lookupProvider = lookupProvider;
    this.existingFileHelper = existingFileHelper;
  }

  @Override
  public CompletableFuture<?> run(CachedOutput cache) {
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
                            protected void addTags(HolderLookup.Provider lookupProvider) {
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
  public String getName() {
    return String.format("Tags: %s", modId);
  }

  private <T> Map<TagKey<?>, net.minecraft.tags.TagBuilder> getTagTypeMap(
      ResourceKey<? extends Registry<T>> registry) {
    return supportedTagTypes.computeIfAbsent(registry, type -> new HashMap<>());
  }

  private <T> net.minecraft.tags.TagBuilder getTagBuilder(
      ResourceKey<? extends Registry<T>> registry, TagKey<T> tag) {
    return getTagTypeMap(registry)
        .computeIfAbsent(tag, ignored -> net.minecraft.tags.TagBuilder.create());
  }

  protected <T> TagBuilder<T, ?> getBuilder(
      ResourceKey<? extends Registry<T>> registry, TagKey<T> tag) {
    return new TagBuilder<>(getTagBuilder(registry, tag), modId);
  }

  protected <T> IntrinsicTagBuilder<T> getBuilder(Registry<T> registry, TagKey<T> tag) {
    return new IntrinsicTagBuilder<>(
        element -> registry.getResourceKey(element).orElseThrow(),
        getTagBuilder(registry.key(), tag),
        modId);
  }

  protected IntrinsicTagBuilder<Block> getBlockBuilder(TagKey<Block> tag) {
    return getBuilder(BuiltInRegistries.BLOCK, tag);
  }

  protected IntrinsicTagBuilder<Item> getItemBuilder(TagKey<Item> tag) {
    return getBuilder(BuiltInRegistries.ITEM, tag);
  }

  protected IntrinsicTagBuilder<Fluid> getFluidBuilder(TagKey<Fluid> tag) {
    return getBuilder(BuiltInRegistries.FLUID, tag);
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

  protected void addToTag(TagKey<Item> parentTag, TagKey<Item>... childTags) {
    getItemBuilder(parentTag).add(childTags);
  }
}
