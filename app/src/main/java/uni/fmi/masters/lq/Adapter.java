package uni.fmi.masters.lq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static uni.fmi.masters.lq.R.layout.custom_list_view;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Quote> quotes;

    Adapter(Context context, List<Quote> quotes) {
        this.inflater = LayoutInflater.from(context);
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        String quote = quotes.get(i).getContent();
        String author = quotes.get(i).getAuthor();
        String date = quotes.get(i).getDate();
        viewHolder.quoteTV.setText(quote);
        viewHolder.dateTV.setText(date);
        viewHolder.authorTV.setText(author);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorTV, quoteTV, dateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorTV = itemView.findViewById(R.id.authorTextView);
            quoteTV = itemView.findViewById(R.id.quoteTextView);
            dateTV = itemView.findViewById(R.id.dateTextView);

        }
    }
}
