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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    final String DATABASE_NAME = "qlnv.sqlite";
    SQLiteDatabase db;
//    public int id = -1;

    ListView lView;
    ArrayList<NhanVien> list;
    AdapterNhanVien adapter;
    Button btnThem, btnPhong;
    // Get max available VM memory, exceeding this amount will throw an
// OutOfMemory exception.
//    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//
//    // Use 1/8th of the available memory for this memory cache.
//    final int cacheSize = maxMemory / 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

            case R.id.itAbout:
                Intent i = new Intent(this, About_Activity.class);
                startActivity(i);
                break;
            case R.id.itExit:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity2.this, AddActivity.class);
                startActivity(i);
            }
        });

        btnPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(i);
            }
        });
    }

    //ham khoi tao gia tri
    private void addControls() {
        lView = findViewById(R.id.lView);
        list = new ArrayList<>();
        adapter = new AdapterNhanVien(this, list);
        lView.setAdapter(adapter);
        btnThem = findViewById(R.id.btnThem);
        btnPhong = findViewById(R.id.btnPhong);
    }

    //doc du lieu tren SQLite
    private void readData() {
        db = database.initDatabase(this, DATABASE_NAME);
        Cursor cur = db.rawQuery("SELECT * FROM nhanvien", null);
        list.clear();
        //truyen du lieu
        for (int i = 0; i < cur.getCount(); i++) {
            cur.moveToPosition(i);
            int id = cur.getInt(0);
            String ten = cur.getString(1);
            String phong = cur.getString(2);
            byte[] anh = cur.getBlob(3);
            String loai = cur.getString(4);
            String dc = cur.getString(5);
            //add du lieu vao list data
            list.add(new NhanVien(id, ten, phong, anh, loai, dc));
        }
        adapter.notifyDataSetChanged();

    }

}