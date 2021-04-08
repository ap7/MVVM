package com.softbankrobotics.mvvm.ui

import android.R
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
        kakaoScreen {
            albumRecyclerView {
                isDisplayed()

                firstChild<MVVMScreen.AlbumRVItem> {
                    isVisible()

                    imageView { isDisplayed() }
                    layoutInfo { isDisplayed() }
                    textViewAlbumId {
                        isDisplayed()
                        hasText("50")
                    }
                    textViewId {
                        isDisplayed()
                        hasText("1")
                    }
                    textViewTitle {
                        isDisplayed()
                        hasText("et inventore quae ut tempore eius voluptatum")
                        hasTextColor(R.color.black)
                    }
                }
            }

        }
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

        kakaoScreen {
            albumRecyclerView {
                isDisplayed()

                scrollTo(pos)

                childAt<MVVMScreen.AlbumRVItem>(pos) {
                    imageView { isDisplayed() }
                    layoutInfo { isDisplayed() }
                    textViewAlbumId {
                        isDisplayed()
                        hasText("550")
                    }
                    val text = pos + 1
                    textViewId {
                        isDisplayed()
                        hasText(text.toString())
                    }
                    textViewTitle {
                        isDisplayed()
                        hasText("eaque ut incidunt quae aut quo quis praesentium")
                        hasTextColor(R.color.black)
                    }

                }
                scrollTo(pos + 10)
            }
        }
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

    private fun checkChildAtAlbumRVItem(
        pos: Int = 30,
        textAlbumId: String = "1550",
        textViewTitle: String = "aut ad hic nemo tempore delectus sed voluptatem quia",
    ) {
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
}