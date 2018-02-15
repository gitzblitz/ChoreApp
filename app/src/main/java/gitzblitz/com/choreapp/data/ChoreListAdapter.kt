package gitzblitz.com.choreapp.data

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import gitzblitz.com.choreapp.R
import gitzblitz.com.choreapp.model.Chore

/**
 * Created by george.ngethe on 15/02/2018.
 */
class ChoreListAdapter(private val list: ArrayList<Chore>, private val context: Context) : RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    private val TAG: String = "CHORELISTADAPTER"

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder {
        //create view
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent,false)

        return ViewHolder(view, context, list)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

            holder?.bindViews(list[position])


    }

  inner class ViewHolder(itemView: View, context: Context, list: ArrayList<Chore>): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var mContext = context
        var mList = list
        var choreName = itemView.findViewById(R.id.listChoreName) as TextView
        var choreAssignedBy = itemView.findViewById(R.id.listAssignedBy) as TextView
        var choreAssignedTo = itemView.findViewById(R.id.listAssignedTo) as TextView
        var chorelistDate = itemView.findViewById(R.id.listDate) as TextView
        var choreDeleteButton = itemView.findViewById(R.id.listDeleteButton) as Button
        var choreEditButton = itemView.findViewById(R.id.listEditButton) as Button


        fun bindViews(chore: Chore): Unit {
            choreName.text = chore.choreName
            choreAssignedBy.text = chore.assignedBy
            choreAssignedTo.text = chore.assignedTo
            chorelistDate.text = chore.showHumanDate(System.currentTimeMillis())
            choreDeleteButton.setOnClickListener(this)
            choreEditButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            var mPosition: Int = adapterPosition

            var chore = mList[mPosition]

            when(v!!.id){
                choreDeleteButton.id ->{
                    deleteChore(chore.id!!.toInt())
                    mList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                    Toast.makeText(mContext, "Chore deleted", Toast.LENGTH_LONG).show()

                }
                choreEditButton.id->{
                    Toast.makeText(mContext, "Edit clicked", Toast.LENGTH_LONG).show()
                }
            }
        }

        fun deleteChore(id: Int){

            var db: ChoresDatabaseHandler = ChoresDatabaseHandler(mContext)

            db.deleteChore(id)
        }
    }


}