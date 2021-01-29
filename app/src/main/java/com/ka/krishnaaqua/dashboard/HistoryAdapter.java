package com.ka.krishnaaqua.dashboard;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.data.HistoryData;
import com.ka.krishnaaqua.data.Order;
import com.ka.krishnaaqua.network.ServerResponse;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> {
    private static final String TAG = "MovieAdapter";
    List<Order> historyList;
    private int money,paisa;

    public HistoryAdapter ( List<Order> historyList ) {
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

        Order historyData = (Order) historyList.get ( position );

        holder.titleTextView.setText ( historyData.getId ( )+". "+ historyData.getStartDate ());
        holder.yearTextView.setText ( historyData.getStartDate ( ) );
        holder.ratingTextView.setText ( historyData.getEndDate ( ) );
        holder.plotTextViewValue.setText ( historyData.getQty ( )+"   Litres" );
//        holder.TotalValue.setText ( historyData.getTotal ( ) );
        paisa = Integer.parseInt ( historyData.getTotal () );
        money = paisa/100;
        holder.TotalValue.setText ("₹"+ String.valueOf ( money ) );



        boolean isExpanded = historyData.isExpanded ( );
        holder.expandableLayout.setVisibility ( isExpanded ? View.VISIBLE : View.GONE );

    }


    @Override
    public int getItemCount () {
        return historyList.size ( );
    }

    public class HistoryVH extends RecyclerView.ViewHolder {
        private static final String TAG = "MovieVH";

        ConstraintLayout expandableLayout;
        TextView titleTextView, yearTextView, ratingTextView, plotTextView, TotalValue,plotTextViewValue;

        public HistoryVH ( @NonNull final View itemView ) {
            super ( itemView );

            titleTextView    = itemView.findViewById ( R.id.titleTextView );
            yearTextView     = itemView.findViewById ( R.id.yearTextView );
            ratingTextView   = itemView.findViewById ( R.id.ratingTextView );
            plotTextView     = itemView.findViewById ( R.id.plotTextView );
            plotTextViewValue = itemView.findViewById ( R.id.plotTextViewValue );
            expandableLayout = itemView.findViewById ( R.id.expandableLayout );
            TotalValue       = itemView.findViewById ( R.id.textViewTotal );


            titleTextView.setOnClickListener ( view -> {

                Order historyData = (Order) historyList.get ( getAdapterPosition ( ) );
                historyData.setExpanded ( !historyData.isExpanded ( ) );
                notifyItemChanged ( getAdapterPosition ( ) );

            } );
        }

    }
}
