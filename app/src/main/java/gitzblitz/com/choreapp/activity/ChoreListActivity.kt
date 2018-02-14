package gitzblitz.com.choreapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import gitzblitz.com.choreapp.R
import gitzblitz.com.choreapp.data.ChoreListAdapter
import gitzblitz.com.choreapp.data.ChoresDatabaseHandler
import gitzblitz.com.choreapp.model.Chore
import kotlinx.android.synthetic.main.activity_chore_list.*

class ChoreListActivity : AppCompatActivity() {

    private var adapter: ChoreListAdapter? = null
    private var choreList: ArrayList<Chore>? = null
    private var choreListItems: ArrayList<Chore>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
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



//
        for (c in choreList!!.iterator()){
            Log.d("List", c.choreName)
            val chore = Chore()
            chore.choreName = c.choreName
            chore.assignedBy = c.assignedBy
            chore.assignedTo = c.assignedTo

            choreListItems!!.add(chore)
        }
        adapter!!.notifyDataSetChanged()


    }
}
