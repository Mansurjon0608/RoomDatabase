package com.example.tz

import adapter.RvAdapter
import adapter.RvItemClick
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tz.databinding.ActivityMainBinding
import dao.AppDatabase
import dao.Contact
import kotlinx.android.synthetic.main.activity_main.*
import utils.MyObject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var appDatabase: AppDatabase
    lateinit var listData: ArrayList<Contact>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }


    override fun onStart() {
        super.onStart()


        binding.btnAdd.setOnClickListener {
            var intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }


        val contact = Contact()
        appDatabase = AppDatabase.getInstance(this)

        listData = ArrayList()

        listData.addAll(appDatabase.contactDao().getAllContact())

        rvAdapter = RvAdapter(listData, this, object : RvItemClick {
            override fun edit(contact: Contact, position: Int) {
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtra("contact", contact)
                startActivity(intent)
            }


            override fun delete(contact: Contact, position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "Delete id $position",
                    Toast.LENGTH_SHORT
                ).show()

                val alertDialog = AlertDialog.Builder(this@MainActivity)

                alertDialog.setTitle("Delete")
                alertDialog.setMessage("Do you want to delete ${contact.name}?")

                alertDialog.setPositiveButton(
                    "Yes",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Toast.makeText(
                                this@MainActivity,
                                "Deleted",
                                Toast.LENGTH_SHORT
                            ).show()

                            appDatabase.contactDao().deleteContact(contact)
                            listData.remove(contact)
                            rvAdapter.notifyItemRemoved(position)
                            rvAdapter.notifyItemChanged(position, listData.size)


                        }

                    })

                alertDialog.setNegativeButton(
                    "No",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Toast.makeText(
                                this@MainActivity,
                                "Canceled",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    })

                alertDialog.setNeutralButton(
                    "Nothing...",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Toast.makeText(
                                this@MainActivity,
                                "Nothing, progress is canceled",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
                alertDialog.show()


            }

            override fun onClick(contact: Contact, position: Int) {
                val intent = Intent(this@MainActivity, InfoActivity2::class.java)
                intent.putExtra("contact", contact)
                startActivity(intent)
            }

        })


        binding.rv.adapter = rvAdapter


        binding.rv.setHasFixedSize(true)


        //add swipe
      /*  val swipe = object : MySwipeHelper(this, binding.rv, 120) {

            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                //add button
                buffer.add(
                    MyButton(this@MainActivity,
                        "Delete",
                        30,
                        R.drawable.ic_baseline_delete_sweep_24,
                        Color.parseColor("#01BAFD"),
                        object : MyButtonClickListener {
                            override fun onClick(position: Int) {
                               Toast.makeText(
                                    this@MainActivity,
                                    "Delete id $position",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val alertDialog = AlertDialog.Builder(this@MainActivity)

                                alertDialog.setTitle("Delete")
                                alertDialog.setMessage("Do you want to delete ${contact.name}?")

                                alertDialog.setPositiveButton(
                                    "Yes",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Deleted",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            appDatabase.contactDao().deleteContact(contact)
                                            listData.remove(contact)
                                            rvAdapter.notifyItemRemoved(position)
                                            rvAdapter.notifyItemChanged(position, listData.size)


                                        }

                                    })

                                alertDialog.setNegativeButton(
                                    "No",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Canceled",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        }

                                    })

                                alertDialog.setNeutralButton(
                                    "Nothing...",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Nothing, progress is canceled",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                    })
                                alertDialog.show()


                            }
                        })


                )
                buffer.add(
                    MyButton(this@MainActivity,
                        "Edit",
                        30,
                        R.drawable.ic_baseline_edit_24,
                        Color.parseColor("#2CB82C"),
                        object : MyButtonClickListener {
                            override fun onClick(position: Int) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Update id $position",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this@MainActivity, EditActivity::class.java)
                                intent.putExtra("contact", contact)
                                intent.putExtra("intent_type", Constants.TYPE_UPDATE)
                                startActivity(intent)
                            }
                        })
                )
            }


        }*/
        binding.rv.adapter = rvAdapter


    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (MyObject.slpash == true) {
            finishAffinity()
        }
    }
}