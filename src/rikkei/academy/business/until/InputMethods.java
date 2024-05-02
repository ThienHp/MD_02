package rikkei.academy.business.until;

import java.util.Scanner;

public class InputMethods {
    private static final String ERROR_ALERT = "===>> Định dạng không hợp lệ ! Vui lòng thử lại....";
    private static final String EMPTY_ALERT = "===>> Trường nhập vào không thể để trống! Vui lòng thử lại....";

    public static String getString() {
        while (true) {
            String result = getInput();
            if (result.equals("")) {
                System.out.println(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }
    public static char getChar() {
        return getString().charAt(0);
    }
    public static boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }
    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.out.println(ERROR_ALERT);
            }
        }
    }
    public static short getShort() {
        while (true) {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException errException) {
                System.out.println(ERROR_ALERT);
            }
        }
    }
    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.out.println(ERROR_ALERT);
            }
        }
    }
    public static long getLong() {
        while (true) {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException errException) {
                System.out.println(ERROR_ALERT);
            }
        }
    }
    public static float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException errException) {
                System.out.println(ERROR_ALERT);
            }
        }
    }
    public static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException errException) {
                System.out.println(ERROR_ALERT);
            }
        }
    }


    private static String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
