package com.vikingwarlock.calculator;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double stack = 0;
    private double display = 0;
    TextView resultView;
    TextView functionView;
    //功能键
    private boolean functionButton = false;
    //功能:
    //0:无功能  1:加  2:减  3:乘  4:除  5:等号
    private int function = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView = (TextView) findViewById(R.id.result);
        functionView = (TextView) findViewById(R.id.textFunction);
        numberButtonSetup();
        functionButtonSetup();
    }

    private void numberButtonSetup() {
        final Button btn0 = (Button) findViewById(R.id.btn0);
        final Button btn1 = (Button) findViewById(R.id.btn1);
        final Button btn2 = (Button) findViewById(R.id.btn2);
        final Button btn3 = (Button) findViewById(R.id.btn3);
        final Button btn4 = (Button) findViewById(R.id.btn4);
        final Button btn5 = (Button) findViewById(R.id.btn5);
        final Button btn6 = (Button) findViewById(R.id.btn6);
        final Button btn7 = (Button) findViewById(R.id.btn7);
        final Button btn8 = (Button) findViewById(R.id.btn8);
        final Button btn9 = (Button) findViewById(R.id.btn9);
//防止空指针
        if (btn0 == null || btn1 == null || btn2 == null || btn3 == null || btn4 == null || btn5 == null || btn6 == null || btn7 == null || btn8 == null || btn9 == null)
            return;

        setupButtonClick(btn0, 0);
        setupButtonClick(btn1, 1);
        setupButtonClick(btn2, 2);
        setupButtonClick(btn3, 3);
        setupButtonClick(btn4, 4);
        setupButtonClick(btn5, 5);
        setupButtonClick(btn6, 6);
        setupButtonClick(btn7, 7);
        setupButtonClick(btn8, 8);
        setupButtonClick(btn9, 9);

    }

    private void functionButtonSetup() {
        final Button btn_plus = (Button) findViewById(R.id.btnplus);
        final Button btn_minus = (Button) findViewById(R.id.btnminus);
        final Button btn_multiply = (Button) findViewById(R.id.btnmulti);
        final Button btn_divide = (Button) findViewById(R.id.btndiv);
        final Button btn_equal = (Button) findViewById(R.id.btnequal);
        final Button btn_clear = (Button) findViewById(R.id.btnclear);
        if (btn_plus == null || btn_multiply == null || btn_minus == null || btn_equal == null || btn_divide == null || btn_clear == null)
            return;

        btn_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                functionButton = true;
                function = 1;
                stack = display;
                functionView.setText("+");
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                functionButton = true;
                function = 2;
                stack = display;
                functionView.setText("-");
            }
        });
        btn_multiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                functionButton = true;
                function = 3;
                stack = display;
                functionView.setText("*");
            }
        });
        btn_divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                functionButton = true;
                function = 4;
                stack = display;
                functionView.setText("/");
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                functionButton = false;
                function = 0;
                display = 0;
                stack = 0;
                functionView.setText(null);
                renderView();
            }
        });
        btn_equal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                functionButton = true;
                calculate();
                stack = 0;
                renderView();
                functionView.setText(null);
                function = 0;

            }
        });
    }

    private void setupButtonClick(Button btn, final int num) {
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleNumberTouch(num);
            }
        });

    }

    private void handleNumberTouch(int num) {
        if (functionButton) {
            display = num;
            functionButton=false;
        } else {
            display = display * 10 + num;
        }
        renderView();
    }

    private void calculate() {
        switch (function) {
            case 1: {
                display = stack + display;
                break;
            }
            case 2: {
                display = stack - display;
                break;
            }
            case 3: {
                display = stack * display;
                break;
            }
            case 4: {
                display = stack / display;
                break;
            }

        }
    }

    private void renderView() {
        long isInteger = (long) display;
        if (isInteger == display) {
            resultView.setText(String.valueOf(isInteger));
        } else {
            resultView.setText(String.valueOf(display));
        }
    }

}
