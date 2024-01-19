//package com.javarush.cryptanalyzerGirls.iablocova.view;
//
//import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
//
//public class ConsoleView implements View{
//    @Override
//    public String[] getParameters() {
//        return new String[0];
//    }
//
//    @Override
//    public Result printResult(Result result) {
//        return null;
//    }
//
//    @Override
//    public boolean repeat() {
//        return false;
//    }
//}
//

package com.javarush.cryptanalyzerGirls.iablocova.view;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

import static com.javarush.cryptanalyzerGirls.iablocova.constants.ApplicationCompletionConstants.EXCEPTION;
import static com.javarush.cryptanalyzerGirls.iablocova.constants.ApplicationCompletionConstants.KEY;
import static com.javarush.cryptanalyzerGirls.iablocova.constants.ApplicationCompletionConstants.SUCCESS;
import static com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet.lengthOfAlphabet;


public class ConsoleView implements View {

    @Override
    public String[] getParameters() {
        int k = 5;
        String[] parameters = new String[k];
        boolean t = true;
        Scanner scanner2 = new Scanner(System.in);
        while (t) {
            System.out.println("Выберите режим, который вы ходите использовать");
            System.out.println("1 - зашифровать файл");
            System.out.println("2 - расшифровать файл (с ключом)");
            System.out.println("3 - BruteForce");
            System.out.println("4 - Статистический анализ");
            System.out.println("5 - BigramMethod");
            parameters[0] = scanner2.nextLine();

            switch (parameters[0]) {
                case "1":
                    System.out.println("Введите путь файла, который вы хотите зашифровать или нажмите Enter для выбора файла по умолчанию (input.txt)");
                    t = false;
                    parameters[1] = scanner2.nextLine();
//                    scanner.close();
                    if (parameters[1].isEmpty()) parameters[1] = "input.txt";
                    break;
                case "2", "3", "4", "5":
                    System.out.println("Введите путь файла, который вы хотите расшифровать или нажмите Enter для выбора файла по умолчанию (encoded.txt)");
                    t = false;
                    parameters[1] = scanner2.nextLine();
//                    scanner.close();
                    if (parameters[1].isEmpty()) parameters[1] = "encoded.txt";
                    break;
                default:
                    System.out.println("Нет такого режима. Попробуйте ещё раз");
            }
        }

        t = true;
        while (t) {
            try {
                switch (parameters[0]) {
                    case "1":
                        System.out.println("Введите ключ для шифровки или нажмите Enter для случайного генерирования ключа");
                        parameters[2] = scanner2.nextLine();

                        if (parameters[2].isEmpty()) {
                            Random random = new Random();
                            parameters[2] = String.valueOf(random.nextInt(lengthOfAlphabet - 1 - 0 + 1) + 0);
                            System.out.println(KEY + parameters[2]);
                        }

                        System.out.println("Выберите в какой файл записать результат или нажмите Enter для выбора файла по умолчанию (encoded.txt)");
                        parameters[3] = scanner2.nextLine();
                        if (parameters[3].isEmpty()) parameters[3] = "encoded.txt";

                        System.out.println("Будет ли этот файл использован для расшифровки статистическим анализом?");
                        System.out.println("1 - да");
                        System.out.println("2 - нет");

                        boolean n = true;
                        while (n) {
                            int answer = scanner2.nextInt();

                            try {
                                switch (answer) {
                                    case 1:
                                        parameters[4] = "1";
                                        n= false;
                                        break;
                                    case 2:
                                        parameters[4] = "0";
                                        n= false;
                                        break;
                                    default:
                                        System.out.println("Нет такого ответа. Попробуйте ещё раз");
                                }
                            } catch (NumberFormatException e) {
                            }
                        }
                        t = false; //пока не введут число не закончится цикл
                        break;
                    case "2":
                        System.out.println("Введите ключ для расшифровки");
                        parameters[2] = scanner2.nextLine();
                        System.out.println("Выберите в какой файл записать результат или нажмите Enter для выбора файла по умолчанию (output.txt)");
                        parameters[3] = scanner2.nextLine();
                        if (parameters[3].isEmpty()) parameters[3] = "output.txt";
                        break;
                        //case 3 4 5
                    default:
                        break;
                }
                t = false; //пока не введут число не закончится цикл
            } catch (NumberFormatException e) {
            }
        }

//        scanner.close();
        return parameters;
    }

    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }

    @Override
    public boolean repeat() {
        boolean t = true;
        boolean re = false;
        while (t) {
            System.out.println("Ходите продолжить выполнение программы?");
            System.out.println("1 - продолжить");
            System.out.println("2 - остановить");

            int k = 0;
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNextInt()) {
                k = scanner1.nextInt();
            } else {
                System.out.println("Введено не целое число.");
            }
            scanner1.close();

            switch (k) {
                case 1:
                    t = false;
                    re = true;
                    break;
                case 2:
                    t = false;
                    re = false;
                    break;
                default:
                    t = true;
            }
        }

        return re;//никогда не выполниться
    }
}