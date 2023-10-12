package vn.edu.stu.nguyendangtin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdatePhong extends AppCompatActivity {

    final String DATABASE_NAME = "qlnv.sqlite";
    String id = "";

    Button btnLuuP, btnThoatP;
    EditText txtTenP;
    EditText txtMaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phong);

        addControls();
        intUI();
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
        btnLuuP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateP();
            }
        });
        btnThoatP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelP();
            }
        });
    }

    private void cancelP() {
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }

    private void updateP() {
        String t = txtTenP.getText().toString();
        String p = txtMaP.getText().toString();

        ContentValues con = new ContentValues();
        con.put("ten_phong", t);
        con.put("ma_phong", p);

        SQLiteDatabase db = database.initDatabase(
                this,
                "qlnv.sqlite"
        );

        db.update("phong", con, "ma_phong = ?", new String[]{id});
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }

    private void intUI() {
        Intent i = getIntent();
        id = i.getStringExtra("MAPHONG");
        SQLiteDatabase db = database.initDatabase(this, DATABASE_NAME);
        Cursor cur = db.rawQuery(
                "SELECT * FROM phong where ma_phong like ?",
                new String[]{id}
        );
        cur.moveToFirst();
        String ten = cur.getString(1);
        String mphong = cur.getString(0);

        txtTenP.setText(ten);
        txtMaP.setText(mphong);


    }

    private void addControls() {
        btnLuuP = findViewById(R.id.btnLuuP);
        btnThoatP = findViewById(R.id.btnThoatP);
        txtTenP = findViewById(R.id.txtTenP);
        txtMaP = findViewById(R.id.txtMaP);
    }
}