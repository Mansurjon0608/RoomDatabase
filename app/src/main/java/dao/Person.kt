package dao

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity

class Contact:Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @ColumnInfo()
    var name:String? = null

    var imagePath:String? = null

    var number:String? = null

    var mail:String? = null

    var info:String? = null

    @SuppressLint("SimpleDateFormat")
    var time = SimpleDateFormat("HH:mm").format(Date())




    constructor(id: Int?, name: String?, number: String?, mail: String?, info:String?) {
        this.id = id
        this.name = name
        this.number = number
        this.mail = mail
        this.info = info

    }

    constructor(name: String?, number: String?, mail: String?, imagePath:String?) {
        this.name = name
        this.number = number
        this.mail = mail
        this.imagePath = imagePath
    }

    constructor()

/*
    override fun toSting(): String {
        return "Contact(id=$id, name=$name, number=$number, mail=$mail, time=$time)"
    }
*/


}