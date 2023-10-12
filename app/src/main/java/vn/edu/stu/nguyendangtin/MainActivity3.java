package vn.edu.stu.nguyendangtin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    final String DATABASE_NAME = "qlnv.sqlite";
    SQLiteDatabase db;

    ListView lvPhong;
    ArrayList<Phong> listP;
    AdapterPhong adapterP;
    Button btnThemP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        addControls();
        readData();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itHome:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                return true;

            case R.id.itAbout:
                Intent i = new Intent(this, About_Activity.class);
                startActivity(i);
                return true;
            case R.id.itExit:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    private void addEvents() {
        btnThemP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, AddPhong.class);
                startActivity(i);
            }
        });
    }

    private void readData() {
        db = database.initDatabase(this, DATABASE_NAME);
        Cursor cur = db.rawQuery("SELECT * FROM phong", null);
        listP.clear();
        for (int i = 0; i < cur.getCount(); i++) {
            cur.moveToPosition(i);
            String id = cur.getString(0);
            String ten = cur.getString(1);
            listP.add(new Phong(id, ten));

        }
        adapterP.notifyDataSetChanged();
    }

    private void addControls() {
        lvPhong = findViewById(R.id.lvPhong);
        listP = new ArrayList<>();
        adapterP = new AdapterPhong(this, listP);
        lvPhong.setAdapter(adapterP);
        btnThemP = findViewById(R.id.btnThemP);
    }


}