package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.resources.ResourceLocation;
import novamachina.novacore.core.registries.SimpleResourceLocationRegistry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleResourceLocationRegistryTest {

  private static SimpleResourceLocationRegistry classUnderTest;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    classUnderTest = new SimpleResourceLocationRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void register() {
    ResourceLocation expected = ResourceLocation.fromNamespaceAndPath("unittest", "test");

    ResourceLocation actual = classUnderTest.register("test");

    assertEquals(expected, actual);
  }
}
