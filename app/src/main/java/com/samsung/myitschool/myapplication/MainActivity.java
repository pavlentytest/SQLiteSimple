package com.samsung.myitschool.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button, button2,button3;
    EditText editext;
    DBHelper dbhelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        editext = findViewById(R.id.editText);

        dbhelper = new DBHelper(this,"dbstudent",null,1);
    }

    @Override
    public void onClick(View v) {

        ContentValues cv = new ContentValues();
        String fio = editext.getText().toString();

        SQLiteDatabase db = dbhelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.button:
                Log.e("IIIIIIII","Insert values...");
                cv.put("name",fio);
                db.insert("student",null,cv);
                break;
            case R.id.button2:
                Log.e("SSSSSSSSSS","Show values ...");
                Cursor c = db.query("student",null,null,null,null,null,"name");
                if(c.moveToFirst()) {
                    int id_index = c.getColumnIndex("id");
                    int name_index = c.getColumnIndex("name");
                    do{
                        Log.e("VVVVVVV","id = "+c.getInt(id_index)+"; name = "+c.getString(name_index));
                    } while(c.moveToNext());
                }
                break;
            case R.id.button3:
                Log.e("DDDDDDDDDDDDDDD","Delete all values...");
                int clear = db.delete("student",null,null);
                break;
        }
        dbhelper.close();

    }
}
