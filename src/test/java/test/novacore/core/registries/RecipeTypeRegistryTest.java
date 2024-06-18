package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.novacore.core.registries.RecipeTypeRegistry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTypeRegistryTest {

  private static RecipeTypeRegistry classUnderTest;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    classUnderTest = new RecipeTypeRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void register() {
    RecipeType<?> expected =
        new RecipeType<>() {
          @Override
          public String toString() {
            return ResourceLocation.fromNamespaceAndPath("unittest", "recipe_type").toString();
          }
        };

    RecipeType<?> actual = classUnderTest.register("recipe_type");

    assertEquals(expected.toString(), actual.toString());
  }
}
