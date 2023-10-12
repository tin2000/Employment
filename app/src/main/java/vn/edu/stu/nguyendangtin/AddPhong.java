package vn.edu.stu.nguyendangtin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;

public class AddPhong extends AppCompatActivity {

    final String DATABASE_NAME = "qlnv.sqlite";

    Button btnLuuP, btnThoatP;
    EditText txtMa, txtTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phong);

        addControls();
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

    private void addControls() {
        btnLuuP = findViewById(R.id.btnLuuP);
        btnThoatP = findViewById(R.id.btnThoatP);
        txtMa = findViewById(R.id.txtMa);
        txtTen = findViewById(R.id.txtTen);
    }

    private void addEvents() {
        btnLuuP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        btnThoatP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void add() {
        String ma = txtMa.getText().toString();
        String ten = txtTen.getText().toString();

        ContentValues con = new ContentValues();
        con.put("ma_phong", ma);
        con.put("ten_phong", ten);

        SQLiteDatabase db = database.initDatabase(
                this,
                "qlnv.sqlite"
        );
        db.insert("phong", null, con);
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }

    private void cancel() {
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }
}