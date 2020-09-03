package com.getzuper.challengeforzuper.data.model

data class MessageFromJS (
    val id: String = "",
    val message: String = "",
    var progress: String? = "",
    val state: String? = "") {

    fun isProgressType(): Boolean {
        return message == "progress"
    }
    fun isCompletedType(): Boolean {
        return message == "completed"
    }
}
/* Sample Result
{"id":"1","message":"progress","progress":28}
{"id":"1","message":"completed","state":"error"}
{"id":"3","message":"completed","state":"success"}
*/