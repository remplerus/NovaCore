package novamachina.novacore.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.items.IItemHandler;

public abstract class Menu extends AbstractContainerMenu {
  private ContainerLevelAccess access;
  protected Inventory playerInventory;
  protected IItemHandler blockInventory;
  private Block block;

  protected Menu(
      MenuType<?> type,
      int id,
      Inventory playerInventory,
      IItemHandler blockInventory,
      ContainerLevelAccess access,
      Block block) {
    super(type, id);
    this.access = access;
    this.playerInventory = playerInventory;
    this.blockInventory = blockInventory;
    this.block = block;
  }

  protected void layoutPlayerInventorySlots(Container playerInventory, int leftCol, int topRow) {
    addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

    topRow += 58;
    addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
  }

  protected int addSlotBox(
      Container playerInventory,
      int index,
      int x,
      int y,
      int horAmount,
      int dx,
      int verAmount,
      int dy) {
    for (int j = 0; j < verAmount; j++) {
      index = addSlotRange(playerInventory, index, x, y, horAmount, dx);
      y += dy;
    }
    return index;
  }

  protected int addSlotRange(
      Container playerInventory, int index, int x, int y, int amount, int dx) {
    for (int i = 0; i < amount; i++) {
      addSlot(new Slot(playerInventory, index, x, y));
      x += dx;
      index++;
    }
    return index;
  }

  @Override
  public boolean stillValid(Player player) {
    return stillValid(access, player, this.block);
  }

  @Override
  public ItemStack quickMoveStack(Player player, int index) {
    if (!player.level().isClientSide) {
      int blockInventorySize = blockInventory.getSlots();
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = this.slots.get(index);
      if (slot.hasItem()) {
        ItemStack stack = slot.getItem();
        itemstack = stack.copy();
        // quick move is from blockInventory
        if (index < blockInventorySize) {
          // Can't move into playerInventory
          if (!this.moveItemStackTo(
              stack, blockInventorySize, Inventory.INVENTORY_SIZE + blockInventorySize, true)) {
            return ItemStack.EMPTY;
          }
        }
        // can't move into blockInventory (ASSUMPTION: blockInventory is added first)
        if (!this.moveItemStackTo(stack, 0, blockInventorySize, false)) {
          // quick move is from player backpack
          if (index < 27 + blockInventorySize) {
            // can't move into hotbar
            if (!this.moveItemStackTo(
                stack, 27 + blockInventorySize, 36 + blockInventorySize, false)) {
              return ItemStack.EMPTY;
            }
            // quick move is from all slots and can't move into player backpack
          } else if (index < Inventory.INVENTORY_SIZE + blockInventorySize
              && !this.moveItemStackTo(stack, blockInventorySize, 27 + blockInventorySize, false)) {
            return ItemStack.EMPTY;
          }
        }

        if (stack.isEmpty()) {
          slot.set(ItemStack.EMPTY);
        } else {
          slot.setChanged();
        }

        if (stack.getCount() == itemstack.getCount()) {
          return ItemStack.EMPTY;
        }

        slot.onTake(player, stack);
      }

      return itemstack;
    }
    return ItemStack.EMPTY;
  }
}
