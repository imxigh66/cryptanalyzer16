package com.javarush.cryptanalyzerGirls.iablocova.services;

import java.io.*;

import com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet;
import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.exception.ApplicationException;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;
//Ева
import static com.javarush.cryptanalyzerGirls.iablocova.constants.ApplicationCompletionConstants.EXCEPTION;

public class Encode implements Function{
    @Override
    public Result execute (String[] parameters){

        try (BufferedReader reader = new BufferedReader(new FileReader(parameters[1]))) {
            StringBuilder encryptedText = new StringBuilder();

            int  keyForEncode = Integer.parseInt (parameters[2]);
            int character;
            while ((character = reader.read()) != -1) {
                int currentIndex = CryptoAlphabet.ALPHABET.indexOf((char)character);
                char encryptedChar = (char) character;

                if (currentIndex != -1) {// если этот символ есть в нашем алфавите
                    if (currentIndex + keyForEncode < CryptoAlphabet.lengthOfAlphabet) {
                        encryptedChar = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode);
                    } else if (currentIndex + keyForEncode >= CryptoAlphabet.ALPHABET.length())
                        encryptedChar = CryptoAlphabet.ALPHABET.charAt(currentIndex + keyForEncode - CryptoAlphabet.lengthOfAlphabet);
                }

                encryptedText.append(encryptedChar);
            }
//        return null;

            try {
                File file = new File(parameters[3]);
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter writer = new FileWriter(file, false);
                writer.write(encryptedText.toString());
                writer.write(System.lineSeparator());
                writer.close();

            } catch (IOException e) {
                System.out.println(EXCEPTION + e.getMessage());
            }
        } catch (Exception e){
            return new Result(ResultCode.ERROR, new ApplicationException("Encode operation finish with exception ", e));
        }
        return new Result(ResultCode.OK);}
}