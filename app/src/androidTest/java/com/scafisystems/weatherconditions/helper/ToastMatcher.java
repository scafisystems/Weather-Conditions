/*
 * Copyright (c) Created by André Scafi on 28/3/ 2021.
 */

package com.scafisystems.weatherconditions.helper;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
/*
*
* Test if the Toast Message is Displayed

onView(withText(R.string.message)).inRoot(new ToastMatcher())
.check(matches(isDisplayed()));
*
Test if the Toast Message is not Displayed

onView(withText(R.string.message)).inRoot(new ToastMatcher())
.check(matches(not(isDisplayed())));
*
Test id the Toast contains specific Text Message

onView(withText(R.string.message)).inRoot(new ToastMatcher())
.check(matches(withText("Invalid Name"));
*
 */

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                // windowToken == appToken means this window isn't contained by any other windows.
                // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
                return true;
            }
        }
        return false;
    }

}

