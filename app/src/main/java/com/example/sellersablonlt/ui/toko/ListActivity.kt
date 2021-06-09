package com.example.sellersablonlt.ui.toko

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellersablonlt.databinding.ActivityTokoBinding
import com.example.sellersablonlt.db.TokoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class  ListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = TokoDatabase.getInstance(this).tokoDao()
        val listAdapter = TokoAdapter(this) {
            val intent = Intent(this, DetailTokoActivity::class.java)
            startActivity(intent)
        }

        with(binding.rvToko) {
            layoutManager = LinearLayoutManager(this@ListActivity)
            setHasFixedSize(true)
            adapter = listAdapter
        }

        lifecycleScope.launch {
            val toko = withContext(Dispatchers.IO) { db.getAllToko() }
            listAdapter.list = toko
        }
    }
}