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

import android.support.annotation.RestrictTo;

import com.stepstone.stepper.StepperLayout;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;

/**
 * Factory class for creating feedback stepper types.
 */
@RestrictTo(LIBRARY)
public class StepperFeedbackTypeFactory {

    private static final String TAG = StepperFeedbackTypeFactory.class.getSimpleName();

    /**
     * Creates a stepper feedback type for provided arguments.
     * @param feedbackType step feedback type, one of <code>attrs - ms_stepperFeedbackType</code>
     * @param stepperLayout stepper layout to use with this stepper feedback type
     * @return a stepper feedback type
     */
    public static AbstractStepperFeedbackType createType(int feedbackType, StepperLayout stepperLayout) {
        // TODO: 14/03/2017 Add a check for stepper type
        // TODO: 14/03/2017 allow to build separately i.e. as you can with gravity
        switch (feedbackType) {
            case AbstractStepperFeedbackType.TABS_OVERLAY:
                return new TabsOverlayStepperFeedbackType(stepperLayout);
            default:
                return new NoStepperFeedbackType(stepperLayout);
        }
    }
}
