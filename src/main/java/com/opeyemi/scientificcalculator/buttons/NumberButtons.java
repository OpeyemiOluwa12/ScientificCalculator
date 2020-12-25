/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opeyemi.scientificcalculator.buttons;

import com.jfoenix.controls.JFXButton;
import com.opeyemi.scientificcalculator.ScientificCalculator;
import com.opeyemi.scientificcalculator.Screen;
import com.opeyemi.scientificcalculator.Solve;
import com.opeyemi.scientificcalculator.model.CalculateType;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 *
 * @author Idris Opeyemi
 */
public class NumberButtons {

    HBox row1;
    HBox row2;
    HBox row3;

    public NumberButtons() {
        row1 = new HBox(10);
        row1.setPrefWidth(350);;
        row1.setPadding(new Insets(0, 5, 5, 5));

        row2 = new HBox(10);
        row2.setPrefWidth(350);;
        row2.setPadding(new Insets(10, 5, 5, 5));

    }

    public HBox numbers() {
        HBox hbox = new HBox();
        return hbox;
    }

    public HBox row4() {
        HBox row4 = new HBox(10);
        row4.setPrefWidth(350);;
        row4.setPadding(new Insets(0, 5, 5, 5));
        row4.getChildren().addAll(zero(), point(), exp(), ans(), equals());
        return row4;
    }

    public HBox row3() {
        HBox row3 = new HBox(10);
        row3.setPrefWidth(350);;
        row3.setPadding(new Insets(0, 5, 5, 5));
        row3.getChildren().addAll(one(), two(), three(), plus(), minus());
        return row3;
    }

    public HBox row2() {
        HBox row2 = new HBox(10);
        row2.setPrefWidth(350);;
        row2.setPadding(new Insets(10, 5, 5, 5));
        row2.getChildren().addAll(four(), five(), six(), multiply(), divide());
        return row2;
    }

    public HBox row1() {
        HBox row1 = new HBox(10);
        row1.setPrefWidth(350);;
        row1.setPadding(new Insets(0, 5, 10, 5));
        row1.getChildren().addAll(seven(), eight(), nine(), del(), ac());
        return row1;
    }

    public JFXButton zero() {
        JFXButton zero = new JFXButton("0");
        zero.setButtonType(JFXButton.ButtonType.RAISED);
        zero.getStyleClass().add("numButton");
        zero.setTextFill(Color.WHITE);
        zero.setPrefWidth(row1.getPrefWidth() / 5);
        zero.setPrefHeight(25);
        zero.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("0");
        });
        return zero;
    }

    public JFXButton one() {
        JFXButton one = new JFXButton("1");
        one.setButtonType(JFXButton.ButtonType.RAISED);
        one.getStyleClass().add("numButton");
        one.setTextFill(Color.WHITE);
        one.setPrefWidth(row1.getPrefWidth() / 5);
        one.setPrefHeight(25);
        one.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("1");
        });
        return one;
    }

    public JFXButton two() {
        JFXButton two = new JFXButton("2");
        two.setButtonType(JFXButton.ButtonType.RAISED);
        two.getStyleClass().add("numButton");
        two.setTextFill(Color.WHITE);
        two.setPrefWidth(row1.getPrefWidth() / 5);
        two.setPrefHeight(25);
        two.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("2");
        });
        return two;
    }

    public JFXButton three() {
        JFXButton three = new JFXButton("3");
        three.setButtonType(JFXButton.ButtonType.RAISED);
        three.getStyleClass().add("numButton");
        three.setTextFill(Color.WHITE);
        three.setPrefWidth(row1.getPrefWidth() / 5);
        three.setPrefHeight(25);
        three.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("3");
        });
        return three;
    }

    public JFXButton four() {
        JFXButton four = new JFXButton("4");
        four.setButtonType(JFXButton.ButtonType.RAISED);
        four.getStyleClass().add("numButton");
        four.setTextFill(Color.WHITE);
        four.setPrefWidth(row1.getPrefWidth() / 5);
        four.setPrefHeight(25);
        four.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("4");
        });
        return four;
    }

    public JFXButton five() {
        JFXButton five = new JFXButton("5");
        five.setButtonType(JFXButton.ButtonType.RAISED);
        five.getStyleClass().add("numButton");
        five.setTextFill(Color.WHITE);
        five.setPrefWidth(row1.getPrefWidth() / 5);
        five.setPrefHeight(25);
        five.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("5");
        });
        return five;
    }

    public JFXButton six() {
        JFXButton six = new JFXButton("6");
        six.setButtonType(JFXButton.ButtonType.RAISED);
        six.getStyleClass().add("numButton");
        six.setTextFill(Color.WHITE);
        six.setPrefWidth(row1.getPrefWidth() / 5);
        six.setPrefHeight(25);
        six.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("6");
        });
        return six;
    }

    public JFXButton seven() {
        JFXButton seven = new JFXButton("7");
        seven.setButtonType(JFXButton.ButtonType.RAISED);
        seven.getStyleClass().add("numButton");
        seven.setTextFill(Color.WHITE);
        seven.setPrefWidth(row1.getPrefWidth() / 5);
        seven.setPrefHeight(25);
        seven.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("7");
        });
        return seven;
    }

    public JFXButton eight() {
        JFXButton eight = new JFXButton("8");
        eight.setButtonType(JFXButton.ButtonType.RAISED);
        eight.getStyleClass().add("numButton");
        eight.setTextFill(Color.WHITE);
        eight.setPrefWidth(row1.getPrefWidth() / 5);
        eight.setPrefHeight(25);
        eight.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("8");
        });
        return eight;
    }

    public JFXButton nine() {
        JFXButton nine = new JFXButton("9");
        nine.setButtonType(JFXButton.ButtonType.RAISED);
        nine.getStyleClass().add("numButton");
        nine.setTextFill(Color.WHITE);
        nine.setPrefWidth(row1.getPrefWidth() / 5);
        nine.setPrefHeight(25);
        nine.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("9");
        });
        return nine;
    }

    public JFXButton point() {
        JFXButton point = new JFXButton(".");
        point.setButtonType(JFXButton.ButtonType.RAISED);
        point.getStyleClass().add("numButton");
        point.setTextFill(Color.WHITE);
        point.setPrefWidth(row1.getPrefWidth() / 5);
        point.setPrefHeight(25);
        point.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText(".");
        });
        return point;
    }

    public JFXButton plus() {
        JFXButton plus = new JFXButton("+");
        plus.setButtonType(JFXButton.ButtonType.RAISED);
        plus.getStyleClass().add("numButton");
        plus.setTextFill(Color.WHITE);
        plus.setPrefWidth(row1.getPrefWidth() / 5);
        plus.setPrefHeight(25);
        plus.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getTypeField().setText(Screen.getResult().getText());
                Screen.getResult().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("+");
        });
        return plus;
    }

    public JFXButton minus() {
        JFXButton minus = new JFXButton("-");
        minus.setButtonType(JFXButton.ButtonType.RAISED);
        minus.getStyleClass().add("numButton");
        minus.setTextFill(Color.WHITE);
        minus.setPrefWidth(row1.getPrefWidth() / 5);
        minus.setPrefHeight(25);
        minus.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getTypeField().setText(Screen.getResult().getText());
                Screen.getResult().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("-");
        });
        return minus;
    }

    public JFXButton multiply() {
        JFXButton multiply = new JFXButton("×");
        multiply.setButtonType(JFXButton.ButtonType.RAISED);
        multiply.getStyleClass().add("numButton");
        multiply.setTextFill(Color.WHITE);
        multiply.setPrefWidth(row1.getPrefWidth() / 5);
        multiply.setPrefHeight(25);
        multiply.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getTypeField().setText(Screen.getResult().getText());
                Screen.getResult().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("×");
        });
        return multiply;
    }

    public JFXButton divide() {
        JFXButton divide = new JFXButton("÷");
        divide.setButtonType(JFXButton.ButtonType.RAISED);
        divide.getStyleClass().add("numButton");
        divide.setTextFill(Color.WHITE);
        divide.setPrefWidth(row1.getPrefWidth() / 5);
        divide.setPrefHeight(25);
        divide.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getTypeField().setText(Screen.getResult().getText());
                Screen.getResult().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("÷");
        });
        return divide;
    }

    public JFXButton exp() {
        JFXButton exp = new JFXButton("exp");
        exp.setButtonType(JFXButton.ButtonType.RAISED);
        exp.getStyleClass().add("numButton");
        exp.setTextFill(Color.WHITE);
        exp.setPrefWidth(row1.getPrefWidth() / 5);
        exp.setPrefHeight(25);
        exp.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
//            Screen.getTypeField().appendText("exp");
        });
        return exp;
    }

    public JFXButton ans() {
        JFXButton ans = new JFXButton("Ans");
        ans.setButtonType(JFXButton.ButtonType.RAISED);
        ans.getStyleClass().add("numButton");
        ans.setTextFill(Color.WHITE);
        ans.setPrefWidth(row1.getPrefWidth() / 5);
        ans.setPrefHeight(25);
        ans.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
//            Screen.getTypeField().appendText("Ans");
        });
        return ans;
    }

    public JFXButton del() {
        JFXButton del = new JFXButton();

        ImageView delIcon = new ImageView(new Image(getClass().getResource("/images/delete.png").toExternalForm()));
        delIcon.setFitWidth(20);
        delIcon.setFitHeight(20);
        ColorAdjust white = new ColorAdjust();
        white.setBrightness(200.0);

        delIcon.setEffect(white);
        delIcon.setCache(true);
        delIcon.setCacheHint(CacheHint.SPEED);

        del.setGraphic(delIcon);
        del.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        del.setButtonType(JFXButton.ButtonType.RAISED);
        del.getStyleClass().add("delac");
        del.setPrefWidth(row1.getPrefWidth() / 5);
        del.setPrefHeight(25);
        del.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                CalculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().setText(Screen.getTypeField().getText().toString().substring(0, Screen.getTypeField().getText().length() - 1));
        });
        return del;
    }

    public JFXButton ac() {
        JFXButton ac = new JFXButton("AC");
        ac.setButtonType(JFXButton.ButtonType.RAISED);
        ac.getStyleClass().add("delac");
        ac.setTextFill(Color.WHITE);
        ac.setPrefWidth(row1.getPrefWidth() / 5);
        ac.setPrefHeight(25);
        ac.setOnAction((ev) -> {
            Screen.getResult().setText("");
            Screen.getTypeField().setText("");
            CalculateType.setCalculated(Boolean.FALSE);
        });
        return ac;
    }

    public JFXButton equals() {
        Solve calculateSolve = new Solve();
        JFXButton equals = new JFXButton("=");
        equals.setButtonType(JFXButton.ButtonType.RAISED);
        equals.getStyleClass().add("numButton");
        equals.setTextFill(Color.WHITE);
        equals.setPrefWidth(row1.getPrefWidth() / 5);
        equals.setPrefHeight(25);
        equals.setOnAction((ev) -> {
            if (CalculateType.getCalculated()) {
                CalculateType.setCalculated(Boolean.FALSE);
            }
            if (CalculateType.getShifMode()) {
                Screen.getTypeField().appendText("%");
            } else if (!Screen.getTypeField().getText().equals("")) {
                switch (CalculateType.getType()) {
                    case "Scientific":
                        Screen.getToCalculate().add(Screen.getTypeField().getText().trim());
                        calculateSolve.solveScientific();
                        break;
                    case "Normal":
                        Screen.getToCalculate().add(Screen.getTypeField().getText().trim());
                        calculateSolve.solve();
                        break;
                    case "Combination":
                        Screen.getToCalculate().add(Screen.getTypeField().getText().trim());
                        calculateSolve.combination();
                        break;
                    case "Permutation":
                        Screen.getToCalculate().add(Screen.getTypeField().getText().trim());
                        calculateSolve.permutation();
                        break;
                }
            }
        });

        return equals;
    }

}
