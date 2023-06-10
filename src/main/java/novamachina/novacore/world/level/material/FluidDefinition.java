package novamachina.novacore.world.level.material;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.item.ItemDefinition;

public class FluidDefinition<
    F extends FlowingFluid,
    B extends BlockDefinition<? extends LiquidBlock>,
    I extends ItemDefinition<? extends Item>> {
  private final ResourceLocation id;
  private final String englishName;

  private final F stillFluid;
  private final F flowingFluid;

  private final FluidType fluidType;

  private final Supplier<B> block;

  private final Supplier<I> bucket;

  public FluidDefinition(
      String englishName,
      ResourceLocation id,
      Supplier<F> stillFluid,
      Supplier<F> flowingFluid,
      Supplier<B> block,
      Supplier<I> bucket) {
    Objects.requireNonNull(id, "id");

    this.id = id;
    this.englishName = englishName;
    this.stillFluid = stillFluid.get();
    this.flowingFluid = flowingFluid.get();
    this.block = block;
    this.bucket = bucket;
    this.fluidType = createFluidType();
  }

  private FluidType createFluidType() {
    return new FluidType(FluidType.Properties.create()) {
      @Override
      public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(
            new IClientFluidTypeExtensions() {
              @Override
              public ResourceLocation getStillTexture() {
                return getTexture();
              }

              @Override
              public ResourceLocation getFlowingTexture() {
                return getFlowTexture();
              }
            });
      }
    };
  }

  public ResourceLocation getId() {
    return id;
  }

  public ResourceLocation getFlowingId() {
    return new ResourceLocation(
        this.id.getNamespace(), String.format("%s_flow", this.id.getPath()));
  }

  public String getEnglishName() {
    return englishName;
  }

  public ResourceLocation getTexture() {
    return new ResourceLocation(
        this.id.getNamespace(), String.format("block/%s", this.id.getPath()));
  }

  public ResourceLocation getFlowTexture() {
    return new ResourceLocation(
        this.id.getNamespace(), String.format("block/%s_flow", this.id.getPath()));
  }

  public B getBlock() {
    return block.get();
  }

  public I getBucket() {
    return bucket.get();
  }

  public FlowingFluid getFlowingFluid() {
    return flowingFluid;
  }

  public FlowingFluid getStillFluid() {
    return stillFluid;
  }

  public FluidType getFluidType() {
    return fluidType;
  }
}
