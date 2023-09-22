package novamachina.novacore.core.registries;

import java.util.function.Supplier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.novacore.world.item.crafting.Recipe;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;

public class RecipeSerializerRegistry
    extends AbstractRegistry<RecipeSerializerDefinition<? extends Recipe>> {

  public RecipeSerializerRegistry(String modId) {
    super(modId);
  }

  public RecipeSerializerDefinition<? extends Recipe> register(
      String shortId, Supplier<? extends RecipeSerializer<? extends Recipe>> supplier) {
    RecipeSerializer<? extends Recipe> instance = supplier.get();
    RecipeSerializerDefinition<? extends Recipe> definition =
        new RecipeSerializerDefinition<>(id(shortId), instance);
    this.register(definition);
    return definition;
  }
}
