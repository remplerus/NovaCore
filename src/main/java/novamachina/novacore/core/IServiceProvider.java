package novamachina.novacore.core;

import novamachina.novacore.world.item.IBlockItemFactory;
import novamachina.novacore.world.level.block.IBlockFactory;
import novamachina.novacore.world.level.block.entity.IBlockEntityTypeFactory;

public interface IServiceProvider {
  IBlockEntityTypeFactory blockEntityTypeFactory();

  IBlockItemFactory blockItemFactory();

  IBlockFactory blockFactory();
}
