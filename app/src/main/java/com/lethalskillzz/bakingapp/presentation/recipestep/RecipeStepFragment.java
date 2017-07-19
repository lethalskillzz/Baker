package com.lethalskillzz.bakingapp.presentation.recipestep;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.di.component.ActivityComponent;
import com.lethalskillzz.bakingapp.presentation.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_STEP_DATA;

/**
 * Created by ibrahimabdulkadir on 06/07/2017.
 */

public class RecipeStepFragment extends BaseFragment implements RecipeStepMvpView, ExoPlayer.EventListener {

    private final String TAG = RecipeStepFragment.class.getSimpleName();

    @Inject
    RecipeStepMvpPresenter<RecipeStepMvpView> mPresenter;

    @BindString(R.string.error_default)
    String errorMessage;

    @BindView(R.id.fragment_step_exo_player_view)
    SimpleExoPlayerView mSimpleExoPlayerView;

    @BindView(R.id.fragment_step_text_directions)
    TextView mTextDirections;

    @BindView(R.id.fragment_step_no_video_placeholder)
    ImageView mImageNoVideo;

    private Step mStep;
    private SimpleExoPlayer mExoPlayer;
    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;


    public static RecipeStepFragment newInstance(Step step) {
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_STEP_DATA, step);
        RecipeStepFragment fragment = new RecipeStepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if ((arguments != null) && (arguments.containsKey(BUNDLE_STEP_DATA))) {
            mStep = arguments.getParcelable(BUNDLE_STEP_DATA);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);

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

        if(mStep.videoURL()!=null && !mStep.videoURL().matches("")) {
            initializeMediaSession();
            initializePlayer(Uri.parse(mStep.videoURL()));
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                    !getResources().getBoolean(R.bool.master_detail_mode)) {

                hideSystemUI();
                mTextDirections.setVisibility(View.GONE);
                mSimpleExoPlayerView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                mSimpleExoPlayerView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                mTextDirections.setText(mStep.description());
            }
        } else {
            mSimpleExoPlayerView.setVisibility(View.GONE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                    !getResources().getBoolean(R.bool.master_detail_mode)) {

                hideSystemUI();
                mTextDirections.setVisibility(View.GONE);
                mImageNoVideo.setVisibility(View.VISIBLE);
            } else {
                mTextDirections.setText(mStep.description());
            }
        }
    }


    @Override
    public void showErrorMessage() {
        // User should not see this
        onError(errorMessage);
    }


    @Override
    public void refreshStepContainerFragment(String desc, String videoUrl, String imageUrl) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        if(mMediaSession!=null) {
            mMediaSession.setActive(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()) {
            if(!isVisibleToUser) {
                if (mExoPlayer != null) {
                    //Pause video when user swipes away in view pager
                    mExoPlayer.setPlayWhenReady(false);
                }
            }
        }
    }

    private void hideSystemUI() {

        if(((AppCompatActivity) getActivity()).getSupportActionBar()!=null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        //Use Google's "LeanBack" mode to get fullscreen in landscape
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }


    private void initializeMediaSession() {
        mMediaSession = new MediaSessionCompat(getContext(), TAG);
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mMediaSession.setMediaButtonReceiver(null);
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());
        mMediaSession.setCallback(new MySessionCallback());
        mMediaSession.setActive(true);
    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mSimpleExoPlayerView.setPlayer(mExoPlayer);
            mExoPlayer.addListener(this);
            String userAgent = Util.getUserAgent(getContext(), "RecipeStepVideo");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if(mExoPlayer!=null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {}

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {}

    @Override
    public void onLoadingChanged(boolean isLoading) {}

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    mExoPlayer.getCurrentPosition(), 1f);
        } else if((playbackState == ExoPlayer.STATE_READY)) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {}

    @Override
    public void onPositionDiscontinuity() {}

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        releasePlayer();
    }

}
