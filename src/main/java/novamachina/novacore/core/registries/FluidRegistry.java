package novamachina.novacore.core.registries;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.material.FluidDefinition;

public class FluidRegistry extends AbstractRegistry<FluidDefinition<?, ?, ?>, Fluid> {
  public FluidRegistry(String modId) {
    super(modId, Registries.FLUID);
  }

  public <
          F extends FlowingFluid,
          B extends BlockDefinition<? extends LiquidBlock>,
          I extends ItemDefinition<? extends Item>>
      FluidDefinition<F, B, I> fluid(
          String englishName,
          String shortId,
          Supplier<F> stillSupplier,
          Supplier<F> flowSupplier,
          Supplier<B> blockSupplier,
          Supplier<I> bucketSupplier) {
    FluidDefinition<F, B, I> definition =
        new FluidDefinition<>(
            englishName, id(shortId), stillSupplier, flowSupplier, blockSupplier, bucketSupplier);
    this.register(definition);
    return definition;
  }
}
