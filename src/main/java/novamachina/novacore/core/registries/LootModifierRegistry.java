package novamachina.novacore.core.registries;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import novamachina.novacore.common.loot.modifier.LootModifierDefinition;

public class LootModifierRegistry
    extends AbstractRegistry<
        LootModifierDefinition<? extends LootModifier>, MapCodec<? extends IGlobalLootModifier>> {
  public LootModifierRegistry(String modId) {
    super(modId, NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);
  }

  public <T extends LootModifier> LootModifierDefinition<T> create(
      String shortId, MapCodec<? extends IGlobalLootModifier> codec) {
    LootModifierDefinition<T> definition = new LootModifierDefinition<>(id(shortId), codec);
    this.register(definition);
    return definition;
  }
}
