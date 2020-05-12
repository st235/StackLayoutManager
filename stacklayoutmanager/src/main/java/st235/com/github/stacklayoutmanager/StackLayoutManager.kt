package st235.com.github.stacklayoutmanager

import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import kotlin.math.min

class StackLayoutManager(
    val visibleItemsCount: Int = MAX_VISIBLE_ITEMS,
    internal val stackItemDownScale: Float = 0.1F
): RecyclerView.LayoutManager() {

    private var endListener: OnEndListener? = null

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun isAutoMeasureEnabled(): Boolean {
        return true
    }

    override fun onLayoutChildren(
        recycler: Recycler,
        state: RecyclerView.State
    ) {
        detachAndScrapAttachedViews(recycler)

        val itemCount = min(itemCount, visibleItemsCount)

        for (position in itemCount - 1 downTo 0) {
            val view = recycler.getViewForPosition(position)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            val availableWidth = width - getDecoratedMeasuredWidth(view)
            layoutDecorated(
                view, availableWidth / 2, (view.layoutParams as MarginLayoutParams).topMargin,
                availableWidth / 2 + getDecoratedMeasuredWidth(view),
                (view.layoutParams as MarginLayoutParams).topMargin + getDecoratedMeasuredHeight(view)
            )

            view.scale(1 - position * stackItemDownScale)
        }
    }

    override fun onItemsAdded(
        recyclerView: RecyclerView,
        positionStart: Int,
        itemCount: Int
    ) {
        super.onItemsAdded(recyclerView, positionStart, itemCount)

        if (getItemCount() < visibleItemsCount && endListener != null) {
            endListener!!.onNeedMoreItems()
        }
    }

    override fun onItemsRemoved(
        recyclerView: RecyclerView,
        positionStart: Int, itemCount: Int
    ) {
        super.onItemsRemoved(recyclerView, positionStart, itemCount)

        rewindHead()
        val childCount = getItemCount()
        if (childCount == visibleItemsCount && endListener != null) {
            endListener!!.onNeedMoreItems()
        }
        if (childCount == 0 && endListener != null) {
            endListener!!.onEmpty()
        }
    }

    private fun rewindHead() {
        if (childCount > 0) {
            getChildAt(0)?.scale(1F)
        }
    }

    interface OnEndListener {
        fun onNeedMoreItems()
        fun onEmpty()
    }

    private companion object {
        const val MAX_VISIBLE_ITEMS = 2
    }
}