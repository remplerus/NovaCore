package novamachina.novacore.registries;

import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import novamachina.novacore.world.item.ItemDefinition;

public class ItemRegistry extends AbstractRegistry<ItemDefinition<? extends Item>> {
  public ItemRegistry(String modId) {
    super(modId);
  }

  public <T extends Item> ItemDefinition<T> item(
      String englishName, String shortId, Supplier<T> itemSupplier, ItemDefinition.ItemType type) {
    T item = itemSupplier.get();
    ItemDefinition<T> definition = new ItemDefinition<>(englishName, id(shortId), item, type);

    register(definition);
    return definition;
  }
}
