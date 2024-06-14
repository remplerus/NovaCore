package novamachina.novacore.common.loot.modifier;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class LootModifierDefinition<T extends LootModifier> {
  private final ResourceLocation id;
  private final LootItemCondition[] conditions;
  private final MapCodec<? extends IGlobalLootModifier> codec;
  private final Factory<T> factory;

  public LootModifierDefinition(
      ResourceLocation id,
      LootItemCondition[] conditions,
      MapCodec<? extends IGlobalLootModifier> codec,
      Factory<T> factory) {
    this.id = id;
    this.conditions = conditions;
    this.codec = codec;
    this.factory = factory;
  }

  public T getModifier() {
    return this.factory.create(this.conditions);
  }

  public ResourceLocation getId() {
    return this.id;
  }

  public LootItemCondition[] getConditions() {
    return this.conditions;
  }

  public MapCodec<? extends IGlobalLootModifier> getCodec() {
    return this.codec;
  }

  public Factory<T> getFactory() {
    return this.factory;
  }

  @FunctionalInterface
  public interface Factory<T> {
    T create(LootItemCondition[] conditions);
  }
}
