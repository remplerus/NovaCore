package novamachina.novacore;

import static novamachina.novacore.NovaCore.MOD_ID;

import net.neoforged.fml.common.Mod;
import novamachina.novacore.bootstrap.core.ServiceProvider;
import novamachina.novacore.core.IServiceProvider;

@Mod(MOD_ID)
public class NovaCore {
  public static final String MOD_ID = "novacore";
  public static final IServiceProvider SERVICE_PROVIDER = new ServiceProvider();

  public NovaCore() {}
}
