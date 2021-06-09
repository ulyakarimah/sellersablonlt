package com.example.sellersablonlt.ui.toko


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sellersablonlt.databinding.ActivityDesainBinding
import com.example.sellersablonlt.db.TokoDatabase
import com.example.sellersablonlt.ui.desain.DesainAdapter
import com.example.sellersablonlt.ui.desain.DetailDesainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DesainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "ID_TOKO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDesainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = TokoDatabase.getInstance(this).tokoDao()
        val idToko = intent.getIntExtra(EXTRA_ID, 1)

        lifecycleScope.launch {
            val data = withContext(Dispatchers.IO) { db.getTokoById(idToko) }
            if (data != null) {
                binding.namaToko.text = data.name
                binding.alamat.text = data.alamat

                with(this@DesainActivity) {
                    com.bumptech.glide.Glide.with(this)
                        .load(data.image)
                        .into(binding.fotoToko)

                }
            }
        }
        val listAdapter = DesainAdapter(this) {
            val intent = Intent(this, DetailDesainActivity::class.java)
            intent.putExtra(DetailDesainActivity.EXTRA_ID, it.id)
            startActivity(intent)
        }

        with(binding.rvListdesain) {
            layoutManager = GridLayoutManager(this@DesainActivity,2)
            setHasFixedSize(true)
            adapter = listAdapter
        }

        lifecycleScope.launch {
            val desain = withContext(Dispatchers.IO) { db.getAllDesain() }
            listAdapter.list = desain

        }
    }
}




