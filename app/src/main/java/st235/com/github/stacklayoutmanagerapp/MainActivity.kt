package st235.com.github.stacklayoutmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import st235.com.github.stacklayoutmanager.StackItemTouchHelperCallback
import st235.com.github.stacklayoutmanager.StackLayoutManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ad = RecyclerViewAdapter(50)

        with(recyclerView) {
            layoutManager = StackLayoutManager()
            adapter = ad
        }

        val callback = StackItemTouchHelperCallback()
        val itemTouchHelper = ItemTouchHelper(callback)

        callback.onSwipeListener = object: StackItemTouchHelperCallback.OnSwipeListener {

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder?,
                item: Int,
                direction: StackItemTouchHelperCallback.Direction
            ) {
                ad.notifyItemRemoved(item)
            }

            override fun onSwiping(
                viewHolder: RecyclerView.ViewHolder?,
                ratio: Float,
                direction: StackItemTouchHelperCallback.Direction
            ) {
                Log.d("HelloWorld", "ratio: $ratio")
            }
        }

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
