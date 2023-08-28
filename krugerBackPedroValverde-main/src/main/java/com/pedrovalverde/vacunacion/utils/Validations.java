package com.pedrovalverde.vacunacion.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validations {
    public static final String REGEX_MAIL_VALIDATION = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGEX_NAME_VALIDATION = "^[a-zA-Z ]+$";
    public static final String DATE_FORMAT= "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT= "dd/MM/yyyy HH:mm:ss";

    public static Date parseDate(String dateStr) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
    }

}
