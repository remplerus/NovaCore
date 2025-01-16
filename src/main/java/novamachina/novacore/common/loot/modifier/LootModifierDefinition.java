package novamachina.novacore.common.loot.modifier;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class LootModifierDefinition<T extends LootModifier> {
  private final ResourceLocation id;
  private final MapCodec<? extends IGlobalLootModifier> codec;

  public LootModifierDefinition(
      ResourceLocation id, MapCodec<? extends IGlobalLootModifier> codec) {
    this.id = id;
    this.codec = codec;
  }

  public ResourceLocation getId() {
    return this.id;
  }

  public MapCodec<? extends IGlobalLootModifier> getCodec() {
    return this.codec;
  }
}
