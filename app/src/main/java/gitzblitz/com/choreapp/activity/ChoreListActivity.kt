package gitzblitz.com.choreapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import gitzblitz.com.choreapp.R
import gitzblitz.com.choreapp.data.ChoreListAdapter
import gitzblitz.com.choreapp.data.ChoresDatabaseHandler
import gitzblitz.com.choreapp.model.Chore
import kotlinx.android.synthetic.main.activity_chore_list.*
import kotlinx.android.synthetic.main.popup.view.*

class ChoreListActivity : AppCompatActivity() {
    private val TAG: String = "CHORELISTACTIVITY"
    private var adapter: ChoreListAdapter? = null
    private var choreList: ArrayList<Chore>? = null
    private var choreListItems: ArrayList<Chore>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var dialogBuilder: AlertDialog.Builder? = null
    private var dialog: AlertDialog? = null
    var databaseHandler: ChoresDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chore_list)

        databaseHandler = ChoresDatabaseHandler(this)

        choreList = ArrayList<Chore>()
        choreListItems = ArrayList()
        layoutManager = LinearLayoutManager(this)
        adapter = ChoreListAdapter(choreListItems!!, this)


        //setup view & recycler list

        choreRecyclerView.layoutManager = layoutManager

        choreRecyclerView.adapter = adapter

        choreList  = databaseHandler!!.readChores()
        choreList!!.reverse()



//
        for (c in choreList!!.iterator()){
            Log.d("List", c.choreName)
            val chore = Chore()
            chore.choreName = c.choreName
            chore.assignedBy = c.assignedBy
            chore.assignedTo = c.assignedTo
            chore.showHumanDate(c.timeAssigned!!)

            choreListItems!!.add(chore)
        }
        adapter!!.notifyDataSetChanged()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId == R.id.addMenuButton){
            Log.d(TAG, "Menu button was clicked")
            createPopupDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    fun createPopupDialog(): Unit {
        var view = layoutInflater.inflate(R.layout.popup, null)

        var choreName = view.popupEnterChoreName
        var assignedBy = view.popupAssignById
        var assignedTo = view.popupAssignToId
        var popSaveButton = view.popupBtnSaveChore

        dialogBuilder = AlertDialog.Builder(this).setView(view)
        dialog = dialogBuilder!!.create()
        dialog!!.show()


        popSaveButton.setOnClickListener {
            var name = choreName.text.toString().trim()
            var aBy = assignedBy.text.toString().trim()
            var aTo = assignedTo.text.toString().trim()

            if (!TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(aBy) && !TextUtils.isEmpty(aTo)){
                var chore = Chore()
                chore.choreName = name
                chore.assignedTo = aTo
                chore.assignedBy = aBy

                databaseHandler!!.createChore(chore)

                dialog!!.dismiss()
                startActivity(Intent(this, ChoreListActivity::class.java))
                finish()
                Log.d(TAG, "Chore added")
                Toast.makeText(this, " Chore Added", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, " Chore not added", Toast.LENGTH_LONG).show()
                Log.e(TAG, "Chore not added to database")

            }
        }

    }
}
