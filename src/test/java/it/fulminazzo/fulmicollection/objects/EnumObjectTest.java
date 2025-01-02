package it.fulminazzo.fulmicollection.objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class EnumObjectTest {

    private static Object[][] composedValues(Function<MockEnum, Object[]> mapFunction) {
        return Arrays.stream(MockEnum.values()).map(mapFunction).toArray(Object[][]::new);
    }

    private static Object[][] ordinalValues() {
        AtomicInteger i = new AtomicInteger();
        return composedValues(e -> new Object[]{e, i.getAndIncrement()});
    }

    @ParameterizedTest
    @MethodSource("ordinalValues")
    void testOrdinal(MockEnum mockEnum, int ordinal) {
        assertEquals(ordinal, mockEnum.ordinal());
    }

    private static Object[][] nameValues() {
        String[] names = new String[]{"FIRST", "SECOND", "THIRD"};
        AtomicInteger i = new AtomicInteger();
        return composedValues(e -> new Object[]{e, names[i.getAndIncrement()]});
    }

    @ParameterizedTest
    @MethodSource("nameValues")
    void testName(MockEnum mockEnum, String name) {
        assertEquals(name, mockEnum.name());
    }

    @ParameterizedTest
    @MethodSource("nameValues")
    void testValueOf(MockEnum mockEnum, String name) {
        assertEquals(mockEnum, MockEnum.valueOf(name));
    }

    @Test
    void testInvalidValueOf() {
        assertThrowsExactly(IllegalArgumentException.class, () -> MockEnum.valueOf("INVALID"));
    }

    static class MockEnum extends EnumObject {
        public static final MockEnum FIRST = new MockEnum();
        public static final MockEnum SECOND = new MockEnum();
        public static final MockEnum THIRD = new MockEnum();

        public static MockEnum valueOf(String name) {
            return valueOf(MockEnum.class, name);
        }

        public static MockEnum[] values() {
            return values(MockEnum.class);
        }

    }

}