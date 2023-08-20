package novamachina.novacore.world.item;

import java.util.Objects;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ItemDefinition<T extends Item> implements ItemLike {
  @Getter private final ResourceLocation id;
  @Getter private final String englishName;
  private final T item;
  @Getter private final ItemType type;

  public ItemDefinition(String englishName, ResourceLocation id, T item, ItemType type) {
    Objects.requireNonNull(id, "id");
    this.id = id;
    this.englishName = englishName;
    this.item = item;
    this.type = type;
  }

  @Override
  @NonNull
  public T asItem() {
    return item;
  }

  public ItemStack itemStack() {
    return new ItemStack(item, 1);
  }

  public ItemStack itemStack(int stacksize) {
    return new ItemStack(item, stacksize);
  }

  public enum ItemType {
    TOOL,
    OTHER;
  }
}
