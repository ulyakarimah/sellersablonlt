package com.example.sellersablonlt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sellersablonlt.databinding.ActivityMainBinding
import com.example.sellersablonlt.ui.toko.AddActivity
import com.example.sellersablonlt.ui.toko.DesainActivity
import com.example.sellersablonlt.ui.toko.DetailTokoActivity
import com.example.sellersablonlt.ui.toko.ListActivity

class MainActivity : AppCompatActivity() {

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                val binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

                binding.btnAdd.setOnClickListener {
                    startActivity(Intent(this, AddActivity::class.java)) }

                binding.btnProfile.setOnClickListener {
                    startActivity(Intent(this, ListActivity::class.java)) }

                binding.btnCatalog.setOnClickListener {
                    startActivity(Intent(this, DesainActivity::class.java)) }
            }
        }
