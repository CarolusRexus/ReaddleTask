package com.example.readdletask.view.Activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import  com.example.readdletask.view.Adapter.MainAdapter
import com.example.readdletask.R


import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.readdletask.ViewModel.MainViewModel
import com.example.readdletask.ViewModel.ViewModelFactory
import com.example.readdletask.model.network.retrofit.RandomNameApi
import com.example.readdletask.model.network.retrofit.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData

import androidx.lifecycle.Observer
import com.example.readdletask.model.Contact
import com.example.readdletask.util.State

import com.example.readdletask.util.Status
import com.example.readdletask.view.Adapter.ViewType
import kotlinx.android.synthetic.main.avatar_view.*

class ainActivity : AppCompatActivity() {

    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var contacts: LiveData<State<List<Contact>>>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setRecyclerView()

        contacts = viewModel.initialState()
        setObserver(contacts)

        this.simulate_changes.setOnClickListener {
            contacts = viewModel.simulateChanges()
            setObserver(contacts)
        }
    }


    private fun setObserver(data: LiveData<State<List<Contact>>>) {
        data.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        this.progress_circular.visibility = View.GONE
                        resource.data?.let { contacts -> retrieveList(contacts = contacts) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        this.progress_circular.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(RandomNameApi(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setRecyclerView() {
        layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager
        adapter = MainAdapter(layoutManager, arrayListOf())
        { selected:Contact,view:View->

            val avatar:ImageView = this.findViewById(R.id.avatar);
            val options = ActivityOptions.makeSceneTransitionAnimation(this,view,"avatarTransition")
            val intent = Intent(this@ainActivity, DisplayContactActivity::class.java)

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("CONTACT_EXTRA", selected)
            startActivity(intent,options.toBundle())
        }
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.change_layout -> {
                if (viewModel.viewType == ViewType.LIST) {
                    viewModel.viewType = ViewType.GRID
                    item.title = getString(R.string.grid)
                    adapter.setGrid()
                } else {
                    viewModel.viewType = ViewType.LIST
                    item.title = getString(R.string.list)
                    adapter.setLinear()
                }
                adapter.notifyItemRangeChanged(0, adapter.itemCount ?: 0)
            }
        }

        return super.onOptionsItemSelected(item)

    }


    private fun retrieveList(contacts: List<Contact>) {
        adapter.apply {
            addContacts(contacts)
            notifyDataSetChanged()
        }
    }


}