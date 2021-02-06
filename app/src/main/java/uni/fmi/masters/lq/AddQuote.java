package uni.fmi.masters.lq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;


public class AddQuote extends AppCompatActivity {
    EditText quoteContextET;
    EditText authorET;
    //show  the toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        quoteContextET = findViewById(R.id.quoteContextEditText);
        authorET = findViewById(R.id.authorEditText);
        //set toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //change the title  of the toolbar
        getSupportActionBar().setTitle("Add Quote");
        //save the author
        authorET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //show toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_edit_menu, menu);
        return true;
    }
}