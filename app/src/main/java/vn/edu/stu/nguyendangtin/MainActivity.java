package vn.edu.stu.nguyendangtin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnDangNhap;
    CheckBox chkRememberMe;
    final String COOKIE = "COOKIE_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        chkRememberMe = findViewById(R.id.chkRememberMe);
    }

    private void addEvents() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userN = "tin";
                String passW = "123";

                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();

                if (user.equals(userN) && pass.equals(passW)) {
                    Toast.makeText(
                            MainActivity.this,
                            "Login succeeded",
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent intent = new Intent(
                            MainActivity.this,
                            MainActivity2.class
                    );
                    startActivity(intent);
                    //chk

                } else {
                    Toast.makeText(MainActivity.this, " Your username or password is incorrect. Please try again.",
                            Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getSharedPreferences(
                            COOKIE,
                            MODE_PRIVATE
                    );
                }
                //rememberme

            }
        });
    }
}