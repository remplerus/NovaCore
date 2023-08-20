package novamachina.novacore.data.loot;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;

public abstract class LootProvider extends LootTableProvider {
  protected LootProvider(PackOutput output, List<LootTableProvider.SubProviderEntry> subProviders) {
    this(output, Collections.emptySet(), subProviders);
  }

  protected LootProvider(
      PackOutput output,
      Set<ResourceLocation> requiredTables,
      List<SubProviderEntry> subProviders) {
    super(output, requiredTables, subProviders);
  }
}
