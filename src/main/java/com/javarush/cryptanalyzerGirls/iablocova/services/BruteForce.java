package com.javarush.cryptanalyzerGirls.iablocova.services;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import static com.javarush.cryptanalyzerGirls.iablocova.constants.ApplicationCompletionConstants.EXCEPTION;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BruteForce implements Function{
    @Override
    public Result execute(String[] parameters) {
        return null;
    }
}


//public class BruteForce {
//
////    private static final CryptoAlphabet ALPHABET = new CryptoAlphabet();
//    private static int key;
//
////    public static void main(String[] args) throws IOException {
//        File file = new File("D:\\2)STUDY\\KURSOV\\proba\\proektik\\src\\services\\input.txt");
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes);
//        inputStream.close();
//
//        String encryptedText = new String(bytes, StandardCharsets.UTF_8);
//
//        // Ввод ключа шифрования
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Введите ключ шифрования от 0 до 25:");
//        key = scanner.nextInt();
//        scanner.close();
//
//        // Расшифровка текста с указанным ключом
//        String decryptedText = encrypt(encryptedText, key);
//
//        // Проверка осмысленности расшифрованного текста
//        if (isMeaningful(decryptedText)) {
//            // Вывод расшифрованного текста в файл
//            File outputFile = new File("D:\\2)STUDY\\KURSOV\\proba\\proektik\\src\\services\\output.txt");
//            try (PrintWriter writer = new PrintWriter(outputFile, StandardCharsets.UTF_8)) {
//                writer.println("Зашифрованный текст осмысленный: " + decryptedText);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Зашифрованный текст осмысленный также записан в файл output.txt");
//        } else {
//            System.out.println("Зашифрованный текст не осмысленный: " + decryptedText);
//        }
//
//
//    public static String encrypt( String encryptedText, int key) {
//        StringBuilder encryptedTextBuilder = new StringBuilder();
//        for (int i = 0; i < encryptedText.length(); i++) {
//            char c = encryptedText.charAt(i);
//            char base = Character.isUpperCase(c) ? 'А' : 'а';
//            if (Character.isLetter(c) && Character.isLetter(base)) {
//                char encryptedChar = (char) ((c - base + key + 33) % 33 + base); // 33 - количество букв в кириллице
//                encryptedTextBuilder.append(encryptedChar);
//            } else {
//                encryptedTextBuilder.append(c);
//            }
//        }
//        return encryptedTextBuilder.toString();
//    }
//
//    private static boolean isMeaningful(String text) {
//        return text.length() > 10 && text.contains("я") && text.contains("ты") && text.contains("это") && !text.matches("[0-9]+");
//    }
//}