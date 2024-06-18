package test.novacore.core.registries;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import novamachina.novacore.core.registries.SoundEventRegistry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoundEventRegistryTest {
  private static SoundEventRegistry classUnderTest;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    classUnderTest = new SoundEventRegistry("unittest");
  }

  @BeforeEach
  void setUp() {
    classUnderTest.getRegistry().clear();
  }

  @Test
  void soundEvent() {
    SoundEvent expected =
        SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath("unittest", "sound_event"));

    SoundEvent actual = classUnderTest.soundEvent("sound_event");

    assertEquals(expected.getLocation(), actual.getLocation());
  }
}
