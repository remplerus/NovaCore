package novamachina.novacore.core.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class SimpleResourceLocationRegistry extends AbstractRegistry<ResourceLocation, Object> {
  public SimpleResourceLocationRegistry(String modId) {
    super(modId, null);
  }

  @Override
  public ResourceKey<Object> key(String name) {
    throw new UnsupportedOperationException();
  }

  public ResourceLocation register(String shortId) {
    ResourceLocation statResourceLocation = id(shortId);
    this.register(statResourceLocation);
    return statResourceLocation;
  }
}
