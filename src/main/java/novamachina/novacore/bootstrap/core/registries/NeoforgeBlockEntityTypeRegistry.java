package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.block.entity.BlockEntityTypeDefinition;

public class NeoforgeBlockEntityTypeRegistry
    implements IRegistry<BlockEntityTypeDefinition<? extends BlockEntity>> {
  @Override
  public void register(BlockEntityTypeDefinition<? extends BlockEntity> entry) {
    Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, entry.getId(), entry.getType());
  }
}
