package com.example.todolistapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.folder_name.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val folderList: MutableList<String> = ArrayList()

        // Button click
        button.setOnClickListener {
            // Inflate dialog with view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.folder_name, null)
            // Alert Dialog Builder
            val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("New Folder Name")
            // Show dialog
            val mAlertDialog = mBuilder.show()
            // Save button click on custom layout
            mDialogView.save.setOnClickListener {
                // dismiss alert
                mAlertDialog.dismiss()
                // get values from alert dialog
                val folderName = mDialogView.dialogFolderName.text.toString()
                // Set inputed text in listView
                folderList.add(folderName)
            }
            // Cancel button click on custom layout
            mDialogView.cancel.setOnClickListener {
                // dismiss alert
                mAlertDialog.dismiss()
            }
        }

        //val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, folderList)

        // Custom adapter for ListView
        //listView.adapter = arrayAdapter
        listView.adapter = MyCustomAdapter(this, folderList)
    }

    private class MyCustomAdapter(context: Context, list: MutableList<String>): BaseAdapter() {

        private val mContext: Context
        private val folders = list

        init {
            mContext = context
        }

        // Responsible for how many rows in ListView
        override fun getCount(): Int {
            return folders.size
        }

        // Ignore for now
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // Ignore for now
        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        // responsible for rendering each row in ListView
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = folders.get(position)
            return textView
        }
    }
}
