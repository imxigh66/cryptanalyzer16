package com.javarush.cryptanalyzerGirls.iablocova.view;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;

public interface View {
    public String[] getParameters();
    public void printResult(Result result);
    public boolean repeat();

}
