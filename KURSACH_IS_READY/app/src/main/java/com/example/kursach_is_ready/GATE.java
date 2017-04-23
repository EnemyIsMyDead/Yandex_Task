package com.example.kursach_is_ready;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.kursach_is_ready.R;

public class GATE extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);

        Button Practice = (Button) findViewById(R.id.Practice);
        Button Moreower = (Button) findViewById(R.id.Moreover);
        Button Holy_Bible = (Button) findViewById(R.id.Holy_Bible);

        Practice.setOnClickListener(this);
        Moreower.setOnClickListener(this);
        Holy_Bible.setOnClickListener(this);

    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.Practice:
                intent = new Intent(this, Activity_1_Practice.class);
                startActivity(intent);
                break;
            case R.id.Moreover:
                intent = new Intent(this, Activity_2_Moreover.class);
                startActivity(intent);
                break;
            case R.id.Holy_Bible:
                intent = new Intent(this, Activity_3_Holy_Bible.class);
                startActivity(intent);
                break;

        }
    }


}