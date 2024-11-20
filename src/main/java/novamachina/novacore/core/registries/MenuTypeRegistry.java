package novamachina.novacore.core.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import novamachina.novacore.world.inventory.MenuTypeDefinition;

public class MenuTypeRegistry extends AbstractRegistry<MenuTypeDefinition<?>, MenuType<?>> {

  public MenuTypeRegistry(String modId) {
    super(modId, Registries.MENU);
  }

  public <T extends AbstractContainerMenu> MenuTypeDefinition<T> menuType(
      String shortId, MenuType.MenuSupplier<T> supplier) {
    MenuTypeDefinition<T> definition =
        new MenuTypeDefinition<>(id(shortId), new MenuType<>(supplier, FeatureFlags.DEFAULT_FLAGS));
    this.register(definition);
    return definition;
  }
}
