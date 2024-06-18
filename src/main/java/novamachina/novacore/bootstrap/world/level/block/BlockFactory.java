package novamachina.novacore.bootstrap.world.level.block;

import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import novamachina.novacore.world.level.block.IBlockFactory;

public class BlockFactory implements IBlockFactory {
  @Override
  public <T extends Block> T block(BlockBehaviour.Properties properties) {
    return (T) new Block(properties);
  }

  @Override
  public <T extends FallingBlock> T fallingBlock(BlockBehaviour.Properties properties) {
    return (T) new ColoredFallingBlock(new ColorRGBA(-8356741), properties);
  }

  @Override
  public <T extends LiquidBlock> T liquidBlock(
      BlockBehaviour.Properties properties, FlowingFluid fluid) {
    return (T) new LiquidBlock(fluid, properties.liquid().replaceable());
  }
}
