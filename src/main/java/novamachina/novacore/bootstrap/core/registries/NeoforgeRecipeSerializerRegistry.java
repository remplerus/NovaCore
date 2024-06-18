package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;

public class NeoforgeRecipeSerializerRegistry implements IRegistry<RecipeSerializerDefinition<?>> {

  public void register(RecipeSerializerDefinition<?> entry) {
    Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, entry.id(), entry.recipeSerializer());
  }
}
