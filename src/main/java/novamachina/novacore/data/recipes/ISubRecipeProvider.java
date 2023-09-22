package novamachina.novacore.data.recipes;

import java.util.function.Consumer;
import net.minecraft.data.recipes.FinishedRecipe;

public interface ISubRecipeProvider {
  void addRecipes(Consumer<FinishedRecipe> consumer);
}
