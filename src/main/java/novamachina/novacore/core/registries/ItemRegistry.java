package novamachina.novacore.core.registries;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import novamachina.novacore.world.item.ItemDefinition;

public class ItemRegistry extends AbstractRegistry<ItemDefinition<? extends Item>, Item> {
  public ItemRegistry(String modId) {
    super(modId, Registries.ITEM);
  }

  public <T extends Item> ItemDefinition<T> item(
      String englishName,
      String shortId,
      Item.Properties properties,
      Function<Item.Properties, T> itemSupplier,
      ItemDefinition.ItemType type) {
    properties = properties.setId(key(shortId));
    T item = itemSupplier.apply(properties);
    ItemDefinition<T> definition = new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }

  public ItemDefinition<BucketItem> bucketItem(
      String englishName,
      String shortId,
      Fluid fluid,
      Item.Properties properties,
      BiFunction<Fluid, Item.Properties, BucketItem> itemSupplier,
      ItemDefinition.ItemType type) {
    properties = properties.setId(key(shortId));
    BucketItem item = itemSupplier.apply(fluid, properties);
    ItemDefinition<BucketItem> definition =
        new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }
}
