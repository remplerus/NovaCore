package novamachina.novacore.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import novamachina.novacore.world.level.block.BlockDefinition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BlockRegistry extends AbstractRegistry<BlockDefinition<? extends Block>> {

  public BlockRegistry(String modId) {
    super(modId);
  }

    public <T extends Block> BlockDefinition<T> block(
        String englishName, String shortId, Supplier<T> blockSupplier) {
        T block = blockSupplier.get();
        BlockItem item = new BlockItem(block, new Item.Properties());
        return blockDefinition(englishName, id(shortId), block, item);
    }

    public <T extends Block> BlockDefinition<T> burnableBlock(
        String englishName, String shortId, Supplier<T> blockSupplier) {
        T block = blockSupplier.get();
        BlockItem item =
            new BlockItem(block, new Item.Properties()) {
                @Override
                public int getBurnTime(
                    @Nonnull final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
                    return 400;
                }
            };
        return blockDefinition(englishName, id(shortId), block, item);
    }

    private <T extends Block> BlockDefinition<T> blockDefinition(
        String englishName, ResourceLocation id, T block, BlockItem item) {
        BlockDefinition<T> definition = new BlockDefinition<>(englishName, id, block, item);
        register(definition);
        return definition;
    }
}
