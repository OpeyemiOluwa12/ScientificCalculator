/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Idris Opeyemi
 */
public class calculateType {
    private static String type;
    private static Boolean calculated = false;
    private static Boolean shifMode = false;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        calculateType.type = type;
    }

    public static Boolean getCalculated() {
        return calculated;
    }

    public static void setCalculated(Boolean calculated) {
        calculateType.calculated = calculated;
    }

    public static Boolean getShifMode() {
        return shifMode;
    }

    public static void setShifMode(Boolean shifMode) {
        calculateType.shifMode = shifMode;
    }
    
}
