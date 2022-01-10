package com.example.tz

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tz.databinding.ActivityInfo2Binding
import dao.Contact

class InfoActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityInfo2Binding
    lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar.hide()

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        contact = intent.getSerializableExtra("contact") as Contact

        binding.txtInfo.text = contact.name
        binding.txtNumber.text = contact.number
        binding.txtMail.text = contact.mail
        binding.txtInfo.text = contact.info

        binding.imageInfo.setImageURI(Uri.parse(contact.imagePath))
    }
}