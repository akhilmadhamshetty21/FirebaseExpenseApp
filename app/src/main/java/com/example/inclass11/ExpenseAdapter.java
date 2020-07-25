package com.example.inclass11;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    ArrayList<Expense> expenseArrayList=new ArrayList<>();
    Context ctx;
    public static InteractMainActivity interact;

    public ExpenseAdapter(ArrayList<Expense> expenseArrayList, Context ctx) {
        this.expenseArrayList = expenseArrayList;
        this.ctx = ctx;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout=(LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(linearLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        interact=(InteractMainActivity)ctx;
        holder.tv_name.setText(expenseArrayList.get(position).getTitle());
        holder.tv_price.setText("$"+String.valueOf(expenseArrayList.get(position).getCost()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras=new Bundle();
                extras.putString("name",expenseArrayList.get(position).getTitle());
                extras.putString("category",expenseArrayList.get(position).getCategory());
                extras.putString("amount",expenseArrayList.get(position).getCost());
                extras.putString("date",expenseArrayList.get(position).getDate().toString());
                extras.putString("DocumentID",expenseArrayList.get(position).getDocumentId().toString());
                Intent i1=new Intent(ctx,ShowExpenseActivity.class);
                i1.putExtras(extras);
                ctx.startActivity(i1);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                interact.deleteitem(position,expenseArrayList.get(position).getDocumentId().toString());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseArrayList.size();
    }
    public interface InteractMainActivity{
        void deleteitem(int position,String document);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_price;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_expensename);
            tv_price=itemView.findViewById(R.id.tv_amountmain);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
