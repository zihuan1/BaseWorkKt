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
 *  可配置的dialog,无侵入式代码
 * @author Zihuan
 */

class GraceAlertKt<T : AlertBaseView> {

    private var animType = GraceAlertManager.defAnim

    //    private lateinit var dialogBuild: AlertDialog.Builder
//    private lateinit var dialog: Dialog
    private lateinit var mBaseDialog: T
    private var mContext: Context

    constructor(context: Context) {
        mContext = context
    }

    //默认为淡入淡出
    private lateinit var mAnimation: Array<Int>
    private fun builder() {
//        dialogBuild = AlertDialog.Builder(mContext)
//        dialogBuild.setView(mBaseDialog)
//        dialog = dialogBuild.create().apply {
//            window.attributes.windowAnimations = when (animType) {
//                AnimLeft -> R.style.AnimLeft
//                AnimRight -> R.style.AnimRight
//                AnimUp -> R.style.AnimUp
//                AnimDown -> R.style.AnimDown
//                AnimLeftRight -> R.style.AnimLeftRight
//                AnimUpDown -> R.style.AnimUpDown
//                AnimFadeInOut -> R.style.AnimFadeInOut
//                else -> R.style.AnimZoomInOut
//            }
//
//            mBaseDialog?.dialog = this
//            setCancelable(GraceAlertManager.isCancelable)
//            setCanceledOnTouchOutside(GraceAlertManager.isCancelableTouchOutside)
//        }
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


    /***
     * 是否可取消
     */
    fun cancelable(isCancelable: Boolean = GraceAlertManager.isCancelable): GraceAlertKt<T> {
//        dialog.setCancelable(isCancelable)
        return this
    }

    private var mOutside: Boolean = GraceAlertManager.isCancelableTouchOutside

    /**
     * 点击外部区域是否可取消
     */
    fun outside(outside: Boolean = GraceAlertManager.isCancelableTouchOutside): GraceAlertKt<T> {
        mOutside = outside
        return this
    }

    private lateinit var parentViewView: GraceAlertParentView

    /**
     * 设置自定义view
     *
     */
    fun setView(view: T) {
        builder()
        mBaseDialog = view
        val activity = mContext as Activity
        val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val childParam = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        childParam.gravity = Gravity.CENTER
        childParam.leftMargin = mBaseDialog.marginLeft.toInt()
        childParam.rightMargin = mBaseDialog.marginRight.toInt()
        mBaseDialog.layoutParams = childParam
        parentViewView = GraceAlertParentView(mContext, mBaseDialog, mAnimation).apply {
            setOnClickListener {
                if (mOutside) {
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
    fun show(): GraceAlertKt<T> {
        mBaseDialog?.initData()
        parentViewView.show()
        return this
    }

    /**
     *隐藏dialog
     */
    fun dismiss(): GraceAlertKt<T> {
        parentViewView.dismiss()
        return this
    }

}


