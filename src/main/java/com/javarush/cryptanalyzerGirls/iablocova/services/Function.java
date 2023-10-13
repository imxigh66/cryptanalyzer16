package com.javarush.cryptanalyzerGirls.iablocova.services;

import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;

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
}