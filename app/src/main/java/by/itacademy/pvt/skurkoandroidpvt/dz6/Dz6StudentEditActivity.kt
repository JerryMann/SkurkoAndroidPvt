package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dz6_edit.*
import kotlin.random.Random
import by.itacademy.pvt.skurkoandroidpvt.R

private const val ITEM_ID = "ITEM_ID"

class Dz6StudentEditActivity : Activity() {

    companion object {
        fun getIntent(context: Context, id: String = ""): Intent {
            val intent = Intent(context, Dz6StudentEditActivity::class.java)
            intent.putExtra(ITEM_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_edit)

        val itemId = intent.getStringExtra(ITEM_ID)
        val item: Student? = itemId?.let { StudentManager.getStudent(it) }
        if (item != null) {
            dz6_urlEditText.setText(item.imageUrl)
            dz6_nameEditText.setText(item.name)
            dz6_ageEditText.setText(item.age.toString())
        }

        dz6_edit_save.setOnClickListener {

            val newUrl = dz6_urlEditText.text.toString().trim()
            val newName = dz6_nameEditText.text.toString().trim()
            val newAge = dz6_ageEditText.text.toString().toIntOrNull()

            if (newUrl.isEmpty() ||
                newName.isEmpty() ||
                newAge == null
            ) {
                Toast.makeText(this, "Fields should'nt be empty", Toast.LENGTH_SHORT).show()
            } else {

                if (item == null) {
                    StudentManager.createStudent(
                        Student(
                            Random(System.currentTimeMillis()).toString(),
                            newUrl,
                            newName,
                            newAge
                        )
                    )
                } else {
                    StudentManager.updateStudent(Student(item.id, newUrl, newName, newAge))
                }
                startActivity(Intent(this, Dz6StudentListActivity::class.java))
                finish()
            }
        }
    }
}