package com.trasimus.adastrasample.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.trasimus.adastrasample.R
import com.trasimus.adastrasample.communication.local.AppDatabase
import com.trasimus.adastrasample.models.Beer
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(), ListFragmentAdapterHandler {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListFragmentViewModel
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewAdapter: ListFragmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = ListFragmentAdapter(this)

        list_fragment_beer_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val beerListObserver = Observer<List<Beer>> { beerList ->
            viewAdapter.setData(beerList.toMutableList())
        }

        context?.let {
            viewModel.beers(AppDatabase.getInstance(it)).observe(viewLifecycleOwner, beerListObserver)
        }
    }

    override fun onBeerClick(id: Int) = (activity as ListFragmentHandler).openBeerDetail(id)
}