package numbers;

import java.util.HashMap;
import java.util.Map;

public class ArabicToRomanConverter {

    Map<Integer, String> numbersMap;

    {
        numbersMap = new HashMap<>();
        numbersMap.put(1, "I");
        numbersMap.put(4, "IV");
        numbersMap.put(5, "V");
        numbersMap.put(9, "IX");
        numbersMap.put(10, "X");
        numbersMap.put(40, "XL");
        numbersMap.put(50, "L");
        numbersMap.put(90, "XC");
        numbersMap.put(100, "C");
        numbersMap.put(400, "CD");
        numbersMap.put(500, "D");
        numbersMap.put(900, "CM");
        numbersMap.put(1000, "M");
    }

    public String convert(Integer arabic) {
        var validationError = validateInput(arabic);
        if (validationError != null) {
            return validationError;
        }
        var romanNumber = new StringBuilder();
        for (int i = 1000; i >= 1; i = i / 10) {
            var group = arabic / i;
            romanNumber.append(generateRomanNumberPart(group, i));
            arabic = arabic % i;
        }
        return romanNumber.toString();
    }

    private static String validateInput(Integer arabic) {
        if (arabic == null) {
            return "invalid input";
        }
        if (arabic < 1) {
            return "invalid input: " + arabic;
        }
        return null;
    }

    private String generateRomanNumberPart(int units, int multiplier) {
        var number = units * multiplier;
        StringBuilder romanNumber = new StringBuilder();
        if (number == (1 * multiplier)) {
            romanNumber.append(numbersMap.get(1 * multiplier));
        } else if (number == (4 * multiplier)) {
            romanNumber.append(numbersMap.get(4 * multiplier));
        } else if (number == (5 * multiplier)) {
            romanNumber.append(numbersMap.get(5 * multiplier));
        } else if (number == (9 * multiplier)) {
            romanNumber.append(numbersMap.get(9 * multiplier));
        } else if (number < (4 * multiplier)) {
            romanNumber.append(generateRepeatingPart(units, 1 * multiplier));
        } else if (number > (5 * multiplier)) {
            romanNumber.append(numbersMap.get(5 * multiplier));
            units = units - 5;
            romanNumber.append(generateRepeatingPart(units, 1 * multiplier));
        }
        return romanNumber.toString();
    }

    private String generateRepeatingPart(int increments, int key) {
        var romanNumber = new StringBuilder();
        for (int i = 0; i < increments; i++) {
            romanNumber.append(numbersMap.get(key));
        }
        return romanNumber.toString();
    }
}
