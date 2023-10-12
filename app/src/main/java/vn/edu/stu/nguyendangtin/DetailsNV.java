package vn.edu.stu.nguyendangtin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DetailsNV extends AppCompatActivity {
    final String DATABASE_NAME = "qlnv.sqlite";
    int id = -1;

    Button btnThoat;
    EditText txtName, txtPhong, txtLoai, txtAdress;
    ImageView imgDaiDien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_nv);

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

    private void addControls() {
        btnThoat = findViewById(R.id.btnThoat);
        txtName = findViewById(R.id.txtName);
        txtPhong = findViewById(R.id.txtPhong);
        txtLoai = findViewById(R.id.txtLoai);
        txtAdress = findViewById(R.id.txtAdress);
        imgDaiDien = findViewById(R.id.imgDaiDien);
    }

    private void intUI() {
        Intent i = getIntent();
        id = i.getIntExtra("ID", -1);
        SQLiteDatabase db = database.initDatabase(this, DATABASE_NAME);
        @SuppressLint("Recycle") Cursor cur = db.rawQuery(
                "SELECT * FROM nhanvien where id_nv = ?",
                new String[]{id + ""});
        cur.moveToFirst();
        String ten = cur.getString(1);
        String phong = cur.getString(2);
        byte[] anh = cur.getBlob(3);
        String loai = cur.getString(4);
        String diaChi = cur.getString(5);


        Bitmap bm = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        imgDaiDien.setImageBitmap(bm);
        txtName.setText(ten);
        txtPhong.setText(phong);

        txtLoai.setText(loai);
        txtAdress.setText(diaChi);
    }

    private void addEvents() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}