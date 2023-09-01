package novamachina.novacore.bootstrap;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;

public class ForgeCreativeModeTabRegistry implements IRegistry<CreativeModeTabDefinition> {
  @Override
  public void register(CreativeModeTabDefinition entry) {
    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, entry.id(), entry.tab());
  }
}
