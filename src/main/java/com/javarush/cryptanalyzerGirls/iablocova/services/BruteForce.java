package com.javarush.cryptanalyzerGirls.iablocova.services;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.javarush.cryptanalyzerGirls.iablocova.constants.CryptoAlphabet.lengthOfAlphabet;
import static com.javarush.cryptanalyzerGirls.iablocova.constants.Paths.tmpFileForBruteForce;
public class BruteForce implements Function {


    @Override
    public Result execute(String[] parameters) {
        String fileOutputOriginal = parameters[3];
        parameters[3] = tmpFileForBruteForce;

       //перебираем ключи, сравниваем по количеству совпавших регулярок - сохраняем вариант, где наибольшее количество совпрадений

        int key =0;
        int maxMatch =0;
        Decode decoder = new Decode();
        for (int i = 0; i < lengthOfAlphabet; i++) {//количество возможных ключей = количеству букв в алфавите
            parameters[2] = Integer.toString(i);
            decoder.execute(parameters);

            try {
                File fileForMatches = new File(parameters[3]);
                if (!fileForMatches.exists()) {
                    fileForMatches.createNewFile();
                }
                String textForMatches = readTextFromFile(fileForMatches);

                //ищем количество совпавших регулярок
                if (countMatches(textForMatches) > maxMatch){  //если это количество больше прошлого макс -> сохраняем этот ключ расшифровки
                    maxMatch = countMatches(textForMatches);
                    key = i; // в итоге имеем ключ с наиболее подходящим шаблоном расшифровки
                }

            } catch (IOException e){
                e.printStackTrace();
            }
        }

        //выбрали подходящие параметры и дешифруем
        parameters[2] = Integer.toString(key);
        parameters[3] = fileOutputOriginal;
        decoder.execute(parameters);

        return new Result(ResultCode.OK);
    }

    private int countMatches (String stringForMatches){

        int maxLen =0; //максимальная длина совпадений

        Pattern pattern = Pattern.compile("^[А-ЯЁ]{1}(,|[а-яё])*(\\s)*( [а-яё]+,?)*([\\.\\?!])$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(stringForMatches);

        while (matcher.find()) {
            if (matcher.group().length() > maxLen) {
                maxLen = matcher.group().length();
            }
        }

        return maxLen;
    }

}
