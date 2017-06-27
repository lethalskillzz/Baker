package com.lethalskillzz.bakingapp.presentation.base;

/**
 * Created by ibrahimabdulkadir on 20/06/2017.
 */

import com.lethalskillzz.bakingapp.data.RecipeRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final RecipeRepository mRecipeRepository;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(RecipeRepository recipeRepository,
                         CompositeDisposable compositeDisposable) {
        this.mRecipeRepository = recipeRepository;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public RecipeRepository getRecipeRepository() {
        return mRecipeRepository;
    }


    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

//    @Override
//    public void handleApiError(ANError error) {
//
//        if (error == null || error.getErrorBody() == null) {
//            getMvpView().onError(R.string.api_default_error);
//            return;
//        }
//
//        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
//                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
//            getMvpView().onError(R.string.connection_error);
//            return;
//        }
//
//        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR
//                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
//            getMvpView().onError(R.string.api_retry_error);
//            return;
//        }
//
//        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
//        final Gson gson = builder.create();
//
//        try {
//            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);
//
//            if (apiError == null || apiError.getMessage() == null) {
//                getMvpView().onError(R.string.api_default_error);
//                return;
//            }
//
//            switch (error.getErrorCode()) {
//                case HttpsURLConnection.HTTP_UNAUTHORIZED:
//                case HttpsURLConnection.HTTP_FORBIDDEN:
//                    setUserAsLoggedOut();
//                    getMvpView().openActivityOnTokenExpire();
//                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
//                case HttpsURLConnection.HTTP_NOT_FOUND:
//                default:
//                    getMvpView().onError(apiError.getMessage());
//            }
//        } catch (JsonSyntaxException | NullPointerException e) {
//            Log.e(TAG, "handleApiError", e);
//            getMvpView().onError(R.string.api_default_error);
//        }
//    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
