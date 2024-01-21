package com.javarush.cryptanalyzerGirls.iablocova.services;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;

import java.io.*;

public interface Function {
    /**
     *
     * получили инфорацию из базы данных о том, что нужно сделать
     * сделали
     * вернулись обратно в mainController
     *
     * @param parameters
     * @return
     */
    Result execute (String[] parameters);

    default String readTextFromFile(File fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    default void rewriteTextToFile(File fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write(content);
        }
    }

    default void convertFileToLowerCase(File inputFile, File outputFile) {
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