package novamachina.novacore.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;

public abstract class AbstractRecipe implements Recipe<RecipeInput> {

  @Override
  public boolean matches(RecipeInput pContainer, Level pLevel) {
    return false;
  }

  @Override
  public ItemStack assemble(RecipeInput pContainer, HolderLookup.Provider provider) {
    return ItemStack.EMPTY;
  }

  public boolean canCraftInDimensions(int pWidth, int pHeight) {
    return true;
  }

  public ItemStack getResultItem(HolderLookup.Provider provider) {
    return ItemStack.EMPTY;
  }
}
