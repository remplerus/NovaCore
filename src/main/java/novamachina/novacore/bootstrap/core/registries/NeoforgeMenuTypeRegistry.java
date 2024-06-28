package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.inventory.MenuTypeDefinition;

public class NeoforgeMenuTypeRegistry implements IRegistry<MenuTypeDefinition<?>> {
  @Override
  public void register(MenuTypeDefinition<?> entry) {
    Registry.register(BuiltInRegistries.MENU, entry.id(), entry.type());
  }
}
