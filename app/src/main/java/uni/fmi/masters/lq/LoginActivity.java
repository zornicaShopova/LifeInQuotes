package uni.fmi.masters.lq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordEt;
    Button loginB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameEditText);
        passwordEt  = findViewById(R.id.passwordEditText);
        loginB  =  findViewById(R.id.loginButton);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.loginButton){
                String usernameInput = usernameET.getText().toString();
                String passwordInput = passwordEt.getText().toString();
            }
        }
    };
}