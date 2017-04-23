package com.example.kursach_is_ready;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.id;
import static android.R.attr.start;

public class Activity_2_Moreover extends AppCompatActivity implements View.OnClickListener {



    Button btnAdd, btnRead, btnClear, btnUpd, btnDel;
    EditText etOpr1, etTerm1;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2__moreover);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnUpd = (Button) findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(this);

        btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etTerm1 = (EditText) findViewById(R.id.etTerm1);
        etOpr1 = (EditText) findViewById(R.id.etOpr1);


        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v){
        String term1 = etTerm1.getText().toString();
        String opr1 = etOpr1.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_TERM, term1);
                contentValues.put(DBHelper.KEY_OPR, opr1);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);

                Intent intent = new Intent(this, Activity_3_Holy_Bible.class);
                intent.putExtra("Term", etTerm1.getText().toString());
                intent.putExtra("Opr", etOpr1.getText().toString());
                startActivity(intent);


            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int term1Index = cursor.getColumnIndex(DBHelper.KEY_TERM);
                    int opr1Index = cursor.getColumnIndex(DBHelper.KEY_OPR);
                    do {
                        Log.d("mLog", "Termin = " + cursor.getString(term1Index) +
                        ", meaning = " + cursor.getString(opr1Index));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;

            case R.id.btnUpd:
                if (term1.equalsIgnoreCase("")) {
                    break;
                }
                contentValues.put(DBHelper.KEY_TERM, term1);
                contentValues.put(DBHelper.KEY_OPR, opr1);
                int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues, null, null);

                Log.d("mLog", "updates rows count = " + updCount);

            case R.id.btnDel:
                if (term1.equalsIgnoreCase("")) {
                    break;
                }
                int delCount = database.delete(DBHelper.TABLE_CONTACTS, null, null);
                Log.d(",Log", "deleted rows count = " + delCount);

        }
        dbHelper.close();
    }
}





























