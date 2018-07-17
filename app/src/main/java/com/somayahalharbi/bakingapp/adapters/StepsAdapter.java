package com.somayahalharbi.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.somayahalharbi.bakingapp.R;
import com.somayahalharbi.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder> {
    private StepAdapterOnClickHandler mOnClickHandler;
    private List<Step> stepsList = new ArrayList<>();

    public StepsAdapter() {
        //mOnClickHandler=onClickHandler;

    }

    @NonNull
    @Override
    public StepsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.step_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new StepsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapterViewHolder holder, int position) {
        String description = stepsList.get(position).getShortDescription();
        holder.descriptionTextView.setText(description);

    }

    @Override
    public int getItemCount() {
        if (stepsList.size() == 0)
            return 0;
        return stepsList.size();
    }

    public void setData(List<Step> steps) {
        clear();
        stepsList = steps;
        notifyDataSetChanged();

    }

    public void clear() {
        int size = stepsList.size();
        stepsList.clear();
        notifyItemRangeRemoved(0, size);

    }

    public interface StepAdapterOnClickHandler {
        void onClick(Step step);
    }

    public class StepsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.step_description)
        TextView descriptionTextView;

        public StepsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
