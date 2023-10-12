package vn.edu.stu.nguyendangtin;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPhong extends BaseAdapter {

    Activity context;
    ArrayList<Phong> listP;

    public AdapterPhong(Activity context, ArrayList<Phong> listP) {
        this.context = context;
        this.listP = listP;
    }

    @Override
    public int getCount() {
        return listP.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listphong, null);
        TextView txtIDPhong = row.findViewById(R.id.txtIDPhong);
        TextView txtTenPhong = row.findViewById(R.id.txtTenPhong);
        Button btnSuaPhong = row.findViewById(R.id.btnSuaPhong);
        Button btnXoaPhong = row.findViewById(R.id.btnXoaPhong);

        Phong p = listP.get(position);
        txtIDPhong.setText(p.maPhong);
        txtTenPhong.setText(p.tenPhong);

        btnSuaPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdatePhong.class);
                i.putExtra("MAPHONG", p.maPhong);
                context.startActivity(i);
            }
        });

        btnXoaPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("Xác Nhận Xóa");
                builder.setMessage("Bạn có muốn xóa phòng này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(p.maPhong);
                    }


                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return row;
    }

    private void delete(String maPhong) {
        SQLiteDatabase db = database.initDatabase(context, "qlnv.sqlite");
        db.delete("phong", "ma_phong like ? ", new String[]{maPhong});
        listP.clear();
        Cursor cur = db.rawQuery("SELECT * FROM phong", null);
        while (cur.moveToNext()) {
            String ma = cur.getString(0);
            String ten = cur.getString(1);
            listP.add(new Phong(ma, ten));
        }
        notifyDataSetChanged();
    }
}