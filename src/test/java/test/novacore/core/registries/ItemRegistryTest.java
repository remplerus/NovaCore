package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ItemRegistryTest {
  private static ItemRegistry classUnderTest;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    classUnderTest = new ItemRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void item() {
    Item mockItem = Mockito.mock(Item.class);
    ItemDefinition<Item> expected =
        new ItemDefinition<>(
            "Test Item",
            ResourceLocation.fromNamespaceAndPath("unittest", "test_item"),
            mockItem,
            ItemDefinition.ItemType.CUSTOM);
    ItemDefinition<Item> actual =
        classUnderTest.item(
            "Test Item", "test_item", () -> mockItem, ItemDefinition.ItemType.CUSTOM);

    assertEquals(expected.asItem(), actual.asItem());
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getType(), actual.getType());
    assertEquals(expected.getEnglishName(), actual.getEnglishName());
  }
}
