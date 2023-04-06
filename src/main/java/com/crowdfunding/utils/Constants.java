package com.crowdfunding.utils;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static final String API_version = "api/v1/";
    public static final String API_USER = API_version+"user/";
    public static final String API_CAMPAIGN = API_version+"campaign/";
    public static final String API_CAMPAIGN_IMAGE = API_version+"campaignimage/";
    public static final String API_TRANSACTION = API_version+"transaction/";
    public static final String API_TEST = API_version+"test/";

    public static final String TOKEN = "token";
    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String TOTAL = "total";
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";
    public static final String NOTFOUND = "not found";
    public static final String LIST = "list";

    public static final String STATUS_CODE = "statusCode";
    public static final String ORDER = "order";
    public static final String PARAM = "param";
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";
    public static final String SUCCESS_CODE = "00";
    public static final String FAILED_CODE = "01";
    public static final String TOKEN_FAILED = "02";

}
