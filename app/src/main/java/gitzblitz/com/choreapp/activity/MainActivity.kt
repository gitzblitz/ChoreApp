package gitzblitz.com.choreapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHandler = ChoresDatabaseHandler(this)
        progressBar = ProgressBar(this)
        // check if db has a count then navigate to chore list activity
        checkDB()

        btnSaveChore.setOnClickListener {

            progressBar!!.isIndeterminate = true
            progressBar!!.visibility = View.VISIBLE
            if (!TextUtils.isEmpty(popupEnterChoreName.text.toString())
                    && !TextUtils.isEmpty(popupAssignToId.text.toString())
                    && !TextUtils.isEmpty(popupAssignById.text.toString())){
                // save to db

                var chore = Chore()
                chore.choreName = popupEnterChoreName.text.toString()
                chore.assignedTo = popupAssignToId.text.toString()
                chore.assignedBy = popupAssignById.text.toString()
                saveToDB(chore)

                progressBar!!.visibility = View.GONE

                startActivity(Intent(this, ChoreListActivity::class.java))

            } else {
                Toast.makeText(this, "Please enter a chore", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun checkDB() {
        if (databaseHandler!!.getChoresCount() > 0){
            startActivity(Intent(this, ChoreListActivity::class.java))
        }
    }

    fun saveToDB(chore: Chore){
        databaseHandler!!.createChore(chore)
    }
}
