package com.crowdfunding.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Utils {

    public static String getClauseWhere(Map<String, String> params) {
        String where = "";

        for (String key : params.keySet()) {

            if (key.equals("where")) {

                if (params.get("where").equals("")) {

                    where = "";
                } else {

//                    where = " where " +  params.get("where").replace("," , " and ");
                    where = params.get("where");
                }


            }
        }

        return where;
    }

}
