package it.fulminazzo.fulmicollection.objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ReflTest extends AbstractReflTest {

    @Test
    void testEquality() {
        assertEquals(this.refl, new Refl<>(this.testClass));
    }

    @Test
    void testInEquality() {
        assertNotEquals(this.refl, new Refl<>(new Object()));
    }

    @Test
    void testToString() {
        assertEquals(this.testClass.toString(), this.refl.toString());
    }

    @Nested
    @DisplayName("Test constructors")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ConstructorTest extends AbstractReflTest {

        private Object[] getConstructors() {
            return new Object[]{
                    (Supplier<Refl<TestClass>>) () -> new Refl<>(TestClass.class.getCanonicalName(), "James"),
                    (Supplier<Refl<TestClass>>) () -> new Refl<>(TestClass.class.getCanonicalName(), new Class[]{String.class}, "James"),
                    (Supplier<Refl<TestClass>>) () -> new Refl<>(TestClass.class, "James"),
                    (Supplier<Refl<TestClass>>) () -> new Refl<>(TestClass.class, new Class[]{String.class}, "James")
            };
        }

        @ParameterizedTest
        @MethodSource("getConstructors")
        void test(Supplier<Refl<TestClass>> supplier) {
            assertEquals(this.refl, supplier.get());
            assertEquals(this.refl.getObject(), supplier.get().getObject());
        }

        @Test
        void testClass() {
            assertEquals(TestClass.class, new Refl<>(TestClass.class).getObject());
        }
    }
}