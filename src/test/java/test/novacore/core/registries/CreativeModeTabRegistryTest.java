package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import novamachina.novacore.core.registries.CreativeModeTabRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreativeModeTabRegistryTest {
  private static CreativeModeTabRegistry classUnderTest;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    classUnderTest = new CreativeModeTabRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void creativeModeTab() {
    CreativeModeTab tab =
        CreativeModeTab.builder()
            .icon(() -> new ItemStack(Blocks.DIRT))
            .title(Component.literal("Unit Test Tab"))
            .displayItems(
                (parameters, output) -> {
                  Set.of(Items.DIRT, Items.BEEHIVE).forEach(output::accept);
                })
            .build();

    CreativeModeTabDefinition expected =
        new CreativeModeTabDefinition(
            ResourceLocation.fromNamespaceAndPath("unittest", "test_tab"), tab);

    CreativeModeTabDefinition actual = classUnderTest.creativeModeTab("test_tab", tab);

    assertEquals(expected.id(), actual.id());
    assertEquals(expected.tab(), actual.tab());
  }
}
