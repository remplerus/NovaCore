package novamachina.novacore.registries;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;

public class LootModifierRegistry extends AbstractRegistry<Codec<? extends IGlobalLootModifier>> {
  public LootModifierRegistry(String modId) {
    super(modId);
//    registry = DeferredRegister.create(Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, modId);
  }
}
