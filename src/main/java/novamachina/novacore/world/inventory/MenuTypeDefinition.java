package novamachina.novacore.world.inventory;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public record MenuTypeDefinition<T extends AbstractContainerMenu>(
    ResourceLocation id, MenuType<T> type) {}
