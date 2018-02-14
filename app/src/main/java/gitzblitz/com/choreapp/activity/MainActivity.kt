package gitzblitz.com.choreapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import gitzblitz.com.choreapp.R
import gitzblitz.com.choreapp.data.ChoresDatabaseHandler
import gitzblitz.com.choreapp.model.Chore

class MainActivity : AppCompatActivity() {

    var choresDatabaseHandler: ChoresDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choresDatabaseHandler = ChoresDatabaseHandler(this)

        var chore = Chore()
        chore.choreName = "Clean room 2"
        chore.assignedTo = "Joe"
        chore.assignedBy = "Gitz"

        choresDatabaseHandler!!.createChore(chore)

        //read from database

        var chores: Chore = choresDatabaseHandler!!.readChore(2)

        Log.d("Item: ", chores.choreName)
    }
}
