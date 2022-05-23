package sk.globing.notesappandroid.ui.notelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.notesappandroid.databinding.SingleNoteItemBinding

class NotesAdapter(
    private val clickListener: (NoteData) -> Unit,
    private val deleteClickListener: (Int?) -> Unit
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<NoteData>() {

        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData) = oldItem == newItem

    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(SingleNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(differ.currentList[position], clickListener, deleteClickListener)

    override fun getItemCount() = differ.currentList.size

    fun setList(list: List<NoteData>) {
        differ.submitList(list)
    }
    class ViewHolder(private val binding: SingleNoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            noteData: NoteData,
            clickListener: (NoteData) -> Unit,
            deleteClickListener: (Int?) -> Unit
        ) {
            binding.run {
                noteData.apply {

                    title.let {
                        textViewTitle.text = it
                    }

                    content?.let {
                        textViewContent.text = it
                    }
                    category.let {
                        textViewCategory.text = it
                    }

                }

                root.setOnClickListener { clickListener(noteData) }
                imageButtonDelete.setOnClickListener { deleteClickListener(noteData.id) }
            }

        }
    }


}