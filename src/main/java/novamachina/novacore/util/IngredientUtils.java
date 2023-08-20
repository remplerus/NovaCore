package novamachina.novacore.util;

import java.util.Arrays;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

@Slf4j
public class IngredientUtils {

  private IngredientUtils() {}

  public static boolean areIngredientsEqual(
      @Nonnull final Ingredient i1, @Nonnull final Ingredient i2) {
    @Nonnull final String item1;
    @Nonnull final String item2;
    try {
      item1 = Arrays.toString(i1.getItems());
      item2 = Arrays.toString(i2.getItems());
    } catch (Exception e) {
      log.debug("Cannot compare ingredients");
      log.debug("Ingredient 1: " + Arrays.toString(i1.getItems()));
      log.debug("Ingredient 2: " + Arrays.toString(i2.getItems()));
      log.debug(e.getMessage());
      return false;
    }

    return item1.equals(item2);
  }

  /**
   * Check if Ingredient is a subset of another Ingredient
   *
   * @param test Ingredient that is in question
   * @param source Ingredient that contains all valid values
   * @return true if test is a subset of source, false otherwise
   */
  public static boolean isIngredientIn(Ingredient test, Ingredient source) {
    for (ItemStack stack : test.getItems()) {
      if (source.test(stack)) {
        return true;
      }
    }
    return false;
  }
}
