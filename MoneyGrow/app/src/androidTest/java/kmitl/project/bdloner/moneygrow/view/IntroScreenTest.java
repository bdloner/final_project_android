package kmitl.project.bdloner.moneygrow.view;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kmitl.project.bdloner.moneygrow.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntroScreenTest {

    @Rule
    public ActivityTestRule<IntroActivity> mActivityTestRule = new ActivityTestRule<>(IntroActivity.class);

    @Test
    public void introScreenTest() {
        try {
            ViewInteraction button = onView(
                    allOf(withId(R.id.btn_next), withText("Next"),
                            childAtPosition(
                                    allOf(withId(R.id.activity_intro),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    3),
                            isDisplayed()));
            button.perform(click());
        } catch (NoMatchingViewException e) {

        }

        try {
            ViewInteraction viewPager = onView(
                    allOf(withId(R.id.view_pager),
                            childAtPosition(
                                    allOf(withId(R.id.activity_intro),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    0),
                            isDisplayed()));
            viewPager.perform(swipeLeft());
        }catch (NoMatchingViewException e) {

        }

        try {
            ViewInteraction button2 = onView(
                    allOf(withId(R.id.btn_next), withText("Next"),
                            childAtPosition(
                                    allOf(withId(R.id.activity_intro),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    3),
                            isDisplayed()));
            button2.perform(click());
        }catch (NoMatchingViewException e) {

        }

        try {
            ViewInteraction viewPager2 = onView(
                    allOf(withId(R.id.view_pager),
                            childAtPosition(
                                    allOf(withId(R.id.activity_intro),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    0),
                            isDisplayed()));
            viewPager2.perform(swipeLeft());
        }catch (NoMatchingViewException e){

        }

        try {
            ViewInteraction button3 = onView(
                    allOf(withId(R.id.btn_next), withText("Next"),
                            childAtPosition(
                                    allOf(withId(R.id.activity_intro),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    3),
                            isDisplayed()));
            button3.perform(click());
        }catch (NoMatchingViewException e) {

        }

        try {
            ViewInteraction viewPager3 = onView(
                    allOf(withId(R.id.view_pager),
                            childAtPosition(
                                    allOf(withId(R.id.activity_intro),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    0),
                            isDisplayed()));
            viewPager3.perform(swipeLeft());
        }catch (NoMatchingViewException e){

        }

        try {
            ViewInteraction button4 = onView(
                    allOf(withId(R.id.btn_next), withText("Start"),
                            childAtPosition(
                                    allOf(withId(R.id.activity_intro),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    3),
                            isDisplayed()));
            button4.perform(click());
        }catch (NoMatchingViewException e) {

        }

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
