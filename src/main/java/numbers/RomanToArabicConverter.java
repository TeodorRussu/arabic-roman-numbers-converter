package numbers;

import java.util.*;

public class RomanToArabicConverter {
    Map<Character, Integer> standardNumbersMap;
    Map<Integer, Set<Integer>> validSuccessions;

    {
        standardNumbersMap = new HashMap<>();
        standardNumbersMap.put('I', 1);
        standardNumbersMap.put('V', 5);
        standardNumbersMap.put('X', 10);
        standardNumbersMap.put('L', 50);
        standardNumbersMap.put('C', 100);
        standardNumbersMap.put('D', 500);
        standardNumbersMap.put('M', 1000);

        validSuccessions = new HashMap<>();
        validSuccessions.put(-1, Set.of(5, 10));
        validSuccessions.put(-10, Set.of(50, 100));
        validSuccessions.put(-100, Set.of(500, 1000));

    }

    public Integer convert(String roman) {
        if (roman == null || roman.isBlank()) {
            return -1;
        }
        Deque<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < roman.length(); i++) {
            char currentSymbol = roman.charAt(i);
            var currentArabicValue = standardNumbersMap.get(currentSymbol);

            var previousArabicValue = numbers.peekLast();

            if (previousArabicValue != null && currentArabicValue > previousArabicValue) {
                var previousNumberCorrected = numbers.removeLast() * (-1);
                Integer x = validateAgainstIllegalSuccessionsOfRomanNumbers(currentArabicValue, previousNumberCorrected);
                if (x != null) {
                    return x;
                }
                numbers.add(previousNumberCorrected);
            }
            numbers.add(currentArabicValue);

            Integer x = validateAgainstFourSimilarConsecutiveItems(numbers);
            if (x != null){
                return x;
            }
        }
        return numbers.stream().mapToInt(Integer::new).sum();
    }

    private Integer validateAgainstIllegalSuccessionsOfRomanNumbers(Integer currentArabicValue, int previousNumberCorrected) {
        if (!validSuccessions.get(previousNumberCorrected).contains(currentArabicValue)) {
            return -1;
        }
        return null;
    }

    private static Integer validateAgainstFourSimilarConsecutiveItems(Deque<Integer> numbers) {
        if (numbers.size() >= 4) {
            Set<Integer> lastFour = new HashSet<>();

            Iterator<Integer> descendingIterator = numbers.descendingIterator();
            for (int j = 0; j < 4 && descendingIterator.hasNext(); j++) {
                lastFour.add(descendingIterator.next());
            }
            if (lastFour.size() == 1){
                return -1;
            }
        }
        return null;
    }
}
