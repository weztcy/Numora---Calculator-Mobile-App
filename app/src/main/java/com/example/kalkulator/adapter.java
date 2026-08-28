package com.example.kalkulator;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ExampleViewHolder> {
    private final ArrayList<history> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mShowInput1;
        public TextView mSimbolHitung;
        public TextView mShowInput2;
        public TextView mHasilCount;

        @SuppressLint("CutPasteId")
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mShowInput1 = itemView.findViewById(R.id.show_input1);
            mSimbolHitung = itemView.findViewById(R.id.simbol_hitung);
            mShowInput2 = itemView.findViewById(R.id.show_input2);
            mHasilCount = itemView.findViewById(R.id.hasil_count);
        }
    }

    public adapter(ArrayList<history> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        history currentItem = mExampleList.get(position);

        holder.mShowInput1.setText(currentItem.getInput1());
        holder.mShowInput2.setText(currentItem.getInput2());
        holder.mSimbolHitung.setText(currentItem.getSimbolHitung());
        holder.mHasilCount.setText(currentItem.getHasilCount());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
