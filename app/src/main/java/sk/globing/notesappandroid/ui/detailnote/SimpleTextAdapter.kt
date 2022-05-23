package sk.globing.notesappandroid.ui.detailnote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sk.globing.notesappandroid.R

class SimpleTextAdapter(private val onClickListener: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        SimpleTextViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.simple_text_item_layout, parent, false),
            onClickListener
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SimpleTextViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<String>) = differ.submitList(list)

    class SimpleTextViewHolder(
        itemView: View,
        private val interaction: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String): Unit = with(itemView) {
            setOnClickListener {
                interaction(item)
            }
            findViewById<TextView>(R.id.text_title).text = item
        }
    }
}
