package com.example.sellersablonlt.ui.toko

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sellersablonlt.databinding.AddTokoBinding
import com.example.sellersablonlt.db.Toko
import com.example.sellersablonlt.db.TokoDao
import com.example.sellersablonlt.db.TokoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddActivity : AppCompatActivity() {
    companion object {
        const val REQ_CODE = 110
    }

    private lateinit var binding: AddTokoBinding
    private lateinit var db: TokoDao
    private var imageUri = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TokoDatabase.getInstance(this).tokoDao()
        binding.fotoToko.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*";
            intent.action = Intent.ACTION_OPEN_DOCUMENT;
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            startActivityForResult(intent, REQ_CODE)
        }

        binding.btnSimpan.setOnClickListener {
            val name = binding.nama.text.toString()
            val alamat = binding.alamat.text.toString()
            if (name.isEmpty() || alamat.isEmpty() || imageUri.isEmpty()) {
                Toast.makeText(this, "There is empty data", Toast.LENGTH_SHORT).show()
            } else {
                insertToDb()
            }
        }
    }

    private fun insertToDb() {
        val name = binding.nama.text.toString()
        val alamat = binding.alamat.text.toString()
        val image = imageUri
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val toko = Toko(name = name, alamat = alamat, image = image)
                db.insertToko(toko)
            }
            Toast.makeText(this@AddActivity, "Success", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK || requestCode == REQ_CODE) {
            val uri = data?.data as Uri
            imageUri = uri.toString()
            binding.fotoToko.setImageURI(uri)
        }
    }
}