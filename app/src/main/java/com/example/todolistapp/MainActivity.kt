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

        // MutableList to hold all user generated folder names
        val folderList: MutableList<String> = ArrayList()

        // Add new folder button click
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
                // Dismiss alert
                mAlertDialog.dismiss()
                // Get user inputted folder name from alert dialog
                val folderName = (mDialogView.dialogFolderName.text.toString()).trim()
                // Add folder name into list if not left blank
                if (folderName.isNotEmpty()) {
                    folderList.add(folderName)
                }
            }
            // Cancel button click on custom layout
            mDialogView.cancel.setOnClickListener {
                // Dismiss alert
                mAlertDialog.dismiss()
            }
        }

        // Custom adapter for ListView - pass both context/user's folder list
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
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main, viewGroup, false)

            val folderNames = rowMain.findViewById<TextView>(R.id.folderName_textView)
            folderNames.text = folders.get(position)

            val positionTextView = rowMain.findViewById<TextView>(R.id.position_textView)
            positionTextView.text = "Row number: $position"

            return rowMain
            //val textView = TextView(mContext)
            //textView.text = folders.get(position)
            //return textView
        }
    }
}
