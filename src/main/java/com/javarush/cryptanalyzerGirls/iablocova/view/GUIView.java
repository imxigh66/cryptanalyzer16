package com.javarush.cryptanalyzerGirls.iablocova.view;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;

public class GUIView implements View{
    @Override
    public String[] getParameters() {
        return new String[0];
    }

    @Override
    public void printResult(Result result) {
    }

    @Override
    public boolean repeat() {
        return false;
    }
}
