package novamachina.novacore.world.level.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;

public interface IBlockFactory {
  <T extends Block> T block(BlockBehaviour.Properties properties);

  <T extends FallingBlock> T fallingBlock(BlockBehaviour.Properties properties);

  <T extends LiquidBlock> T liquidBlock(BlockBehaviour.Properties properties, FlowingFluid fluid);
}
