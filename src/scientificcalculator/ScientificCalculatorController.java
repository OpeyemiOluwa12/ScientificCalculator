/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.geom.QuadCurve2D;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import java.beans.Expression;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * FXML Controller class
 *
 * @author opeyemi
 */
public class ScientificCalculatorController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    VBox panelBox;
    JFXTextField typeField;
    Boolean calculted = false;
    JFXTextField result;
    Boolean shiftMode = false;
    int index = 0;
    ObservableList<String> toCalculate = FXCollections.observableArrayList();
    ObservableList<String> resultList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println(Math.IEEEremainder(7, 3));
//        System.out.println(Math.);
        caclulateType.setType("Normal");
        panel();
    }

    public void panel() {
//        anchorPane.getStyleClass().add("anchorPane");
        panelBox = new VBox(5);
        CubicCurve curve = new CubicCurve(-175, 0, 0, 25, 50, 17.5, 175, 0);
        curve.setFill(Color.TRANSPARENT);
        panelBox.setPrefWidth(350);
        panelBox.setPadding(new Insets(20));
        panelBox.setOnKeyPressed((act) -> {
            if (act.getCode() == KeyCode.RIGHT) {
                getNext();
            } else if (act.getCode() == KeyCode.LEFT) {
                getPrev();
            }
        });
        VBox calculate = new VBox();
        Text mode = new Text("DRG");

        typeField = new JFXTextField();
        typeField.getStyleClass().add("typefont");
        typeField.setFocusColor(Color.WHITE);
        typeField.setUnFocusColor(Color.WHITE);
        typeField.setFocusTraversable(false);
        typeField.setEditable(false);

        result = new JFXTextField();
        result.setFocusTraversable(false);
        result.setEditable(false);
        result.getStyleClass().add("resultfont");
        result.setFocusColor(Color.WHITE);
        result.setUnFocusColor(Color.WHITE);
        result.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        calculate.getChildren().addAll(mode, typeField, result);
        calculate.setPadding(new Insets(5, 5, 5, 5));
        calculate.setStyle("-fx-background-color: white");

        Region reg = new Region();
        VBox.setVgrow(reg, Priority.ALWAYS);

        VBox numberBox = numberBox();
        VBox sciBox = sciBox();

        HBox topBox = new HBox();
        topBox.setPadding(new Insets(20, 5, 0, 5));
        topBox.setPrefWidth(panelBox.getPrefWidth());
        topBox.getChildren().addAll(topBox(), replay(), rightTopBox());

        panelBox.getChildren().add(curve);
        panelBox.getChildren().addAll(calculate, topBox, sciBox, reg, numberBox);

        AnchorPane.setLeftAnchor(panelBox, 1.0);
        AnchorPane.setRightAnchor(panelBox, 1.0);
        AnchorPane.setTopAnchor(panelBox, 1.0);
        AnchorPane.setBottomAnchor(panelBox, 1.0);
        AnchorPane.setLeftAnchor(curve, 18.952728271484375);
        AnchorPane.setRightAnchor(curve, 18.952728271484375);
        AnchorPane.setBottomAnchor(curve, -1.0);
//        anchorPane.getChildren().add(curve);
        anchorPane.getChildren().add(panelBox);
    }

    public VBox numberBox() {
        VBox numBox = new VBox();
        numBox.getChildren().addAll(numShiftRow1(), numRow1(), numRow2(), numShiftRow3(), numRow3(), numShiftRow4(), numRow4());
        return numBox;
    }

    public VBox sciBox() {
        VBox sciBox = new VBox();
        sciBox.getChildren().addAll(sciShiftRow2(), sciRow2(), sciShiftRow3(), sciRow3(), sciShiftRow4(), sciRow4());
        return sciBox;
    }

    public BorderPane replay() {
        BorderPane replay = new BorderPane();
        replay.setPrefHeight(0);
        replay.setPrefWidth(panelBox.getPrefWidth() / 3);

        JFXButton left = new JFXButton();

        left.setButtonType(JFXButton.ButtonType.RAISED);
        Text leftIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_LEFT, "20px");
        leftIcon.setFill(Color.DARKGREY);
        left.setGraphic(leftIcon);
        left.setOnAction((ev) -> {
            getPrev();

        });

        JFXButton right = new JFXButton();
        right.setButtonType(JFXButton.ButtonType.RAISED);
        Text rightIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_RIGHT, "20px");
        rightIcon.setFill(Color.DARKGREY);
        right.setGraphic(rightIcon);
        right.setOnAction((ev) -> {
            getNext();
        });

        JFXButton center = new JFXButton("REPLAY");
        center.setTextFill(Color.DARKGREY);
        center.getStyleClass().add("replay");
        center.setPrefWidth(panelBox.getPrefWidth());
        center.setPadding(new Insets(1));

        JFXButton top = new JFXButton();
        top.setButtonType(JFXButton.ButtonType.RAISED);
        Text topIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_UP, "20px");
        topIcon.setFill(Color.DARKGREY);
        top.setGraphic(topIcon);

        JFXButton down = new JFXButton();
        down.setButtonType(JFXButton.ButtonType.RAISED);
        Text downIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_DOWN, "20px");
        downIcon.setFill(Color.DARKGREY);
        down.setGraphic(downIcon);

        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(down, Pos.CENTER);
        replay.setLeft(left);
        replay.setTop(top);
        replay.setBottom(down);
        replay.setRight(right);
        replay.setCenter(center);
        replay.getStyleClass().add("borderpane");
        return replay;
    }

    public void getPrev() {
        String prevToCal = toCalculate.get(toCalculate.size() - 1);
        String prevResult = resultList.get(resultList.size() - 1);
        if (toCalculate.contains(typeField.getText())) {
            int index2 = toCalculate.indexOf(typeField.getText());
            prevToCal = toCalculate.get(index2 - 1);
            prevResult = resultList.get(index2 - 1);
            typeField.setText(prevToCal);
            result.setText(prevResult);
        } else {
            typeField.setText(prevToCal);
            result.setText(prevResult);
        }
    }

    public void getNext() {
        if (toCalculate.contains(typeField.getText())) {
            int index2 = toCalculate.indexOf(typeField.getText());
            String nextToCal = toCalculate.get(index2 + 1);
            String nextResult = resultList.get(index2 + 1);
            typeField.setText(nextToCal);
            result.setText(nextResult);
        }
    }

    public VBox topBox() {
        VBox shiftAlpha = new VBox();
        shiftAlpha.setPrefWidth(panelBox.getPrefWidth() / 3);

        JFXButton shift = new JFXButton();
        VBox.setMargin(shift, new Insets(0, 50, 0, 0));

        shift.getStyleClass().add("modeButton");
        shift.getStyleClass().add("modeColor");
        shift.setPrefWidth(shiftAlpha.getPrefWidth() / 3);
        shift.setOnAction((ev) -> {
            if (shift.getStyleClass().contains("modeColor")) {
                shiftMode = true;
                shift.getStyleClass().remove("modeColor");
                shift.getStyleClass().add("shiftModeColor");
            } else if (shift.getStyleClass().contains("shiftModeColor")) {
                shiftMode = false;
                shift.getStyleClass().remove("shiftModeColor");
                shift.getStyleClass().add("modeColor");
            }
        });

        JFXButton alpha = new JFXButton();
        VBox.setMargin(alpha, new Insets(-10, 0, 0, shiftAlpha.getPrefWidth() / 2));
        alpha.getStyleClass().add("modeButton");
        alpha.getStyleClass().add("modeColor");
        alpha.setPrefWidth(shiftAlpha.getPrefWidth() / 3);

        Region reg = new Region();
        VBox.setVgrow(reg, Priority.ALWAYS);

        shiftAlpha.getChildren().addAll(ShiftTopBox(), shift, alpha, reg, shiftHalfSciRow1(), halfSciRow1());
        return shiftAlpha;
    }

    public VBox ShiftTopBox() {
        VBox row3 = new VBox();
        row3.setPadding(Insets.EMPTY);
        row3.setPrefWidth(panelBox.getPrefWidth() / 3);
        Label shift = new Label("Shift");
        shift.setTextFill(Color.GOLDENROD);
        row3.setAlignment(Pos.BOTTOM_LEFT);
//        VBox.setMargin(shift, new Insets(0, 50, 0, 0));

        Label alpha = new Label("Alpha");
        alpha.setTextFill(Color.MEDIUMVIOLETRED);
        VBox.setMargin(alpha, new Insets(-10, 0, 0, row3.getPrefWidth() / 2));
        row3.getChildren().addAll(shift, alpha);
        return row3;
    }

    public VBox rightTopBox() {
        VBox modeOn = new VBox();
        modeOn.setPrefWidth(panelBox.getPrefWidth() / 3);
        modeOn.setAlignment(Pos.TOP_RIGHT);

        JFXButton mode = new JFXButton();
        VBox.setMargin(mode, new Insets(-10, (modeOn.getPrefWidth() / 2) - 5, 0, 0));
        mode.getStyleClass().add("modeButton");
        mode.getStyleClass().add("modeColor");
        mode.setPrefWidth(modeOn.getPrefWidth() / 3);

        JFXButton on = new JFXButton();
        VBox.setMargin(on, new Insets(0, 0, 0, modeOn.getPrefWidth() / 2));
        on.getStyleClass().add("modeButton");
        on.getStyleClass().add("modeColor");
        on.setPrefWidth(modeOn.getPrefWidth() / 3);

        Region reg = new Region();
        VBox.setVgrow(reg, Priority.ALWAYS);

        modeOn.getChildren().addAll(ShiftRightTopBox(), on, mode, reg, shiftHalf2SciRow1(), half2SciRow1());
        return modeOn;
    }

    public VBox ShiftRightTopBox() {
        VBox row3 = new VBox();
        row3.setPrefWidth(panelBox.getPrefWidth() / 3);
        Label on = new Label("on");
        on.setTextFill(Color.GOLD);
        on.setPadding(new Insets(0, 0, 0, 0));
        VBox.setMargin(on, new Insets(0, 0, 0, row3.getPrefWidth() / 2));

        Label mode = new Label("mode");
        mode.setTextFill(Color.GOLD);
        mode.setPadding(new Insets(0, 0, 0, 0));
        VBox.setMargin(mode, new Insets(-10, (row3.getPrefWidth() / 2) - 5, 0, 0));
        row3.getChildren().addAll(on, mode);
        return row3;
    }

    public HBox halfSciRow1() {
        HBox row1 = new HBox(10);
        row1.setPrefWidth(panelBox.getPrefWidth() / 3);
        JFXButton mixedFrac = new JFXButton("");

        mixedFrac.setButtonType(JFXButton.ButtonType.RAISED);
        mixedFrac.getStyleClass().add("sciButton");
        mixedFrac.setTextFill(Color.WHITE);
        mixedFrac.setPrefWidth(row1.getPrefWidth() / 3);
        mixedFrac.setPrefHeight(20);
        mixedFrac.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("7");
        });

        JFXButton sqroot = new JFXButton("nCr");
        sqroot.setButtonType(JFXButton.ButtonType.RAISED);
        sqroot.getStyleClass().add("sciButton");
        sqroot.setTextFill(Color.WHITE);
        sqroot.setPrefWidth(row1.getPrefWidth() / 3);
        sqroot.setPrefHeight(20);
        sqroot.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("√(");
        });
        row1.getChildren().addAll(mixedFrac, sqroot);
        return row1;
    }

    public HBox shiftHalfSciRow1() {
        HBox row3 = new HBox();
        row3.setPrefWidth(panelBox.getPrefWidth() / 3);
        Label factorial = new Label("x!");
        factorial.setTextFill(Color.DARKGOLDENROD);
        factorial.setPadding(new Insets(0, row3.getPrefWidth() / 3, 0, 0));

        Label permute = new Label("nPr");
        permute.setTextFill(Color.DARKGOLDENROD);
        permute.setPadding(new Insets(0, 0, 0, 0));
        row3.getChildren().addAll(factorial, permute);
        return row3;
    }

    public HBox half2SciRow1() {
        HBox row1 = new HBox(10);
        row1.setAlignment(Pos.BASELINE_RIGHT);
        row1.setPrefWidth(panelBox.getPrefWidth() / 3);
//        row1.setPadding(new Insets(0, 5, 10, 5));
        JFXButton sqr = new JFXButton("pol(");
        sqr.setButtonType(JFXButton.ButtonType.RAISED);
        sqr.getStyleClass().add("sciButton");
        sqr.setTextFill(Color.WHITE);
        sqr.setPrefWidth(row1.getPrefWidth() / 3);
        sqr.setPrefHeight(20);
        sqr.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("9");
        });

        JFXButton caret = new JFXButton("x^3");
        caret.getStyleClass().add("sciButton");
        caret.setTextFill(Color.WHITE);
        caret.setPrefWidth(row1.getPrefWidth() / 3);
        caret.setPrefHeight(20);
        caret.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("^3");
        });

        row1.getChildren().addAll(sqr, caret);
        return row1;
    }

    public HBox shiftHalf2SciRow1() {
        HBox row3 = new HBox();
        row3.setPrefWidth(panelBox.getPrefWidth() / 3);
        Label rec = new Label("");
        rec.setTextFill(Color.DARKGOLDENROD);
        rec.setPadding(new Insets(0, row3.getPrefWidth() / 3, 0, 0));

        Label cuberoot = new Label("∛");
        cuberoot.setFont(Font.font("verdana", 18));
        cuberoot.setTextFill(Color.DARKGOLDENROD);
        cuberoot.setPadding(new Insets(0, 0, 0, 0));
        row3.getChildren().addAll(rec, cuberoot);
        return row3;
    }

    public HBox sciRow2() {
        HBox row1 = new HBox(10);
        row1.setPrefWidth(panelBox.getPrefWidth());
        row1.setPadding(new Insets(0, 5, 5, 5));

        Text biga = new Text("a");
        Text small = new Text("b/c");
        TextFlow fract = new TextFlow(biga, small);

        biga.setFont(Font.font(15));
        JFXButton mixedFrac = new JFXButton(fract.toString());
        mixedFrac.setButtonType(JFXButton.ButtonType.RAISED);
        mixedFrac.getStyleClass().add("sciButton");
        mixedFrac.setTextFill(Color.WHITE);
        mixedFrac.setPrefWidth(row1.getPrefWidth() / 6);
        mixedFrac.setPrefHeight(20);
        mixedFrac.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("7");
        });

        JFXButton sqroot = new JFXButton("√");
        sqroot.setButtonType(JFXButton.ButtonType.RAISED);
        sqroot.getStyleClass().add("sciButton");
        sqroot.setTextFill(Color.WHITE);
        sqroot.setPrefWidth(row1.getPrefWidth() / 6);
        sqroot.setPrefHeight(20);
        sqroot.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("√(");
        });

        JFXButton sqr = new JFXButton("x^2");
        sqr.setButtonType(JFXButton.ButtonType.RAISED);
        sqr.getStyleClass().add("sciButton");
        sqr.setTextFill(Color.WHITE);
        sqr.setPrefWidth(row1.getPrefWidth() / 6);
        sqr.setPrefHeight(20);
        sqr.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("^2");
        });

        JFXButton caret = new JFXButton("˄");
        caret.getStyleClass().add("sciButton");
        caret.setTextFill(Color.WHITE);
        caret.setPrefWidth(row1.getPrefWidth() / 6);
        caret.setPrefHeight(20);
        caret.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("^");
        });

        JFXButton log = new JFXButton("log");
        log.setButtonType(JFXButton.ButtonType.RAISED);
        log.getStyleClass().add("sciButton");
        log.setTextFill(Color.WHITE);
        log.setPrefWidth(row1.getPrefWidth() / 6);
        log.setPrefHeight(20);
        log.setOnAction((ev) -> {
            if (calculted) {
                typeField.setText("");
                result.setText("");
                calculted = false;
            }
            typeField.appendText("log(");
        });

        JFXButton in = new JFXButton("ln");
        in.setButtonType(JFXButton.ButtonType.RAISED);
        in.getStyleClass().add("sciButton");
        in.setTextFill(Color.WHITE);
        in.setPrefWidth(row1.getPrefWidth() / 6);
        in.setPrefHeight(20);
        in.setOnAction((ev) -> {
            typeField.setText("");
            result.setText("");
            calculted = false;
        });
        row1.getChildren().addAll(mixedFrac, sqroot, sqr, caret, log, in);
        return row1;
    }

    public HBox sciShiftRow2() {
        HBox row1 = new HBox();
        row1.setPrefWidth(panelBox.getPrefWidth());

        Label improperFract = new Label("d/c");
        improperFract.setTextFill(Color.DARKGOLDENROD);
        improperFract.setPadding(new Insets(0, 95, 0, 12));

        Label root = new Label("x√");
        root.setTextFill(Color.DARKGOLDENROD);
        root.setPadding(new Insets(0, 15, 0, 40));

        Label tenExp = new Label("10^x");
        tenExp.setTextFill(Color.DARKGOLDENROD);
        tenExp.setPadding(new Insets(0, 15, 0, 15));

        Label eExp = new Label("e^x");
        eExp.setTextFill(Color.DARKGOLDENROD);
        eExp.setPadding(new Insets(0, 15, 0, 20));
        row1.getChildren().addAll(improperFract, root, tenExp, eExp);
        return row1;
    }

    public HBox sciRow3() {
        HBox row2 = new HBox(10);
        row2.setPrefWidth(panelBox.getPrefWidth());
        row2.setPadding(new Insets(0, 5, 5, 5));

        JFXButton hypen = new JFXButton("(-)");
        hypen.setButtonType(JFXButton.ButtonType.RAISED);
        hypen.getStyleClass().add("sciButton");
        hypen.setTextFill(Color.WHITE);
        hypen.setPrefWidth(row2.getPrefWidth() / 6);
        hypen.setPrefHeight(20);
        hypen.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("4");
        });

        JFXButton dot = new JFXButton("۰٬٬٬");
        dot.setButtonType(JFXButton.ButtonType.RAISED);
        dot.getStyleClass().add("sciButton");
        dot.setTextFill(Color.WHITE);
        dot.setPrefWidth(row2.getPrefWidth() / 6);
        dot.setPrefHeight(20);
        dot.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("5");
        });

        JFXButton hyp = new JFXButton("hyp");
        hyp.setButtonType(JFXButton.ButtonType.RAISED);
        hyp.getStyleClass().add("sciButton");
        hyp.setTextFill(Color.WHITE);
        hyp.setPrefWidth(row2.getPrefWidth() / 6);
        hyp.setPrefHeight(20);
        hyp.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
                caclulateType.setType("Scientific");
            }
//            typeField.appendText("6");
        });

        JFXButton sin = new JFXButton("sin");
        sin.setButtonType(JFXButton.ButtonType.RAISED);
        sin.getStyleClass().add("sciButton");
        sin.setTextFill(Color.WHITE);
        sin.setPrefWidth(row2.getPrefWidth() / 6);
        sin.setPrefHeight(20);
        sin.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            caclulateType.setType("Scientific");
            if (shiftMode) {
                typeField.appendText("sin-1(");
            } else {
                typeField.appendText("sin(");
            }

        });

        JFXButton cos = new JFXButton("cos");
        cos.setButtonType(JFXButton.ButtonType.RAISED);
        cos.getStyleClass().add("sciButton");
        cos.setTextFill(Color.WHITE);
        cos.setPrefWidth(row2.getPrefWidth() / 6);
        cos.setPrefHeight(20);
        cos.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            caclulateType.setType("Scientific");
            if (shiftMode) {
                typeField.appendText("cos-1(");
            } else {
                typeField.appendText("cos(");
            }

        });
        JFXButton tan = new JFXButton("tan");
        tan.setButtonType(JFXButton.ButtonType.RAISED);
        tan.getStyleClass().add("sciButton");
        tan.setTextFill(Color.WHITE);
        tan.setPrefWidth(row2.getPrefWidth() / 6);
        tan.setPrefHeight(20);
        tan.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            caclulateType.setType("Scientific");
            if (shiftMode) {
                typeField.appendText("tan-1(");
            } else {
                typeField.appendText("tan(");
            }

        });

        row2.getChildren().addAll(hypen, dot, hyp, sin, cos, tan);
        return row2;
    }

    public HBox sciShiftRow3() {
        HBox row1 = new HBox();
        row1.setPrefWidth(panelBox.getPrefWidth());

        Label alphaA = new Label("A");
        alphaA.setTextFill(Color.MEDIUMVIOLETRED);
        alphaA.setPadding(new Insets(0, 0, 0, 35));

        Label alphaB = new Label("B");
        alphaB.setTextFill(Color.MEDIUMVIOLETRED);
        alphaB.setPadding(new Insets(0, 0, 0, 45));

        Label alphaC = new Label("C");
        alphaC.setTextFill(Color.MEDIUMVIOLETRED);
        alphaC.setPadding(new Insets(0, 0, 0, 45));

        Label sinInv = new Label("sin-1");
        sinInv.setTextFill(Color.DARKGOLDENROD);
        sinInv.setPadding(new Insets(0, 0, 0, 10));

        Label alphaD = new Label("D");
        alphaD.setTextFill(Color.MEDIUMVIOLETRED);
        alphaD.setPadding(new Insets(0, 0, 0, 7));

        Label cosInv = new Label("cos-1");
        cosInv.setTextFill(Color.DARKGOLDENROD);
        cosInv.setPadding(new Insets(0, 0, 0, 8));

        Label alphaE = new Label("E");
        alphaE.setTextFill(Color.MEDIUMVIOLETRED);
        alphaE.setPadding(new Insets(0, 0, 0, 7));

        Label tanInv = new Label("tan-1");
        tanInv.setTextFill(Color.DARKGOLDENROD);
        tanInv.setPadding(new Insets(0, 0, 0, 8));

        Label alphaF = new Label("F");
        alphaF.setTextFill(Color.MEDIUMVIOLETRED);
        alphaF.setPadding(new Insets(0, 0, 0, 7));
        row1.getChildren().addAll(alphaA, alphaB, alphaC, sinInv, alphaD, cosInv, alphaE, tanInv, alphaF);
        return row1;
    }

    public HBox sciRow4() {
        HBox row3 = new HBox(10);
        row3.setPrefWidth(panelBox.getPrefWidth());
        row3.setPadding(new Insets(0, 5, 5, 5));

        JFXButton rcl = new JFXButton("RCL");
        rcl.setButtonType(JFXButton.ButtonType.RAISED);
        rcl.getStyleClass().add("sciButton");
        rcl.setTextFill(Color.WHITE);
        rcl.setPrefWidth(row3.getPrefWidth() / 6);
        rcl.setPrefHeight(20);
        rcl.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("1");
        });
        JFXButton eng = new JFXButton("ENG");
        eng.setButtonType(JFXButton.ButtonType.RAISED);
        eng.getStyleClass().add("sciButton");
        eng.setTextFill(Color.WHITE);
        eng.setPrefWidth(row3.getPrefWidth() / 6);
        eng.setPrefHeight(20);
        eng.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText("1");
        });

        JFXButton bracketOpen = new JFXButton("(");
        bracketOpen.setButtonType(JFXButton.ButtonType.RAISED);
        bracketOpen.getStyleClass().add("sciButton");
        bracketOpen.setTextFill(Color.WHITE);
        bracketOpen.setPrefWidth(row3.getPrefWidth() / 6);
        bracketOpen.setPrefHeight(20);
        bracketOpen.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("(");
        });

        JFXButton bracketClosed = new JFXButton(")");
        bracketClosed.setButtonType(JFXButton.ButtonType.RAISED);
        bracketClosed.getStyleClass().add("sciButton");
        bracketClosed.setTextFill(Color.WHITE);
        bracketClosed.setPrefWidth(row3.getPrefWidth() / 6);
        bracketClosed.setPrefHeight(20);
        bracketClosed.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText(")");
        });

        JFXButton coma = new JFXButton("٬");
        coma.setButtonType(JFXButton.ButtonType.RAISED);
        coma.getStyleClass().add("sciButton");
        coma.setTextFill(Color.WHITE);
        coma.setPrefWidth(row3.getPrefWidth() / 6);
        coma.setPrefHeight(20);
        coma.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText(" + ");
        });

        JFXButton mplus = new JFXButton("M+");
        mplus.setButtonType(JFXButton.ButtonType.RAISED);
        mplus.getStyleClass().add("sciButton");
        mplus.setTextFill(Color.WHITE);
        mplus.setPrefWidth(row3.getPrefWidth() / 6);
        mplus.setPrefHeight(20);
        mplus.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
//            typeField.appendText(" - ");
        });

        row3.getChildren().addAll(rcl, eng, bracketOpen, bracketClosed, coma, mplus);
        return row3;
    }

    public HBox sciShiftRow4() {
        HBox row1 = new HBox();
        row1.setPrefWidth(panelBox.getPrefWidth());

        Label sto = new Label("STO");
        sto.setTextFill(Color.DARKGOLDENROD);
        sto.setPadding(new Insets(0, 0, 0, 10));

        Label alphaX = new Label("X");
        alphaX.setTextFill(Color.MEDIUMVIOLETRED);
        alphaX.setPadding(new Insets(0, 0, 0, (panelBox.getPrefWidth() / 2) - 13));

        Label colon = new Label(":");
        colon.setTextFill(Color.DARKGOLDENROD);
        colon.setPadding(new Insets(0, 0, 0, 8));

        Label alphaY = new Label("Y");
        alphaY.setTextFill(Color.MEDIUMVIOLETRED);
        alphaY.setPadding(new Insets(0, 0, 0, 30));

        Label alphaM = new Label("M");
        alphaM.setTextFill(Color.MEDIUMVIOLETRED);
        alphaM.setPadding(new Insets(0, 0, 0, 40));
        row1.getChildren().addAll(sto, alphaX, colon, alphaY, alphaM);
        return row1;
    }

    public HBox numRow1() {
        HBox row1 = new HBox(10);
        row1.setPrefWidth(panelBox.getPrefWidth());
        row1.setPadding(new Insets(0, 5, 10, 5));

        JFXButton seven = new JFXButton("7");
        seven.setButtonType(JFXButton.ButtonType.RAISED);
        seven.getStyleClass().add("numButton");
        seven.setTextFill(Color.WHITE);
        seven.setPrefWidth(row1.getPrefWidth() / 5);
        seven.setPrefHeight(25);
        seven.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("7");
        });

        JFXButton eight = new JFXButton("8");
        eight.setButtonType(JFXButton.ButtonType.RAISED);
        eight.getStyleClass().add("numButton");
        eight.setTextFill(Color.WHITE);
        eight.setPrefWidth(row1.getPrefWidth() / 5);
        eight.setPrefHeight(25);
        eight.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("8");
        });

        JFXButton nine = new JFXButton("9");
        nine.setButtonType(JFXButton.ButtonType.RAISED);
        nine.getStyleClass().add("numButton");
        nine.setTextFill(Color.WHITE);
        nine.setPrefWidth(row1.getPrefWidth() / 5);
        nine.setPrefHeight(25);
        nine.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("9");
        });

        JFXButton del = new JFXButton();
        Text delIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.REMOVE);
        del.setGraphic(delIcon);
        del.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        del.setButtonType(JFXButton.ButtonType.RAISED);
        del.getStyleClass().add("delac");
        delIcon.setFill(Color.WHITE);
        del.setPrefWidth(row1.getPrefWidth() / 5);
        del.setPrefHeight(25);
        del.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.setText(typeField.getText().toString().substring(0, typeField.getText().length() - 1));
        });

        JFXButton ac = new JFXButton("AC");
        ac.setButtonType(JFXButton.ButtonType.RAISED);
        ac.getStyleClass().add("delac");
        ac.setTextFill(Color.WHITE);
        ac.setPrefWidth(row1.getPrefWidth() / 5);
        ac.setPrefHeight(25);
        ac.setOnAction((ev) -> {
            typeField.setText("");
            result.setText("");
            calculted = false;
        });
        row1.getChildren().addAll(seven, eight, nine, del, ac);
        return row1;
    }

    public HBox numShiftRow1() {
        HBox row1 = new HBox();
        row1.setPrefWidth(panelBox.getPrefWidth());
//        row1.setPadding(new Insets(5));

        Label ins = new Label("INS");
        ins.setTextFill(Color.GOLD);
        ins.setPadding(new Insets(0, 25, 0, 15));
        row1.setAlignment(Pos.TOP_RIGHT);

        Label off = new Label("OFF");
        off.setTextFill(Color.GOLD);
        off.setPadding(new Insets(0, 15, 0, 25));
        row1.getChildren().addAll(ins, off);
        return row1;
    }

    public HBox numRow2() {
        HBox row2 = new HBox(10);
        row2.setPrefWidth(panelBox.getPrefWidth());
        row2.setPadding(new Insets(10, 5, 5, 5));

        JFXButton four = new JFXButton("4");
        four.setButtonType(JFXButton.ButtonType.RAISED);
        four.getStyleClass().add("numButton");
        four.setTextFill(Color.WHITE);
        four.setPrefWidth(row2.getPrefWidth() / 5);
        four.setPrefHeight(25);
        four.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("4");
        });

        JFXButton five = new JFXButton("5");
        five.setButtonType(JFXButton.ButtonType.RAISED);
        five.getStyleClass().add("numButton");
        five.setTextFill(Color.WHITE);
        five.setPrefWidth(row2.getPrefWidth() / 5);
        five.setPrefHeight(25);
        five.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("5");
        });

        JFXButton six = new JFXButton("6");
        six.setButtonType(JFXButton.ButtonType.RAISED);
        six.getStyleClass().add("numButton");
        six.setTextFill(Color.WHITE);
        six.setPrefWidth(row2.getPrefWidth() / 5);
        six.setPrefHeight(25);
        six.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("6");
        });

        JFXButton mul = new JFXButton("x");
        mul.setButtonType(JFXButton.ButtonType.RAISED);
        mul.getStyleClass().add("numButton");
        mul.setTextFill(Color.WHITE);
        mul.setPrefWidth(row2.getPrefWidth() / 5);
        mul.setPrefHeight(25);
        mul.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("x");
        });

        JFXButton divide = new JFXButton("÷");
        divide.setButtonType(JFXButton.ButtonType.RAISED);
        divide.getStyleClass().add("numButton");
        divide.setTextFill(Color.WHITE);
        divide.setPrefWidth(row2.getPrefWidth() / 5);
        divide.setPrefHeight(25);
        divide.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("÷");
        });

        row2.getChildren().addAll(four, five, six, mul, divide);
        return row2;
    }

    public HBox numRow3() {
        HBox row3 = new HBox(10);
        row3.setPrefWidth(panelBox.getPrefWidth());
        row3.setPadding(new Insets(0, 5, 5, 5));

        JFXButton one = new JFXButton("1");
        one.setButtonType(JFXButton.ButtonType.RAISED);
        one.getStyleClass().add("numButton");
        one.setTextFill(Color.WHITE);
        one.setPrefWidth(row3.getPrefWidth() / 5);
        one.setPrefHeight(25);
        one.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("1");
        });

        JFXButton two = new JFXButton("2");
        two.setButtonType(JFXButton.ButtonType.RAISED);
        two.getStyleClass().add("numButton");
        two.setTextFill(Color.WHITE);
        two.setPrefWidth(row3.getPrefWidth() / 5);
        two.setPrefHeight(25);
        two.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("2");
        });

        JFXButton three = new JFXButton("3");
        three.setButtonType(JFXButton.ButtonType.RAISED);
        three.getStyleClass().add("numButton");
        three.setTextFill(Color.WHITE);
        three.setPrefWidth(row3.getPrefWidth() / 5);
        three.setPrefHeight(25);
        three.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("3");
        });

        JFXButton plus = new JFXButton("+");
        plus.setButtonType(JFXButton.ButtonType.RAISED);
        plus.getStyleClass().add("numButton");
        plus.setTextFill(Color.WHITE);
        plus.setPrefWidth(row3.getPrefWidth() / 5);
        plus.setPrefHeight(25);
        plus.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("+");
        });

        JFXButton minus = new JFXButton("-");
        minus.setButtonType(JFXButton.ButtonType.RAISED);
        minus.getStyleClass().add("numButton");
        minus.setTextFill(Color.WHITE);
        minus.setPrefWidth(row3.getPrefWidth() / 5);
        minus.setPrefHeight(25);
        minus.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("-");
        });

        row3.getChildren().addAll(one, two, three, plus, minus);
        return row3;
    }

    public HBox numShiftRow3() {
        HBox row3 = new HBox();
        row3.setPrefWidth(panelBox.getPrefWidth());
        Label sum = new Label("S-SUM");
        sum.setTextFill(Color.GOLD);
        sum.setPadding(new Insets(0, 10, 0, 15));

        Label var = new Label("S-VAR");
        var.setTextFill(Color.GOLD);
        var.setPadding(new Insets(0, 15, 0, 10));
        row3.getChildren().addAll(sum, var);
        return row3;
    }

    public HBox numRow4() {
        HBox row4 = new HBox(10);
        row4.setPrefWidth(panelBox.getPrefWidth());
        row4.setPadding(new Insets(0, 5, 5, 5));

        JFXButton zero = new JFXButton("0");
        zero.setButtonType(JFXButton.ButtonType.RAISED);
        zero.getStyleClass().add("numButton");
        zero.setTextFill(Color.WHITE);
        zero.setPrefWidth(row4.getPrefWidth() / 5);
        zero.setPrefHeight(25);
        zero.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("0");
        });

        JFXButton point = new JFXButton(".");
        point.setButtonType(JFXButton.ButtonType.RAISED);
        point.getStyleClass().add("numButton");
        point.setTextFill(Color.WHITE);
        point.setPrefWidth(row4.getPrefWidth() / 5);
        point.setPrefHeight(25);
        point.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText(".");
        });

        JFXButton exp = new JFXButton("EXP");
        exp.setButtonType(JFXButton.ButtonType.RAISED);
        exp.getStyleClass().add("numButton");
        exp.setTextFill(Color.WHITE);
        exp.setPrefWidth(row4.getPrefWidth() / 5);
        exp.setPrefHeight(25);
        exp.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            if (shiftMode) {
                typeField.appendText("π");
            } else {
                typeField.appendText("");
            }

        });

        JFXButton ans = new JFXButton("ANS");
        ans.setButtonType(JFXButton.ButtonType.RAISED);
        ans.getStyleClass().add("numButton");
        ans.setTextFill(Color.WHITE);
        ans.setPrefWidth(row4.getPrefWidth() / 5);
        ans.setPrefHeight(25);
        ans.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            typeField.appendText("");
        });

        JFXButton equals = new JFXButton("=");
        equals.setButtonType(JFXButton.ButtonType.RAISED);
        equals.getStyleClass().add("numButton");
        equals.setTextFill(Color.WHITE);
        equals.setPrefWidth(row4.getPrefWidth() / 5);
        equals.setPrefHeight(25);
        equals.setOnAction((ev) -> {
            if (calculted) {
                result.setText("");
                typeField.setText("");
                calculted = false;
            }
            if (shiftMode) {
                typeField.appendText("%");
            } else if (!typeField.getText().equals("")) {
                switch (caclulateType.getType()) {
                    case "Scientific":
                        toCalculate.add(typeField.getText().trim());
                        solveScientific();
                        break;
                    case "Normal":
                        toCalculate.add(typeField.getText().trim());
                        solve();
                        break;
                }
            }
        });

        row4.getChildren().addAll(zero, point, exp, ans, equals);
        return row4;
    }

    public HBox numShiftRow4() {
        HBox row3 = new HBox();
        row3.setPrefWidth(panelBox.getPrefWidth());
        Label rnd = new Label("Rnd");
        rnd.setTextFill(Color.GOLD);
        rnd.setPadding(new Insets(0, 20, 0, 20));

        Label ran = new Label("Ran#");
        ran.setTextFill(Color.GOLD);
        ran.setPadding(new Insets(0, 20, 0, 20));

        Label pie = new Label("π");
        pie.setTextFill(Color.GOLD);
        pie.setPadding(new Insets(0, 20, 0, 20));

        Label drg = new Label("DRG");
        drg.setTextFill(Color.GOLD);
        drg.setPadding(new Insets(0, 20, 0, 20));

        Label percent = new Label("%");
        percent.setTextFill(Color.GOLD);
        percent.setPadding(new Insets(0, 20, 0, 20));

        row3.getChildren().addAll(rnd, ran, pie, drg, percent);
        return row3;
    }

    public String solvePower(String calculate) {
        String[] parts = calculate.split("(?=[/x-÷+^])|(?<=[/x÷*-+^])");
        String solvedPow = null;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("^")) {
                int left = Integer.parseInt(parts[i - 1]);
                int right = Integer.parseInt(parts[i + 1]);
                solvedPow = "" + (Math.pow(left, right));
                calculate = calculate.replace("" + parts[i - 1] + parts[i] + parts[i + 1], solvedPow);
//                System.out.println(calculate);
                break;
            }
        }
        return calculate;
    }

    public void solve() {
        String calculate = typeField.getText();
        if (calculate.contains("^")) {
            calculate = solvePower(calculate);
        }
        calculate = calculate.replace("√", "Math.sqrt").
                replace("÷", "/").
                replace("%", "*1/100").
                replace("π", "Math.PI").
                replace("x", "*");

        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

        try {
            // Evaluate the expression
            Object answer = engine.eval(calculate);
            resultList.add(answer.toString());
            result.setText(answer.toString());
            calculted = true;
        } catch (ScriptException e) {
            // Something went wrong
            resultList.add("SYNTAX ERROR");
            result.setText("SYNTAX ERROR");
            calculted = true;
            e.printStackTrace();
        }
    }

    public void solveScientific() {
        String calculate = typeField.getText();
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
                replace("π", "Math.PI").
                replace(")", "*Math.PI)/180)").
                replace("÷", "/").
                replace("%", "*1/100").
                replace("x", "*").
                replace("log", "Math.log(");
        System.out.println(calculate);

        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

        try {
            // Evaluate the expression
            Object answer = engine.eval(calculate);
            resultList.add(answer.toString());
            result.setText(answer.toString());
            calculted = true;
            caclulateType.setType("Normal");
        } catch (ScriptException e) {
            // Something went wrong
            resultList.add("SYNTAX ERROR");
            result.setText("SYNTAX ERROR");
            calculted = true;
            e.printStackTrace();
        }
    }

    public void solveInverse() {
        String calculate = typeField.getText();
        if (calculate.contains("^")) {
            calculate = solvePower(calculate);
        }
        calculate = calculate.replace("sin-1", "180/Math.PI*(Math.asin").
                replace("cos-1", "180/Math.PI*(Math.acos").
                replace("tan-1", "180/Math.PI*(Math.atan").
                replace("√", "Math.sqrt").
                replace("π", "Math.PI").
                replace(")", "))").
                replace("÷", "/").
                replace("%", "*1/100").
                replace("x", "*").
                replace("log", "Math.log(");
                System.out.println(calculate);


        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

        try {
            // Evaluate the expression
            Object answer = engine.eval(calculate);
            resultList.add(answer.toString());
            result.setText(answer.toString());
            calculted = true;
            caclulateType.setType("Normal");
        } catch (ScriptException e) {
            // Something went wrong
            resultList.add("SYNTAX ERROR");
            result.setText("SYNTAX ERROR");
            calculted = true;
            e.printStackTrace();
        }
    }
}
