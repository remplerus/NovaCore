package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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
    LootItemCondition mockCondition = Mockito.mock(LootItemCondition.class);
    LootItemCondition[] mockConditions = new LootItemCondition[] {mockCondition};
    MapCodec<LootModifier> mockCodec = Mockito.mock(MapCodec.class);
    LootModifierDefinition<LootModifier> expected =
        new LootModifierDefinition<>(
            ResourceLocation.fromNamespaceAndPath("unittest", "loot_modifier"),
            mockConditions,
            mockCodec,
            (conditions) -> null);

    LootModifierDefinition<LootModifier> actual =
        classUnderTest.create("loot_modifier", mockConditions, mockCodec, (conditions) -> null);

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getConditions(), actual.getConditions());
    assertEquals(expected.getModifier(), actual.getModifier());
    assertEquals(expected.getCodec(), actual.getCodec());
  }
}
