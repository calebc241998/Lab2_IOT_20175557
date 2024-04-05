package com.example.lab2_iot_20175557;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IndicacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indicaciones);
        Button btnCalcular = findViewById(R.id.buttonCalcular);
        btnCalcular.setOnClickListener(view -> {

            Intent intent = new Intent(IndicacionesActivity.this, calculadora.class);
            startActivity(intent);
        });
    }
}
