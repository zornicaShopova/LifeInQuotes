package uni.fmi.masters.lq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;


public class AddQuote extends AppCompatActivity {
    EditText quoteContextET;
    EditText authorET;
    //show  the toolbar
    Toolbar toolbar;
    //date and time
    Calendar calendar;
    String todaysDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        quoteContextET = findViewById(R.id.quoteContextUpdateET);
        authorET = findViewById(R.id.authorUpdateET);
        //set toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //change the title  of the toolbar
        Objects.requireNonNull(getSupportActionBar()).setTitle("Add Quote");
        //arrow for  back  btn
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //save the author
        authorET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //get current date and time
        calendar = Calendar.getInstance();
        todaysDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH); //  we  write +1 because  its start at 0 and we have to add 1 for december
        currentTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));

        Log.d("calendar", "Date and time: " + todaysDate + " and " + currentTime);
    }

    //it will  add zero  before  the seconds
    private String pad(int i) {
        if (i > 0)
            return "0" + i;
        return String.valueOf(i);
    }

    //show toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.saveItemBtn){
            Quote quote = new Quote(authorET.getText().toString(),quoteContextET.getText().toString(),todaysDate,currentTime);
            //call  db and insert the quote data
            QuotesDatabase db  = new QuotesDatabase(this);
            db.addQuote(quote);
            Toast.makeText(this,"Save quote",Toast.LENGTH_SHORT).show();
            //when is save to return in the home activity
            onBackPressed();
        }

        if(item.getItemId() == R.id.closeItemBtn){
            Toast.makeText(this,"Quote not saved",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}