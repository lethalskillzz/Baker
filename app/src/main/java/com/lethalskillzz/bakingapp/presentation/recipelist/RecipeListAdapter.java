package com.lethalskillzz.bakingapp.presentation.recipelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.model.Recipe;

import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ibrahimabdulkadir on 28/06/2017.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    final OnRecipeClickListener recipeClickListener;

    RecipeListAdapter(List<Recipe> recipes, OnRecipeClickListener listener) {
        setRecipes(recipes);
        recipeClickListener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe_list, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bindTo(recipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @Override
    public long getItemId(int position) {
        return recipeList.get(position).id();
    }

    void refreshRecipeList(List<Recipe> recipes) {
        setRecipes(recipes);
        notifyDataSetChanged();
    }

    private void setRecipes(@NonNull List<Recipe> recipes) {
        recipeList = recipes;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_recipe_list_name)
        TextView recipeName;

        @BindView(R.id.item_recipe_list_ingredients_count)
        TextView ingredientsCount;

        @BindView(R.id.item_recipe_list_steps_count)
        TextView stepsCount;

//        @BindView(R.id.item_recipe_list_servings_count)
//        TextView servinsCount;

        @BindString(R.string.recipe_ingredients_text)
        String ingredientsText;

        @BindString(R.string.recipe_steps_text)
        String stepsText;

        @BindString(R.string.recipe_servings_text)
        String servingsText;

        private int currentId;

        RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bindTo(@NonNull Recipe recipe) {

            currentId = recipe.id();

            String name = recipe.name();
            recipeName.setText(name);
            //int servings = recipe.servings();
            int ingredents = recipe.ingredients().size();
            int steps = recipe.steps().size();
            ingredientsCount.setText(String.format(Locale.US, ingredientsText, ingredents));
            stepsCount.setText(String.format(Locale.US, stepsText, steps));
        }

        @Override
        public void onClick(View v) {
            recipeClickListener.recipeClicked(currentId);
        }
    }

    interface OnRecipeClickListener {

        void recipeClicked(int recipeId);
    }

}
