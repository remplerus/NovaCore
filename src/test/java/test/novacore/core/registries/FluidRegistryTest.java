package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import novamachina.novacore.core.registries.FluidRegistry;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.material.FluidDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FluidRegistryTest {
  private static FluidRegistry classUnderTest;

  @BeforeAll
  static void setUpClass() {
    classUnderTest = new FluidRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void fluid() {
    LiquidBlock mockBlock = Mockito.mock(LiquidBlock.class);
    BlockItem mockBlockItem = Mockito.mock(BlockItem.class);
    FlowingFluid mockStillFluid = Mockito.mock(FlowingFluid.class);
    FlowingFluid mockFlowingFluid = Mockito.mock(FlowingFluid.class);
    ItemDefinition<BucketItem> mockBucketItemDefinition = Mockito.mock(ItemDefinition.class);

    BlockDefinition<LiquidBlock> mockBlockDefinition =
        new BlockDefinition<>(
            "Test Block",
            ResourceLocation.fromNamespaceAndPath("unittest", "testblock"),
            mockBlock,
            mockBlockItem);

    FluidDefinition<FlowingFluid, BlockDefinition<LiquidBlock>, ItemDefinition<BucketItem>>
        expected =
            new FluidDefinition<>(
                "Test Fluid",
                ResourceLocation.fromNamespaceAndPath("unittest", "testfluid"),
                () -> mockStillFluid,
                () -> mockFlowingFluid,
                () -> mockBlockDefinition,
                () -> mockBucketItemDefinition);
    FluidDefinition<FlowingFluid, BlockDefinition<LiquidBlock>, ItemDefinition<BucketItem>> actual =
        classUnderTest.fluid(
            "Test Fluid",
            "testfluid",
            () -> mockStillFluid,
            () -> mockFlowingFluid,
            () -> mockBlockDefinition,
            () -> mockBucketItemDefinition);
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getFlowingId(), actual.getFlowingId());
    assertEquals(expected.getTexture(), actual.getTexture());
    assertEquals(expected.getFlowTexture(), actual.getFlowTexture());
    assertEquals(expected.getBucket(), actual.getBucket());
    assertEquals(expected.getBlock(), actual.getBlock());
    assertEquals(expected.getEnglishName(), actual.getEnglishName());
  }
}
