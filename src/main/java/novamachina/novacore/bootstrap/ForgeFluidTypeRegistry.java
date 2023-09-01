package novamachina.novacore.bootstrap;

import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.material.FluidDefinition;

public class ForgeFluidTypeRegistry implements IRegistry<FluidDefinition<?, ?, ?>> {
  @Override
  public void register(FluidDefinition<?, ?, ?> entry) {
    ForgeRegistries.FLUID_TYPES.get().register(entry.getId(), entry.getFluidType());
  }
}
