package test.novacore.core.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import novamachina.novacore.core.IServiceProvider;
import novamachina.novacore.core.registries.BlockEntityTypeRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.block.entity.BlockEntityTypeDefinition;
import novamachina.novacore.world.level.block.entity.IBlockEntityTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import test.models.MockBlockEntity;

public class BlockEntityTypeRegistryTest {
  private static BlockEntityTypeRegistry classUnderTest;
  private static IBlockEntityTypeFactory mockBlockEntityTypeFactory;
  private static IServiceProvider serviceProvider;

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    serviceProvider = Mockito.mock(IServiceProvider.class);
    mockBlockEntityTypeFactory = Mockito.mock(IBlockEntityTypeFactory.class);
    Mockito.when(serviceProvider.blockEntityTypeFactory()).thenReturn(mockBlockEntityTypeFactory);
    classUnderTest = new BlockEntityTypeRegistry("unittest", serviceProvider);
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void create() {
    BlockEntityTypeRegistry.BlockEntityFactory<MockBlockEntity> mockBlockEntityFactory =
        Mockito.mock(BlockEntityTypeRegistry.BlockEntityFactory.class);
    BlockDefinition<Block> mockBlockDefinition = Mockito.mock(BlockDefinition.class);
    BlockEntityType<BlockEntity> mockBlockEntityType = Mockito.mock(BlockEntityType.class);

    Mockito.when(mockBlockEntityTypeFactory.createBlockEntityType(Mockito.any(), Mockito.any()))
        .thenReturn(mockBlockEntityType);

    BlockEntityTypeDefinition<?> expected =
        new BlockEntityTypeDefinition<>(
            ResourceLocation.fromNamespaceAndPath("unittest", "type"), mockBlockEntityType);

    BlockEntityTypeDefinition<?> actual =
        classUnderTest.create("type", mockBlockEntityFactory, mockBlockDefinition);

    Assertions.assertEquals(expected.getType(), actual.getType());
    Assertions.assertEquals(expected.getId(), actual.getId());
  }
}
