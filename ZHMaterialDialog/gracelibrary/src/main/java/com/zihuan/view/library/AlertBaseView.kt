package com.zihuan.view.library

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.FrameLayout

/**
 * view基类
 */
abstract class AlertBaseView : FrameLayout, OnConfirmListener {
    constructor(context: Context) : super(context) {
        createView()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    /**
     * 重置宽高
     */
    var resetWidth = false
    var resetHeight= false
    protected var mZhListenerImp = GraceAlertListenerImp()
    var dialog: Dialog? = null
    protected var textOk = GraceAlertManager.textOk
    protected var textNo = GraceAlertManager.textNo
    protected var textOther = GraceAlertManager.textOther
    private fun createView() {
        val view = View.inflate(context, getLayoutId(), null)
        addView(view)
        if (isInEditMode) return
        initView()
    }


    override fun onPositive(text: String, onClicked: () -> Unit) {
        textOk = text
        mZhListenerImp.setOkButton {
            onClicked()
            dialog?.dismiss()
        }
    }

    override fun onNegative(text: String, onClicked: () -> Unit) {
        textNo = text
        mZhListenerImp.setNoButton {
            onClicked()
            dialog?.dismiss()
        }
    }

    override fun onNeutral(text: String, onClicked: () -> Unit) {
        textOther = text
        mZhListenerImp.setOtherButton {
            onClicked()
            dialog?.dismiss()
        }
    }
}