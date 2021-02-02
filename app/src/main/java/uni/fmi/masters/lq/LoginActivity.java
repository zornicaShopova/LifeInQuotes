package uni.fmi.masters.lq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pref;
    SQLiteDatabaseHelper dbHelper;

    String username;
    String password;
    
    //objects
    EditText usernameEText;
    EditText passwordEText;
    Button loginB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new SQLiteDatabaseHelper(this);

        //find  object by id
        usernameEText = findViewById(R.id.usernameEditText);
        passwordEText = findViewById(R.id.passwordEditText);
        loginB = findViewById(R.id.logButton);

        loginB.setOnClickListener(onClick);

        pref = getApplicationContext().getSharedPreferences(RegisterActivity.SHARED_PREF_NAME, MODE_PRIVATE);

//        username = pref.getString(RegisterActivity.SHARED_PREF_USERNAME, "zori");
//        password = pref.getString(RegisterActivity.SHARED_PREF_PASSWORD, "1234");

    }

    // if we  have login button then we take  the username and password to login in Home  activity
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.logButton) {
                String usernameInput = usernameEText.getText().toString();
                String passwordInput = passwordEText.getText().toString();

                if (dbHelper.login(usernameInput, passwordInput)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("user", usernameInput);

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "THE PASSWORD OR USERNAME ARE INCORRECT!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        }
    };
}