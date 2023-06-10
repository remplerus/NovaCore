package novamachina.novacore.registries;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class RecipeTypeRegistry extends AbstractRegistry<RecipeType<? extends Recipe<?>>> {
  public RecipeTypeRegistry(String modId) {
    super(modId);
  }

  public <T extends Recipe<?>> RecipeType<T> register(String shortId) {
    RecipeType<T> type =
        new RecipeType<T>() {
          @Override
          public String toString() {
            return id(shortId).toString();
          }
        };
    register(type);
    return type;
  }
}
