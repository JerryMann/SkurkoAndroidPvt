package by.itacademy.pvt.skurkoandroidpvt.transform

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.BitmapShader
import android.graphics.Shader
import android.graphics.Color
import com.squareup.picasso.Transformation

class Circular : Transformation {

    override fun key(): String {
        return "Circular"
    }

    override fun transform(source: Bitmap): Bitmap {

        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)

        val paint = Paint()
        paint.shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true

        val paintBorder = Paint()
        paintBorder.color = Color.WHITE
        paintBorder.isAntiAlias = true

        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paintBorder)
        canvas.drawCircle(size / 2f, size / 2f, size / 2f - 10, paint)

        squaredBitmap.recycle()
        return bitmap
    }
}