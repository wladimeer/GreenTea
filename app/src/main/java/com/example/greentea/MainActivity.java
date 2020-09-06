package com.example.greentea;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner listSize, listMilk, listSugar;
    private ArrayAdapter<String> adapter;
    private TextView quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listSize = findViewById(R.id.listSize);
        listMilk = findViewById(R.id.listMilk);
        listSugar = findViewById(R.id.listSugar);
        quantity = findViewById(R.id.quantity);

        listSize.setAdapter(sizes());
        listMilk.setAdapter(milks());
        listSugar.setAdapter(sugars());
    }

    public ArrayAdapter<String> sizes() {
        String[] array = {
                "Small (5$/cup)", "Medium (6$/cup)",
                "Big (7$/cup)"
        };

        adapter = new ArrayAdapter<>(
                this, R.layout.item_size, array
        );

        return adapter;
    }

    public ArrayAdapter<String> milks() {
        String[] array = {
                "None", "1% Milk", "4% Milk", "8% Milk"
        };

        adapter = new ArrayAdapter<>(
                this, R.layout.item_milk, array
        );

        return adapter;
    }

    public ArrayAdapter<String> sugars() {
        String[] array = {
                "0% - Not sweet", "20% - Little sweet",
                "50% - Half sweet", "90% - Half sweet"
        };

        adapter = new ArrayAdapter<>(
                this, R.layout.item_sugar, array
        );

        return adapter;
    }

    public void less(View view) {
        int number = Integer.parseInt(
                quantity.getText().toString()
        );

        if(number > 0) {
            number -= 1;
            String result = String.valueOf(number);
            quantity.setText(result);
        }
    }

    public void more(View view) {
        int number = Integer.parseInt(
                quantity.getText().toString()
        );

        if(number < 10) {
            number += 1;
            String result = String.valueOf(number);
            quantity.setText(result);
        }
    }

    public void prepare(View view) {
        String typeTea = listSize.getSelectedItem().toString();
        int quantityTea = Integer.parseInt(quantity.getText().toString());
        String error = "";
        int total = 0;

        switch (typeTea) {
            case "Small (5$/cup)":
                total = quantityTea * 5;
                break;
            case "Medium (6$/cup)":
                total = quantityTea * 6;
                break;
            case "Big (7$/cup)":
                total = quantityTea * 7;
                break;
        }

        if(quantityTea < 1) {
            error = "Añade al Menos un Té";
        }

        if(error.isEmpty()) {
            Intent intent = new Intent(this, MainActivity2.class);

            intent.putExtra("quantity", quantity.getText().toString());
            intent.putExtra("size", listSize.getSelectedItem().toString());
            intent.putExtra("milk", listMilk.getSelectedItem().toString());
            intent.putExtra("sugar", listSugar.getSelectedItem().toString());
            intent.putExtra("total", "$" +  total + ".00");

            startActivity(intent);
        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
    }

}