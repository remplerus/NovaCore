package novamachina.novacore.data.loot;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;

public abstract class LootProvider extends LootTableProvider {
  protected LootProvider(PackOutput output, List<LootTableProvider.SubProviderEntry> subProviders, CompletableFuture<HolderLookup.Provider> provider) {
    super(output, Collections.emptySet(), subProviders, provider);
  }
}
