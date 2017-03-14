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

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.widget.ProgressBar;

import com.stepstone.stepper.R;
import com.stepstone.stepper.StepperLayout;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;

/**
 * A base stepper feedback type all feedback types must extend.
 * See Stepper feedback section in https://material.io/guidelines/components/steppers.html#steppers-types-of-steppers
 */
@RestrictTo(LIBRARY)
public abstract class AbstractStepperFeedbackType {

    public static final int NONE = 0x01;

    public static final int TABS_OVERLAY = 0x02;

    protected final StepperLayout mStepperLayout;

    protected final View mPager;

    protected final ProgressBar mPagerProgressBar;

    private boolean mInProgress;

    public AbstractStepperFeedbackType(@NonNull StepperLayout stepperLayout) {
        mStepperLayout = stepperLayout;

        mPager = mStepperLayout.findViewById(R.id.ms_stepPager);
        mPagerProgressBar = (ProgressBar) mStepperLayout.findViewById(R.id.stepPagerProgressBar);
    }

    // TODO: 13/03/2017 JavaDoc
    @CallSuper
    public void showProgress(@NonNull String progressMessage) {
        mInProgress = true;
    }

    // TODO: 13/03/2017 JavaDoc
    @CallSuper
    public void hideProgress() {
        mInProgress = false;
    }

    // TODO: 13/03/2017 JavaDoc
    public boolean isInProgress() {
        return mInProgress;
    }
}