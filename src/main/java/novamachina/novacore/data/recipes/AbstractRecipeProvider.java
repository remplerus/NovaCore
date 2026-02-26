package novamachina.novacore.data.recipes;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public abstract class AbstractRecipeProvider extends RecipeProvider {

  protected AbstractRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
    super(packOutput, provider);
  }

  protected void buildRecipes(RecipeOutput recipeOutput) {
    this.getSubProviders().forEach((subProvider) -> subProvider.addRecipes(recipeOutput));
  }

  protected List<ISubRecipeProvider> getSubProviders() {
    return Collections.emptyList();
  }
}
