package com.example.sellersablonlt.ui.desain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sellersablonlt.databinding.AddDesainBinding
import com.example.sellersablonlt.db.Desain
import com.example.sellersablonlt.db.TokoDao
import com.example.sellersablonlt.db.TokoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddDesain :  AppCompatActivity() {
    companion object {
        const val REQ_CODE = 110
    }

    private lateinit var binding: AddDesainBinding
    private lateinit var db: TokoDao
    private var imageUri = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDesainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TokoDatabase.getInstance(this).tokoDao()
        binding.fotoProduk.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*";
            intent.action = Intent.ACTION_OPEN_DOCUMENT;
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            startActivityForResult(intent, REQ_CODE)
        }

        binding.btnTambah.setOnClickListener {
            val name = binding.namaDesain.text.toString()
            val desc = binding.deskripsi.text.toString()
            val harga = binding.harga.text.toString()
            if (name.isEmpty() || desc.isEmpty() ||harga.isEmpty() || imageUri.isEmpty()) {
                Toast.makeText(this, "There is empty data", Toast.LENGTH_SHORT).show()
            } else {
                insertToDb()
            }
        }
    }

    private fun insertToDb() {
        val name = binding.namaDesain.text.toString()
        val desc = binding.deskripsi.text.toString()
        val harga = binding.harga.text.toString()
        val image = imageUri
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val desain = Desain (name = name, desc = desc, harga = harga,image = image)
                db.insertDesain(desain)
            }
            Toast.makeText(this@AddDesain, "Success", Toast.LENGTH_SHORT).show()
            finish()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK || requestCode == REQ_CODE) {
            val uri = data?.data as Uri
            imageUri = uri.toString()
            binding.fotoProduk.setImageURI(uri)
        }
    }


}