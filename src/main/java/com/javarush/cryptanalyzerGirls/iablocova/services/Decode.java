package com.javarush.cryptanalyzerGirls.iablocova.services;
// Ева
import java.io.*;

import com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet;
import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.exception.ApplicationException;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;

import static com.javarush.cryptanalyzerGirls.iablocova.constants.ApplicationCompletionConstants.EXCEPTION;

public class Decode implements Function{
    @Override
    public Result execute(String[] parameters) {
        try{
            File file1 = new File(parameters[1]);
            if (!file1.exists()) { file1.createNewFile();}

            String textForDecode = readTextFromFile(file1);
            int  keyForDecode = Integer.parseInt (parameters[2]);
            String decodedText = decryptText (textForDecode, keyForDecode);

            File file2 = new File(parameters[3]);
            if (!file2.exists()) { file2.createNewFile();}
            rewriteTextToFile (file2, decodedText);
        }
        catch (IOException e){
            System.out.println(EXCEPTION + e.getMessage());
            return new Result(ResultCode.ERROR);
        }

        return new Result(ResultCode.OK);
    }

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
