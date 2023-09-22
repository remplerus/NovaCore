package novamachina.novacore.common.loot.modifier;

import com.mojang.serialization.Codec;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

@Getter
public class LootModifierDefinition<T extends LootModifier> {
  private final ResourceLocation id;
  private final LootItemCondition[] conditions;
  private final Codec<? extends IGlobalLootModifier> codec;
  private final Factory<T> factory;

  public LootModifierDefinition(
      ResourceLocation id,
      LootItemCondition[] conditions,
      Codec<? extends IGlobalLootModifier> codec,
      Factory<T> factory) {
    this.id = id;
    this.conditions = conditions;
    this.codec = codec;
    this.factory = factory;
  }

  public T getModifier() {
    return this.factory.create(conditions);
  }

  @FunctionalInterface
  public interface Factory<T> {
    T create(LootItemCondition[] conditions);
  }
}
