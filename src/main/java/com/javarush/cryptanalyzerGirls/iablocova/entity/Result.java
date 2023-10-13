package com.javarush.cryptanalyzerGirls.iablocova.entity;

import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;
import com.javarush.cryptanalyzerGirls.iablocova.exception.ApplicationException;

public class Result {
    /**
     * понять как всё прошло - хорошо или плохо
     */
    private ResultCode resultCode;
    private ApplicationException applicationException;
    public Result (ResultCode resultCode){ this.resultCode = resultCode;}
    public Result (ResultCode resultCode, ApplicationException applicationException){
        this.resultCode = resultCode;
        this.applicationException = applicationException;
    }
    public ResultCode getResultCode(){
        return resultCode;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }
}