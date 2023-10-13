package com.javarush.cryptanalyzerGirls.iablocova.services;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;

public class UnsupportedFunction implements Function{
    @Override
    public Result execute (String[] parameters){
        return new Result(ResultCode.ERROR);
    }
}