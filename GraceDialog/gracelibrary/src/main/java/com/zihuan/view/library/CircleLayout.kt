package com.zihuan.view.library

import android.content.Context
import android.graphics.Canvas
import android.widget.FrameLayout

/**
 * 圆形背景
 */
open class CircleLayout(context: Context?) : FrameLayout(context) {
    var mCircleHelper: CircleHelper? = null
    fun setAttr(roundCornerTopLeft: Float, roundCornerTopRight: Float, roundCornerBottomRight: Float, roundCornerBottomLeft: Float) {
        mCircleHelper = CircleHelper()
        mCircleHelper!!.initAttrs(roundCornerTopLeft, roundCornerTopRight, roundCornerBottomRight, roundCornerBottomLeft)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCircleHelper!!.onSizeChanged(this, w, h)
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.saveLayer(mCircleHelper!!.mLayer, null, 31)
        super.dispatchDraw(canvas)
        mCircleHelper!!.onClipDraw(canvas)
        canvas.restore()
    }

    override fun invalidate() {
        if (null != mCircleHelper) {
            mCircleHelper!!.refreshRegion(this)
        }
        super.invalidate()
    }
}