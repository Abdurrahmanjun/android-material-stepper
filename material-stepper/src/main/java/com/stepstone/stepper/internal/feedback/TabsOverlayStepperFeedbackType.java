/*
Copyright 2017 StepStone Services

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.stepstone.stepper.internal.feedback;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.stepstone.stepper.R;
import com.stepstone.stepper.StepperLayout;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;

/**
 * Feedback stepper type which displays a progress message instead of the tabs.
 */
@RestrictTo(LIBRARY)
public class TabsOverlayStepperFeedbackType extends AbstractStepperFeedbackType {

    private static final int PROGRESS_ANIMATION_DURATION = 200;

    private static final float ALPHA_OPAQUE = 1.0f;
    private static final float ALPHA_INVISIBLE = 0.0f;
    private static final float ALPHA_HALF = 0.5f;

    private final float mProgressMessageTranslationWhenHidden;

    private TextView mProgressMessageTextView;

    private View mTabs;

    public TabsOverlayStepperFeedbackType(@NonNull StepperLayout stepperLayout) {
        super(stepperLayout);
        mProgressMessageTranslationWhenHidden = stepperLayout.getResources().getDimension(R.dimen.ms_progress_message_translation_when_hidden);
        mProgressMessageTextView = (TextView) mStepperLayout.findViewById(R.id.ms_stepTabsProgressMessage);
        mTabs = mStepperLayout.findViewById(R.id.ms_stepTabsScrollView);
        mProgressMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(@NonNull String progressMessage) {
        super.showProgress(progressMessage);
        setButtonsEnabled(false);
        setTabNavigationEnabled(false);
        mProgressMessageTextView.setText(progressMessage);
        mProgressMessageTextView.animate()
                .setStartDelay(PROGRESS_ANIMATION_DURATION)
                .alpha(ALPHA_OPAQUE)
                .translationY(0.0f)
                .setDuration(PROGRESS_ANIMATION_DURATION);
        mTabs.animate()
                .alpha(ALPHA_INVISIBLE)
                .setStartDelay(0)
                .setInterpolator(new LinearInterpolator())
                .setDuration(PROGRESS_ANIMATION_DURATION);

        mPagerProgressBar.setVisibility(View.VISIBLE);
        mPager.animate()
                .alpha(ALPHA_HALF)
                .setDuration(PROGRESS_ANIMATION_DURATION);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        setButtonsEnabled(true);
        setTabNavigationEnabled(true);

        mProgressMessageTextView.animate()
                .setStartDelay(0)
                .alpha(ALPHA_INVISIBLE)
                .translationY(mProgressMessageTranslationWhenHidden)
                .setDuration(PROGRESS_ANIMATION_DURATION);
        mTabs.animate()
                .alpha(ALPHA_OPAQUE)
                .setStartDelay(PROGRESS_ANIMATION_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(PROGRESS_ANIMATION_DURATION);

        mPagerProgressBar.setVisibility(View.GONE);
        mPager.animate()
                .alpha(ALPHA_OPAQUE)
                .setDuration(PROGRESS_ANIMATION_DURATION);
    }

    private void setButtonsEnabled(boolean enabled) {
        mStepperLayout.setNextButtonEnabled(enabled);
        mStepperLayout.setCompleteButtonEnabled(enabled);
        mStepperLayout.setBackButtonEnabled(enabled);
    }

    private void setTabNavigationEnabled(boolean tabNavigationEnabled) {
        mStepperLayout.setTabNavigationEnabled(tabNavigationEnabled);
    }
}
