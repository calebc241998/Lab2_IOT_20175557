package com.example.lab2_iot_20175557;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private int colorIndex = 0; // Para llevar el control de cuál color mostrar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textViewTeleMath);
        registerForContextMenu(mTextView);
        Button btnIndicaciones = findViewById(R.id.buttonIndicaciones);
        btnIndicaciones.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IndicacionesActivity.class);
            startActivity(intent);
        });

        Button btnCalcular = findViewById(R.id.buttonCalcular);
        btnCalcular.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, calculadora.class);
            startActivity(intent);
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor();
            }
        });

    }
    private void changeColor() {
        // Define los colores que quieres que tenga el TextView
        int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
        mTextView.setTextColor(colors[colorIndex]);
        colorIndex = (colorIndex + 1) % colors.length;
    }
}