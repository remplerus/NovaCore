package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import novamachina.novacore.common.loot.modifier.LootModifierDefinition;
import novamachina.novacore.core.IRegistry;

public class NeoforgeLootModifierRegistry
    implements IRegistry<LootModifierDefinition<? extends LootModifier>> {

  public void register(LootModifierDefinition<? extends LootModifier> entry) {
    Registry.register(
        NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, entry.getId(), entry.getCodec());
  }
}
