package lotto.util;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.InputException;
import lotto.exception.LottoGameException;

import java.util.List;
import java.util.stream.Stream;

public class InputUtil {

//    private static final String SPLIT_REGEX = "\\s*" + LottoGameConfig.LOTTO_NUMBER_INPUT_DELIMITER + "\\s*";
    private static final String SPLIT_REGEX = "";

    private InputUtil() {
    }

    public static int readInt() {
        return parseToInteger(readLine().trim());
    }

    public static List<Integer> readIntegerList() {
        String input = readLine().trim();
        validateDelimiter(input);
        return stringToIntegerList(input);
    }

    private static int parseToInteger(String input) {
        try {
            int convertedInput = Integer.parseInt(input);
            validatePositiveNumber(convertedInput);
            return convertedInput;
        } catch (NumberFormatException e) {
            throw new LottoGameException(InputException.INVALID_INTEGER);
        }
    }

    private static void validatePositiveNumber(int convertedInput) {
        if (isPositiveNumber(convertedInput)) {
            throw new LottoGameException(InputException.INVALID_INTEGER);
        }
    }

    private static boolean isPositiveNumber(int convertedInput) {
        return convertedInput < 0;
    }

    private static void validateDelimiter(String input) {
        if (!input.contains(SPLIT_REGEX)) {
            throw new LottoGameException(InputException.INVALID_DELIMITER);
        }
    }

    private static List<Integer> stringToIntegerList(String input) {
        try {
            return Stream.of(input.split(SPLIT_REGEX))
                    .filter(InputUtil::validateHasInput)
                    .map(Integer::valueOf)
                    .toList();
        } catch (NumberFormatException e) {
            throw new LottoGameException(InputException.INVALID_INTEGER);
        }
    }

    private static boolean validateHasInput(String input) {
        if (input.isBlank()) {
            throw new LottoGameException(InputException.INVALID_INPUT);
        }
        return true;
    }

    private static String readLine() {
        String input = Console.readLine().trim();
        validateHasInput(input);
        return input;
    }

}