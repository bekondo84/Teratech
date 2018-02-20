/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import java.util.GregorianCalendar;

/**
 *Determiner si une annee est bissextile ou non
 * @author DEV
 */
public class IsLeapYear {

    public static boolean IsLeapYear(int year) {
        GregorianCalendar cal = new GregorianCalendar();
        return cal.isLeapYear(year);
    }
}
