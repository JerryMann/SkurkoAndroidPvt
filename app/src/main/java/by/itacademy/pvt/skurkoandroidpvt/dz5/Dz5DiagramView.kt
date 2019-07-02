package by.itacademy.pvt.skurkoandroidpvt.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz5DiagramView : View {

    private val diagramColors: Array<Int> = arrayOf(
        R.color.dz5_diagram_color_1,
        R.color.dz5_diagram_color_2,
        R.color.dz5_diagram_color_3,
        R.color.dz5_diagram_color_4,
        R.color.dz5_diagram_color_5
    )

    private var diagramPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var valuesPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val diagramRectF = RectF()
    private val textRect = Rect()

    private var diagramRadius = 0f
    private var screenCX = 0f
    private var screenCY = 0f
    private var scaledValues = FloatArray(0)

    var diagramValues: FloatArray = FloatArray(0)
        set(value) {
            field = value
            scaledValues = scale(value)
            invalidate()
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
        diagramPaint.style = Paint.Style.FILL

        textPaint.color = ContextCompat.getColor(context, R.color.dz3_black)
        textPaint.textSize = resources.getDimension(R.dimen.dz5TextSize)

        linePaint.color = ContextCompat.getColor(context, R.color.white)
        linePaint.style = Paint.Style.FILL
        linePaint.strokeWidth = resources.getDimension(R.dimen.dz5LineWidth)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        var radialAngle: Double
        val textOffset = 1.4f
        var paintColorsIndex = 0
        var sliceStartPoint = 0f

        diagramValues.zip(scaledValues).forEach {
            diagramPaint.color = ContextCompat.getColor(context, diagramColors[paintColorsIndex++])
            paintColorsIndex %= diagramColors.size

            radialAngle = (sliceStartPoint + it.second / 2) * Math.PI / 180
            val bubbleX = (screenCX + (diagramRadius * Math.cos(radialAngle))).toFloat()
            val bubbleY = (screenCY + (diagramRadius * Math.sin(radialAngle))).toFloat()

            val textX = (screenCX + ((diagramRadius * textOffset) * Math.cos(radialAngle))).toFloat()
            val textY = (screenCY + ((diagramRadius * textOffset) * Math.sin(radialAngle))).toFloat()
            val text = it.first.toString()
            textPaint.getTextBounds(text, 0, text.length, textRect)
            val textWidth = textPaint.measureText(text, 0, text.length) / 2.0f

            showValues(canvas, bubbleX, bubbleY, textX, textY, textWidth)
            canvas.drawText(text, textX - textWidth, textY + textRect.height() / 2, textPaint)

            canvas.drawArc(diagramRectF, sliceStartPoint, it.second, true, diagramPaint)
            sliceStartPoint += it.second
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        diagramRadius = Math.min(width, height) * 0.3f
        screenCX = width / 2f
        screenCY = height / 2f

        diagramRectF.left = screenCX - diagramRadius
        diagramRectF.top = screenCY - diagramRadius
        diagramRectF.right = screenCX + diagramRadius
        diagramRectF.bottom = screenCY + diagramRadius
    }

    private fun scale(array: FloatArray): FloatArray {
        val values = FloatArray(array.size)
        val sum = getSum(array)
        for (i in 0 until array.size) {
            values[i] = array[i] / sum * 360f
        }
        return values
    }

    private fun getSum(array: FloatArray): Float {
        var sum = 0f
        for (i in array) {
            sum += i
        }
        return sum
    }

    private fun showValues(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float, textWidth: Float) {
        canvas.drawLine(startX, startY, endX, endY, linePaint)
        val radiusCircle = textWidth * 1.25f
        canvas.drawCircle(endX, endY, radiusCircle, linePaint)
    }
}