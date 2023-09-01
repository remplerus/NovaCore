package novamachina.novacore.core.registries;

import net.minecraft.world.item.CreativeModeTab;
import novamachina.novacore.world.item.CreativeModeTabDefinition;

public class CreativeModeTabRegistry extends AbstractRegistry<CreativeModeTabDefinition> {
  public CreativeModeTabRegistry(String modId) {
    super(modId);
  }

  public CreativeModeTabDefinition creativeModeTab(String shortName, CreativeModeTab tab) {
    CreativeModeTabDefinition definition = new CreativeModeTabDefinition(id(shortName), tab);
    this.register(definition);
    return definition;
  }
}
