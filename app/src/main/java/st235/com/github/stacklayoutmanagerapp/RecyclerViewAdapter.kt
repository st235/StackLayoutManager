package st235.com.github.stacklayoutmanagerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private val count: Int
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val v = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = count

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // empty on purpose
    }
}
