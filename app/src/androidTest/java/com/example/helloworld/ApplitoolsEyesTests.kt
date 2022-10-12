package com.example.helloworld


import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.applitools.eyes.android.components.androidx.AndroidXComponentsProvider
import com.applitools.eyes.android.espresso.Eyes
import com.applitools.eyes.android.espresso.fluent.Target.window
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ApplitoolsEyesTests {

    lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun beforeTest() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun myFirstEyesTest() {
        val eyes = Eyes()
        eyes.componentsProvider = AndroidXComponentsProvider()
        var applitoolsApiKey = InstrumentationRegistry.getArguments().getString("APPLITOOLS_API_KEY")
        if ( applitoolsApiKey == null) {
            applitoolsApiKey = "set your key here if not passing as param"
        }

        assertNotNull("parameter APPLITOOLS_API_KEY not set", applitoolsApiKey)
        eyes.apiKey = applitoolsApiKey

        try {
            eyes.open("Hello World!", "Android Applitools Eyes test");
            eyes.check(window().withName("Hello World"))
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            assertEquals("com.example.helloworld", appContext.packageName)
            eyes.close()
        }
        finally {
            eyes.abortIfNotClosed()
        }
    }
}