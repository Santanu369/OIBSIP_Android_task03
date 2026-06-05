package com.example.calculatorjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput;

    Boolean lastDot = false;
    Boolean lastNumeric = false;

    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInput = findViewById(R.id.tvInput);

    }

    public void onDigit(View v) {
        if (v instanceof Button) {
            String text = ((Button) v).getText().toString();
            tvInput.append(text);
            lastNumeric = true;
            lastDot = false;
        }
    }

    public void onOperator(View v) {
        if (!lastNumeric && (!tvInput.getText().toString().isEmpty())) return;
        if (v instanceof Button) {
            String text = ((Button) v).getText().toString();
            tvInput.append(text);
            lastNumeric = false;
            lastDot = false;
        }
    }

    public void onClear(View v) {
        tvInput.setText("");
    }

    public void onDecimalPoint(View v) {
        if (lastNumeric && !lastDot) {
            tvInput.append(".");
            lastNumeric = false;
            lastDot = true;
        }
    }

    public void onResult(View v) {
        Expression e = new Expression(tvInput.getText().toString());
        result = e.calculate();
        tvInput.setText(String.valueOf(result));
    }
}