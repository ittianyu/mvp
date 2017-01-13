package com.ittianyu.mvp.lcee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.lce.LceAnimator;

/**
 * Add a empty view
 */
public class LceeAnimator{

    /**
     * Show the loading view. No animations, because sometimes loading things is pretty fast (i.e.
     * retrieve data from memory cache).
     */
    public static void showLoading(@NonNull View loadingView, @NonNull View contentView,
                                   @NonNull View errorView, final View emptyView) {
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        // add: empty view can be null
        if (null != emptyView)
            emptyView.setVisibility(View.GONE);
    }

    /**
     * Shows the error view instead of the loading view
     */
    public static void showErrorView(@NonNull final View loadingView, @NonNull final View contentView,
                                     final View errorView, final View emptyView) {

        contentView.setVisibility(View.GONE);
        // add: empty view can be null
        if (null != emptyView)
            emptyView.setVisibility(View.GONE);

        // Not visible yet, so animate the view in
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator in = ObjectAnimator.ofFloat(errorView, "alpha", 1f);
        ObjectAnimator loadingOut = ObjectAnimator.ofFloat(loadingView, "alpha", 0f);

        set.playTogether(in, loadingOut);
        set.setDuration(200);

        set.addListener(new AnimatorListenerAdapter() {

            @Override public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                errorView.setVisibility(View.VISIBLE);
            }

            @Override public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                loadingView.setVisibility(View.GONE);
                loadingView.setAlpha(1f); // For future showLoading calls
            }
        });

        set.start();
    }

    /**
     * Display the content instead of the loadingView
     */
    public static void showContent(@NonNull final View loadingView, @NonNull final View contentView,
                                   @NonNull final View errorView, final View emptyView) {

        if (contentView.getVisibility() == View.VISIBLE) {
            // No Changing needed, because contentView is already visible
            errorView.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
            // add: empty view can be null
            if (null != emptyView)
                emptyView.setVisibility(View.GONE);
        } else {

            errorView.setVisibility(View.GONE);
            // add: empty view can be null
            if (null != emptyView)
                emptyView.setVisibility(View.GONE);

            int translateDp = 40;
            // Not visible yet, so animate the view in
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator contentFadeIn = ObjectAnimator.ofFloat(contentView, "alpha", 0f, 1f);
            ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(contentView, "translationY",
                    LceAnimator.dpToPx(loadingView.getContext(), translateDp), 0);

            ObjectAnimator loadingFadeOut = ObjectAnimator.ofFloat(loadingView, "alpha", 1f, 0f);
            ObjectAnimator loadingTranslateOut = ObjectAnimator.ofFloat(loadingView, "translationY", 0,
                    -LceAnimator.dpToPx(loadingView.getContext(), translateDp));

            set.playTogether(contentFadeIn, contentTranslateIn, loadingFadeOut, loadingTranslateOut);
            set.setDuration(500);

            set.addListener(new AnimatorListenerAdapter() {

                @Override public void onAnimationStart(Animator animation) {
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                    contentView.setVisibility(View.VISIBLE);
                }

                @Override public void onAnimationEnd(Animator animation) {
                    loadingView.setVisibility(View.GONE);
                    loadingView.setAlpha(1f); // For future showLoading calls
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                }
            });

            set.start();
        }
    }

    /**
     * Show the empty view. No animations(i.e.
     * retrieve data from memory cache).
     */
    public static void showEmpty(@NonNull View loadingView, @NonNull View contentView,
                                   @NonNull View errorView, final View emptyView) {
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        // add: empty view can be null
        if (null != emptyView)
            emptyView.setVisibility(View.VISIBLE);
    }
}
