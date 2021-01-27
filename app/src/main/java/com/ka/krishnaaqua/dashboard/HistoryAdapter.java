package com.ka.krishnaaqua.dashboard;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ka.krishnaaqua.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> {
    private static final String TAG = "MovieAdapter";
    List<History> historyList;

    public HistoryAdapter ( List<History> historyList ) {
        this.historyList = historyList;

    }

    @NonNull
    @Override
    public HistoryVH onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.history_row , parent , false );
        return new HistoryVH ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull HistoryVH holder , int position ) {

        History history = historyList.get ( position );
        holder.titleTextView.setText ( history.getTitle ( ) );
        holder.yearTextView.setText ( history.getYear ( ) );
        holder.ratingTextView.setText ( history.getRating ( ) );
        holder.plotTextView.setText ( history.getPlot ( ) );

        boolean isExpanded = historyList.get ( position ).isExpanded ( );
        holder.expandableLayout.setVisibility ( isExpanded ? View.VISIBLE : View.GONE );

    }


    @Override
    public int getItemCount () {
        return historyList.size ( );
    }

    public class HistoryVH extends RecyclerView.ViewHolder {
        private static final String TAG = "MovieVH";

        ConstraintLayout expandableLayout;
        TextView titleTextView, yearTextView, ratingTextView, plotTextView;

        public HistoryVH ( @NonNull final View itemView ) {
            super ( itemView );

            titleTextView    = itemView.findViewById ( R.id.titleTextView );
            yearTextView     = itemView.findViewById ( R.id.yearTextView );
            ratingTextView   = itemView.findViewById ( R.id.ratingTextView );
            plotTextView     = itemView.findViewById ( R.id.plotTextView );
            expandableLayout = itemView.findViewById ( R.id.expandableLayout );


            titleTextView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View view ) {

                    History history = historyList.get ( getAdapterPosition ( ) );
                    history.setExpanded ( !history.isExpanded ( ) );
                    notifyItemChanged ( getAdapterPosition ( ) );

                }
            } );
        }

    }
}
