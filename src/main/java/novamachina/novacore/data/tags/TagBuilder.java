package novamachina.novacore.data.tags;

import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;

public class TagBuilder<TYPE, BUILDER extends TagBuilder<TYPE, BUILDER>> {

  protected final net.minecraft.tags.TagBuilder builder;
  protected final String modID;

  public TagBuilder(net.minecraft.tags.TagBuilder builder, String modID) {
    this.builder = builder;
    this.modID = modID;
  }

  @SuppressWarnings("unchecked")
  private BUILDER self() {
    return (BUILDER) this;
  }

  @SafeVarargs
  public final BUILDER add(TagKey<TYPE>... tags) {
    return apply(builder::addTag, TagKey::location, tags);
  }

  public BUILDER add(TagEntry tag) {
    builder.add(tag);
    return self();
  }

  @SafeVarargs
  public final BUILDER add(ResourceKey<TYPE>... keys) {
    return add(ResourceKey::location, keys);
  }

  @SafeVarargs
  public final <T> BUILDER add(Function<T, ResourceLocation> locationGetter, T... elements) {
    return apply(builder::addElement, locationGetter, elements);
  }

  @SafeVarargs
  protected final <T> BUILDER apply(
      Consumer<ResourceLocation> consumer,
      Function<T, ResourceLocation> locationGetter,
      T... elements) {
    for (T element : elements) {
      consumer.accept(locationGetter.apply(element));
    }
    return self();
  }
}
