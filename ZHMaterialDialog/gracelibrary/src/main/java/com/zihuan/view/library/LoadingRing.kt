package com.zihuan.view.library

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt

class LoadingRing @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null as AttributeSet?, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mWidth = 0.0f
    private var mPadding = 5f
    private var startAngle = 0.0f
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = color
        strokeWidth = 8f
    }

    private var color = Color.argb(100, 255, 255, 255)

    private var valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
        duration = 1000L
        interpolator = LinearInterpolator()
        repeatCount = -1
        repeatMode = ValueAnimator.RESTART
        addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            startAngle = 360f * value
            this@LoadingRing.invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = if (measuredWidth > height) measuredHeight.toFloat() else measuredWidth.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        mPaint.color = color
        canvas.drawCircle(mWidth / 2.0f, mWidth / 2.0f, mWidth / 2.0f - mPadding, mPaint)
        mPaint.color = -1
        val rectF = RectF(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding)
        canvas.drawArc(rectF, startAngle, 100.0f, false, mPaint)
    }


    fun startAnim() {
        if (!valueAnimator.isRunning) {
            valueAnimator.start()
        }
    }

    fun stopAnim() {
        valueAnimator.cancel()
        valueAnimator.end()
    }

    fun setColor(@ColorInt color: Int) {
        this.color = color
        mPaint.color = color
    }

}