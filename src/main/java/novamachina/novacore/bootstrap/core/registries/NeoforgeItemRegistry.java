package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.ItemDefinition;

public class NeoforgeItemRegistry implements IRegistry<ItemDefinition<?>> {

  public void register(ItemDefinition<?> entry) {
    Registry.register(BuiltInRegistries.ITEM, entry.getId(), entry.asItem());
  }
}
