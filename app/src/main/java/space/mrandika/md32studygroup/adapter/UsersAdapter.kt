package space.mrandika.md32studygroup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.mrandika.md32studygroup.R
import space.mrandika.md32studygroup.model.UserResponse

class UsersAdapter(private val response: UserResponse): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.name_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = response.results?.get(position)?.name?.first
    }

    override fun getItemCount(): Int = response.results!!.size
}