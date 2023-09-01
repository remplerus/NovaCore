package novamachina.novacore.bootstrap;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;

public class ForgeRecipeTypeRegistry implements IRegistry<RecipeType<?>> {
  @Override
  public void register(RecipeType<?> entry) {
    ForgeRegistries.RECIPE_TYPES.register(new ResourceLocation(entry.toString()), entry);
  }
}
