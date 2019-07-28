package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
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
            dz6UrlEditText.setText(item.imageUrl)
            dz6NameEditText.setText(item.name)
            dz6AgeEditText.setText(item.age.toString())
        }

        dz6EditSave.setOnClickListener {

            val newUrl = dz6UrlEditText.text.toString().trim()
            val newName = dz6NameEditText.text.toString().trim()
            val newAge = dz6AgeEditText.text.toString().toIntOrNull()

            if (newUrl.isEmpty() ||
                newName.isEmpty() ||
                newAge == null
            ) {
                Toast.makeText(this, R.string.dz6_error_empty, Toast.LENGTH_SHORT).show()
            } else if (!URLUtil.isValidUrl(newUrl)) {
                Toast.makeText(this, R.string.dz6_error_url, Toast.LENGTH_SHORT).show()
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