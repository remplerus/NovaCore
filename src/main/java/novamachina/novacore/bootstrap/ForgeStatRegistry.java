package novamachina.novacore.bootstrap;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import novamachina.novacore.core.IRegistry;

public class ForgeStatRegistry implements IRegistry<ResourceLocation> {
  @Override
  public void register(ResourceLocation entry) {
    Registry.register(BuiltInRegistries.CUSTOM_STAT, entry, entry);
  }
}
