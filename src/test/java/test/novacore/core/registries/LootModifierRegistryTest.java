package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.loot.LootModifier;
import novamachina.novacore.common.loot.modifier.LootModifierDefinition;
import novamachina.novacore.core.registries.LootModifierRegistry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LootModifierRegistryTest {

  private static LootModifierRegistry classUnderTest;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    classUnderTest = new LootModifierRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void create() {
    MapCodec<LootModifier> mockCodec = Mockito.mock(MapCodec.class);
    LootModifierDefinition<LootModifier> expected =
        new LootModifierDefinition<>(
            ResourceLocation.fromNamespaceAndPath("unittest", "loot_modifier"), mockCodec);

    LootModifierDefinition<LootModifier> actual = classUnderTest.create("loot_modifier", mockCodec);

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getCodec(), actual.getCodec());
  }
}
