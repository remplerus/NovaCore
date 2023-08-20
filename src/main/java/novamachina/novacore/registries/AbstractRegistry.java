package novamachina.novacore.registries;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractRegistry<T> {
  @Getter private final List<T> registry;
  private final String modId;

  protected AbstractRegistry(String modId) {
    this.registry = new ArrayList<>();
    this.modId = modId;
  }

  protected ResourceLocation id(String name) {
    return new ResourceLocation(this.modId, name);
  }

  protected void register(T registryObject) {
    registry.add(registryObject);
  }
}
