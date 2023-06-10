package novamachina.novacore.registries;

import java.util.function.Supplier;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;
import novamachina.novacore.world.item.crafting.SerializableRecipe;

public class RecipeSerializerRegistry
    extends AbstractRegistry<RecipeSerializerDefinition<? extends SerializableRecipe>> {

  public RecipeSerializerRegistry(String modId) {
    super(modId);
  }

  public RecipeSerializerDefinition<? extends SerializableRecipe> register(
      String shortId,
      Supplier<? extends NovaRecipeSerializer<? extends SerializableRecipe>> supplier) {
    NovaRecipeSerializer<? extends SerializableRecipe> instance = supplier.get();
    RecipeSerializerDefinition<? extends SerializableRecipe> definition =
        new RecipeSerializerDefinition<>(id(shortId), instance);
    register(definition);
    return definition;
  }
}
