package novamachina.novacore.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;

public class TankUtil {
  private static final ItemStack WATER_BOTTLE;

  private TankUtil() {}

  public static boolean drainWaterFromBottle(
      BlockEntity tileEntity, Player player, IFluidHandler tank) {
    int waterAmount = 333;
    if (tank.getFluidInTank(0).getAmount() % FluidType.BUCKET_VOLUME == 666) {
      waterAmount = 334;
    }

    if (player.getMainHandItem().getItem() == Items.POTION
        && WATER_BOTTLE.getTags().findAny().isPresent()
        && WATER_BOTTLE.getTags().anyMatch(player.getMainHandItem().getTags().toList()::contains)) {
      FluidStack water = new FluidStack(Fluids.WATER, waterAmount);
      if (tank.fill(water, FluidAction.SIMULATE) == water.getAmount()
          && player.addItem(new ItemStack(Items.GLASS_BOTTLE))) {
        if (!player.isCreative()) {
          player.getMainHandItem().shrink(1);
        }

        tank.fill(water, FluidAction.EXECUTE);
        tileEntity.setChanged();
        return true;
      }
    }

    return false;
  }

  public static boolean drainWaterIntoBottle(
      BlockEntity tileEntity, Player player, IFluidHandler tank) {
    int waterAmount = 333;
    if (tank.getFluidInTank(0).getAmount() % FluidType.BUCKET_VOLUME == 334) {
      waterAmount = 334;
    }

    if (player.getMainHandItem().getItem() == Items.GLASS_BOTTLE
        && tank.getFluidInTank(0).getFluid() != null
        && tank.getFluidInTank(0).getAmount() >= waterAmount
        && tank.getFluidInTank(0).getFluid() == Fluids.WATER
        && player.addItem(WATER_BOTTLE.copy())) {
      if (!player.isCreative()) {
        player.getMainHandItem().shrink(1);
      }

      tank.drain(waterAmount, FluidAction.EXECUTE);
      tileEntity.setChanged();
      return true;
    } else {
      return false;
    }
  }

  static {
    WATER_BOTTLE = PotionContents.createItemStack(Items.POTION, Potions.WATER);
  }
}
