package com.example.kursach_is_ready;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_3_Holy_Bible extends Activity {
    Intent intent = getIntent();

    String a[]; // создаёшь массив
    int i = 0;

    Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);// тут все как в занятии

    if (cursor.moveToFirst()) {
        int term1Index = cursor.getColumnIndex(DBHelper.KEY_TERM);
        int opr1Index = cursor.getColumnIndex(DBHelper.KEY_OPR);
        do {
            a[i] =("Termin = " + cursor.getString(term1Index) +
                    ", meaning = " + cursor.getString(opr1Index));
            i++;
        } while (cursor.moveToNext());
    }
    // …


    String Term = intent.getStringExtra("Term");
    //названия групп
    String[] groups = new String[] { a[0], a[1], a[2]};

    //названия элементов
    String[] elementsKOKOKO = new String[] { "k", "K", "o"};
    String[] elementsLALALA = new String[] { "aa", "a", "aaa"};
    String[] elementsRERERER = new String[] { "rrr", "rr", "r"};

    //коллекция для групп
    ArrayList<Map<String, String>> groupData;

    //коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    //общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;

    //список атрибутов группы или элемента
    Map<String, String> m;

    ExpandableListView elvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Activity_3_Holy_Bible);

        //заполняем коллекцию групп из массива названий групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            //заполняем список аттрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group); //имя компании
            groupData.add(m);
        }
        // список атрибутов для чтения
        String groupFrom[] = new String[] {"groupName"};
        //список АЙДИ view-элементов, в которые будут помещены аттрибуты групп
        int groupTo[] = new int[] { android.R.id.text1};

        //создаём коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        //создаём коллекцию для елементов первой группы
        childDataItem = new ArrayList<Map<String, String>>();

        //заполняем список аттрибутов для каждого элемента
        for (String phone : elementsKOKOKO) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        //добавляем в коллекцию коллекций
        childData.add(childDataItem);

        //создаём коллекцию елементов для второй группы
        for (String phone : elementsLALALA) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        //добавляем в коллекцию коллекций
        childData.add(childDataItem);

        //создаем коллекцию елементов для третьей группы
        for (String phone : elementsRERERER) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        //добавляем в коллекцию коллекций
        childData.add(childDataItem);

        //список аттрибутов и элеметнов для чтения
        String childFrom[] = new String[] { "phoneName"};
        //список АЙДИ view-элементов, в которые будут помещены аттрибуты элементов
        int childTo[] = new int[] { android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupData,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, childData, android.R.layout.simple_list_item_1, childFrom, childTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);


    }
}











