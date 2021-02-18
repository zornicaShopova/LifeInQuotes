package uni.fmi.masters.lq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//viewHolder is  in  used  to show  the data on  a row
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Quote> quotes;
    //the context from homeActivity
    Context context;
    HomeFragment activity;

    Adapter(HomeFragment activity, List<Quote> quotes, Context context ) {
        this.quotes = quotes;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_quote, parent, false);
        return new ViewHolder(view);


    }

    //update the viewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.getAuthorTV().setText(quotes.get(position).getAuthor());
        viewHolder.getDateTV().setText(quotes.get(position).getDate());
        viewHolder.getQuoteTV().setText(quotes.get(position).getContent());

        //click on item and show update layout
        viewHolder.singleRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(quotes.get(position).getID()));
                intent.putExtra("quote", String.valueOf(quotes.get(position).getContent()));
                intent.putExtra("author", String.valueOf(quotes.get(position).getAuthor()));

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //refresh the updated item
                activity.startActivityForResult(intent,1);


            }
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }


    //custom  adapter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorTV, quoteTV, dateTV;
        LinearLayout singleRowLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorTV = itemView.findViewById(R.id.authorTextView);
            quoteTV = itemView.findViewById(R.id.quoteTextView);
            dateTV = itemView.findViewById(R.id.dateTextView);
            singleRowLayout = itemView.findViewById(R.id.single_row_layout);
        }

        public TextView getAuthorTV() {
            return authorTV;
        }

        public TextView getQuoteTV() {
            return quoteTV;
        }

        public TextView getDateTV() {
            return dateTV;
        }

    }


}
