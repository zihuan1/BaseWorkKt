package com.zihuan.view.library

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import java.time.Duration
import java.util.logging.Logger

class LoadingRing @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null as AttributeSet?, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mWidth = 0.0f
    private var mPadding = 5f
    private var startAngle = 0.0f
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
//        color = mColor
        strokeWidth = 10f
    }
    private var mColor = Color.argb(100, 255, 255, 255)

    private var valueAnimator = ValueAnimator.ofFloat(0f, 360f).apply {
        duration = 500L
        interpolator = LinearInterpolator()
        repeatCount = -1
        repeatMode = ValueAnimator.RESTART
        addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            startAngle = value
            invalidate()
        }
    }
    private var mRadius = 0f
    private var arcColors = IntArray(2)
    private val linearGradient: LinearGradient by lazy {
        arcColors[0] = Color.parseColor("#ffffff")
        arcColors[1] = Color.parseColor("#33FFFFFF")
        //从左到右
        LinearGradient(0f, 0f, mRadius, 0f, arcColors, null, Shader.TileMode.CLAMP)
    }

    override fun onDraw(canvas: Canvas) {
        mPaint.color = mColor
        mPaint.shader = null
        val real = mRadius - mPadding
        canvas.drawCircle(mRadius, mRadius, real, mPaint)
        mPaint.color = -1
        mPaint.shader = linearGradient
        canvas.save()
        canvas.rotate(startAngle,mRadius,mRadius)
        canvas.drawCircle(mRadius, mRadius, real, mPaint)
//        Log.e("角度", "$startAngle")
        canvas.restore()
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

    fun setDuration(duration: Long) {
        valueAnimator.duration = duration
    }

    fun setColor(@ColorInt color: Int) {
        this.mColor = color
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = if (measuredWidth > height) measuredHeight.toFloat() else measuredWidth.toFloat()
        mPadding = mWidth * 0.15f
        mRadius = mWidth / 2
    }

}