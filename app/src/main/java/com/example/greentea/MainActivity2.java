package com.example.greentea;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView quantity, size, milkType, sugarAmount, orderTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        quantity = findViewById(R.id.quantity);
        size = findViewById(R.id.size);
        milkType = findViewById(R.id.milkType);
        sugarAmount = findViewById(R.id.sugarAmount);
        orderTotal = findViewById(R.id.orderTotal);

        quantity.setText(getIntent().getStringExtra("quantity"));
        size.setText(getIntent().getStringExtra("size"));
        milkType.setText(getIntent().getStringExtra("milk"));
        sugarAmount.setText(getIntent().getStringExtra("sugar"));
        orderTotal.setText(getIntent().getStringExtra("total"));
    }

    public void send(View view) {
        Uri uri = Uri.parse("mailto: besttea@green.cl");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);

        intent.putExtra(Intent.EXTRA_SUBJECT, "Compra de Té");
        intent.putExtra(Intent.EXTRA_TEXT,
                "Tipo de Té: Té Verde" + "\n" +
                       "Cantidad: " + quantity.getText().toString() + "\n" +
                       "Tamaño: " + size.getText().toString() + "\n" +
                       "Tipo de Leche: " + milkType.getText().toString() + "\n" +
                       "Cantidad de Azucar: " + sugarAmount.getText().toString() + "\n" +
                       "Total: " + orderTotal.getText().toString()
        );

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}