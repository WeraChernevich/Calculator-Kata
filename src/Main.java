import java.io.IOException;
import java.util.Scanner;
import Converter.Converter;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите два числа: ");
        String input = sc.nextLine();
        String result = calc(input);
        System.out.println("Результат: " + result);
    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "*", "/"};
        String[] regexActions = {"\\+", "-", "\\*", "/"};
        int actionsIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionsIndex = i;
                break;
            }
        }
        if (actionsIndex == -1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("формат математической операции не удовлетворяет заданию - выбор оператора (+, -, /, *)");
            }
        }
        String[] parts = input.split(regexActions[actionsIndex]);
        if (parts.length!=2) {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        }
        int num1;
        int num2;
        int result = 0;
        if (converter.isRoman(parts[0]) == converter.isRoman(parts[1])) {
            boolean isRoman = converter.isRoman(parts[0]);

            if (isRoman) {
                num1 = converter.romanToInt(parts[0]);
                num2 = converter.romanToInt(parts[1]);

            } else {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[1]);
            }

            switch (actions[actionsIndex]) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("формат математической операции не удовлетворяет заданию - числа от 1 до 10");
                    }
                    break;
            }
            if (num1 > 10 || num2 > 10) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("формат математической операции не удовлетворяет заданию - числа от 1 до 10");
                }
            }

            else if (isRoman) {
                return String.valueOf(converter.intToRoman(result));
            }
            else {
                return String.valueOf((result));
            }
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("используются одновременно разные системы счисления");
            }
        }
        return String.valueOf(result);
    }
}