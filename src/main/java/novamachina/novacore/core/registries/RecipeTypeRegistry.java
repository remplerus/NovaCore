package novamachina.novacore.core.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class RecipeTypeRegistry
    extends AbstractRegistry<RecipeType<? extends Recipe<?>>, RecipeType<?>> {
  public RecipeTypeRegistry(String modId) {
    super(modId, Registries.RECIPE_TYPE);
  }

  public <T extends Recipe<?>> RecipeType<T> register(String shortId) {
    RecipeType<T> type =
        new RecipeType<T>() {
          @Override
          public String toString() {
            return id(shortId).toString();
          }
        };
    this.register(type);
    return type;
  }
}
