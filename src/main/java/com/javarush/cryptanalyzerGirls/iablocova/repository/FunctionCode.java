package com.javarush.cryptanalyzerGirls.iablocova.repository;

import com.javarush.cryptanalyzerGirls.iablocova.services.*;

public enum FunctionCode {
    /**
     * имитация базы данных
     * отправляем данные, которые получили от пользователя
     * и в зависимости от них выполняем дальнейшние наши действия
     *
     * описывает те возможности, которые может делать наша программа
     * и соответсвенно вернет необходимые инструменты, чтобы это сделать
     *
     * преобразование какой-то циферки, которую пользователь введёт преобразовать в джавовский класс
     */
    ENCODE (new Encode()),
    DECODE (new Decode()),
    BRUTE_FORCE (new BruteForce()),
    BIGRAM_METHOD (new BigramMethod()),
    ENCODE_FOR_STATISTICAL_ANALYSIS (new EncodeForStatisticalAnalysis()),
    STATISTICAL_ANALISIS(new StatisticalAnalysis()),
    UNSUPPORTED_FUNCTION (new UnsupportedFunction());

    private Function function;
    FunctionCode (Function function){ this.function = function;}
    public Function getFunction (){return function;}
}