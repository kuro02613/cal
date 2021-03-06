package com.example.makiko.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.example.makiko.sample.Calculate.*;

public class CalculatorActivity extends BaseActivity {

    @InjectView(R.id.btn_1)
    Button btn1;
    @InjectView(R.id.btn_2)
    Button btn2;
    @InjectView(R.id.btn_3)
    Button btn3;
    @InjectView(R.id.btn_4)
    Button btn4;
    @InjectView(R.id.btn_5)
    Button btn5;
    @InjectView(R.id.btn_6)
    Button btn6;
    @InjectView(R.id.btn_7)
    Button btn7;
    @InjectView(R.id.btn_8)
    Button btn8;
    @InjectView(R.id.btn_9)
    Button btn9;
    @InjectView(R.id.btn_0)
    Button btn0;
    @InjectView(R.id.btn_eq)
    Button btnEq;
    @InjectView(R.id.btn_add)
    Button btnAdd;
    @InjectView(R.id.btn_sub)
    Button btnSub;
    @InjectView(R.id.btn_mul)
    Button btnMul;
    @InjectView(R.id.btn_div)
    Button btnDiv;
    @InjectView(R.id.btn_dot)
    Button btnDot;
    @InjectView(R.id.btn_clear)
    Button btnClear;
    @InjectView(R.id.btn_m_plus)
    Button btnMPlus;
    @InjectView(R.id.btn_m_minus)
    Button btnMMinus;
    @InjectView(R.id.text)
    TextView text;
    @InjectView(R.id.btn_mrc)
    Button btnMrc;

    private double result = 0;
    private double memory = 0;
    private double resultMemory = 0;

    int recentOperator;
    boolean isOperatorKeyPushed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.inject(this);

    }

    private void setButtonToText(View view){
        Button btn = setButtonView(view);
        if (isOperatorKeyPushed == true) {
            text.setText(btn.getText());
        } else {
            text.append(btn.getText());
        }
        isOperatorKeyPushed = false;
    }

    private void operation(View view) {
        Button operatorBtn = setButtonView(view);
        double value = setValue(text);
        if (operatorBtn.getId() == R.id.btn_eq) {
            result = cal(recentOperator, result, value);
            text.setText(format(result));
            clear();
        }else{
            result = cal(recentOperator, value, result);
        }

        recentOperator = operatorBtn.getId();
        isOperatorKeyPushed = true;
    }

    private void clear(){
        recentOperator = 0;
        isOperatorKeyPushed = false;
        result = 0;
        resultMemory = 0;
    }

    private void clear_text(){
        text.setText("");
    }

    private void reset(){
        clear();
        clear_text();
    }
    private double cal(int operator, double value1, double value2) {
        switch (operator) {
            case R.id.btn_add:
                 return add(value1, value2);
            case R.id.btn_sub:
                return subtraction(value1, value2);
            case R.id.btn_mul:
                return multiplication(value1, value2);
            case R.id.btn_div:
                return division(value1, value2);
            default:
                return value1;
        }
    }


    @OnClick({
            R.id.btn_0, R.id.btn_dot,
            R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6,
            R.id.btn_7, R.id.btn_8, R.id.btn_9,
    })
    public void onBtnClicked(View view) {
        setButtonToText(view);
    }

    @OnClick({R.id.btn_eq, R.id.btn_add, R.id.btn_sub, R.id.btn_mul, R.id.btn_div})
    public void onBtnAddClicked(View view) {
        operation(view);
    }

    @OnClick(R.id.btn_clear)
    public void onBtnClear(){
        reset();
    }

    @OnClick({R.id.btn_m_plus, R.id.btn_m_minus})
    public void onBtnMemoryCal(View view){
        double value = setValue(text);
        switch (view.getId()) {
            case R.id.btn_m_plus:
                resultMemory = add(resultMemory, value);
                break;
            case R.id.btn_m_minus:
                resultMemory = subtraction(resultMemory, value);
                break;

        }
        isOperatorKeyPushed = true;
    }

    @OnClick({R.id.btn_mrc})
    public void onBtMrc(){
        text.setText(format(resultMemory));
        isOperatorKeyPushed = true;
    }

}
