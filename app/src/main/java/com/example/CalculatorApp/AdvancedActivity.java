package com.example.CalculatorApp;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.math.BigDecimal;

public class AdvancedActivity extends AdvancedCalculatorCore implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_advanced);

        initButtons();

        if (savedInstanceState != null) {
            String first = savedInstanceState.getString("firstValue"), second = savedInstanceState.getString("secondValue");
            char operation = savedInstanceState.getChar("operation");

            resultText.setText(savedInstanceState.getString("result"));

            if (first != null) {
                firstValue = new BigDecimal(first);
            }
            if (second != null) {
                secondValue = new BigDecimal(second);
            }

            if (operation == '+') {
                isAdd = true;
            } else if (operation == '-') {
                isSub = true;
            } else if (operation == '*') {
                isMul = true;
            } else if (operation == '/') {
                isDiv = true;
            }else if (operation == '^') {
                isSquared = true;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        String temp = String.valueOf(resultText.getText());
        super.onSaveInstanceState(bundle);
        bundle.putString("result", temp);

        if (firstValue != null) {
            bundle.putString("firstValue", firstValue.toString());
        }
        if (secondValue != null) {
            bundle.putString("secondValue", secondValue.toString());
        }
        if (isAdd) {
            bundle.putChar("operation", '+');
        } else if (isSub) {
            bundle.putChar("operation", '-');
        } else if (isMul) {
            bundle.putChar("operation", '*');
        } else if (isDiv) {
            bundle.putChar("operation", '/');
        } else if (isSquared) {
            bundle.putChar("operation", '^');
        }
    }

    public void initButtons() {
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        point = findViewById(R.id.point);
        addition = findViewById(R.id.addition);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);
        subtraction = findViewById(R.id.subtraction);
        backspace = findViewById(R.id.backspace);
        equal = findViewById(R.id.equal);
        delete = findViewById(R.id.delete);
        plus_minus = findViewById(R.id.plus_minus);
        resultText = findViewById(R.id.result);
        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);
        ln = findViewById(R.id.ln);
        sqrt = findViewById(R.id.sqrt);
        squared = findViewById(R.id.squared);
        power = findViewById(R.id.power);
        log = findViewById(R.id.log);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        point.setOnClickListener(this);
        addition.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        backspace.setOnClickListener(this);
        equal.setOnClickListener(this);
        delete.setOnClickListener(this);
        plus_minus.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        ln.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        squared.setOnClickListener(this);
        power.setOnClickListener(this);
        log.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String value = resultText.getText().toString();
        switch (v.getId()) {
            case R.id.addition:
                addingOperation(value);
                break;

            case R.id.subtraction:
                subtractionOperation(value);
                break;

            case R.id.multiplication:
                multiplicationOperation(value);
                break;

            case R.id.division:
                divisionOperation(value);
                break;

            case R.id.delete:
                deleteOperation();
                break;

            case R.id.backspace:
                backspaceOperation(value);
                break;

            case R.id.plus_minus:
                reverseNumber(value);
                break;

            case R.id.point:
                insertPoint(value);
                break;

            case R.id.equal:
                performOperation(value);
                break;

            case R.id.sin:
                sinusOperation(value);
                break;

            case R.id.cos:
                cosinusOperation(value);
                break;

            case R.id.tan:
                tangensOperation(value);
                break;

            case R.id.ln:
                lnOperation(value);
                break;

            case R.id.log:
                logOperation(value);
                break;

            case R.id.sqrt:
                sqrtOperation(value);
                break;

            case R.id.squared:
                squaredOperation(value);
                break;

            case R.id.power:
                powerOperation(value);
                break;


            default:
                firstValue = null;
                secondValue = null;
                BigDecimal numb = new BigDecimal(((Button) v).getText().toString());

                if (equals) {
                    resultText.setText("");
                    value = "";
                    equals = false;
                    isAdd = false;
                    isSub = false;
                    isMul = false;
                    isDiv = false;
                    isSquared = false;
                }
                if (value.equals("0")) {
                    resultText.setText(String.valueOf(numb));
                } else if (!value.endsWith(")") && !value.contains("E")) {
                    resultText.setText(String.valueOf(value + numb));
                }
                break;
        }
    }
}
