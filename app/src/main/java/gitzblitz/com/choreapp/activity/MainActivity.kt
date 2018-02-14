package gitzblitz.com.choreapp.activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import gitzblitz.com.choreapp.R
import gitzblitz.com.choreapp.data.ChoresDatabaseHandler
import gitzblitz.com.choreapp.model.Chore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var databaseHandler: ChoresDatabaseHandler? = null
    var progressBar: ProgressBar? = null
//    var enterChore = enterChoreId
//    var assignedTo = assignToId
//    var assignedBy = assignById
//    var saveChore = btnSaveChore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHandler = ChoresDatabaseHandler(this)
        progressBar = ProgressBar(this)


        btnSaveChore.setOnClickListener {

            progressBar!!.isIndeterminate = true
            progressBar!!.visibility = View.VISIBLE
            if (!TextUtils.isEmpty(enterChoreId.text.toString())
                    && !TextUtils.isEmpty(assignToId.text.toString())
                    && !TextUtils.isEmpty(assignById.text.toString())){
                // save to db

                var chore = Chore()
                chore.choreName = enterChoreId.text.toString()
                chore.assignedTo = assignToId.text.toString()
                chore.assignedBy = assignById.text.toString()
                saveToDB(chore)

                progressBar!!.visibility = View.GONE

                startActivity(Intent(this, ChoreListActivity::class.java))

            } else {
                Toast.makeText(this, "Please enter a chore", Toast.LENGTH_LONG).show()
            }
        }

//
//        var chore = Chore()
//        chore.choreName = "Clean room 2"
//        chore.assignedTo = "Joe"
//        chore.assignedBy = "Gitz"
//
//        databaseHandler!!.createChore(chore)
//
//        //read from database
//
//        var chores: Chore = databaseHandler!!.readChore(2)
//
//        Log.d("Item: ", chores.choreName)
    }

    fun saveToDB(chore: Chore){
        databaseHandler!!.createChore(chore)
    }
}
