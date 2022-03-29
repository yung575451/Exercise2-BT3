package com.hungphamcom.exercise2_bt3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //variables to hold the operands and type of calculations
    private Double operand1 = null;
    private String pendingOperation = null;

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STAT_OPERAND1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);

        Button buttonEqual = findViewById(R.id.bang);
        Button buttonDivide = findViewById(R.id.chia);
        Button buttonMultiply = findViewById(R.id.nhan);
        Button buttonMinus = findViewById(R.id.tru);
        Button buttonPlus = findViewById(R.id.cong);
        Button mu=findViewById(R.id.buttonmu);

        Button ButtonNegative = findViewById(R.id.ButtonNegative);
        Button ButtonClear = findViewById(R.id.ButtonClear);
        ImageButton BackSpace = findViewById(R.id.backspace);
        View.OnClickListener listener = view -> {
            Button b = (Button) view;
            newNumber.append(b.getText().toString());
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);


        View.OnClickListener opListener = view -> {
            Button b = (Button) view;
            String op = b.getText().toString();
            String value = newNumber.getText().toString();
            try {
                Double doubleValue = Double.valueOf(value);
                performOperation(doubleValue, op);
            } catch (NumberFormatException e) {
                newNumber.setText("");
            }
            pendingOperation = op;
            displayOperation.setText((pendingOperation));
        };

        buttonEqual.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
        mu.setOnClickListener(opListener);

        View.OnClickListener negativeListener = view -> {
            String value = newNumber.getText().toString();
            if (value.length() == 0) {
                newNumber.setText("-");
            } else {
                try {
                    Double newvalue = Double.valueOf(value);
                    newvalue *= -1;
                    newNumber.setText(newvalue.toString());
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
            }
        };

        ButtonNegative.setOnClickListener(negativeListener);

        View.OnClickListener clearListener = view -> {
            operand1 = null;
            result.setText("");
        };

        ButtonClear.setOnClickListener(clearListener);

        View.OnClickListener backSpacelistener = view -> {
            String str = newNumber.getText().toString();
            if (str != null && str.length() > 0) {
                str = str.substring(0, str.length() - 1);
            }
            newNumber.setText(str);
        };

        BackSpace.setOnClickListener(backSpacelistener);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STAT_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STAT_OPERAND1);
        displayOperation.setText(pendingOperation);

    }

    private void performOperation(Double value, String operation) {
        if (null == operand1) {
            operand1 = value;
        } else {

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "÷":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "×":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "^":
                    for(int i=1;i<operand1;i++)
                    {
                        value =value*value;
                    }
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

}