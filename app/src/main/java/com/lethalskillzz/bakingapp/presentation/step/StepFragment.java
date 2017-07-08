package com.lethalskillzz.bakingapp.presentation.step;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.di.component.ActivityComponent;
import com.lethalskillzz.bakingapp.presentation.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.RECIPE_ID;

/**
 * Created by ibrahimabdulkadir on 06/07/2017.
 */

public class StepFragment extends BaseFragment implements StepMvpView{

    @Inject
    StepMvpPresenter<StepMvpView> mPresenter;


    public static StepFragment newInstance(int recipeId) {
        Bundle args = new Bundle();
        args.putInt(RECIPE_ID, recipeId);
        StepFragment fragment = new StepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeId = getArguments().getInt(RECIPE_ID);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

    }

}
