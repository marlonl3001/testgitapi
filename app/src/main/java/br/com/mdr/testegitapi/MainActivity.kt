package br.com.mdr.testegitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.com.mdr.testegitapi.extensions.setupToolbar
import br.com.mdr.testegitapi.view.MainFragment
import br.com.mdr.testegitapi.viewmodel.MainViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    private lateinit var searchView: MaterialSearchView
    var mainViewModel: MainViewModel? = null
    lateinit var toolbar: Toolbar
    private var showMenu: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.activity = this
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

        searchView = findViewById(R.id.searchView)
        setSearchViewListeners()
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Git API REST")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.item_busca)
        searchView.setMenuItem(item)
        item.setVisible(showMenu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setSearchViewListeners() {
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?) = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty())
                    mainViewModel!!.loadRepositories(query)
                return true
            }
        })

        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewClosed() {
                mainViewModel!!.loadRepositories()
            }

            override fun onSearchViewShown() {

            }
        })
    }

    fun configureActionBar(title: String) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        searchView.visibility = View.GONE
        setToolbarTitle(title)
        showMenu = false
        invalidateOptionsMenu()
    }

    fun setToolbarTitle(title: String) {
        toolbar.setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                showMenu = true
                invalidateOptionsMenu()
                searchView.visibility = View.VISIBLE
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}