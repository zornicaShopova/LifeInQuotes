package uni.fmi.masters.lq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RegisterConfirmationActivity extends AppCompatActivity {

    Button confLoginB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirmation);

        confLoginB = findViewById(R.id.ConfirmLoginBtn);

        confLoginB.setOnClickListener(onClick);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterConfirmationActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };
}