package uni.fmi.masters.lq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity {
    //show  the toolbar
    Toolbar toolbar;
    EditText quoteContextUET, authorUET;
    Button updateBTN, deleteBTN;
    //field for update,delete
    String id, authorName, contextQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //set toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //fields
        quoteContextUET = findViewById(R.id.quoteContextUpdateET);
        authorUET = findViewById(R.id.authorUpdateET);
        updateBTN = findViewById(R.id.updateButton);
        deleteBTN = findViewById(R.id.deleteButton);


        //first call the method  to  take  the data
        getAndSetIntentData();
        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuotesDatabase db = new QuotesDatabase(UpdateActivity.this);
                contextQuote = quoteContextUET.getText().toString().trim();
                authorName = authorUET.getText().toString().trim();
                db.updateData(id, contextQuote, authorName);

            }
        });
        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    //i had to add intent for the ID to start working the update
    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("quote") && getIntent().hasExtra("author")) {
            //getting  data  from  intent
            id = getIntent().getStringExtra("id");
            contextQuote = getIntent().getStringExtra("quote");
            authorName = getIntent().getStringExtra("author");

            //setting intent data
            quoteContextUET.setText(contextQuote);
            authorUET.setText(authorName);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

    }

    //conf  message for deleting
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete:");
        builder.setMessage("Are  you sure you want to delete  this quote?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                QuotesDatabase db = new QuotesDatabase(UpdateActivity.this);
                db.deleteOneRow(id);
                //close the current activity to reopen update  activity
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }
}