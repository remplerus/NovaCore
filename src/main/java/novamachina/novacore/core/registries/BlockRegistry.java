package novamachina.novacore.core.registries;

import java.util.function.Function;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import novamachina.novacore.core.IServiceProvider;
import novamachina.novacore.world.item.IBlockItemFactory;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.block.IBlockFactory;

public class BlockRegistry extends AbstractRegistry<BlockDefinition<? extends Block>, Block> {

  private final IBlockItemFactory blockItemFactory;
  private final IBlockFactory blockFactory;

  public BlockRegistry(String modId, IServiceProvider serviceProvider) {
    super(modId, Registries.BLOCK);
    this.blockItemFactory = serviceProvider.blockItemFactory();
    this.blockFactory = serviceProvider.blockFactory();
  }

  public <T extends Block> BlockDefinition<T> block(
      String englishName,
      String shortId,
      BlockBehaviour.Properties properties,
      Function<BlockBehaviour.Properties, T> blockSupplier) {
    properties = properties.setId(key(shortId));
    T block = blockSupplier.apply(properties);
    return blockInternal(englishName, shortId, block);
  }

  public <T extends Block> BlockDefinition<T> block(
      String englishName,
      String shortId,
      BlockBehaviour.Properties properties,
      Function<BlockBehaviour.Properties, T> blockSupplier,
      ItemDefinition.ItemType type) {
    properties = properties.setId(key(shortId));
    T block = blockSupplier.apply(properties);
    return blockInternal(englishName, shortId, block, type);
  }

  public <T extends Block> BlockDefinition<T> block(
      String englishName, String shortId, BlockBehaviour.Properties properties) {
    T block = blockFactory.block(properties.setId(key(shortId)));
    return blockInternal(englishName, shortId, block);
  }

  public <T extends Block> BlockDefinition<T> block(
      String englishName,
      String shortId,
      BlockBehaviour.Properties properties,
      ItemDefinition.ItemType type) {
    T block = blockFactory.block(properties.setId(key(shortId)));
    return blockInternal(englishName, shortId, block, type);
  }

  public <T extends FallingBlock> BlockDefinition<T> fallingBlock(
      String englishName, String shortId, BlockBehaviour.Properties properties) {
    T block = blockFactory.fallingBlock(properties.setId(key(shortId)));
    return blockInternal(englishName, shortId, block);
  }

  public <T extends LiquidBlock> BlockDefinition<LiquidBlock> liquidBlock(
      String englishName,
      String shortId,
      BlockBehaviour.Properties properties,
      FlowingFluid fluid,
      ItemDefinition.ItemType type) {
    T block = blockFactory.liquidBlock(properties.setId(key(shortId)), fluid);
    return blockInternal(englishName, shortId, block, type);
  }

  private <T extends Block> BlockDefinition<T> blockInternal(
      String englishName, String shortId, T block) {
    BlockItem item =
        blockItemFactory.blockItem(block, new Item.Properties().setId(itemKey(shortId)));
    return blockDefinition(englishName, id(shortId), block, item);
  }

  private <T extends Block> BlockDefinition<T> blockInternal(
      String englishName, String shortId, T block, ItemDefinition.ItemType type) {
    BlockItem item =
        blockItemFactory.blockItem(block, new Item.Properties().setId(itemKey(shortId)));
    return blockDefinition(englishName, id(shortId), block, item, type);
  }

  public <T extends Block> BlockDefinition<T> burnableBlock(
      String englishName,
      String shortId,
      BlockBehaviour.Properties properties,
      Function<BlockBehaviour.Properties, T> blockSupplier) {
    properties = properties.setId(key(shortId));
    T block = blockSupplier.apply(properties);
    BlockItem item =
        blockItemFactory.blockItem(block, new Item.Properties().setId(itemKey(shortId)));
    return blockDefinition(englishName, id(shortId), block, item);
  }

  private <T extends Block> BlockDefinition<T> blockDefinition(
      String englishName, ResourceLocation id, T block, BlockItem item) {
    BlockDefinition<T> definition = new BlockDefinition<>(englishName, id, block, item);
    this.register(definition);
    return definition;
  }

  private <T extends Block> BlockDefinition<T> blockDefinition(
      String englishName,
      ResourceLocation id,
      T block,
      BlockItem item,
      ItemDefinition.ItemType type) {
    BlockDefinition<T> definition = new BlockDefinition<>(englishName, id, block, item, type);
    this.register(definition);
    return definition;
  }

  protected ResourceKey<Item> itemKey(String name) {
    return ResourceKey.create(Registries.ITEM, id(name));
  }
}
