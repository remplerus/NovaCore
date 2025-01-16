package novamachina.novacore.data.recipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.novacore.world.item.crafting.AbstractRecipe;

public abstract class RecipeBuilder<T extends AbstractRecipe> {

  protected final RecipeSerializer<?> serializer;

  protected RecipeBuilder(RecipeSerializer<?> serializer) {
    this.serializer = serializer;
  }

  public void build(RecipeOutput consumer, ResourceKey<Recipe<?>> key) {
    validate(key);
    Advancement.Builder advancementBuilder =
        consumer
            .advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(key))
            .rewards(AdvancementRewards.Builder.recipe(key))
            .requirements(AdvancementRequirements.Strategy.OR);
    consumer.accept(key, getRecipe(key), advancementBuilder.build(key.location()));
  }

  protected abstract T getRecipe(ResourceKey<Recipe<?>> id);

  protected void validate(ResourceKey<Recipe<?>> id) {}
}
