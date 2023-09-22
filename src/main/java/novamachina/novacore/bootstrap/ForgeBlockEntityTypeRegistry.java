package novamachina.novacore.bootstrap;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

public class ForgeBlockEntityTypeRegistry
    implements IRegistry<BlockEntityTypeDefinition<? extends BlockEntity>> {
  @Override
  public void register(BlockEntityTypeDefinition<? extends BlockEntity> entry) {
    ForgeRegistries.BLOCK_ENTITY_TYPES.register(entry.getId(), entry.getType());
  }
}
