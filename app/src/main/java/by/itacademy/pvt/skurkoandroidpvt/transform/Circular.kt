package by.itacademy.pvt.skurkoandroidpvt.transform

import android.graphics.Canvas
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Paint
import android.graphics.Shader
import com.squareup.picasso.Transformation

class Circular : Transformation {
    override fun key(): String {
        return "Circular"
    }

    override fun transform(source: Bitmap): Bitmap {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val size = Math.min(source.width, source.height)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

        if (source !== bitmap)
            source.recycle()

        return bitmap
    }
}