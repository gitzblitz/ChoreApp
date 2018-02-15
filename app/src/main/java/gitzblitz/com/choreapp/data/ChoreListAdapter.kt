package gitzblitz.com.choreapp.data

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import gitzblitz.com.choreapp.R
import gitzblitz.com.choreapp.model.Chore

/**
 * Created by george.ngethe on 15/02/2018.
 */
class ChoreListAdapter(private val list: ArrayList<Chore>, private val context: Context) : RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder {
        //create view
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

            holder?.bindViews(list[position])


    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var choreName = itemView.findViewById(R.id.listChoreName) as TextView
        var choreAssignedBy = itemView.findViewById(R.id.listAssignedBy) as TextView
        var choreAssignedTo = itemView.findViewById(R.id.listAssignedTo) as TextView
        var chorelistDate = itemView.findViewById(R.id.listDate) as TextView


        fun bindViews(chore: Chore): Unit {
            choreName.text = chore.choreName
            choreAssignedBy.text = chore.assignedBy
            choreAssignedTo.text = chore.assignedTo
            chorelistDate.text = chore.showHumanDate(System.currentTimeMillis())
        }
    }


}