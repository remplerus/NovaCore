package novamachina.novacore.bootstrap.core;

import novamachina.novacore.bootstrap.world.item.BlockItemFactory;
import novamachina.novacore.bootstrap.world.level.block.BlockFactory;
import novamachina.novacore.bootstrap.world.level.block.entity.BlockEntityTypeFactory;
import novamachina.novacore.core.IServiceProvider;
import novamachina.novacore.world.item.IBlockItemFactory;
import novamachina.novacore.world.level.block.IBlockFactory;
import novamachina.novacore.world.level.block.entity.IBlockEntityTypeFactory;

public class ServiceProvider implements IServiceProvider {

  public IBlockEntityTypeFactory blockEntityTypeFactory() {
    return new BlockEntityTypeFactory();
  }

  public IBlockItemFactory blockItemFactory() {
    return new BlockItemFactory();
  }

  @Override
  public IBlockFactory blockFactory() {
    return new BlockFactory();
  }
}
