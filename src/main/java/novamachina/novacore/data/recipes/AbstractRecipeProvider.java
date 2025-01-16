package novamachina.novacore.data.recipes;

import java.util.Collections;
import java.util.List;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public abstract class AbstractRecipeProvider extends RecipeProvider {

  protected AbstractRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
    super(provider, recipeOutput);
  }

  @Override
  protected void buildRecipes() {
    getSubProviders().forEach(subProvider -> subProvider.addRecipes(registries, this.output));
  }

  protected List<ISubRecipeProvider> getSubProviders() {
    return Collections.emptyList();
  }
}
