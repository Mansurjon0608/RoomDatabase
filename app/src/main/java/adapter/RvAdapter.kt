package adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tz.databinding.ItemRvBinding
import dao.Contact

class RvAdapter(val list: ArrayList<Contact>, val context: Context, var rvItemClick: RvItemClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {


        fun onBind(contact: Contact, position: Int) {
            itemRv.txtUsername.text = contact.name.toString()
            itemRv.txtNumber.text = contact.number.toString()
            itemRv.txtTime.text = contact.time.toString().trim()

            itemRv.profileImage.setImageURI((Uri.parse(contact.imagePath)))

            itemRv.root.setOnClickListener {
                rvItemClick.onClick(contact, position)
            }

            itemRv.btnDelete.setOnClickListener {
                rvItemClick.delete(contact, position)
            }

            itemRv.btnEdit.setOnClickListener {
                rvItemClick.edit(contact, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}

interface RvItemClick {
    fun edit(contact: Contact, position: Int)
    fun delete(contact: Contact, position: Int)
    fun onClick(contact: Contact, position: Int)

}



