package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.novacore.core.registries.RecipeSerializerRegistry;
import novamachina.novacore.world.item.crafting.AbstractRecipe;
import novamachina.novacore.world.item.crafting.RecipeSerializerDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RecipeSerializerRegistryTest {
  private static RecipeSerializerRegistry classUnderTest;

  @BeforeAll
  static void setUpBeforeAll() {
    classUnderTest = new RecipeSerializerRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void register() {
    RecipeSerializer<AbstractRecipe> mockRecipeSerializer = Mockito.mock(RecipeSerializer.class);

    RecipeSerializerDefinition<AbstractRecipe> expected =
        new RecipeSerializerDefinition<>(
            ResourceLocation.fromNamespaceAndPath("unittest", "recipe_serializer"),
            mockRecipeSerializer);
    RecipeSerializerDefinition<? extends AbstractRecipe> actual =
        classUnderTest.register("recipe_serializer", () -> mockRecipeSerializer);

    assertEquals(expected.id(), actual.id());
    assertEquals(expected.recipeSerializer(), actual.recipeSerializer());
  }
}
