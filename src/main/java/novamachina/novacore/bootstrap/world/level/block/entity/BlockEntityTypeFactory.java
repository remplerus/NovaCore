package novamachina.novacore.bootstrap.world.level.block.entity;

import com.mojang.datafixers.types.Type;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import novamachina.novacore.world.level.block.entity.IBlockEntityTypeFactory;

public class BlockEntityTypeFactory implements IBlockEntityTypeFactory {
  @Override
  public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(
      BlockEntityType.BlockEntitySupplier<T> supplier, Block[] blocks) {
    return BlockEntityType.Builder.of(supplier, blocks).build((Type)null);
  }
}
