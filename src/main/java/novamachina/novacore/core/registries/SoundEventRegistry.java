package novamachina.novacore.core.registries;

import net.minecraft.sounds.SoundEvent;

public class SoundEventRegistry extends AbstractRegistry<SoundEvent> {

  public SoundEventRegistry(String modId) {
    super(modId);
  }

  public SoundEvent soundEvent(String shortId) {
    SoundEvent event = SoundEvent.createVariableRangeEvent(id(shortId));
    this.register(event);
    return event;
  }
}
