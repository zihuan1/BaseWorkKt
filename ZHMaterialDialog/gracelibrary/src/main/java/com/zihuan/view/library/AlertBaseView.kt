package com.zihuan.view.library

import android.content.Context
import android.view.LayoutInflater


/**
 * view基类
 */
abstract class AlertBaseView : CircleLayout, OnConfirmListener {
    constructor(context: Context) : super(context) {
        createView()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    /**
     * 左右边距默认20dp
     * 只有在宽高设置为match_parent或屏幕宽度-view宽度小于margin时有效
     */
    var margin = 0f
        set(value) {
            marginLeft = value
            marginRight = value
            marginTop = value
            marginBottom = value
            field = value
        }
    var marginHorizontal = 0f
        set(value) {
            marginLeft = value
            marginRight = value
            field = value
        }
    var marginVertical = 0f
        set(value) {
            marginTop = value
            marginBottom = value
            field = value
        }

    var marginLeft = 20f
        get() = dip2px(context, field)
    var marginRight = 20f
        get() = dip2px(context, field)
    var marginTop = 20f
        get() = dip2px(context, field)
    var marginBottom = 20f
        get() = dip2px(context, field)


    /**
     * 背景色
     */
    var backGround = R.color.color_halfTransparent
        get() = context.resources.getColor(field)

    /**
     * 背景弧度
     */
    var radian = 4f
        get() = dip2px(context, field)

    var radianTopLeft = 0f
        get() = if (field == 0f) radian else dip2px(context, field)
    var radianTopRight = 0f
        get() = if (field == 0f) radian else dip2px(context, field)
    var radianBottomLeft = 0f
        get() = if (field == 0f) radian else dip2px(context, field)
    var radianBottomRight = 0f
        get() = if (field == 0f) radian else dip2px(context, field)

    protected var mZhListenerImp = GraceAlertListenerImp()

    var dialog: GraceAlertParentView? = null

    protected var textOk = GraceAlertManager.textOk
    protected var textNo = GraceAlertManager.textNo
    protected var textOther = GraceAlertManager.textOther

    /**
     * 点击外部区域是否可取消
     */
    var outside = GraceAlertManager.isCancelableTouchOutside

    internal var rootViewWidth = 0
    internal var rootViewHeight = 0

    private fun createView() {
        val view = LayoutInflater.from(context).inflate(getLayoutId(), this, false)
        val viewParams = view.layoutParams
        setAttr(radianTopLeft, radianTopRight, radianBottomLeft, radianBottomRight)
        rootViewWidth = viewParams.width
        rootViewHeight = viewParams.height
        addView(view)
        if (isInEditMode) return
        initView()
        //避免点击非阴影区消失
        setOnClickListener {

        }
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

    open fun onDismiss() {
    }
}