package novamachina.novacore.bootstrap;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.novacore.core.IRegistry;

public class ForgeSoundEventRegistry implements IRegistry<SoundEvent> {
  @Override
  public void register(SoundEvent entry) {
    ForgeRegistries.SOUND_EVENTS.register(entry.getLocation(), entry);
  }
}
