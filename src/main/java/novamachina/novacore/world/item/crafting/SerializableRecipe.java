package novamachina.novacore.world.item.crafting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class SerializableRecipe implements Recipe<Container> {

  @Nonnull protected final ResourceLocation id;
  @Nullable protected final ItemStack result;
  @Nonnull protected final RecipeType<?> type;

  protected SerializableRecipe(
      @Nullable final ItemStack result,
      @Nonnull final RecipeType<?> type,
      @Nonnull final ResourceLocation id) {
    this.result = result;
    this.type = type;
    this.id = id;
  }

  @Override
  @NotNull
  public abstract ItemStack getToastSymbol();

  @Override
  public boolean canCraftInDimensions(final int width, final int height) {
    return false;
  }

  @Override
  public ItemStack assemble(Container container, RegistryAccess registryAccess) {
    return this.getResultItem(registryAccess).copy();
  }

  @Override
  public ItemStack getResultItem(RegistryAccess registryAccess) {
    return this.result;
  }

  @Override
  @Nonnull
  public ResourceLocation getId() {
    return this.id;
  }

  @Override
  @Nonnull
  public RecipeType<?> getType() {
    return this.type;
  }

  @Override
  public boolean isSpecial() {
    return true;
  }

  @Override
  public boolean matches(@Nonnull final Container inv, @Nonnull final Level worldIn) {
    return false;
  }
}
