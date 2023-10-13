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
    public Result execute (String[] parameters){

        try (BufferedReader reader = new BufferedReader(new FileReader(parameters[1]))) {
            StringBuilder encryptedText = new StringBuilder();

            int  keyForDecode = Integer.parseInt (parameters[2]);
            int character;
            while ((character = reader.read()) != -1) {
                int currentIndex = CryptoAlphabet.ALPHABET.indexOf((char)character);
                char encryptedChar = (char) character;


//                if (currentIndex != -1) {// если этот символ есть в нашем алфавите
//                    if (currentIndex + keyForEncode < CryptoAlphabet.lengthOfAlphabet) {
//                        encryptedChar = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode);
//                    } else if (currentIndex + keyForEncode >= CryptoAlphabet.ALPHABET.length())
//                        encryptedChar = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode - CryptoAlphabet.lengthOfAlphabet);
//                }
//

                if (currentIndex != -1) {// если этот символ есть в нашем алфавите
                    int newIndex = currentIndex - keyForDecode;
                    if (newIndex >= 0) {
                        encryptedChar = CryptoAlphabet.ALPHABET.charAt(newIndex);
                    } else if (newIndex < 0)
                        encryptedChar = CryptoAlphabet.ALPHABET.charAt(newIndex + CryptoAlphabet.lengthOfAlphabet);
                }

                encryptedText.append(encryptedChar);
            }
//        return null;

            try (FileWriter fileWriter = new FileWriter(parameters[3])) {
                fileWriter.write(encryptedText.toString());
            } catch (IOException e) {
                System.out.println(EXCEPTION + e.getMessage());
            }
        } catch (Exception e){
            return new Result(ResultCode.ERROR, new ApplicationException("Encode operation finish with exception ", e));
        }
        return new Result(ResultCode.OK);}
}