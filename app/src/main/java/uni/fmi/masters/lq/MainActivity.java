package uni.fmi.masters.lq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    Button loginBtn ;
    Button   registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn  =   findViewById(R.id.loginButton);
        registerBtn  = findViewById(R.id.registerButton);

        loginBtn.setOnClickListener(onClick);
        registerBtn.setOnClickListener(onClick);
    }
//this metod open  new activity when is click on a button
    View.OnClickListener onClick  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()  == R.id.loginButton){
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        }
    };
}