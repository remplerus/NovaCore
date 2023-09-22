package novamachina.novacore.data.recipes;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

  private final ExistingFileHelper existingFileHelper;

  protected RecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper, String modId) {
    super(output);
    this.existingFileHelper = existingFileHelper;
  }

  @Override
  protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
    Consumer<FinishedRecipe> trackingConsumer =
        consumer.andThen(
            recipe ->
                existingFileHelper.trackGenerated(
                    recipe.getId(), PackType.SERVER_DATA, ".json", "recipes"));
    addRecipes(trackingConsumer);
    getSubProviders().forEach(subProvider -> subProvider.addRecipes(trackingConsumer));
  }

  protected List<ISubRecipeProvider> getSubProviders() {
    return Collections.emptyList();
  }

  protected abstract void addRecipes(Consumer<FinishedRecipe> consumer);
}
