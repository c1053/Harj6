package com.androidjson.jsonstoreinsqlite_androidjsoncom;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class FragmentDB extends Fragment {


    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
   ListView LISTVIEW;
    ArrayList<String> ID_Array;
    ArrayList<String> Subject_NAME_Array;
    ArrayList<String> Subject_FullForm_Array;
    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment_db);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        Subject_NAME_Array = new ArrayList<String>();

        Subject_FullForm_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Toast.makeText(FragmentDB.this, ListViewClickItemArray.get(position).toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        Subject_NAME_Array.clear();
        Subject_FullForm_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                //Inserting Column Name into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Subject_Name)));

                Subject_NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Subject_Name)));

                Subject_FullForm_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_SubjectFullForm)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(FragmentDB.this,

                ID_Array,
                Subject_NAME_Array,
                Subject_FullForm_Array
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();



        View view =inflater.inflate(R.layout.fragment_fragment_db, container, false);



        return view;
    }






}
