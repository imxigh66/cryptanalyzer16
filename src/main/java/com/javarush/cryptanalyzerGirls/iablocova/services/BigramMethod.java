package com.javarush.cryptanalyzerGirls.iablocova.services;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet;
import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;

import static com.javarush.cryptanalyzerGirls.iablocova.constants.Paths.bigrams;

public class BigramMethod implements Function{
    @Override
    public Result execute(String[] parameters) {

        try {
            Map<String, Integer> bigramFrequencies = loadBigramFrequencies(parameters[2]);
            File file1 = new File(parameters[1]);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            String encryptedText = readTextFromFile(file1);

            // Вывод всех возможных вариантов дешифровки
            for (int shift = 1; shift <= 32; shift++) {
                String decryptedText = decryptText(encryptedText, shift);
                System.out.println("Дешифрованный текст " + shift + ":");
                System.out.println(decryptedText);
                System.out.println(); // Добавляем пустую строку для разделения результатов
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(ResultCode.OK);
    }


    // Метод для загрузки частот биграмм из файла
    private static Map<String, Integer> loadBigramFrequencies(String filePath) {
        Map<String, Integer> frequencies = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    frequencies.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencies;
    }

//    // Метод для дешифровки текста с заданным сдвигом
//    private static String decrypt(String text, int shift) {
//        StringBuilder decryptedText = new StringBuilder();
//        for (char ch : text.toCharArray()) {
//            if (Character.isLetter(ch)) {
//                char base = (Character.isUpperCase(ch) ? 'А' : 'а');
//                int index = (ch - base - shift) % 32;
//                if (index < 0) index += 32;
//                decryptedText.append((char) (base + index));
//            } else {
//                decryptedText.append(ch);
//            }
//        }
//        return decryptedText.toString();
//    }

    static String decryptText (String textForDecode,int  keyForDecode) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < textForDecode.length(); i++) {
            encryptedText.append(decryptChar(textForDecode.charAt(i), keyForDecode));
        }

        return encryptedText.toString();
    }

    public static char decryptChar(char character, int  keyForDecode) {

        int currentIndex = CryptoAlphabet.ALPHABET.indexOf(character);
        char decryptedChar = character;

        if (currentIndex != -1) {// если этот символ есть в нашем алфавите
            int newIndex = currentIndex - keyForDecode;
            if (newIndex >= 0) {
                decryptedChar = CryptoAlphabet.ALPHABET.charAt(newIndex);
            } else if (newIndex < 0)
                decryptedChar = CryptoAlphabet.ALPHABET.charAt(newIndex + CryptoAlphabet.lengthOfAlphabet);
        }

        return decryptedChar;
    }
}






