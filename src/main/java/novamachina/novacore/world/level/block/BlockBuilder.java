package novamachina.novacore.world.level.block;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;

public class BlockBuilder {

  private BlockBehaviour.Properties properties;

  public static BlockBuilder create() {
    return new BlockBuilder();
  }

  public BlockBuilder noCollision() {
    this.properties.noCollission();
    return this;
  }

  public BlockBuilder noOcclusion() {
    this.properties.noOcclusion();
    return this;
  }

  public BlockBuilder friction(float friction) {
    this.properties.friction(friction);
    return this;
  }

  public BlockBuilder speedFactor(float speedFactor) {
    this.properties.speedFactor(speedFactor);
    return this;
  }

  public BlockBuilder jumpFactor(float jumpFactor) {
    this.properties.jumpFactor(jumpFactor);
    return this;
  }

  public BlockBuilder sound(SoundType soundType) {
    this.properties.sound(soundType);
    return this;
  }

  public BlockBuilder lightLevel(ToIntFunction<BlockState> lightEmission) {
    this.properties.lightLevel(lightEmission);
    return this;
  }

  public BlockBuilder strength(float destroyTime, float explosionResistance) {
    this.properties.destroyTime(destroyTime);
    this.properties.explosionResistance(explosionResistance);
    return this;
  }

  public BlockBuilder instabreak() {
    this.properties.instabreak();
    return this;
  }

  public BlockBuilder strength(float strength) {
    this.properties.strength(strength, strength);
    return this;
  }

  public BlockBuilder randomTicks() {
    this.properties.randomTicks();
    return this;
  }

  public BlockBuilder dynamicShape() {
    this.properties.dynamicShape();
    return this;
  }

  public BlockBuilder noLootTable() {
    this.properties.noLootTable();
    return this;
  }

  public BlockBuilder lootFrom(java.util.function.Supplier<? extends Block> blockIn) {
    this.properties.lootFrom(blockIn);
    return this;
  }

  public BlockBuilder air() {
    this.properties.air();
    return this;
  }

  public BlockBuilder isValidSpawn(
      BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn) {
    this.properties.isValidSpawn(isValidSpawn);
    return this;
  }

  public BlockBuilder isRedstoneConductor(BlockBehaviour.StatePredicate isRedstoneConductor) {
    this.properties.isRedstoneConductor(isRedstoneConductor);
    return this;
  }

  public BlockBuilder isSuffocating(BlockBehaviour.StatePredicate isSuffocating) {
    this.properties.isSuffocating(isSuffocating);
    return this;
  }

  public BlockBuilder isViewBlocking(BlockBehaviour.StatePredicate isViewBlocking) {
    this.properties.isViewBlocking(isViewBlocking);
    return this;
  }

  public BlockBuilder hasPostProcess(BlockBehaviour.StatePredicate hasPostProcess) {
    this.properties.hasPostProcess(hasPostProcess);
    return this;
  }

  public BlockBuilder emissiveRendering(BlockBehaviour.StatePredicate emissiveRendering) {
    this.properties.emissiveRendering(emissiveRendering);
    return this;
  }

  public BlockBuilder requiresCorrectToolForDrops() {
    this.properties.requiresCorrectToolForDrops();
    return this;
  }

  public BlockBuilder destroyTime(float destroyTime) {
    this.properties.destroyTime(destroyTime);
    return this;
  }

  public BlockBuilder explosionResistance(float explosionResistance) {
    this.properties.explosionResistance(explosionResistance);
    return this;
  }

  public BlockBuilder offsetType(BlockBehaviour.OffsetType offsetType) {
    this.properties.offsetType(offsetType);
    return this;
  }

  public BlockBuilder mapColor(MapColor mapColor) {
    this.properties.mapColor(mapColor);
    return this;
  }

  public Block buildBlock() {
    return new Block(properties);
  }

  public FallingBlock buildFallingBlock() {
    return new FallingBlock(properties);
  }

  public LiquidBlock buildLiquidBlock(Supplier<? extends FlowingFluid> fluid) {
    return new LiquidBlock(fluid, properties);
  }

  private BlockBuilder() {
    this.properties = BlockBehaviour.Properties.of();
  }
}
