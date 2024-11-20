package novamachina.novacore.world.item;

import java.util.Objects;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ItemDefinition<T extends Item> implements ItemLike {
  private final ResourceLocation id;
  private final String englishName;
  private final T item;
  private final ItemType type;

  public ItemDefinition(String englishName, ResourceLocation id, T item, ItemType type) {
    Objects.requireNonNull(id, "id");
    this.id = id;
    this.englishName = englishName;
    this.item = item;
    this.type = type;
  }

  @Override
  @Nonnull
  public T asItem() {
    return item;
  }

  public ItemStack itemStack() {
    return new ItemStack(item, 1);
  }

  public ItemStack itemStack(int stackSize) {
    return new ItemStack(item, stackSize);
  }

  public ResourceLocation getId() {
    return this.id;
  }

  public String getEnglishName() {
    return this.englishName;
  }

  public ItemType getType() {
    return this.type;
  }

  public enum ItemType {
    CUSTOM,
    TOOL,
    OTHER;
  }
}
