package numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RomanToArabicConverterTest {

    RomanToArabicConverter converter;
    @BeforeEach
    void setUp() {
        converter = new RomanToArabicConverter();
    }

    @Test
    void individualTest(){
        var expected = -1;
        var actual = converter.convert("MCCCC");
        Assertions.assertEquals(expected, actual);

    }

    @ParameterizedTest
    @MethodSource("arabicNumeralProvider")
    void testConvertToArabicNumeral(String input, int expected) {
        RomanToArabicConverter converter = new RomanToArabicConverter();
        assertEquals(expected, converter.convert(input));
    }

    private static Stream<Arguments> arabicNumeralProvider() {
        return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("II", 2),
                Arguments.of("III", 3),
                Arguments.of("IV", 4),
                Arguments.of("V", 5),
                Arguments.of("VI", 6),
                Arguments.of("IX", 9),
                Arguments.of("X", 10),
                Arguments.of("XIII", 13),
                Arguments.of("XIV", 14),
                Arguments.of("XV", 15),
                Arguments.of("XIX", 19),
                Arguments.of("XX", 20),
                Arguments.of("XXV", 25),
                Arguments.of("XL", 40),
                Arguments.of("XLIX", 49),
                Arguments.of("L", 50),
                Arguments.of("LXXXIX", 89),
                Arguments.of("XC", 90),
                Arguments.of("XCIX", 99),
                Arguments.of("C", 100),
                Arguments.of("CCCXCIX", 399),
                Arguments.of("CD", 400),
                Arguments.of("D", 500),
                Arguments.of("CMXCIX", 999),
                Arguments.of("M", 1000),
                Arguments.of("MMXXI", 2021),
                Arguments.of("MMXIII", 2013),
                Arguments.of("MMMCMXCIX", 3999),
                Arguments.of("MMXIIII", -1), // invalid input (using "IIII" instead of "IV")
                Arguments.of("MCCCC", -1), // invalid input (using "CCCC" instead of "CD")
                Arguments.of("IL", -1), // invalid input (using "IL" instead of "XL")
                Arguments.of("IC", -1), // invalid input (using "IC" instead of "XC")
                Arguments.of("ID", -1), // invalid input (using "ID" instead of "CD")
                Arguments.of("IM", -1), // invalid input (using "IM" instead of "CM")
                Arguments.of("XIIIIIIIIII", -1), // invalid input (using "IIIIIIIIII" instead of "XIV")
                Arguments.of("", -1), // invalid input (empty string)
                Arguments.of("MCDLXXXVIIII", -1) // invalid input (using "VIIII" instead of "IX")
        );
    }
}
