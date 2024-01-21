package com.javarush.cryptanalyzerGirls.iablocova.services;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;

import java.io.*;

import static com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet.ALPHABET_FOR_STATISTICAL_ANALYSIS;
public class EncodeForStatisticalAnalysis implements Function{

    @Override
    public Result execute(String[] parameters) {
        try {
            File fileInput = new File(parameters[1]);
            if (!fileInput.exists()) {
                fileInput.createNewFile();
            }

            File fileOutput= new File(parameters[3]);
            if (!fileOutput.exists()) {
                fileOutput.createNewFile();
            }

        convertFileToLowerCase(fileInput, fileOutput);

            String content = readTextFromFile(fileOutput);
            StringBuilder encryptedText = new StringBuilder();

            for (char character : content.toCharArray()) {
                encryptedText.append(encryptChar(character, Integer.parseInt(parameters[2])));
            }
            rewriteTextToFile(fileOutput, encryptedText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.OK);
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
    public static char encryptChar(char ch, int key) {
        int index = ALPHABET_FOR_STATISTICAL_ANALYSIS.indexOf(ch);
        if (index != -1) {
            int newIndex = (index + key) % ALPHABET_FOR_STATISTICAL_ANALYSIS.length();
            return ALPHABET_FOR_STATISTICAL_ANALYSIS.charAt(newIndex);
        } else {
            return ch; // Ñèìâîë íå íàéäåí â àëôàâèòå, îñòàâëÿåì åãî áåç èçìåíåíèé
        }
    }
}
