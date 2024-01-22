package com.javarush.cryptanalyzerGirls.iablocova.app;

import com.javarush.cryptanalyzerGirls.iablocova.controller.MainController;
import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.repository.FunctionCode;
import com.javarush.cryptanalyzerGirls.iablocova.services.Function;

import static com.javarush.cryptanalyzerGirls.iablocova.constants.FunctionCodeConstants.*;


public class Application {
    /**
     * "машина"
     */
    private final MainController mainController;

    public Application (MainController mainController){
        this.mainController = mainController;
    }

    public Result run(){
        String[] parameters = mainController.getView().getParameters();
        String mode = parameters [0]; //выбор режима работы
        Function function = getFunction (mode);
        return function.execute(parameters);
    }

    private Function getFunction (String mode){
        return switch (mode){
            case "1" -> FunctionCode.valueOf (ENCODE).getFunction();
            case "2" -> FunctionCode.valueOf (DECODE).getFunction();
            case "3" -> FunctionCode.valueOf(BRUTE_FORCE).getFunction();
            case "4" -> FunctionCode.valueOf(ENCODE_FOR_STATISTICAL_ANALYSIS1).getFunction();
            case "5" -> FunctionCode.valueOf(STATISTICAL_ANALYSIS).getFunction();
            case "6" -> FunctionCode.valueOf(BIGRAM_METHOD).getFunction();
            default -> FunctionCode.valueOf (UNSUPPORTED_FUNCTION).getFunction();
        };
    }

    public void printResult (Result result){
        mainController.getView().printResult(result);
    }
}