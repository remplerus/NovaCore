package novamachina.novacore.data.loot.table;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import novamachina.novacore.world.level.block.BlockDefinition;

public abstract class BlockLootTables extends BlockLootSubProvider {
  private final Set<Block> knownBlocks = new HashSet<>();

  protected BlockLootTables(HolderLookup.Provider provider) {
    super(Collections.emptySet(), FeatureFlags.VANILLA_SET, provider);
  }

  @SafeVarargs
  protected final void add(
      Function<Block, LootTable.Builder> factory,
      BlockDefinition<? extends Block>... blockDefinitions) {
    for (BlockDefinition<? extends Block> blockDefinition : blockDefinitions) {
      add(blockDefinition.block(), factory);
      this.knownBlocks.add(blockDefinition.block());
    }
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return knownBlocks;
  }

  @Override
  public LootTable.Builder createSingleItemTable(ItemLike item) {
    return LootTable.lootTable()
        .withPool(
            applyExplosionCondition(
                item,
                LootPool.lootPool()
                    .name("main")
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(item))));
  }

  protected static LootTable.Builder createSelfDropDispatchTable(
      Block block,
      LootItemCondition.Builder conditionBuilder,
      LootPoolEntryContainer.Builder<?> entry) {
    return LootTable.lootTable()
        .withPool(
            LootPool.lootPool()
                .name("main")
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block).when(conditionBuilder).otherwise(entry)));
  }
}
