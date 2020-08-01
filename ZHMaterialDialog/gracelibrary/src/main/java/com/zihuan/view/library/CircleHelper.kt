//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.zihuan.view.library

import android.graphics.*
import android.os.Build.VERSION
import android.view.View

class CircleHelper {
    var radii = FloatArray(8)
    var mClipPath: Path? = null
    var mPaint: Paint? = null
    var mRoundAsCircle = false

    var mLayer: RectF? = null
    fun initAttrs(roundCornerTopLeft: Float, roundCornerTopRight: Float, roundCornerBottomRight: Float, roundCornerBottomLeft: Float) {
        radii[0] = roundCornerTopLeft
        radii[1] = roundCornerTopLeft
        radii[2] = roundCornerTopRight
        radii[3] = roundCornerTopRight
        radii[4] = roundCornerBottomRight
        radii[5] = roundCornerBottomRight
        radii[6] = roundCornerBottomLeft
        radii[7] = roundCornerBottomLeft
        mLayer = RectF()
        mClipPath = Path()
        mPaint = Paint()
        mPaint!!.color = -1
        mPaint!!.isAntiAlias = true
    }

    fun onSizeChanged(view: View, w: Int, h: Int) {
        mLayer!![0.0f, 0.0f, w.toFloat()] = h.toFloat()
        refreshRegion(view)
    }

    fun refreshRegion(view: View) {
        val w = mLayer!!.width().toInt()
        val h = mLayer!!.height().toInt()
        val areas = RectF()
        areas.left = view.paddingLeft.toFloat()
        areas.top = view.paddingTop.toFloat()
        areas.right = (w - view.paddingRight).toFloat()
        areas.bottom = (h - view.paddingBottom).toFloat()
        mClipPath!!.reset()
        if (mRoundAsCircle) {
            val d = if (areas.width() >= areas.height()) areas.height() else areas.width()
            val r = d / 2.0f
            val center = PointF((w / 2).toFloat(), (h / 2).toFloat())
            if (VERSION.SDK_INT <= 27) {
                mClipPath!!.addCircle(center.x, center.y, r, Path.Direction.CW)
                mClipPath!!.moveTo(0.0f, 0.0f)
                mClipPath!!.moveTo(w.toFloat(), h.toFloat())
            } else {
                val y = (h / 2).toFloat() - r
                mClipPath!!.moveTo(areas.left, y)
                mClipPath!!.addCircle(center.x, y + r, r, Path.Direction.CW)
            }
        } else {
            mClipPath!!.addRoundRect(areas, radii, Path.Direction.CW)
        }
    }

    fun onClipDraw(canvas: Canvas) {
        mPaint!!.color = -1
        mPaint!!.style = Paint.Style.FILL
        if (VERSION.SDK_INT <= 27) {
            mPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
            canvas.drawPath(mClipPath, mPaint)
        } else {
            mPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
            val path = Path()
            path.addRect(0.0f, 0.0f, mLayer!!.width().toInt().toFloat(), mLayer!!.height().toInt().toFloat(), Path.Direction.CW)
            path.op(mClipPath, Path.Op.DIFFERENCE)
            canvas.drawPath(path, mPaint)
        }
    }
}