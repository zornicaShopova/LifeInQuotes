package uni.fmi.masters.lq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //db
    static final String SHARED_PREF_FULLNAME = "fullname";
    static final String SHARED_PREF_USERNAME = "username";
    static final String SHARED_PREF_PASSWORD = "password";
    static final String SHARED_PREF_NAME = "SecretData";

    UserDatabase dbHelper;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    //objects
    EditText fullNameET;
    EditText usernameET;
    EditText passwordET;
    EditText confirmPassET;
    EditText emailET;
    Button registrationB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new UserDatabase(this);
        //take object by ID
        fullNameET = findViewById(R.id.FullNameEditText);
        usernameET = findViewById(R.id.usernameEditT);
        passwordET = findViewById(R.id.passwordEditT);
        confirmPassET = findViewById(R.id.confirmPassEditText);
        registrationB = findViewById(R.id.RegistrationButton);
        emailET = findViewById(R.id.emailEditText);

        pref = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = pref.edit();

        registrationB.setOnClickListener(onClick);

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //check if the field  is empty
            if (v.getId() == R.id.RegistrationButton) {
                if (fullNameET.getText().length() > 0
                        && emailET.getText().length() > 0
                        && usernameET.getText().length() > 0
                        && passwordET.getText().length() > 0
                        && passwordET.getText().toString().equals(confirmPassET.getText().toString())) {

                    String usernameSting = usernameET.getText().toString();
                    String passwordString = passwordET.getText().toString();
                    String fullnameString = fullNameET.getText().toString();
                    String emailString = emailET.getText().toString();

                    User user = new User();
                    user.setUsername(usernameSting);
                    user.setFullname(fullnameString);
                    user.setPassword(passwordString);
                    user.setEmail(emailString);


                    if (!dbHelper.registerUser(user)) {
                        Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please provide all the necessary information!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Intent intent = new Intent(RegisterActivity.this, RegisterConfirmationActivity.class);  // Register confirm activity
            startActivity(intent);
        }
    };
}