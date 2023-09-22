package novamachina.novacore.bootstrap;

import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.common.loot.modifier.LootModifierDefinition;
import novamachina.novacore.core.IRegistry;

public class ForgeLootModifierRegistry
    implements IRegistry<LootModifierDefinition<? extends LootModifier>> {
  @Override
  public void register(LootModifierDefinition<? extends LootModifier> entry) {
    ForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS
        .get()
        .register(entry.getId(), entry.getCodec());
  }
}
