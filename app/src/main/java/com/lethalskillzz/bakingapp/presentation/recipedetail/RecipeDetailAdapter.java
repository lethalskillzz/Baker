package com.lethalskillzz.bakingapp.presentation.recipedetail;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.presentation.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 06/07/2017.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private Callback mCallback;
    private List<Step> mStepList;

    public RecipeDetailAdapter(List<Step> stepList) {
        mStepList = stepList;
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

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }



    @Override
    public int getItemCount() {
        if (mOpenSourceResponseList != null && mOpenSourceResponseList.size() > 0) {
            return mOpenSourceResponseList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<OpenSourceResponse.Repo> repoList) {
        mOpenSourceResponseList.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.cover_image_view)
        ImageView coverImageView;

        @BindView(R.id.title_text_view)
        TextView titleTextView;

        @BindView(R.id.content_text_view)
        TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
            contentTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final OpenSourceResponse.Repo repo = mOpenSourceResponseList.get(position);

            if (repo.getCoverImgUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(repo.getCoverImgUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(coverImageView);
            }

            if (repo.getTitle() != null) {
                titleTextView.setText(repo.getTitle());
            }

            if (repo.getDescription() != null) {
                contentTextView.setText(repo.getDescription());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (repo.getProjectUrl() != null) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(repo.getProjectUrl()));
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onRepoEmptyViewRetryClick();
        }
    }
}
