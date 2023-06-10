package novamachina.novacore.world.item.crafting;

import java.util.Objects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class RecipeSerializerDefinition<T extends SerializableRecipe> {
  private final ResourceLocation id;
  private final RecipeSerializer<T> recipeSerializer;

  public RecipeSerializerDefinition(ResourceLocation id, RecipeSerializer<T> recipeSerializer) {
    Objects.requireNonNull(id, "id");
    this.id = id;
    this.recipeSerializer = recipeSerializer;
  }

  public ResourceLocation getId() {
    return id;
  }

  public RecipeSerializer<T> getRecipeSerializer() {
    return recipeSerializer;
  }
}
