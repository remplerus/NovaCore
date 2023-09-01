package novamachina.novacore.bootstrap;

import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;

public class ForgeRecipeSerializerRegistry implements IRegistry<RecipeSerializerDefinition<?>> {
  @Override
  public void register(RecipeSerializerDefinition<?> entry) {
    ForgeRegistries.RECIPE_SERIALIZERS.register(entry.id(), entry.recipeSerializer());
  }
}
