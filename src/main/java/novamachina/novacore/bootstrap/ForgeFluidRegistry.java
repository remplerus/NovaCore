package novamachina.novacore.bootstrap;

import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.material.FluidDefinition;

public class ForgeFluidRegistry implements IRegistry<FluidDefinition<?, ?, ?>> {
  @Override
  public void register(FluidDefinition<?, ?, ?> entry) {
    ForgeRegistries.FLUIDS.register(entry.getId(), entry.getStillFluid());
    ForgeRegistries.FLUIDS.register(entry.getFlowingId(), entry.getFlowingFluid());
  }
}
