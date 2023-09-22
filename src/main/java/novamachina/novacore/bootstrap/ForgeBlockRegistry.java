package novamachina.novacore.bootstrap;

import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;

public class ForgeBlockRegistry implements IRegistry<BlockDefinition<?>> {
  @Override
  public void register(BlockDefinition<?> entry) {
    ForgeRegistries.BLOCKS.register(entry.getId(), entry.block());
  }
}
