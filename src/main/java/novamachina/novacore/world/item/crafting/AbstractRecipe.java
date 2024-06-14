package novamachina.novacore.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

public abstract class AbstractRecipe implements Recipe<Container> {

  @Override
  public boolean matches(Container pContainer, Level pLevel) {
    return false;
  }

  @Override
  public ItemStack assemble(Container pContainer, HolderLookup.Provider provider) {
    return ItemStack.EMPTY;
  }

  @Override
  public boolean canCraftInDimensions(int pWidth, int pHeight) {
    return true;
  }

  @Override
  public ItemStack getResultItem(HolderLookup.Provider provider) {
    return ItemStack.EMPTY;
  }
}
