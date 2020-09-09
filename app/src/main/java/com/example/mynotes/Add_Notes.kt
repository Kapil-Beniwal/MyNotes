package com.example.mynotes;

import android.content.ContentValues
import android.os.BaseBundle
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add__notes.*


class AddNoteActivity : AppCompatActivity() {

    val dbTable = "Notes"
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__notes)

       /* try {
            val bundle: Bundle =intent.extras
            id = bundle.getInt("ID",0)
            if (id!=0){
               //update note
               //update actionbar title
               suportActionBar!!.title = "Update Note"
               //change button text
               addBtn.text = "Update"

                titleEt.setText(bundle.getString("name"))
                descEt.setText(bundle.getString("des"))
            }
        }catch (ex:Exception){}*/
        try{
            val id=intent.getIntExtra("ID",0)
            val name =intent.getIntArrayExtra("name")
            val des=intent.getIntArrayExtra("des")
            if (id!=0){
                //update note
                //update actionbar title
                supportActionBar!!.title = "Update Note"
                //change button text
                addBtn.text = "Update"

                titleEt.setText(name.toString())
                descEt.setText(des.toString())

            }
        }catch (ex:Exception){}
    }

    fun addFunc(view:View){
        var dbManager = DbManager(this)

        var values = ContentValues()
        values.put("Title", titleEt.text.toString())
        values.put("Description", descEt.text.toString())

        if (id ==0){
            val ID = dbManager.insert(values)
            if (ID>0){
                Toast.makeText(this, "Note is added", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this, "Error adding note...", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            var selectionArgs = arrayOf(id.toString())
            val ID = dbManager.update(values, "ID=?", selectionArgs)
            if (ID>0){
                Toast.makeText(this, "Note is updated", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this, "Error updating note...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}