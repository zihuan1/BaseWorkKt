package com.zihuan.view.library

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.zihuan.view.library.GraceAlertUtils.AnimDown
import com.zihuan.view.library.GraceAlertUtils.AnimFadeInOut
import com.zihuan.view.library.GraceAlertUtils.AnimLeft
import com.zihuan.view.library.GraceAlertUtils.AnimLeftRight
import com.zihuan.view.library.GraceAlertUtils.AnimRight
import com.zihuan.view.library.GraceAlertUtils.AnimUp
import com.zihuan.view.library.GraceAlertUtils.AnimUpDown


/**
 * 可配置的dialog,无侵入式代码
 * @author Zihuan
 */

class GraceAlertBuilder<T : AlertBaseView> {

    private var animType = GraceAlertManager.defAnim
    private lateinit var mBaseDialog: T
    private var mContext: Context

    constructor(context: Context) {
        mContext = context
    }

    private lateinit var parentViewView: GraceAlertParentView

    //默认为淡入淡出
    private lateinit var mAnimation: Array<Int>
    private fun builder() {
        mAnimation = when (animType) {
            AnimLeft -> arrayOf(R.anim.from_left, R.anim.to_left)
            AnimRight -> arrayOf(R.anim.from_right, R.anim.to_right)
            AnimUp -> arrayOf(R.anim.from_up, R.anim.to_up)
            AnimDown -> arrayOf(R.anim.from_down, R.anim.to_down)
            AnimLeftRight -> arrayOf(R.anim.from_left, R.anim.to_right)
            AnimUpDown -> arrayOf(R.anim.from_up, R.anim.to_down)
            AnimFadeInOut -> arrayOf(R.anim.fade_in, R.anim.fade_out)
            else -> arrayOf(R.anim.zoom_in, R.anim.zoom_out)
        }
    }


    /**
     * 设置自定义view
     *
     */
    fun setView(view: T) {
        builder()
        mBaseDialog = view
        val activity = mContext as Activity
        val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val childParam = FrameLayout.LayoutParams(mBaseDialog.rootViewWidth, mBaseDialog.rootViewHeight)
        childParam.gravity = Gravity.CENTER
        childParam.leftMargin = mBaseDialog.marginLeft.toInt()
        childParam.rightMargin = mBaseDialog.marginRight.toInt()
        childParam.topMargin = mBaseDialog.marginTop.toInt()
        childParam.bottomMargin = mBaseDialog.marginBottom.toInt()
        mBaseDialog.layoutParams = childParam
        parentViewView = GraceAlertParentView(mContext, mBaseDialog, mAnimation).apply {
            setOnClickListener {
                if (mBaseDialog.outside) {
                    dismiss()
                }
            }
        }
        parentViewView.visibility = View.GONE
        activity.addContentView(parentViewView, param)
    }

    /**
     * 获取当前view
     * reified
     */
    fun getView(): T {
        return mBaseDialog
    }

    /**
     *显示dialog
     */
    fun show(): GraceAlertBuilder<T> {
        mBaseDialog?.initData()
        parentViewView.show()
        return this
    }

    /**
     *隐藏dialog
     */
    fun dismiss(): GraceAlertBuilder<T> {
        parentViewView.dismiss()
        return this
    }

}


