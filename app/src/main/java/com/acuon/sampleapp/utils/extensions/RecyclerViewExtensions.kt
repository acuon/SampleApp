package com.acuon.sampleapp.utils.extensions

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.acuon.sampleapp.utils.LinearLayoutManagerWrapper

fun RecyclerView.verticalView(context: Context, stackEnd: Boolean = false) {
    layoutManager = LinearLayoutManagerWrapper(context).apply { stackFromEnd = stackEnd }
    layoutManager
}

fun RecyclerView.addDecoration(decorator: RecyclerView.ItemDecoration) {
    if (itemDecorationCount == 0) addItemDecoration(decorator)
}

fun createDecorator(value: Int): RecyclerView.ItemDecoration {
    return createDecorator(value, value, value, value)
}

fun createDecorator(top: Int, bottom: Int, left: Int, right: Int): RecyclerView.ItemDecoration {
    return object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            with(outRect) {
                this.top = top.dp
                this.bottom = bottom.dp
                this.left = left.dp
                this.right = right.dp
            }
        }
    }
}