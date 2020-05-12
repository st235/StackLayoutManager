package st235.com.github.stacklayoutmanagerapp

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RecyclerViewAdapter(
    private val infos: MutableList<CardInfo>
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val backgroundView = itemView.backgroundView
        val like = itemView.like
        val dislike = itemView.dislike
        val emojiView = itemView.emojiView

        fun bind(info: CardInfo) {
            backgroundView.background =
                GradientDrawable(GradientDrawable.Orientation.TR_BL, info.colors)
            like.alpha = 0F
            dislike.alpha = 0F
            emojiView.text = info.emoji
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val v = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = infos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(infos[position])
    }

    fun remove(position: Int) {
        infos.removeAt(position)
        notifyItemRemoved(position)
    }
}
