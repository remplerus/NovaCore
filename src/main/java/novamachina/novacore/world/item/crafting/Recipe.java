package novamachina.novacore.world.item.crafting;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.NonNull;

@Getter
public abstract class Recipe implements net.minecraft.world.item.crafting.Recipe<Container> {

  private final ResourceLocation id;

  protected Recipe(ResourceLocation id) {
    Preconditions.checkNotNull(id, "Id cannot be null");
    this.id = id;
  }

  public abstract void write(FriendlyByteBuf buffer);

  @Override
  public boolean matches(@NonNull Container pContainer, @NonNull Level pLevel) {
    return false;
  }

  @Override
  @NonNull
  public ItemStack assemble(
      @NonNull Container pContainer, @NonNull RegistryAccess pRegistryAccess) {
    return ItemStack.EMPTY;
  }

  @Override
  public boolean canCraftInDimensions(int pWidth, int pHeight) {
    return true;
  }

  @Override
  @NonNull
  public ItemStack getResultItem(@NonNull RegistryAccess pRegistryAccess) {
    return ItemStack.EMPTY;
  }
}
