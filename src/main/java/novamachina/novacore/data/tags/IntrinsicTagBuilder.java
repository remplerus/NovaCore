package novamachina.novacore.data.tags;

import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class IntrinsicTagBuilder<T> extends TagBuilder<T, IntrinsicTagBuilder<T>> {
  private final Function<T, ResourceKey<T>> keyExtractor;

  public IntrinsicTagBuilder(
      Function<T, ResourceKey<T>> keyExtractor,
      net.minecraft.tags.TagBuilder builder,
      String modId) {
    super(builder, modId);
    this.keyExtractor = keyExtractor;
  }

  @SafeVarargs
  public final IntrinsicTagBuilder<T> add(Supplier<T>... elements) {
    return addTyped(Supplier::get, elements);
  }

  private ResourceLocation getKey(T element) {
    return keyExtractor.apply(element).location();
  }

  @SafeVarargs
  public final IntrinsicTagBuilder<T> add(T... elements) {
    return add(this::getKey, elements);
  }

  @SafeVarargs
  public final <S> IntrinsicTagBuilder<T> addTyped(Function<S, T> converter, S... elements) {
    return add(converter.andThen(this::getKey), elements);
  }
}
