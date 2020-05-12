package st235.com.github.stacklayoutmanagerapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import st235.com.github.stacklayoutmanager.StackItemTouchHelperCallback
import st235.com.github.stacklayoutmanager.StackLayoutManager
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    val cardInfos = mutableListOf(
        CardInfo(intArrayOf(Color.parseColor("#ee9ca7"), Color.parseColor("#ffdde1")), "👋"),
        CardInfo(intArrayOf(Color.parseColor("#B7F8DB"), Color.parseColor("#50A7C2")), "💪"),
        CardInfo(intArrayOf(Color.parseColor("#bdc3c7"), Color.parseColor("#2c3e50")), "🐶"),
        CardInfo(intArrayOf(Color.parseColor("#dd5e89"), Color.parseColor("#f7bb97")), "🐧"),
        CardInfo(intArrayOf(Color.parseColor("#eecda3"), Color.parseColor("#ef629f")), "🐝"),
        CardInfo(intArrayOf(Color.parseColor("#7EE8FA"), Color.parseColor("#EEC0C6")), "😇"),
        CardInfo(intArrayOf(Color.parseColor("#B91372"), Color.parseColor("#6B0F1A")), "⚽️"),
        CardInfo(intArrayOf(Color.parseColor("#A7ACD9"), Color.parseColor("#9E8FB2")), "🍊"),
        CardInfo(intArrayOf(Color.parseColor("#FF928B"), Color.parseColor("#FFAC81")), "🌕"),
        CardInfo(intArrayOf(Color.parseColor("#0D324D"), Color.parseColor("#7F5A83")), "💃")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvAdapter = RecyclerViewAdapter(cardInfos)

        with(recyclerView) {
            layoutManager = StackLayoutManager()
            adapter = rvAdapter
        }

        val callback = object : StackItemTouchHelperCallback() {
            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder?,
                position: Int,
                direction: Direction
            ) {
                rvAdapter.remove(position)
            }

            override fun onSwiping(
                viewHolder: RecyclerView.ViewHolder?,
                progress: Float,
                direction: Direction
            ) {
                val vh = (viewHolder as RecyclerViewAdapter.ViewHolder)
                when (direction) {
                    Direction.LEFT -> {
                        vh.like.alpha = abs(progress)
                    }
                    Direction.RIGHT -> {
                        vh.dislike.alpha = abs(progress)
                    }
                }

                vh.itemView.rotation = 4 * progress
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
