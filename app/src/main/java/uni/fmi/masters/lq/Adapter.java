package uni.fmi.masters.lq;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



//viewHolder is  in  used  to show  the data on  a row
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Quote> quotes;

    Adapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_quote,parent, false);
        return new ViewHolder(view);
    }

    //update the viewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.getAuthorTV().setText(quotes.get(position).getAuthor());
        viewHolder.getDateTV().setText(quotes.get(position).getDate());
        viewHolder.getQuoteTV().setText(quotes.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorTV, quoteTV, dateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorTV = itemView.findViewById(R.id.authorTextView);
            quoteTV = itemView.findViewById(R.id.quoteTextView);
            dateTV = itemView.findViewById(R.id.dateTextView);

        }

        public TextView getAuthorTV(){
            return authorTV;
        }
        public TextView getQuoteTV(){
            return quoteTV;
        }
        public TextView getDateTV(){
            return dateTV;
        }

    }




}
