package novamachina.novacore.world.item.crafting;

import java.util.Objects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public record RecipeSerializerDefinition<T extends Recipe>(
    ResourceLocation id, RecipeSerializer<T> recipeSerializer) {
  public RecipeSerializerDefinition {
    Objects.requireNonNull(id, "id");
  }
}
