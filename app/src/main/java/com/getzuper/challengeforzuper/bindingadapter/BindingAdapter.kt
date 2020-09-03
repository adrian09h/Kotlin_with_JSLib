package com.getzuper.challengeforzuper.bindingadapter

import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.getzuper.challengeforzuper.R
import com.getzuper.challengeforzuper.data.model.MessageFromJS
import kotlinx.android.synthetic.main.item_operation.view.*

@BindingAdapter("bind:setProgress")
fun setProgress(progressBar: ProgressBar, messageFromJS: MessageFromJS) {
    if (!messageFromJS.progress.isNullOrEmpty()) {
        progressBar.progress = messageFromJS.progress!!.toInt()
    }
}

@BindingAdapter("bind:setProgressText")
fun setProgressText(textView: TextView, messageFromJS: MessageFromJS) {
    textView.text = messageFromJS.progress
    if (messageFromJS.isCompletedType()) {
        if( messageFromJS.state!! == "success") {
            textView.setTextColor(textView.context.getColor(R.color.greenColor))
        } else {
            textView.setTextColor(textView.context.getColor(R.color.redColor))
        }
    }
}
