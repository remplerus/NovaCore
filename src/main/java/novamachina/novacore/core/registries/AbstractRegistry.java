package novamachina.novacore.core.registries;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractRegistry<T, K> {
  private final List<T> registry = new ArrayList<>();
  private final String modId;
  private final ResourceKey<? extends Registry<K>> registryKey;

  protected AbstractRegistry(String modId, ResourceKey<? extends Registry<K>> registryKey) {
    this.modId = modId;
    this.registryKey = registryKey;
  }

  public ResourceLocation id(String name) {
    return ResourceLocation.fromNamespaceAndPath(this.modId, name);
  }

  public ResourceKey<K> key(String name) {
    return ResourceKey.create(registryKey, id(name));
  }

  protected void register(T registryObject) {
    this.registry.add(registryObject);
  }

  public List<T> getRegistry() {
    return this.registry;
  }
}
