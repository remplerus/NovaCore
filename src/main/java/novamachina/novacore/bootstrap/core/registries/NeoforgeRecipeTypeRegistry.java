package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.novacore.core.IRegistry;

public class NeoforgeRecipeTypeRegistry implements IRegistry<RecipeType<?>> {
  @Override
  public void register(RecipeType<?> entry) {
    Registry.register(
        BuiltInRegistries.RECIPE_TYPE, ResourceLocation.parse(entry.toString()), entry);
  }
}
