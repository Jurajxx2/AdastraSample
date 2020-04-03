package com.trasimus.adastrasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.trasimus.adastrasample.screens.detail.DetailFragmentHandler
import com.trasimus.adastrasample.screens.list.ListFragmentDirections
import com.trasimus.adastrasample.screens.list.ListFragmentHandler

class MainActivity : AppCompatActivity(), DetailFragmentHandler, ListFragmentHandler {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun openBeerDetail(id: Int) {
        navController?.navigate(ListFragmentDirections.actionListFragmentToDetailFragment(id))
    }
}
