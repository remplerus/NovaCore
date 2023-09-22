package novamachina.novacore.data.recipes;

import com.google.gson.JsonObject;
import java.util.function.Consumer;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class RecipeBuilder<T extends RecipeBuilder<T>> {

  protected final RecipeSerializer<?> serializer;

  protected RecipeBuilder(RecipeSerializer<?> serializer) {
    this.serializer = serializer;
  }

  public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
    validate(id);
    consumer.accept(getResult(id));
  }

  protected abstract RecipeResult getResult(ResourceLocation id);

  protected void validate(ResourceLocation id) {}

  protected abstract class RecipeResult implements FinishedRecipe {

    private final ResourceLocation id;

    public RecipeResult(ResourceLocation id) {
      this.id = id;
    }

    @Override
    @NonNull
    public ResourceLocation getId() {
      return this.id;
    }

    @Override
    @NonNull
    public RecipeSerializer<?> getType() {
      return serializer;
    }

    @Override
    @Nullable
    public JsonObject serializeAdvancement() {
      return null;
    }

    @Override
    @Nullable
    public ResourceLocation getAdvancementId() {
      return new ResourceLocation(id.getNamespace(), String.format("recipes/%s", id.getPath()));
    }
  }
}
