package com.example.kursach_is_ready;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_1_Practice extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1__practice);

        Button btnImpossible = (Button) findViewById(R.id.btnImpossible);
        Button btn4Variant = (Button) findViewById(R.id.btn4Variant);
        Button btnEndlessSummer = (Button) findViewById(R.id.btnEndless_Summer);
        Button btnFallBack1_0 = (Button) findViewById(R.id.btnFallBack1_0);

        btnImpossible.setOnClickListener(this);
        btn4Variant.setOnClickListener(this);
        btnEndlessSummer.setOnClickListener(this);
        btnFallBack1_0.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnImpossible:
                intent = new Intent(this, Activity_1_1_Impossible.class);
                intent.putExtra("caller", "Limited");
                startActivity(intent);
                break;

            case R.id.btn4Variant:
                intent = new Intent(this, Activity_1_2_4Variant.class);
                intent.putExtra("caller", "Limited");
                startActivity(intent);
                break;

            case R.id.btnEndless_Summer:
                intent = new Intent(this, Activity_1_3_Endless_Summer.class);
                startActivity(intent);
                break;

            case R.id.btnFallBack1_0:
                intent = new Intent(this, GATE.class);
                startActivity(intent);
                break;
        }
    }
}
