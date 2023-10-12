package vn.edu.stu.nguyendangtin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class About_Activity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    EditText txtPhoneN;
    Button btnDial, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        txtPhoneN = findViewById(R.id.txtPhoneN);
        btnCall = findViewById(R.id.btnCall);
    }

    private void addEvents() {
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    makePhoneCall();
                } else {
                    requestPermissions();
                }
            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(
                About_Activity.this,
                Manifest.permission.CALL_PHONE
        );
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                About_Activity.this,
                Manifest.permission.CALL_PHONE
        )) {
            Toast.makeText(
                    About_Activity.this,
                    "Vui lòng cấp quyền thủ công trong app Setting",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            ActivityCompat.requestPermissions(
                    About_Activity.this,
                    new String[]{
                            Manifest.permission.CALL_PHONE
                    },
                    123
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==123){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(About_Activity.this,
                        "bạn đã từ chối cấp quyền gọi.Hủy thao tác",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void makePhoneCall() {
        String phoneN = txtPhoneN.getText().toString();
        Intent intent = new Intent(
                Intent.ACTION_CALL,
                Uri.parse("tel:" + phoneN)
        );
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng STU = new LatLng(10.738054681731345, 106.67782920081302);
        mMap.addMarker(new MarkerOptions().position(STU).title("Đại Học Công Nghệ Sài Gòn"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(STU,18));
    }
}