package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;

public class NeoforgeBlockRegistry implements IRegistry<BlockDefinition<?>> {

  public void register(BlockDefinition<?> entry) {
    Registry.register(BuiltInRegistries.BLOCK, entry.getId(), entry.block());
  }
}
