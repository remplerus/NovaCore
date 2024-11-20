package novamachina.novacore.core.registries;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;

public class RecipeSerializerRegistry
    extends AbstractRegistry<
        RecipeSerializerDefinition<? extends AbstractRecipe>, RecipeSerializer<?>> {

  public RecipeSerializerRegistry(String modId) {
    super(modId, Registries.RECIPE_SERIALIZER);
  }

  public <T extends AbstractRecipe> RecipeSerializerDefinition<T> register(
      String shortId, Supplier<? extends RecipeSerializer<T>> supplier) {
    RecipeSerializer<T> instance = supplier.get();
    RecipeSerializerDefinition<T> definition =
        new RecipeSerializerDefinition<>(id(shortId), instance);
    this.register(definition);
    return definition;
  }
}
