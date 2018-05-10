/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import scientificcalculator.buttons.ScientificButtons;
import scientificcalculator.buttons.NumberButtons;
import model.calculateType;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Idris Opeyemi
 */
public class ScientificCalculatorController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    VBox panelBox;
    int index = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println(Math.IEEEremainder(7, 3));
//        System.out.println(Math.);
        calculateType.setType("Normal");
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
            if (act.getCode() == KeyCode.UP) {
                getNext();
            } else if (act.getCode() == KeyCode.DOWN) {
                getPrev();
            } else if (act.getCode() == KeyCode.RIGHT) {
                Screen.getTypeField().setEditable(true);
                Screen.getTypeField().requestFocus();
            } else if (act.getCode() == KeyCode.LEFT) {
                Screen.getTypeField().setEditable(true);
                Screen.getTypeField().selectEnd();
                Screen.getTypeField().setFocusTraversable(true);
            }
        });

        VBox calculate = new VBox();
        calculate = new Screen().calculateScreen();

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
        numBox.getChildren().addAll(numShiftRow1(), new NumberButtons().row1(), new NumberButtons().row2(), numShiftRow3(), new NumberButtons().row3(), numShiftRow4(), new NumberButtons().row4());
        return numBox;
    }

    public VBox sciBox() {
        VBox sciBox = new VBox();
        sciBox.getChildren().addAll(sciShiftRow2(), new ScientificButtons().row2(), sciShiftRow3(), new ScientificButtons().row3(), sciShiftRow4(), new ScientificButtons().row4());
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

        JFXButton right = new JFXButton();
        right.setButtonType(JFXButton.ButtonType.RAISED);
        Text rightIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_RIGHT, "20px");
        rightIcon.setFill(Color.DARKGREY);
        right.setGraphic(rightIcon);

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
        top.setOnAction((ev) -> {
            getNext();
        });

        JFXButton down = new JFXButton();
        down.setButtonType(JFXButton.ButtonType.RAISED);
        Text downIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_DOWN, "20px");
        downIcon.setFill(Color.DARKGREY);
        down.setGraphic(downIcon);
        down.setOnAction((ev) -> {
            getPrev();

        });

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
        String prevToCal = Screen.getToCalculate().get(Screen.getToCalculate().size() - 1);
        String prevResult = Screen.getResultList().get(Screen.getResultList().size() - 1);
        if (Screen.getToCalculate().contains(Screen.getTypeField().getText())) {
            int index2 = Screen.getToCalculate().indexOf(Screen.getTypeField().getText());
            prevToCal = Screen.getToCalculate().get(index2 - 1);
            prevResult = Screen.getResultList().get(index2 - 1);
            Screen.getTypeField().setText(prevToCal);
            Screen.getResult().setText(prevResult);
        } else {
            Screen.getTypeField().setText(prevToCal);
            Screen.getResult().setText(prevResult);
//        }
        }
    }

    public void getNext() {
        if (Screen.getToCalculate().contains(Screen.getTypeField().getText())) {
            int index2 = Screen.getToCalculate().indexOf(Screen.getTypeField().getText());
            String nextToCal = Screen.getToCalculate().get(index2 + 1);
            String nextResult = Screen.getResultList().get(index2 + 1);
            Screen.getTypeField().setText(nextToCal);
            Screen.getResult().setText(nextResult);
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
                calculateType.setShifMode(Boolean.TRUE);
                shift.getStyleClass().remove("modeColor");
                shift.getStyleClass().add("shiftModeColor");
            } else if (shift.getStyleClass().contains("shiftModeColor")) {
                calculateType.setShifMode(Boolean.FALSE);
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

        shiftAlpha.getChildren().addAll(ShiftTopBox(), shift, alpha, reg, shiftHalfSciRow1(), new ScientificButtons().halfRow1());
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

        modeOn.getChildren().addAll(ShiftRightTopBox(), on, mode, reg, shiftHalf2SciRow1(), new ScientificButtons().half2Row());
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
}
