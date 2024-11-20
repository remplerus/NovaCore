package test.novacore.core.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import novamachina.novacore.bootstrap.world.level.block.BlockFactory;
import novamachina.novacore.core.IServiceProvider;
import novamachina.novacore.core.registries.BlockRegistry;
import novamachina.novacore.world.item.IBlockItemFactory;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BlockRegistryTest {
  private static BlockRegistry classUnderTest;
  private static IBlockItemFactory blockItemFactory;
  private static IServiceProvider serviceProvider;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    serviceProvider = Mockito.mock(IServiceProvider.class);
    blockItemFactory = Mockito.mock(IBlockItemFactory.class);
    Mockito.when(serviceProvider.blockItemFactory()).thenReturn(blockItemFactory);
    classUnderTest = new BlockRegistry("unittest", serviceProvider);
  }

  @BeforeEach
  void setUp() throws Exception {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void block() {
    Block mockBlock = Mockito.mock(Block.class);
    BlockItem mockBlockItem = Mockito.mock(BlockItem.class);
    BlockFactory blockFactory = Mockito.mock(BlockFactory.class);

    Mockito.when(blockItemFactory.blockItem(Mockito.any(), Mockito.any()))
        .thenReturn(mockBlockItem);
    Mockito.when(blockFactory.block(Mockito.any())).thenReturn(mockBlock);

    BlockDefinition<Block> expected =
        new BlockDefinition<>(
            "Test Block",
            ResourceLocation.fromNamespaceAndPath("unittest", "testblock"),
            mockBlock,
            mockBlockItem);

    BlockDefinition<Block> actual =
        classUnderTest.block("Test Block", "testblock", BlockBehaviour.Properties.of());

    Assertions.assertEquals(expected.block(), actual.block());
    Assertions.assertEquals(expected.asItem(), actual.asItem());
    Assertions.assertEquals(expected.getId(), actual.getId());
  }

  @Test
  void blockWithItemDefinition() {
    Block mockBlock = Mockito.mock(Block.class);
    BlockItem mockBlockItem = Mockito.mock(BlockItem.class);
    BlockFactory blockFactory = Mockito.mock(BlockFactory.class);

    Mockito.when(blockItemFactory.blockItem(Mockito.any(), Mockito.any()))
        .thenReturn(mockBlockItem);
    Mockito.when(blockFactory.block(Mockito.any())).thenReturn(mockBlock);

    BlockDefinition<Block> expected =
        new BlockDefinition<>(
            "Test Block",
            ResourceLocation.fromNamespaceAndPath("unittest", "testblock"),
            mockBlock,
            mockBlockItem,
            ItemDefinition.ItemType.CUSTOM);

    BlockDefinition<Block> returnValue =
        classUnderTest.block(
            "Test Block",
            "testblock",
            BlockBehaviour.Properties.of(),
            ItemDefinition.ItemType.CUSTOM);

    Assertions.assertEquals(expected.block(), returnValue.block());
    Assertions.assertEquals(expected.asItem(), returnValue.asItem());
    Assertions.assertEquals(expected.getId(), returnValue.getId());
    Assertions.assertEquals(expected.getType(), returnValue.getType());
  }
}
