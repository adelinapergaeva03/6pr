package com.example.sixpractice;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class OnBoard3 extends AppCompatActivity implements View.OnClickListener {
    private TextView button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board3);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(OnBoard3.this, Registration.class); // или MainActivity
        startActivity(intent);
    }
}