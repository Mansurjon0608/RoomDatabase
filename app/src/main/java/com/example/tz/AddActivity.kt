package com.example.tz

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.tz.databinding.ActivityAddBinding
import com.github.florent37.runtimepermission.kotlin.askPermission
import dao.AppDatabase
import dao.Contact
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var appDatabase: AppDatabase
    lateinit var binding: ActivityAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.btnSave.setOnClickListener {
            val contact = Contact()

            contact.name = binding.txtName.text.toString()
            contact.number = binding.txtNumber.text.toString()
            contact.mail = binding.txtMail.text.toString()
            contact.info = binding.txtInfo.text.toString()
            contact.imagePath = absolutePath

            appDatabase.contactDao().addContact(contact)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)

        }

        appDatabase = AppDatabase.getInstance(this)
        val listContact = appDatabase.contactDao().getAllContact()




        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        binding.image.setOnClickListener {
            askPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
                getImageContent.launch("image/*")
            }.onDeclined { e ->
                if (e.hasDenied()) {
                    AlertDialog.Builder(this)
                        .setMessage("Please accept our permissions")
                        .setPositiveButton("Yes") { dialog, which ->
                            e.askAgain();
                        } //ask again
                        .setNegativeButton("No") { dialog, which ->
                            dialog.dismiss();
                        }
                        .show()
                }

                if (e.hasForeverDenied()) {
                    //the list of forever denied permissions, user has check 'never ask again'

                    // you need to open setting manually if you really need it
                    e.goToSettings();
                }
            }
        }
    }


    var absolutePath = ""
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
            uri ?: return@registerForActivityResult
            binding.image.setImageURI(uri)
            val inputStream = contentResolver?.openInputStream(uri)
            val format = SimpleDateFormat("yyMMdd_hhss").format(Date())
            val file = File(filesDir, "${format}image.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            absolutePath = file.absolutePath

            Toast.makeText(this, "$absolutePath", Toast.LENGTH_SHORT).show()
        }
}