package dao

import androidx.room.*


@Dao

interface ContectDao{

    @Query("select * from contact")
    fun getAllContact():List<Contact>

    @Insert
    fun addContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Update
    fun editContact(contact: Contact)




}