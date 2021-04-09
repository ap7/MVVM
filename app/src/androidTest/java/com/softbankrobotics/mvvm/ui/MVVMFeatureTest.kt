package com.softbankrobotics.mvvm.ui

import android.R
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class MVVMFeatureTest {

    @Rule
    @JvmField
    val myActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    companion object {
        private val kakaoScreen = MVVMScreen()
    }

    @Test
    fun checkMainViewIsVisible() {
        kakaoScreen {
            mainView {
                isDisplayed()
            }
        }
    }

    @Test
    fun checkMainViewContainRV() {
        kakaoScreen {
            albumRecyclerView {
                isDisplayed()
                hasSize(100)
            }
        }
    }

    @Test
    fun checkRVIsFilled() {
        val pos = 0
        val textAlbumId = "50"
        val textViewTitle = "et inventore quae ut tempore eius voluptatum"
        kRecyclerView(pos, textAlbumId, textViewTitle)
    }

    /**
     * To avoid flakiness, we highly recommend that you turn off system animations on the virtual or
     * physical devices used for testing. On your device, under Settings > Developer options,
     * disable the following 3 settings:
     *
     * Window animation scale
     * Transition animation scale
     * Animator duration scale
     */
    @Test
    fun checkScrollRV() {
        kakaoScreen {
            albumRecyclerView {
                isDisplayed()
                scrollToEnd()
            }
        }
    }

    @Test
    fun checkScrollRVToSpecificPos() {
        val pos = 10
        val textAlbumId = "550"
        val textViewTitle = "eaque ut incidunt quae aut quo quis praesentium"
        kRecyclerView(pos, textAlbumId, textViewTitle)
    }

    @Test
    fun ensureCursorPositionIsSavedWhenOrientationDeviceChange() {
        checkChildAtAlbumRVItem()
        1.changeConfigurationOfVirtualDevice()
        checkChildAtAlbumRVItem()
        2.changeConfigurationOfVirtualDevice()
        checkChildAtAlbumRVItem()
        3.changeConfigurationOfVirtualDevice()
    }

    @Test
    fun ensureDataStillDisplayWhenAirModeIsEnabled() {

        kakaoScreen {
            albumRecyclerView {
                isDisplayed()
                scrollTo(50)
            }
        }

        setAirplaneMode(true)

        val pos = 10
        val textAlbumId = "550"
        val textViewTitle = "eaque ut incidunt quae aut quo quis praesentium"

        kRecyclerView(pos, textAlbumId, textViewTitle)

        setAirplaneMode(false)
    }

    private fun checkChildAtAlbumRVItem(
        pos: Int = 30,
        textAlbumId: String = "1550",
        textViewTitle: String = "aut ad hic nemo tempore delectus sed voluptatem quia",
    ) {
        kRecyclerView(pos, textAlbumId, textViewTitle)
    }

    private fun kRecyclerView(pos: Int, textAlbumId: String, textViewTitle: String) {
        kakaoScreen {
            albumRecyclerView {
                isDisplayed()
                scrollTo(pos)
                childAt<MVVMScreen.AlbumRVItem>(pos) {
                    imageView { isDisplayed() }
                    layoutInfo { isDisplayed() }
                    textViewAlbumId {
                        isDisplayed()
                        hasText(textAlbumId)
                    }
                    val text = pos + 1
                    textViewId {
                        isDisplayed()
                        hasText(text.toString())
                    }
                    textViewTitle {
                        isDisplayed()
                        hasText(textViewTitle)
                        hasTextColor(R.color.black)
                    }

                }
            }
        }
    }

    private fun Int.changeConfigurationOfVirtualDevice() {
        val device = UiDevice.getInstance(getInstrumentation())

        when (this) {
            1 -> device.setOrientationLeft()
            2 -> device.setOrientationRight()
            3 -> device.setOrientationNatural()
            else -> Log.e("Error", "unknown orientation")
        }
    }

    private fun setAirplaneMode(enable: Boolean) {
        if ((if (enable) 1 else 0) == Settings.System.getInt(getInstrumentation().context.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0)
        ) {
            return
        }
        val device = UiDevice.getInstance(getInstrumentation())
        device.openQuickSettings()
        // Find the text of your language
        val description = By.desc("Airplane mode")
        // Need to wait for the button, as the opening of quick settings is animated.
        device.wait(Until.hasObject(description), 700)
        device.findObject(description).click()
        getInstrumentation().context.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }
}