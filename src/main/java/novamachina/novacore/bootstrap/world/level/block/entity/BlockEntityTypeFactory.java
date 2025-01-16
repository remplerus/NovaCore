package novamachina.novacore.bootstrap.world.level.block.entity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import novamachina.novacore.world.level.block.entity.IBlockEntityTypeFactory;

public class BlockEntityTypeFactory implements IBlockEntityTypeFactory {
  @Override
  public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(
      BlockEntityType.BlockEntitySupplier<T> supplier, Block[] blocks) {
    return new BlockEntityType<T>(supplier, blocks);
  }
}
