package com.example.mi_order.adapters

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class FoodItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.bottom = 20
    }
}