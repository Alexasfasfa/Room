package com.example.room.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.room.R
import com.example.room.utils.Constants

class AddEditActivity : AppCompatActivity() {
    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var numberPicker: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        editTextTitle = findViewById(R.id.title)
        editTextDescription = findViewById(R.id.description)
        numberPicker = findViewById(R.id.priority)

        numberPicker.maxValue = 10
        numberPicker.minValue = 0

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        if (intent.hasExtra(Constants.EXTRA_ID)) {
            title = "Edit Note"
            editTextTitle.setText(intent.getStringExtra(Constants.EXTRA_TITLE))
            editTextDescription.setText(intent.getStringExtra(Constants.EXTRA_DESCRIPTION))
            numberPicker.value = intent.getIntExtra(Constants.EXTRA_PRIORITY, -1)
        } else {
            title = "Add Note"
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_menu_item -> {
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val title = editTextTitle.text.toString()
        val description = editTextDescription.text.toString()
        val priority = numberPicker.value

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this@AddEditActivity, "Please insert title and description", Toast.LENGTH_SHORT).show()
            return
        }


        val id = intent.getIntExtra(Constants.EXTRA_ID, -1)
        if (id != -1) {
            setResult(Constants.EDIT_REQUEST_CODE, Intent().apply {
                putExtra(Constants.EXTRA_TITLE, title)
                putExtra(Constants.EXTRA_DESCRIPTION, description)
                putExtra(Constants.EXTRA_PRIORITY, priority)
                putExtra(Constants.EXTRA_ID, id)
            })
        } else {
            setResult(Constants.REQUEST_CODE, Intent().apply {
                putExtra(Constants.EXTRA_TITLE, title)
                putExtra(Constants.EXTRA_DESCRIPTION, description)
                putExtra(Constants.EXTRA_PRIORITY, priority)
            })
        }
        finish()

    }
}