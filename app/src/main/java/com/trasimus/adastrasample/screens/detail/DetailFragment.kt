package com.trasimus.adastrasample.screens.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.trasimus.adastrasample.R
import com.trasimus.adastrasample.communication.local.AppDatabase
import com.trasimus.adastrasample.models.Beer
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment(), DetailFragmentAdapterHandler {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailFragmentViewModel
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewAdapter: DetailFragmentAdapter

    val args: DetailFragmentArgs by navArgs<DetailFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailFragmentViewModel::class.java)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = DetailFragmentAdapter(this)

        detail_fragment_detail_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val beerListObserver = Observer<Beer> { beer ->
            val image = DetailFragmentAdapter.BeerDetailAdapterItem.ItemImage(beer.image_url)
            val title = DetailFragmentAdapter.BeerDetailAdapterItem.ItemTitle(beer.name)
            val info1 = DetailFragmentAdapter.BeerDetailAdapterItem.ItemInfo(beer.description)
            val info2 = DetailFragmentAdapter.BeerDetailAdapterItem.ItemInfo("First brewed: " + beer.first_brewed)
            val info3 = DetailFragmentAdapter.BeerDetailAdapterItem.ItemInfo("Tagline: " + beer.tagline)

            val items = mutableListOf<DetailFragmentAdapter.BeerDetailAdapterItem>()
            items.add(image)
            items.add(title)
            items.add(info1)
            items.add(info2)
            items.add(info3)
            viewAdapter.setData(items)
        }

        context?.let {
            viewModel.beer(AppDatabase.getInstance(it), id).observe(viewLifecycleOwner, beerListObserver)
        }
    }

    override fun inflateImage(url: String, imageView: ImageView) {
        Picasso.get().load(Uri.parse(url)).into(imageView)
    }
}