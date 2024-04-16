package it.fulminazzo.fulmicollection.structures;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * The type Nullable optional.
 *
 * @param <T> the type parameter
 */
public final class NullableOptional<T> {
    private static final NullableOptional<?> EMPTY = new NullableOptional<>();
    @Getter
    private final boolean present;
    private final T value;

    private NullableOptional() {
        this.present = false;
        this.value = null;
    }

    /**
     * Returns an empty NullableOptional.
     *
     * @param <K> the type of the value
     * @return the NullableOptional
     */
    @SuppressWarnings("unchecked")
    public static <K> NullableOptional<K> empty() {
        return (NullableOptional<K>) EMPTY;
    }

    private NullableOptional(T value) {
        this.present = true;
        this.value = value;
    }

    /**
     * Gets an instance of NullableOptional containing the provided object.
     *
     * @param <K>   the type of the value
     * @param value the value
     * @return the NullableOptional
     */
    public static <K> NullableOptional<K> of(@Nullable K value) {
        return new NullableOptional<>(value);
    }

    /**
     * Gets the value of this NullableOptional.
     *
     * @return the value
     */
    public T get() {
        if (isPresent()) return this.value;
        else throw new NoSuchElementException("No value present");
    }

    /**
     * Executes the given function if {@link #isPresent()} is true.
     *
     * @param function the function
     */
    public void ifPresent(Consumer<? super T> function) {
        if (isPresent()) function.accept(this.value);
    }

    /**
     * If {@link #isPresent()} is true, {@link #get()} is returned.
     * Otherwise, the specified value is returned.
     *
     * @param value the value
     * @return the value
     */
    public T orElse(T value) {
        return isPresent() ? get() : value;
    }

    /**
     * If {@link #isPresent()} is true, {@link #get()} is returned.
     * Otherwise, the return of the given function is returned.
     *
     * @param function the function
     * @return the value
     */
    public T orElseGet(Supplier<T> function) {
        return isPresent() ? get() : function.get();
    }

    /**
     * If {@link #isPresent()} is true, {@link #get()} is returned.
     * Otherwise, the given exception is thrown.
     *
     * @param <X>      the type of the exception
     * @param function the function
     * @return the tuple
     * @throws X the exception
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> function) throws X {
        if (isPresent()) return get();
        else throw function.get();
    }

    @Override
    public String toString() {
        final String className = getClass().getSimpleName();
        return isPresent() ? String.format("%s[%s]", className, this.value) : String.format("%s.empty", className);
    }

}
