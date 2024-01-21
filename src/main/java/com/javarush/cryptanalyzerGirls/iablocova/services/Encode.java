package com.javarush.cryptanalyzerGirls.iablocova.services;

import java.io.*;

import com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet;
import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.exception.ApplicationException;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;
//Ева
import static com.javarush.cryptanalyzerGirls.iablocova.constants.ApplicationCompletionConstants.EXCEPTION;

public class Encode implements Function {
    @Override
    public Result execute(String[] parameters) {
        try{
        File file1 = new File(parameters[1]);
        if (!file1.exists()) { file1.createNewFile();}

        String textForEncode = readTextFromFile(file1);

        int  keyForEncode = Integer.parseInt (parameters[2]);
        String encodedText = encryptText (textForEncode, keyForEncode);

        File file2 = new File(parameters[3]);
        if (!file2.exists()) { file2.createNewFile();}
        rewriteTextToFile (file2, encodedText);
        }
        catch (IOException e){
            System.out.println(EXCEPTION + e.getMessage());
            return new Result(ResultCode.ERROR);
        }

        return new Result(ResultCode.OK);
    }


    public static char encryptChar(char character, int  keyForEncode) {

        int currentIndex = CryptoAlphabet.ALPHABET.indexOf(character);
        char encryptedChar = character;

        if (currentIndex != -1) {// если этот символ есть в нашем алфавите
            if (currentIndex + keyForEncode < CryptoAlphabet.lengthOfAlphabet) {
                encryptedChar = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode);
            } else if (currentIndex + keyForEncode >= CryptoAlphabet.ALPHABET.length())
                encryptedChar = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode - CryptoAlphabet.lengthOfAlphabet);
        }

        return encryptedChar;
    }

    static String encryptText (String textForEncode,int  keyForEncode) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < textForEncode.length(); i++) {
            encryptedText.append(encryptChar(textForEncode.charAt(i), keyForEncode));
        }

        return encryptedText.toString();
    }

    public void convertFileToLowerCase(File inputFile, File outputFile) {
        try {
            String content = readTextFromFile(inputFile);
            String convertedContent = content.toLowerCase();
            rewriteTextToFile(outputFile, convertedContent);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
