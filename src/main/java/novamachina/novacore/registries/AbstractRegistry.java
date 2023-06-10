package novamachina.novacore.registries;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractRegistry<T> {
  private final List<T> registry;
  protected String modId;

  protected AbstractRegistry(String modId) {
    this.registry = new ArrayList<>();
    this.modId = modId;
  }

  public ResourceLocation id(String name) {
    return new ResourceLocation(this.modId, name);
  }

  protected void register(T registryObject) {
    registry.add(registryObject);
  }

  public List<T> getRegistry() {
    return registry;
  }
}
