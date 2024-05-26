package com.example.myscheduler

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.realm.OrderedRealmCollection

class ScheduleAdapter(data: OrderedRealmCollection<Schedule>?) : RealmBaseAdapter<Schedule>(data),
    ListAdapter {

    inner class ViewHolder(cell: View) {
        val date = cell.findViewById<TextView>(android.R.id.text1)
        val title = cell.findViewById<TextView>(android.R.id.text2)
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        when (converView) {
            null -> {
                val inflater = LayoutInflater.from(parent?.context)
                view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
                viewHolder = ViewHolder(view)
            }
            else -> {
                view = converView
                viewHolder = view.tag as ViewHolder
            }
        }
        adapterDate?.run {
            val schedule = get(position)
            viewHolder.date.text = DateFormat.format("yyyy/MM/dd", schedule.date)
            viewHolder.title.text = schedule.title
        }
        return view
    }
}

open class RealmBaseAdapter<T>(value: OrderedRealmCollection<T>?) {

}
