package com.javarush.cryptanalyzer.iablocova;

import com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet;
import org.w3c.dom.ls.LSOutput;

import java.lang.String;
import java.util.Scanner;

public class tmp {
    public static final int lengthOfAlphabet = CryptoAlphabet.ALPHABET.length();
    public static void main(String[] args) {
        // main controller ±
        boolean d = true;
        while (d) {
            System.out.println("Choose what interface do you want to use?");
            System.out.println("1 - console");
            System.out.println("2 - Graphic User Interface");
            System.out.println("0 - End use");
            int t = 0;
            switch (t) {
                case 1: //functionConsole.run();
                    d = false;
                    break;
                case 2: // functionGUI.run();
                    d = false;
                    break;
                case 0: //finish programm
                    d = false;
                    break;
                default:
                    System.out.println("Не предусмотренный режим. Повторите попытку!");
            }
        }


        Scanner scanner = new Scanner (System.in);
        int keyForEncode=0;
        boolean t = true;
        while (t){
            System.out.println("Введите ключ для кодирования:");
            if (scanner.hasNextInt()) {keyForEncode = scanner.nextInt();
                keyForEncode = keyForEncode % lengthOfAlphabet; // целое число смещений
                t = false; }
            else System.out.println("Введите целое число");
        }

        System.out.println("Введите строку для кодирования:");
        String exmpOfString = scanner.nextLine();

        scanner.close();

        //Encode(exmpOfString, keyForEncode);

        Decode((Encode(exmpOfString, keyForEncode)), keyForEncode);

    }

    //Encode
    //I have:  right text - input.txt
    // key for decode - int
    // Should get: cifertext - output.txt


    public static String Encode (String strForEncode, int keyForEncode) {
        char[] newStrForEncode = strForEncode.toCharArray();
        System.out.println("start Encode" + strForEncode);

        //идем по строке
        int count = strForEncode.length();
        for (int i = 0; i < count; i++) {
            int currentIndex = CryptoAlphabet.ALPHABET.indexOf(newStrForEncode[i]);
            if (currentIndex != -1) {// если этот символ есть в нашем алфавите
                if (currentIndex + keyForEncode < lengthOfAlphabet) {
                    newStrForEncode[i] = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode + 1);
                } else if (currentIndex + keyForEncode >= CryptoAlphabet.ALPHABET.length())
                    newStrForEncode[i] = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode + 1 - lengthOfAlphabet);
            }
        }

        strForEncode ="";
        for (int i = 0; i < count; i++) {
            strForEncode += newStrForEncode[i];
        }

        System.out.println("Finish Encode " + strForEncode);
        return strForEncode;
    };

    //Decode
    //I have: cifertext- input.txt
    // key for decode - int
    // Should get: right text - output.txt

    public static String Decode (String ExampleOfFile, int keyForEncode){

        System.out.println("start Decode " + ExampleOfFile);
        int count = ExampleOfFile.length();
        char[] newExampleOfFile = ExampleOfFile.toCharArray();
        for (int i = 0; i < count; i++) {
            int currentIndex = CryptoAlphabet.ALPHABET.indexOf(newExampleOfFile[i]);
            if (currentIndex != -1) {// если этот символ есть в нашем алфавите
                int newIndex = currentIndex - keyForEncode - 1;
                if (newIndex >= 0)
                    newExampleOfFile[i] = CryptoAlphabet.ALPHABET.charAt(newIndex);
                else if (newIndex < 0)
                    newExampleOfFile[i] = CryptoAlphabet.ALPHABET.charAt(lengthOfAlphabet + newIndex);
            }
        }

        ExampleOfFile ="";
        for (int i = 0; i < count; i++) {
            ExampleOfFile += newExampleOfFile[i];
        }

        System.out.println("Finish Decode " + ExampleOfFile);

        return ExampleOfFile;

    };

    public int BrutForce (String strForBruteForce){
        //брут форс перебираем ключи, сравниваем по количеству совпавших регулярок - сохраняем вариант где наибольшее количство совпрадений
        // инкод, декод с файла
        //регулярки


        int key =0;
        for (int i = 0; i < lengthOfAlphabet; i++) {//количество возможных ключей = количеству букв в алфавите
            Decode (strForBruteForce, i);
            //ищем количество совпавших регулярок
            //если это количсетво больше прошлого макс -> сохраняем этот ключ расшифровки
            // в итоге имеем ключ с наиболее подходящим шаблоном расшифровки
            // выводим декод с этим ключом

        }


        return key;
    }


}