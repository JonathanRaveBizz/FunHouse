package com.example.funhouse

import Records
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class BookingAdapter :RecyclerView.Adapter<BookingAdapter.ViewHolder>() {
    lateinit var listener: BookingListener

    private var RecordList: List<Records> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingAdapter.ViewHolder {
        Log.d("_WALLPAPER", "created Recylcer Veiw")
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.record_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = RecordList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(RecordList[position])
    }
    fun updateList(newList: List<Records>){
        this.RecordList= newList
        notifyDataSetChanged()
    }

    inner class  ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        private var mugshot: ImageView = itemView.findViewById(R.id.mug)
        private var name : TextView = itemView.findViewById(R.id.name)
        private var bookDate: TextView = itemView.findViewById(R.id.bookdate)
        private lateinit var booking: Records

        fun bind(record: Records) {
            booking = record
            mugshot.apply {
                Glide.with(itemView)
                    .load(booking.mugshot)
                    .into(mugshot)
            }
            name.text = booking.name
            bookDate.text = booking.book_date_formatted

        }

    }


}