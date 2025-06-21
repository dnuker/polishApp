package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class BezpiecznaTrasaTest {
    private static final int COLOR_ACTIVATED = android.R.color.holo_green_dark;
    private static final int COLOR_DEACTIVATED = android.R.color.holo_red_dark;

    @Rule
    public ActivityScenarioRule<BezpiecznaTrasa> rule =
            new ActivityScenarioRule<>(BezpiecznaTrasa.class);

    @Test
    public void testInvalidPhoneNumber_ShowsToast() {
        onView(withId(R.id.phoneInput)).perform(typeText("123"));
        onView(withId(R.id.activateButton)).perform(click());
        onView(withId(R.id.activatedMessage))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    public void testValidPhoneNumber_ShowsConfirmation() {
        onView(withId(R.id.phoneInput)).perform(typeText("123456789"));
        onView(withId(R.id.activateButton)).perform(click());
        onView(withId(R.id.activatedMessage)).check(matches(isDisplayed()));
    }

    @Test
    public void testActivateButton_ChangesColorOnActivation() {
        onView(withId(R.id.phoneInput)).perform(typeText("123456789"));
        onView(withId(R.id.activateButton)).perform(click());

        // sprawdzenie wlaczenia
        onView(withId(R.id.activateButton))
                .check(matches(withBackgroundTintColor(COLOR_ACTIVATED)));

        // sprawdzanie wyłączenia
        onView(withId(R.id.activateButton)).perform(click());

        onView(withId(R.id.activateButton))
                .check(matches(withBackgroundTintColor(COLOR_DEACTIVATED)));
    }


    public static TypeSafeMatcher<View> withBackgroundTintColor(final int expectedColorResId) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                ColorStateList tintList = view.getBackgroundTintList();
                if (tintList == null) return false;

                Context context = ApplicationProvider.getApplicationContext();
                int expectedColor = ContextCompat.getColor(context, expectedColorResId);
                int actualColor = tintList.getDefaultColor();

                return actualColor == expectedColor;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background tint color resource id: " + expectedColorResId);
            }
        };
    }
}
