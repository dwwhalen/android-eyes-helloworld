package com.example.helloworld


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.applitools.eyes.android.components.androidx.AndroidXComponentsProvider
import com.applitools.eyes.android.espresso.Eyes
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val eyes = Eyes()
        eyes.componentsProvider = AndroidXComponentsProvider()
        eyes.apiKey = "SET IT!"

        try {
            eyes.open("Hello World!", "My first Espresso Android test!");
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            assertEquals("com.example.helloworld", appContext.packageName)
        }
        finally {
            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed()
        }
    }
}