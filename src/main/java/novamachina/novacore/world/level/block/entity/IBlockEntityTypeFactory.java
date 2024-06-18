package novamachina.novacore.world.level.block.entity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface IBlockEntityTypeFactory {

  <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(
      BlockEntityType.BlockEntitySupplier<T> supplier, Block[] blocks);
}
