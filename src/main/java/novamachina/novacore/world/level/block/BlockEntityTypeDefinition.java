package novamachina.novacore.world.level.block;

import java.util.Objects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityTypeDefinition<T extends BlockEntity> {
  private final ResourceLocation id;
  private final BlockEntityType<T> type;

  public BlockEntityTypeDefinition(ResourceLocation id, BlockEntityType<T> blockEntityType) {
    Objects.requireNonNull(id, "id");
    this.id = id;
    this.type = blockEntityType;
  }

  public ResourceLocation getId() {
    return id;
  }

  public BlockEntityType<T> getType() {
    return type;
  }
}
