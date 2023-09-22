package novamachina.novacore.world.level.block;

import com.google.common.base.Preconditions;
import java.util.Objects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import novamachina.novacore.world.item.ItemDefinition;

public class BlockDefinition<T extends Block> extends ItemDefinition<BlockItem> {
  private final T block;

  public BlockDefinition(String englishName, ResourceLocation id, T block, BlockItem item) {
    super(englishName, id, item, ItemType.OTHER);
    this.block = Objects.requireNonNull(block, "block");
  }

  @Override
  public ItemStack itemStack(int stackSize) {
    Preconditions.checkArgument(stackSize > 0);
    return new ItemStack(block, stackSize);
  }

  public T block() {
    return block;
  }
}
