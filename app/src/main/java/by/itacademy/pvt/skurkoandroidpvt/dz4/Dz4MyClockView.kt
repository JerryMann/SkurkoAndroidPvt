package by.itacademy.pvt.skurkoandroidpvt.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import by.itacademy.pvt.skurkoandroidpvt.R
import java.util.Calendar

class Dz4MyClockView : View {

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val clockNumbers = intArrayOf(3, 6, 9, 12)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var cx = 0f
    private var cy = 0f
    private var radius = 0f
    private var padding = 0f
    private var numberSpace = 0f
    private var handHourPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var handMinutePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var handSecondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var handTruncation = 0f
    private var hourHandTruncation = 0f
    private val rect = Rect()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        cx = width / 2f
        cy = height / 2f
        padding = numberSpace + 50
        radius = Math.min(width, height) / 2f - padding
        handTruncation = Math.min(width, height) / 20f
        hourHandTruncation = Math.min(width, height) / 7f
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
        borderPaint.color = ContextCompat.getColor(context, R.color.clock_border)
        borderPaint.style = Paint.Style.FILL

        backgroundPaint.color = ContextCompat.getColor(context, R.color.clock_white)
        backgroundPaint.style = Paint.Style.FILL

        linePaint.color = ContextCompat.getColor(context, R.color.clock_border)
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = resources.getDimension(R.dimen.dz4LinesDim)

        textPaint.color = ContextCompat.getColor(context, R.color.dz3_black)
        textPaint.textSize = resources.getDimension(R.dimen.clockNumbers)

        handHourPaint.color = ContextCompat.getColor(context, R.color.dz3_black)
        handHourPaint.style = Paint.Style.STROKE
        handHourPaint.strokeWidth = resources.getDimension(R.dimen.dz4ClockHandHourDim)

        handMinutePaint.color = ContextCompat.getColor(context, R.color.dz3_black)
        handMinutePaint.style = Paint.Style.STROKE
        handMinutePaint.strokeWidth = resources.getDimension(R.dimen.dz4ClockHandMinuteDim)

        handSecondPaint.color = ContextCompat.getColor(context, R.color.dz3_black)
        handSecondPaint.style = Paint.Style.STROKE
        handSecondPaint.strokeWidth = resources.getDimension(R.dimen.dz4ClockHandSecondDim)
    }

    private fun drawNumbers(canvas: Canvas) {
        val numberRadius = radius - padding
        for (number in clockNumbers) {
            val tmp = number.toString()
            textPaint.getTextBounds(tmp, 0, tmp.length, rect)
            val angle = Math.PI / 2 * (number + 1)
            val x = (cx + Math.cos(angle) * numberRadius - rect.width() / 2)
            val y = (cy - Math.sin(angle) * numberRadius + rect.height() / 2)
            canvas.drawText(tmp, x.toFloat(), y.toFloat(), textPaint)
        }
    }

    private fun drawLines(canvas: Canvas) {
        for (i in 0..11) {
            if (i == 0 || i == 3 || i == 6 || i == 9) {
                canvas.rotate(30f, cx, cy)
            } else {
                canvas.drawLine(cx, cy / 2, cx, cy / 2 + 75, linePaint)
                canvas.rotate(30f, cx, cy)
            }
        }
    }

    private fun drawHand(canvas: Canvas, paint: Paint, loc: Double, isHour: Boolean) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = if (isHour) radius - handTruncation - hourHandTruncation else radius - handTruncation
        canvas.drawLine(
            cx, cy,
            (cx + Math.cos(angle) * handRadius).toFloat(),
            (cy + Math.sin(angle) * handRadius).toFloat(),
            paint
        )
    }

    private fun drawHands(canvas: Canvas) {
        val c = Calendar.getInstance()
        var hour = c.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour
        drawHand(canvas, handHourPaint, ((hour + c.get(Calendar.MINUTE) / 60.0) * 5), true)
        drawHand(canvas, handMinutePaint, c.get(Calendar.MINUTE).toDouble(), false)
        drawHand(canvas, handSecondPaint, c.get(Calendar.SECOND).toDouble(), false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.drawCircle(cx, cy, radius + 20, borderPaint)
        canvas.drawCircle(cx, cy, radius, backgroundPaint)
        drawNumbers(canvas)
        drawLines(canvas)
        drawHands(canvas)
        canvas.drawCircle(cx, cy, radius / 15, borderPaint)

        postInvalidateDelayed(500)
        invalidate()
    }
}