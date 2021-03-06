package pe.edu.upc.collections.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_item.view.*
import pe.edu.upc.collections.R
import pe.edu.upc.collections.activities.ItemActivity
import pe.edu.upc.collections.models.ItemClass

class ItemsAdapter(private var items: List<ItemClass>) :
        RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var firstNameTextView: TextView
        var lastNameTextView: TextView
        var contentItem: ConstraintLayout
        init {
            firstNameTextView = itemView.firstName
            lastNameTextView = itemView.lastName
            contentItem = itemView.contentItem
        }
        fun bindTo(item: ItemClass) {
            firstNameTextView.text = item.firstName
            lastNameTextView.text = item.lastName
            contentItem.setOnClickListener {
                val bundle = Bundle()
                bundle.apply {
                    putString("first_name", item.firstName)
                    putString("last_name", item.lastName)
                }
                val intent = Intent(it.context, ItemActivity::class.java)
                intent.putExtras(bundle)
                it.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_item, parent, false ))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        holder.bindTo(items[position])
    }

}