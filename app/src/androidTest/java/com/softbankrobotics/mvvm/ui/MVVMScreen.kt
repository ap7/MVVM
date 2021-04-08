package com.softbankrobotics.mvvm.ui

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.softbankrobotics.mvvm.R
import org.hamcrest.Matcher

class MVVMScreen : Screen<MVVMScreen>() {

    val mainView = KView { withId(R.id.mainView) }

    val albumRecyclerView = KRecyclerView(builder = { withId(R.id.recycler_view_album) },
        itemTypeBuilder = { itemType(MVVMScreen::AlbumRVItem) })

    class AlbumRVItem(parent: Matcher<View>) : KRecyclerItem<AlbumRVItem>(parent) {
        val imageView = KImageView(parent) { withId(R.id.imageView) }
        val layoutInfo = KView(parent) { withId(R.id.layoutInfos) }
        val textViewAlbumId = KTextView(parent) { withId(R.id.textViewAlbumId) }
        val textViewId = KTextView(parent) { withId(R.id.textViewId) }
        val textViewTitle = KTextView(parent) { withId(R.id.textViewTitle) }
    }

}