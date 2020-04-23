package com.zihuan.view.library

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
     * 左右边距默认20dp
     */
    var marginLeft = 20f
        get() = dip2px(context, field)
    var marginRight = 20f
        get() = dip2px(context, field)

    var backGround = R.color.color_halfTransparent
        get() = context.resources.getColor(field)
    protected var mZhListenerImp = GraceAlertListenerImp()

    var dialog: GraceAlertParentView? = null

    protected var textOk = GraceAlertManager.textOk
    protected var textNo = GraceAlertManager.textNo
    protected var textOther = GraceAlertManager.textOther

    private fun createView() {
        val view = View.inflate(context, getLayoutId(), null)
        val w = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        view.measure(w, h)
        val ww = view.measuredWidth
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