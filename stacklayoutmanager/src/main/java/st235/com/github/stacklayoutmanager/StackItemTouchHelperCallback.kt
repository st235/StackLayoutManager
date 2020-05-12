package st235.com.github.stacklayoutmanager

import android.graphics.Canvas
import androidx.core.math.MathUtils.clamp
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import st235.com.github.stacklayoutmanager.StackItemTouchHelperCallback.Direction.Companion.toDirection
import kotlin.math.abs

abstract class StackItemTouchHelperCallback(
    private val orientation: Orientation = Orientation.HORIZONTAL
) : ItemTouchHelper.Callback() {

    abstract fun onSwiped(
        viewHolder: ViewHolder?,
        position: Int,
        direction: Direction
    )

    abstract fun onSwiping(
        viewHolder: ViewHolder?,
        progress: Float,
        direction: Direction
    )

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        return false
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        if (recyclerView.layoutManager !is StackLayoutManager) {
            throw IllegalStateException("StackItemTouchHelperCallback can be attached only to StackLayoutManager")
        }

        return makeMovementFlags(
            0 /*drag flags */,
            orientation.internalSwipeFlag /* swipe flags */
        )
    }

    override fun onSwiped(viewHolder: ViewHolder, rawDirection: Int) {
        viewHolder.itemView.setOnTouchListener(null)
        onSwiped(viewHolder, viewHolder.adapterPosition, rawDirection.toDirection())
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        val layoutManager = recyclerView.layoutManager

        if (layoutManager !is StackLayoutManager) {
            throw IllegalStateException("StackItemTouchHelperCallback can be attached only to StackLayoutManager")
        }

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE) {
            return
        }

        val ratio = clamp(dX / recyclerView.getDxThreshold(viewHolder), -1f, 1f)

        val childCount = recyclerView.childCount
        for (position in 0 until childCount - 1) {
            val index = childCount - position - 1
            val view = recyclerView.getChildAt(position)
            view.scale(1 - index * layoutManager.stackItemDownScale + abs(ratio) * layoutManager.stackItemDownScale)
        }

        onSwiping(viewHolder, ratio, getDirection(ratio))
    }

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.rotation = 0f
    }

    private fun getDirection(ratio: Float): Direction {
        if (ratio == 0f) return Direction.NONE
        return if (ratio < 0) {
            Direction.LEFT
        } else {
            Direction.RIGHT
        }
    }

    private fun RecyclerView.getDyThreshold(viewHolder: ViewHolder): Float {
        return height * getSwipeThreshold(viewHolder)
    }

    private fun RecyclerView.getDxThreshold(viewHolder: ViewHolder): Float {
        return width * getSwipeThreshold(viewHolder)
    }

    enum class Orientation(
        internal val internalSwipeFlag: Int
    ) {
        VERTICAL(ItemTouchHelper.UP or ItemTouchHelper.DOWN),
        HORIZONTAL(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    }

    enum class Direction {
        TOP, BOTTOM, LEFT, RIGHT, NONE;

        internal companion object {
            fun Int.toDirection() = when(this) {
                ItemTouchHelper.LEFT -> LEFT
                ItemTouchHelper.UP -> TOP
                ItemTouchHelper.DOWN -> BOTTOM
                ItemTouchHelper.RIGHT -> RIGHT
                else -> NONE
            }
        }
    }
}