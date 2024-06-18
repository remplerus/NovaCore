package novamachina.novacore.core.registries;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractRegistry<T> {
  private final List<T> registry = new ArrayList<>();
  private final String modId;

  protected AbstractRegistry(String modId) {
    this.modId = modId;
  }

  protected ResourceLocation id(String name) {
    return ResourceLocation.fromNamespaceAndPath(this.modId, name);
  }

  protected void register(T registryObject) {
    this.registry.add(registryObject);
  }

  public List<T> getRegistry() {
    return this.registry;
  }
}
