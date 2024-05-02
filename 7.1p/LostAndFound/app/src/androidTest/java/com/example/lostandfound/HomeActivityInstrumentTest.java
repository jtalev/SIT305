package com.example.lostandfound;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.lostandfound.presentation.createAdvert.CreateAdvertActivity;
import com.example.lostandfound.presentation.home.HomeActivity;
import com.example.lostandfound.presentation.showAdverts.ShowAdvertsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeActivityInstrumentTest {

    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule =
            new ActivityScenarioRule<>(HomeActivity.class);

    @Before
    public void setup() {
        // Initialize Espresso Intents
        Intents.init();
    }

    @After
    public void tearDown() {
        // Release Espresso Intents
        Intents.release();
    }

    @Test
    public void testCreateAdvertButton() {
        onView(withId(R.id.create_advert_button)).perform(click());
        intended(hasComponent(CreateAdvertActivity.class.getName()));
    }

    @Test
    public void testShowAdvertsButton() {
        onView(withId(R.id.show_adverts_button)).perform(click());
        intended(hasComponent(ShowAdvertsActivity.class.getName()));
    }
}
