package com.suhazri.sqlitedemo

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.suhazri.sqlitedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserData: EventDataSqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.LoadBtn.isEnabled = false

        mUserData = EventDataSqlHelper(this)

        binding.SaveBtn.setOnClickListener {
            enterData(binding.NameEditText.text.toString(),
                binding.EmailEditText.text.toString(),
                binding.PhoneEditText.text.toString())
            binding.LoadBtn.isEnabled = true
        }

        binding.LoadBtn.setOnClickListener {
            val cursor = getEvents()
            if (cursor!= null) {
                binding.LoadTextView.text = showData(cursor)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mUserData.close()
    }

    private fun enterData(name: String, email: String, phone: String) {
        val db = mUserData.writableDatabase
        val values = ContentValues()
        values.put(EventDataSqlHelper.COL_NAME, name)
        values.put(EventDataSqlHelper.COL_EMAIL, email)
        values.put(EventDataSqlHelper.COL_PHONE, phone)
        db?.insert(EventDataSqlHelper.TABLE_NAME, null, values)
        db?.close()

    }

    private fun getEvents(): Cursor {
        val db = mUserData.readableDatabase
        val cursor = db.query(EventDataSqlHelper.TABLE_NAME, null, null, null, null, null, null)
        return cursor
    }

    private fun showData(cursor: Cursor): String {
        val ret = StringBuilder("")
        while(cursor.moveToNext()) {
            val id = cursor.getLong(0)
            val name = cursor.getString(1)
            val email = cursor.getString(2)
            val phone = cursor.getString(3)
            ret.append("ID: $id, Name: $name, Email: $email, Phone: $phone")
            ret.append("\n")
        }
        return ret.toString()
    }

}