package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.utils.loadCircularImage
import kotlinx.android.synthetic.main.activity_dz6_detail.*

private const val ITEM_ID = "ITEM_ID"

class Dz6StudentDetailActivity : Activity() {

    companion object {
        fun getIntent(context: Context, id: String): Intent {
            val intent = Intent(context, Dz6StudentDetailActivity::class.java)
            intent.putExtra(ITEM_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_detail)

        val itemId = intent.getStringExtra(ITEM_ID)
        val item = StudentManager.getStudent(itemId)
        val avatarImageView = findViewById<ImageView>(R.id.dz6_detail_image)

        if (item != null) {
            loadCircularImage(item.imageUrl, avatarImageView)
            dz6_detail_name.text = item.name
            dz6_detail_age.text = item.age.toString()
        }

        dz6_detail_edit.setOnClickListener {
            if (item == null) {
                Toast.makeText(this@Dz6StudentDetailActivity, R.string.dz6_wrong_id, Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentDetailActivity, itemId))
            }
            finish()
        }

        dz6_detail_delete.setOnClickListener {
            if (item == null) {
                Toast.makeText(this@Dz6StudentDetailActivity, R.string.dz6_wrong_id, Toast.LENGTH_SHORT).show()
            } else {
                StudentManager.deleteStudent(itemId)
            }
            startActivity(Intent(this, Dz6StudentListActivity::class.java))
            finish()
        }
    }
}