package novamachina.novacore.registries;

import net.minecraft.resources.ResourceLocation;

public class SimpleResourceLocationRegistry extends AbstractRegistry<ResourceLocation> {
  public SimpleResourceLocationRegistry(String modId) {
    super(modId);
  }

  public ResourceLocation register(String shortId) {
    ResourceLocation statResourceLocation = id(shortId);
    register(statResourceLocation);
    return statResourceLocation;
  }
}
