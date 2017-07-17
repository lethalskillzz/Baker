package com.lethalskillzz.bakingapp.presentation.recipedetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.presentation.base.BaseViewHolder;

import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ibrahimabdulkadir on 06/07/2017.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private Callback mCallback;
    private List<Step> mStepList;

    public RecipeDetailAdapter(List<Step> stepList) {
        setSteps(stepList);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step_list, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return mStepList.size();
    }

//    @Override
//    public int getItemCount() {
//        if (mStepList != null && mStepList.size() > 0) {
//            return mStepList.size();
//        } else {
//            return 1;
//        }
//    }

    private void setSteps(@NonNull List<Step> steps) {
        mStepList = steps;
    }

    public void refreshStepList(@NonNull List<Step> stepList) {
        setSteps(stepList);
        mStepList.addAll(stepList);
        notifyDataSetChanged();
    }

    class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_step_list_image)
        ImageView thumbImageView;

        @BindView(R.id.item_step_list_id)
        TextView idTextView;

        @BindView(R.id.item_step_list_desc)
        TextView descTextView;


        @BindString(R.string.step_id_text)
        String idText;

        @BindString(R.string.step_desc_text)
        String descText;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            thumbImageView.setImageDrawable(null);
            idTextView.setText("");
            descTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Step step = mStepList.get(position);
            int stepId = step.id();
            String desc = step.shortDescription();

            if(step.videoURL()!=null && !step.videoURL().matches("")) {
                thumbImageView.setImageResource(R.drawable.ic_video);
            } else {
                thumbImageView.setImageResource(R.drawable.ic_no_video);
            }

            if(stepId == 0)
                idTextView.setText("  ");
            else
                idTextView.setText(String.format(Locale.US, idText, stepId));

            descTextView.setText(desc);

            itemView.setOnClickListener(v -> {
                if (mCallback != null)
                    mCallback.onRecipeStepClick(stepId);
            });
        }

    }

    public interface Callback {
        void onRecipeStepClick(int stepId);
    }


}
