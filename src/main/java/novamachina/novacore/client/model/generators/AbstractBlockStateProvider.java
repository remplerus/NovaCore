package novamachina.novacore.client.model.generators;

import java.util.function.BiConsumer;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class AbstractBlockStateProvider extends BlockStateProvider {

  protected static final String PARTICLE_TAG = "particle";

  protected AbstractBlockStateProvider(
      PackOutput output, String modId, ExistingFileHelper exFileHelper) {
    super(output, modId, exFileHelper);
  }

  protected void basicBlock(Block block) {
    simpleItemBlock(block, cubeAll(block));
  }

  protected void registerFluid(Fluid fluid, ResourceLocation stillTexture) {
    ResourceLocation resourceLocation = BuiltInRegistries.FLUID.getKey(fluid);
    ModelFile model =
        models()
            .getBuilder("block/" + resourceLocation.getPath())
            .texture(PARTICLE_TAG, stillTexture);
    getVariantBuilder(fluid.defaultFluidState().createLegacyBlock().getBlock())
        .partialState()
        .setModels(new ConfiguredModel(model));
  }

  protected void simpleItemBlock(Block block, ModelFile modelFile) {
    simpleBlock(block, modelFile);
    simpleBlockItem(block, modelFile);
  }

  protected VariantBlockStateBuilder horrizontalOrrientable(
      Block block, BiConsumer<BlockState, ConfiguredModel.Builder<?>> model) {
    VariantBlockStateBuilder builder = getVariantBuilder(block);
    builder.forAllStates(
        state -> {
          ConfiguredModel.Builder<?> bld = ConfiguredModel.builder();
          model.accept(state, bld);
          applyRotationBld(bld, state.getValue(BlockStateProperties.HORIZONTAL_FACING));
          return bld.build();
        });

    simpleBlockItem(
        block,
        new ConfiguredModel(
                models().withExistingParent(getRegistryName(block), mcLoc("block/cube")))
            .model);

    return builder;
  }

  protected VariantBlockStateBuilder orrientable(
      Block block, BiConsumer<BlockState, ConfiguredModel.Builder<?>> model) {
    VariantBlockStateBuilder builder = getVariantBuilder(block);
    builder.forAllStates(
        state -> {
          ConfiguredModel.Builder<?> bld = ConfiguredModel.builder();
          model.accept(state, bld);
          applyRotationBld(bld, state.getValue(BlockStateProperties.FACING));
          return bld.build();
        });

    simpleBlockItem(
        block,
        new ConfiguredModel(
                models().withExistingParent(getRegistryName(block), mcLoc("block/cube")))
            .model);

    return builder;
  }

  private void applyRotationBld(ConfiguredModel.Builder<?> builder, Direction direction) {
    switch (direction) {
      case DOWN -> builder.rotationX(90);
      case UP -> builder.rotationX(-90);
      case NORTH -> {}
      case SOUTH -> builder.rotationY(180);
      case WEST -> builder.rotationY(270);
      case EAST -> builder.rotationY(90);
    }
  }

  protected String getRegistryName(Block b) {
    return BuiltInRegistries.BLOCK.getKey(b).toString();
  }
}
