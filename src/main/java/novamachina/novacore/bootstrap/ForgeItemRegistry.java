package novamachina.novacore.bootstrap;

import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.ItemDefinition;

public class ForgeItemRegistry implements IRegistry<ItemDefinition<?>> {
  @Override
  public void register(ItemDefinition<?> entry) {
    ForgeRegistries.ITEMS.register(entry.getId(), entry.asItem());
  }
}
