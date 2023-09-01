package novamachina.novacore.core.registries;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import novamachina.novacore.common.loot.modifier.LootModifierDefinition;

public class LootModifierRegistry
    extends AbstractRegistry<LootModifierDefinition<? extends LootModifier>> {
  public LootModifierRegistry(String modId) {
    super(modId);
  }

  public <T extends LootModifier> LootModifierDefinition<T> create(
      String shortId,
      LootItemCondition[] conditions,
      Codec<? extends IGlobalLootModifier> codec,
      LootModifierDefinition.Factory<T> factory) {
    LootModifierDefinition<T> definition =
        new LootModifierDefinition<>(id(shortId), conditions, codec, factory);
    this.register(definition);
    return definition;
  }
}
