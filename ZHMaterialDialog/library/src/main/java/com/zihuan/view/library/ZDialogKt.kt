package com.zihuan.view.library

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.zihuan.view.library.ZHAnimUtils.AnimDown
import com.zihuan.view.library.ZHAnimUtils.AnimFadeInOut
import com.zihuan.view.library.ZHAnimUtils.AnimLeft
import com.zihuan.view.library.ZHAnimUtils.AnimLeftRight
import com.zihuan.view.library.ZHAnimUtils.AnimRight
import com.zihuan.view.library.ZHAnimUtils.AnimUp
import com.zihuan.view.library.ZHAnimUtils.AnimUpDown


/**
 *  可配置的dialog,无侵入式代码
 * @author zihuan
 */

class ZDialogKt<T : ZBaseView> {

    private var animType = ZDialogManage.defAnim
    private lateinit var dialogBuild: AlertDialog.Builder
    private lateinit var dialog: Dialog
    private var mBaseDialog: ZBaseView? = null
    private var mContext: Context

    constructor(context: Context) {
        mContext = context
        builder()
    }

    private fun builder() {
        dialogBuild = AlertDialog.Builder(mContext)
        checkView()
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
            setCancelable(ZDialogManage.isCancelable)
            setCanceledOnTouchOutside(ZDialogManage.isCancelable)
        }
    }

    /***
     * 是否可取消
     */
    fun cancelable(isCancelable: Boolean = ZDialogManage.isCancelable): ZDialogKt<T> {
        dialog.setCancelable(isCancelable)
        return this
    }

    /**
     * 点击外部区域是否可取消
     */
    fun outside(isCancelableTouchOutside: Boolean = ZDialogManage.isCancelableTouchOutside): ZDialogKt<T> {
        dialog.setCanceledOnTouchOutside(isCancelableTouchOutside)
        return this
    }

    /**
     * 设置自定义view
     */
    fun setView(view: ZBaseView) {
        mBaseDialog = view
    }

    /**
     * 获取当前view
     */
    fun getView() = mBaseDialog as T

    /**
     *显示dialog
     */
    fun show(): ZDialogKt<T> {
        mBaseDialog?.initView()
        dialog.show()
        return this
    }

    /**
     *隐藏dialog
     */
    fun dismiss(): ZDialogKt<T> {
        dialog.dismiss()
        return this
    }

    /***
     * 如果没有自定义view则使用默认view
     */
    private fun checkView() {
        if (null == mBaseDialog) {
            mBaseDialog = ZDialogView(mContext)
            dialogBuild.setView(mBaseDialog)
        }
    }
}


