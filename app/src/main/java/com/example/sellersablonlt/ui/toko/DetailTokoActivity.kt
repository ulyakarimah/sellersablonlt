package com.example.sellersablonlt.ui.toko

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sellersablonlt.databinding.ActivityDetailtokoBinding
import com.example.sellersablonlt.ui.desain.AddDesain

class DetailTokoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailtokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambahkan.setOnClickListener {
            startActivity(Intent(this, AddDesain::class.java))
        }
    }
}


