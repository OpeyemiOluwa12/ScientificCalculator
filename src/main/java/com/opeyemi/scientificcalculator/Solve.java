/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opeyemi.scientificcalculator;

import com.opeyemi.scientificcalculator.model.CalculateType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Idris Opeyemi
 */
public class Solve {

    public Solve() {
    }

    public String solvePower(String calculate) {
        String[] parts = calculate.split("(?=[/×-÷+^])|(?<=[/×÷*-+^])");
        float solvedPow = (float) 0.00;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("^")) {
                int left = Integer.parseInt(parts[i - 1]);
                int right = Integer.parseInt(parts[i + 1]);
                solvedPow = (float) (Math.pow(left, right));
                int round = Math.round(solvedPow);
                calculate = calculate.replace("" + parts[i - 1] + parts[i] + parts[i + 1], "" + round);
//                System.out.println(calculate);
                break;
            }
        }
        return calculate;
    }

    public String solveNthRoot(String calculate, int power) {
        String[] parts = calculate.split("(?=[/×-÷+∛])|(?<=[/×÷*-+∛])");
        float solvedPow = (float) 0.0000;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("∛")) {
                int right = Integer.parseInt(parts[i + 1]);
                solvedPow = (float) (Math.pow(right, 1.0 / power));
                int round = Math.round(solvedPow);
                calculate = calculate.replace("" + parts[i] + parts[i + 1], "" + round);

                System.out.println(calculate);
                break;
            }
        }
        return calculate;
    }

    public void solve() {
        String calculate = Screen.getTypeField().getText();
        if (calculate.contains("^")) {
            calculate = solvePower(calculate);
        } else if (calculate.contains("∛")) {
            calculate = solveNthRoot(calculate, 3);
        }
        calculate = calculate.replace("√", "Math.sqrt").
                replace("÷", "/").
                replace("%", "*1/100").
                replace("π", "Math.PI").
                replace("×", "*");
        System.out.println(calculate);
        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

        try {
            // Evaluate the expression
            Object answer = engine.eval(calculate);
            Screen.getResultList().add(answer.toString());
            Screen.getResult().setText(answer.toString());
            CalculateType.setCalculated(Boolean.TRUE);
        } catch (ScriptException e) {
            // Something went wrong
            Screen.getResultList().add("SYNTAX ERROR");
            Screen.getResult().setText("SYNTAX ERROR");
            CalculateType.setCalculated(Boolean.TRUE);
            e.printStackTrace();
        }
    }

    public void solveScientific() {
        String calculate = Screen.getTypeField().getText();
        if (calculate.contains("sin-1") || calculate.contains("cos-1") || calculate.contains("tan-1")) {
            solveInverse();
            return;
        }
        if (calculate.contains("^")) {
            calculate = solvePower(calculate);
        }
        calculate = calculate.replace("sin-1", "Math.asin(").
                replace("cos-1", "Math.acos(").
                replace("tan-1", "Math.atan(").
                replace("sin", "Math.sin(").
                replace("cos", "Math.cos(").
                replace("tan", "Math.tan(").
                replace("√", "Math.sqrt").
                replace("∛", "Math.cbrt").
                replace("π", "Math.PI").
                replace(")", "*Math.PI)/180)").
                replace("÷", "/").
                replace("%", "*1/100").
                replace("×", "*").
                replace("log", "Math.log(");
        System.out.println(Math.log(10));

        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

        try {
            // Evaluate the expression
            Object answer = engine.eval(calculate);
            Screen.getResultList().add(answer.toString());
            Screen.getResult().setText(answer.toString());
            CalculateType.setCalculated(Boolean.TRUE);
            CalculateType.setType("Normal");
        } catch (ScriptException e) {
            // Something went wrong
            Screen.getResultList().add("SYNTAX ERROR");
            Screen.getResult().setText("SYNTAX ERROR");
            CalculateType.setCalculated(Boolean.TRUE);
            e.printStackTrace();
        }
    }

    public void solveInverse() {
        String calculate = Screen.getTypeField().getText();
        if (calculate.contains("^")) {
            calculate = solvePower(calculate);
        }
        calculate = calculate.replace("sin-1", "180/Math.PI*(Math.asin").
                replace("cos-1", "180/Math.PI*(Math.acos").
                replace("tan-1", "180/Math.PI*(Math.atan").
                replace("√", "Math.sqrt").
                replace("∛", "StrictMath.cbrt").
                replace("π", "Math.PI").
                replace(")", "))").
                replace("÷", "/").
                replace("%", "*1/100").
                replace("×", "*").
                replace("log", "Math.log(");
        System.out.println(calculate);
        Math.cbrt(0);

        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

        try {
            // Evaluate the expression
            Object answer = engine.eval(calculate);
            Screen.getResultList().add(answer.toString());
            Screen.getResult().setText(answer.toString());
            CalculateType.setCalculated(Boolean.TRUE);
            CalculateType.setType("Normal");
        } catch (ScriptException e) {
            // Something went wrong
            Screen.getResultList().add("SYNTAX ERROR");
            Screen.getResult().setText("SYNTAX ERROR");
            CalculateType.setCalculated(Boolean.TRUE);
            e.printStackTrace();
        }
    }

    public void solveFactorial() {
        int fac = Integer.parseInt(Screen.getTypeField().getText());
        fac = factorial(fac);
        Screen.getResult().setText("" + fac);
        CalculateType.setCalculated(Boolean.TRUE);
        Screen.getResultList().add(fac + "");
    }

    public int factorial(int value) {
        for (int i = (value - 1); i > 0; i--) {
            value = value * i;
        }
        return value;
    }

    public void combination() {
        String val[] = Screen.getTypeField().getText().split("(?=[C])|(?<=[C])");
        int nValue = Integer.parseInt(val[0]);
        int rValue = Integer.parseInt(val[2]);
        int nFactorial = factorial(nValue);
        int rFactorial = factorial(rValue);
        int nMinusRFactorial = factorial(nValue - rValue);
        int comb = nFactorial / (rFactorial * (nMinusRFactorial));
        Screen.getResultList().add(comb + "");
        CalculateType.setCalculated(Boolean.TRUE);
        Screen.getResult().setText("" + comb);

    }

    public void permutation() {
        String val[] = Screen.getTypeField().getText().split("(?=[P])|(?<=[P])");
        int nValue = Integer.parseInt(val[0]);
        int rValue = Integer.parseInt(val[2]);
        int nFactorial = factorial(nValue);
        int nMinusRFactorial = factorial(nValue - rValue);
        int perm = nFactorial / (nMinusRFactorial);
        Screen.getResultList().add(perm + "");
        CalculateType.setCalculated(Boolean.TRUE);
        Screen.getResult().setText("" + perm);

    }
}
