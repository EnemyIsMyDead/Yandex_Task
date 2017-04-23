package com.example.kursach_is_ready;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class Activity_1_1_Impossible extends AppCompatActivity implements View.OnClickListener {
    int QuestNum = 1; // номер вопроса в попытке
    int Limit;  //количество вопросов в попытке
    int type=0; // Ограниченная или бесконечная
    int rightAnswerCount=0; //счётчик правильных ответов
    String caller; //откуда вызвали 1_1
    String strLimit;    // ?_?
    String strQuestNum;  //текст заголовка вопроса...?
    String rightAnswer; //ф для правильного ответа
    String Question;
    String nextQuestion; //ф перехода к сл вопросу
    boolean needChekAnsw; // ?_?

    Button btnExit;        //кнопка выхода
    TextView tvQuestNumber;     //заголовок-номер вопроса
    TextView tvTerm;            //вопрос
    EditText etDefine;          //ответ
    Button btnSkipWord;         //пропустить слово
    Button btnReady;            //проверить ответ
    TextView tvRightAnswer;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_1__impossible);



        btnExit = (Button) findViewById(R.id.btnExit);
        tvQuestNumber = (TextView) findViewById(R.id.tvQuestNumber);
        tvTerm = (TextView) findViewById(R.id.tvTerm);
        etDefine = (EditText) findViewById(R.id.etDefine);
        btnSkipWord = (Button) findViewById(R.id.btnSkipWord);
        btnReady = (Button) findViewById(R.id.btnReady);
        tvRightAnswer=(TextView) findViewById(R.id.tvRightAnswer);

        dbHelper = new DBHelper(this);

        Intent intentIN = getIntent(); //определяем caller
        caller = intentIN.getStringExtra("caller");

        if (caller.equals("Limited")) {
            Limit = 6;
            strLimit = "of" + Integer.toString(Limit);
            type = 1;
        }
        else if (caller.equals("Unlimited")) {
            type = 2;
            strLimit = "of infinity";
        }
        strQuestNum=Integer.toString(QuestNum) + strLimit;
        tvQuestNumber.setText(strQuestNum); // заголовок
    }
        int k = 0;


    public void checkanswer(boolean needChekAnsw) {


        SQLiteDatabase database = dbHelper.getWritableDatabase();


        String c[] = new String[0];
        String b[] = new String[0]; // создаёшь массив
        int i = 0;

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);// тут все как в занятии

        if (cursor.moveToFirst()) {
            int term1Index = cursor.getColumnIndex(DBHelper.KEY_TERM);
            int opr1Index = cursor.getColumnIndex(DBHelper.KEY_OPR);
            int IDIndex = cursor.getColumnIndex(DBHelper.KEY_OPR);

            do {
                b[i] = (cursor.getString(term1Index));
                c[i] =(cursor.getString(opr1Index));
                i++;
            } while (cursor.moveToNext());
        }



        // И ЭТО ТВОЙ ЗВЁЗДНЫЙ ЧАС.
        int m;

        Random myRandom = new Random();
        m = myRandom.nextInt(i);
        int k = myRandom.nextInt(i);


            nextQuestion = b[k];
            Question = b[m];
            rightAnswer = c[m];



        if (needChekAnsw==true){
            if (etDefine.getText().equals(rightAnswer)) {
                rightAnswerCount++;
                etDefine.setBackgroundColor(GREEN);
            }
            else {
                etDefine.setTextColor(RED);
                tvRightAnswer.setText(rightAnswer);
                tvRightAnswer.setBackgroundColor(GREEN);
            }
        }

        else if (needChekAnsw==false) {
            tvRightAnswer.setText(rightAnswer);
            tvRightAnswer.setBackgroundColor(GREEN);
        }

        try {Thread.sleep(3000);}
        catch (Exception e) {}

        updateQuestion();
    }

    // как тебя вызвать, друг?
    public void updateQuestion(){
        //обновить номер вопроса в заголовке
        QuestNum++;
        strQuestNum=Integer.toString(QuestNum)+strLimit;
        tvQuestNumber.setText(strQuestNum);
        //обновить вопрос
        tvTerm.setText(nextQuestion); //вывести новый вопрос
        //обновить поле ввода ответа
        etDefine.setBackgroundColor(WHITE);
        etDefine.setText("");
        //обновить поле вывода правильного ответа
        tvRightAnswer.setBackgroundColor(WHITE);
        tvRightAnswer.setText("");

    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnExit:
                finish();
                Intent intent = new Intent(this, Activity_1_Practice.class);
                startActivity(intent);
                break;

            case R.id.btnReady | R.id.btnSkipWord:
                if(v.getId()==R.id.btnReady) {
                    needChekAnsw=true;
                }
                else if(v.getId()==R.id.btnSkipWord) {
                    needChekAnsw=false;

                }

                checkanswer(needChekAnsw);
                if (type==1){
                    if (QuestNum==Limit) {
                        Intent intentStatistic = new Intent(this, Activity_1_4_Statistic.class);
                        startActivity(intentStatistic);
                    }
                    else {
                            updateQuestion();
                    }
                }

                else if (type==2){
                    updateQuestion();
                }
            break;
        }
    }
}



















