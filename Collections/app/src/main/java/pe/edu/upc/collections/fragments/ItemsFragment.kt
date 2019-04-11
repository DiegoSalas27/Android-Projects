package pe.edu.upc.collections.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_items.view.*

import pe.edu.upc.collections.R
import pe.edu.upc.collections.adapters.ItemsAdapter
import pe.edu.upc.collections.models.ItemClass

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ItemsFragment : Fragment() {
    lateinit var itemsRecyclerView: RecyclerView //promise dont initialize now
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_items, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) //refinamiento(invoca al metodo del padre) o remplazo(no lo invoca) //recyclerview delega
        itemsRecyclerView = view.itemsRecyclerView
        itemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ItemsAdapter(listOf(ItemClass("", "Dominic", "Toretto")))
        }
    }

}
