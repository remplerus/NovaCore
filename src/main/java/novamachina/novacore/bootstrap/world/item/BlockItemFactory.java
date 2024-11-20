package novamachina.novacore.bootstrap.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import novamachina.novacore.world.item.IBlockItemFactory;

public class BlockItemFactory implements IBlockItemFactory {
  @Override
  public BlockItem blockItem(Block block, Item.Properties properties) {
    return new BlockItem(block, properties);
  }
}
