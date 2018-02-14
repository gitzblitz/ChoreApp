package gitzblitz.com.choreapp.model

import java.text.DateFormat
import java.util.*

/**
 * Created by george.ngethe on 14/02/2018.
 */
class Chore() {
    var choreName: String? = null
    var assignedBy: String? = null
    var assignedTo: String? = null
    var timeAssigned: Long? = null
    var id:Int? = null

    constructor(choreName: String, assignedBy: String, assignedTo: String, timeAssigned: Long, id: Int):this(){
        this.choreName = choreName
        this.assignedBy = assignedBy
        this.assignedTo = assignedTo
        this.timeAssigned = timeAssigned
        this.id = id
    }
    fun showHumanDate(timeAssigned: Long): String {

        var dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        var formattedDate: String = dateFormat.format(Date(timeAssigned).time)

        return "Created: ${formattedDate}"

    }

}