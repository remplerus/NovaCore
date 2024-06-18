package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.material.FluidDefinition;

public class NeoforgeFluidTypeRegistry implements IRegistry<FluidDefinition<?, ?, ?>> {
  @Override
  public void register(FluidDefinition<?, ?, ?> entry) {
    Registry.register(NeoForgeRegistries.FLUID_TYPES, entry.getId(), entry.getFluidType());
  }
}
