package novamachina.novacore.bootstrap.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import novamachina.novacore.core.IRegistry;

public class NeoforgeSoundEventRegistry implements IRegistry<SoundEvent> {

  public void register(SoundEvent entry) {
    Registry.register(BuiltInRegistries.SOUND_EVENT, entry.location(), entry);
  }
}
