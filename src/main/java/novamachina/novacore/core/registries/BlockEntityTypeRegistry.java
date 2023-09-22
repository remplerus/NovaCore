package novamachina.novacore.core.registries;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

public class BlockEntityTypeRegistry
    extends AbstractRegistry<BlockEntityTypeDefinition<? extends BlockEntity>> {
  public BlockEntityTypeRegistry(String modId) {
    super(modId);
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
    var type = BlockEntityType.Builder.of(supplier, blocks).build(null);
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
