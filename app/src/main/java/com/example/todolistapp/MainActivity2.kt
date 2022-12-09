package com.example.todolistapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.folder_name.view.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val folder = intent.getStringExtra("folderName")

        var actionBar = supportActionBar

        if (actionBar != null) {
            title = folder
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // MutableList to hold all user generated folder names
        val folderList: MutableList<String> = ArrayList()

        button2.setOnClickListener {
            // Inflate dialog with view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.folder_name, null)
            // Alert Dialog Builder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("New Note Name")
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
                    if (!folderList.contains(folderName)) {
                        folderList.add(folderName)
                    }
                }
            }
            // Cancel button click on custom layout
            mDialogView.cancel.setOnClickListener {
                // Dismiss alert
                mAlertDialog.dismiss()
            }
        }

        listView2.adapter = NoteAdapter(this, folderList)

    }

    private class NoteAdapter(context: Context, list: MutableList<String>): BaseAdapter() {

        private val mContext: Context
        private val notes = list

        init {
            mContext = context
        }

        // Responsible for how many rows in ListView
        override fun getCount(): Int {
            return notes.size
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
            val rowMain = layoutInflater.inflate(R.layout.note_main, viewGroup, false)

            val noteNames = rowMain.findViewById<TextView>(R.id.noteName_textView)
            noteNames.text = notes.get(position)

            return rowMain
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}