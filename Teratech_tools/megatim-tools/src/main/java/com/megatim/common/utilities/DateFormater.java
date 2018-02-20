/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author BAKARI
 */
public class DateFormater {

    public static String DateFormater(Date date, Locale pays) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:MM", Locale.FRENCH);
        return simpleDateFormat.format(date);
    }
}
