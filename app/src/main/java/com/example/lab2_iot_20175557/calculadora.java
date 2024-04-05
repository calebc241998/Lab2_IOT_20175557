package com.example.lab2_iot_20175557;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.Locale;

public class calculadora extends AppCompatActivity {

    private TextView textViewOperation, textViewInput;
    private StringBuilder currentInput, currentOperation;
    private double lastNumber, currentResult;
    private char lastOperation;
    private DecimalFormat decimalFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        decimalFormat = new DecimalFormat("0.####");
        textViewOperation = findViewById(R.id.rpta_2);
        textViewInput = findViewById(R.id.rpta_1);

        currentInput = new StringBuilder();
        currentOperation = new StringBuilder();
        lastOperation = ' ';
        currentResult = 0;

        setupNumberButtons();
        setupOperationButtons();
        setupClearButton();

    }

    private void setupNumberButtons() {
        int[] numberButtonIds = {
                R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9, R.id.button0
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::onNumberClicked);
        }
    }

    private void setupOperationButtons() {
        findViewById(R.id.button_plus).setOnClickListener(this::onOperationClicked);
        findViewById(R.id.button_multi).setOnClickListener(this::onOperationClicked);
        findViewById(R.id.button_lash).setOnClickListener(this::onOperationClicked);
        findViewById(R.id.button_resta).setOnClickListener(this::onOperationClicked);
    }



    private void setupClearButton() {
        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(view -> {
            currentOperation.setLength(0);
            currentInput.setLength(0);
            lastNumber = 0;
            currentResult = 0;
            lastOperation = ' ';
            textViewOperation.setText("");
            textViewInput.setText("");
        });
    }

    public void onEqualsClicked(View view) {
        if (currentInput.length() > 0) {
            double number = Double.parseDouble(currentInput.toString());
            calculateResult(number, lastOperation);
            currentOperation.append(String.format(Locale.US, "%s = %s",
                    decimalFormat.format(number), decimalFormat.format(currentResult)));
            textViewOperation.setText(currentOperation.toString());
            textViewInput.setText(decimalFormat.format(currentResult));

            lastOperation = '=';
            currentInput.setLength(0);
            currentOperation.setLength(0); // Opcional, si deseas comenzar una nueva operación después del igual
        }
    }


    private void onNumberClicked(View view) {
        Button b = (Button) view;
        currentInput.append(b.getText().toString());
        textViewInput.setText(currentInput.toString());
    }
    private void onOperationClicked(View view) {
        Button b = (Button) view;
        char op = b.getText().toString().charAt(0);

        if (currentInput.length() > 0) {
            double number = Double.parseDouble(currentInput.toString());
            if (lastOperation == ' ' || lastOperation == '=') {
                currentResult = number;
            } else {
                calculateResult(number, lastOperation);
            }
            lastNumber = number;
        }
        lastOperation = op;
        currentOperation.append(String.format(Locale.US, "%s %c ", decimalFormat.format(lastNumber), op));
        textViewOperation.setText(currentOperation.toString());

        currentInput.setLength(0); // Clear current input
    }

    private void calculateResult(double number, char operation) {
        switch (operation) {
            case '+':
                currentResult += number;
                break;
            case '*':
                currentResult *= number;
                break;
            case '-':
                currentResult -= number;
                break;
            case '/':
                if (number != 0) {
                    currentResult /= number;
                } else {
                    textViewInput.setText("Error");
                    currentResult = 0;
                }
                break;
            case '=':
                // Para '=' no necesitamos hacer nada aquí, pero podrías querer actualizar la UI o preparar algo.
                break;
        }
        if (operation != '=') {
            textViewInput.setText(decimalFormat.format(currentResult));
        }   
    }


}