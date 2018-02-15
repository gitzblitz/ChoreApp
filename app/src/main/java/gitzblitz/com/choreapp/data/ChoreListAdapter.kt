package gitzblitz.com.choreapp.data

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import gitzblitz.com.choreapp.R
import gitzblitz.com.choreapp.model.Chore
import kotlinx.android.synthetic.main.popup.view.*

/**
 * Created by george.ngethe on 15/02/2018.
 */
class ChoreListAdapter(private val list: ArrayList<Chore>, private val context: Context) : RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    private val TAG: String = "CHORELISTADAPTER"

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder {
        //create view
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)

        return ViewHolder(view, context, list)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindViews(list[position])
    }

    inner class ViewHolder(itemView: View, context: Context, list: ArrayList<Chore>) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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

            when (v!!.id) {
                choreDeleteButton.id -> {
                    deleteChore(chore.id!!.toInt())
                    mList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
                choreEditButton.id -> {
                    editChore(chore)
                }
            }
        }

        fun deleteChore(id: Int) {

            var db = ChoresDatabaseHandler(mContext)

            db.deleteChore(id)
        }

        fun editChore(chore: Chore) {

            var dialogBuilder: AlertDialog.Builder?
            var dialog: AlertDialog?
            var databaseHandler: ChoresDatabaseHandler = ChoresDatabaseHandler(mContext)

            var view = LayoutInflater.from(mContext).inflate(R.layout.popup, null)

            var choreName = view.popupEnterChoreName
            var assignedBy = view.popupAssignById
            var assignedTo = view.popupAssignToId
            var popSaveButton = view.popupBtnSaveChore

            dialogBuilder = AlertDialog.Builder(mContext).setView(view)
            dialog = dialogBuilder!!.create()
            dialog.setTitle(R.string.chore_popup_update_title)
            dialog?.show()

            popSaveButton.setOnClickListener {
                var name = choreName.text.toString().trim()
                var aBy = assignedBy.text.toString().trim()
                var aTo = assignedTo.text.toString().trim()

                if (!TextUtils.isEmpty(name)
                        && !TextUtils.isEmpty(aBy)
                        && !TextUtils.isEmpty(aTo)) {

                    chore.choreName = name
                    chore.assignedTo = aTo
                    chore.assignedBy = aBy

                    databaseHandler.updateChore(chore)
                    notifyItemChanged(adapterPosition, chore)

                    dialog.dismiss()

                    Log.d(TAG, "Chore Edited")

                } else {
                    Log.e(TAG, "Chore not updated to database")
                }
            }
        }
    }


}