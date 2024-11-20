package novamachina.novacore.core.registries;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.novacore.core.IServiceProvider;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.block.entity.BlockEntityTypeDefinition;
import novamachina.novacore.world.level.block.entity.IBlockEntityTypeFactory;

public class BlockEntityTypeRegistry
    extends AbstractRegistry<BlockEntityTypeDefinition<? extends BlockEntity>, BlockEntityType<?>> {
  private final IBlockEntityTypeFactory blockEntityTypeFactory;

  public BlockEntityTypeRegistry(String modId, IServiceProvider serviceProvider) {
    super(modId, Registries.BLOCK_ENTITY_TYPE);
    this.blockEntityTypeFactory = serviceProvider.blockEntityTypeFactory();
  }

  @SafeVarargs
  public final <T extends BlockEntity> BlockEntityTypeDefinition<T> create(
      String shortId,
      BlockEntityFactory<T> factory,
      BlockDefinition<? extends Block>... blockDefinitions) {
    Preconditions.checkArgument(blockDefinitions.length > 0);

    ResourceLocation id = id(shortId);

    var blocks = Arrays.stream(blockDefinitions).map(BlockDefinition::block).toArray(Block[]::new);
    AtomicReference<BlockEntityType<T>> typeHolder = new AtomicReference<>();
    BlockEntityType.BlockEntitySupplier<T> supplier =
        (blockPos, blockState) -> factory.create(typeHolder.get(), blockPos, blockState);
    var type = blockEntityTypeFactory.createBlockEntityType(supplier, blocks);
    typeHolder.set(type);

    BlockEntityTypeDefinition<T> definition = new BlockEntityTypeDefinition<>(id, type);

    this.register(definition);

    return definition;
  }

  @FunctionalInterface
  public interface BlockEntityFactory<T extends BlockEntity> {
    T create(BlockEntityType<T> type, BlockPos pos, BlockState state);
  }
}
