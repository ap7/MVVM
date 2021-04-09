package com.softbankrobotics.mvvm.ui.albums

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.softbankrobotics.mvvm.R
import com.softbankrobotics.mvvm.ui.MVVMScreen
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumFragmentTest : TestCase() {

    companion object {
        private val kakaoScreen = MVVMScreen()
    }

    @Test
    fun testFragment() {
        launchFragmentInContainer<AlbumFragment>(themeResId = R.style.Theme_MVVM)
        kakaoScreen {
            albumRecyclerView {
                isDisplayed()
                for (i in 1..100) {
                    scrollTo(i)
                }
            }
        }
    }

}