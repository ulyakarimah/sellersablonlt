package com.example.sellersablonlt.ui.desain

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sellersablonlt.databinding.DetailDesainBinding
import com.example.sellersablonlt.db.Desain
import com.example.sellersablonlt.db.TokoDao
import com.example.sellersablonlt.db.TokoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailDesainActivity : AppCompatActivity() {

    private lateinit var dao: TokoDao


    companion object {
        const val EXTRA_ID = "ID_DESAIN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DetailDesainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = TokoDatabase.getInstance(this).tokoDao()
        val idDesain = intent.getIntExtra(EXTRA_ID, 0)
        lifecycleScope.launch {

            val data = withContext(Dispatchers.IO) { db.getDesainById(idDesain) }
            binding.namaDesain.text = data.name
            binding.harga.text = data.harga
            binding.deskripsi.text = data.desc
            with(this@DetailDesainActivity) {
                com.bumptech.glide.Glide.with(this)
                    .load(data.image)
                    .into(binding.fotoProduk)
            }

            binding.hapus.setOnClickListener {
                deleteDesain(data)
                finish()
            }
        }
    }

            private fun deleteDesain(data: Desain) {
                dao = TokoDatabase.getInstance(this).tokoDao()
                lifecycleScope.launch (Dispatchers.IO){
                    dao.deleteDesain(data)
                }
                Toast.makeText(applicationContext, "Sukses", Toast.LENGTH_SHORT).show()
            }
        }
