package numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ArabicToRomanConverterTest {
    ArabicToRomanConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ArabicToRomanConverter();
    }

    @Test
    void convertTest() {
        converter.convert(42);
    }

    @ParameterizedTest
    @MethodSource("romanNumeralProvider")
    void testConvertToRomanNumeral(int input, String expected) {
        assertEquals(expected, converter.convert(input));
    }

    @Test
    void testConvertToRoman() {
        var input = 6;
        assertEquals("VI", converter.convert(input));
    }

    private static Stream<Arguments> romanNumeralProvider() {
        return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(2, "II"),
                Arguments.of(3, "III"),
                Arguments.of(4, "IV"),
                Arguments.of(5, "V"),
                Arguments.of(6, "VI"),
                Arguments.of(9, "IX"),
                Arguments.of(10, "X"),
                Arguments.of(13, "XIII"),
                Arguments.of(14, "XIV"),
                Arguments.of(15, "XV"),
                Arguments.of(19, "XIX"),
                Arguments.of(20, "XX"),
                Arguments.of(25, "XXV"),
                Arguments.of(40, "XL"),
                Arguments.of(49, "XLIX"),
                Arguments.of(50, "L"),
                Arguments.of(89, "LXXXIX"),
                Arguments.of(90, "XC"),
                Arguments.of(99, "XCIX"),
                Arguments.of(100, "C"),
                Arguments.of(399, "CCCXCIX"),
                Arguments.of(400, "CD"),
                Arguments.of(500, "D"),
                Arguments.of(999, "CMXCIX"),
                Arguments.of(1000, "M"),
                Arguments.of(3999, "MMMCMXCIX")
        );
    }
}
