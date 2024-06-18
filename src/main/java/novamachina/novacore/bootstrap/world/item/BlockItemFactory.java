package novamachina.novacore.bootstrap.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import novamachina.novacore.world.item.IBlockItemFactory;

public class BlockItemFactory implements IBlockItemFactory {
  @Override
  public BlockItem blockItem(Block block, Item.Properties properties) {
    return new BlockItem(block, properties);
  }

  @Override
  public BlockItem burnableBlockItem(Block block, Item.Properties properties, int burnTime) {
    return new BlockItem(block, properties) {
      @Override
      public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return burnTime;
      }
    };
  }
}
