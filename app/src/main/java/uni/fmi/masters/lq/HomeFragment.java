package uni.fmi.masters.lq;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<Quote> quotes ;
    QuotesDatabase db;
    Adapter adapter;

    //use onCreateView to create and display the layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.quotesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        quotes = new ArrayList<>();
        db = new QuotesDatabase(view.getContext());
        quotes = db.getQuotes();

        //activity added
        adapter = new Adapter(HomeFragment.this,quotes,getActivity().getApplicationContext()); // take  context from  HomeActivity
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        return view;

    }

    // refresh the updated item
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
          // recreate();
        }
    }

}

