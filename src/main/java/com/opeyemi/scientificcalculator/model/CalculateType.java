/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opeyemi.scientificcalculator.model;

/**
 *
 * @author Idris Opeyemi
 */
public class CalculateType {
    private static String type;
    private static Boolean calculated = false;
    private static Boolean shifMode = false;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        CalculateType.type = type;
    }

    public static Boolean getCalculated() {
        return calculated;
    }

    public static void setCalculated(Boolean calculated) {
        CalculateType.calculated = calculated;
    }

    public static Boolean getShifMode() {
        return shifMode;
    }

    public static void setShifMode(Boolean shifMode) {
        CalculateType.shifMode = shifMode;
    }
    
}
