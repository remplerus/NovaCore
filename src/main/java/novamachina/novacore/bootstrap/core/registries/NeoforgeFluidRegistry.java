package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.material.FluidDefinition;

public class NeoforgeFluidRegistry implements IRegistry<FluidDefinition<?, ?, ?>> {

  public void register(FluidDefinition<?, ?, ?> entry) {
    Registry.register(BuiltInRegistries.FLUID, entry.getId(), entry.getStillFluid());
    Registry.register(BuiltInRegistries.FLUID, entry.getFlowingId(), entry.getFlowingFluid());
  }
}
