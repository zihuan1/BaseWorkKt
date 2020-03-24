package com.zihuan.view.library

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
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
    private lateinit var dialogBuild: AlertDialog.Builder
    private lateinit var dialog: Dialog
    private lateinit var mBaseDialog: T
    private var mContext: Context

    constructor(context: Context) {
        mContext = context
    }

    private fun builder() {
        dialogBuild = AlertDialog.Builder(mContext)
        dialogBuild.setView(mBaseDialog)
        dialog = dialogBuild.create().apply {
            window.attributes.windowAnimations = when (animType) {
                AnimLeft -> R.style.AnimLeft
                AnimRight -> R.style.AnimRight
                AnimUp -> R.style.AnimUp
                AnimDown -> R.style.AnimDown
                AnimLeftRight -> R.style.AnimLeftRight
                AnimUpDown -> R.style.AnimUpDown
                AnimFadeInOut -> R.style.AnimFadeInOut
                else -> R.style.AnimZoomInOut
            }
            mBaseDialog?.dialog = this
            setCancelable(GraceAlertManager.isCancelable)
            setCanceledOnTouchOutside(GraceAlertManager.isCancelableTouchOutside)
        }
    }

    private fun resetTheWideHigh() {
        var viewInfo = mBaseDialog?.layoutParams
    }

    /***
     * 是否可取消
     */
    fun cancelable(isCancelable: Boolean = GraceAlertManager.isCancelable): GraceAlertKt<T> {
        dialog.setCancelable(isCancelable)
        return this
    }

    /**
     * 点击外部区域是否可取消
     */
    fun outside(outside: Boolean = GraceAlertManager.isCancelableTouchOutside): GraceAlertKt<T> {
        dialog.setCanceledOnTouchOutside(outside)
        return this
    }

    /**
     * 设置自定义view
     */
    fun setView(view: T) {
        mBaseDialog = view
        builder()
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
        dialog.show()
        return this
    }

    /**
     *隐藏dialog
     */
    fun dismiss(): GraceAlertKt<T> {
        dialog.dismiss()
        return this
    }
}


