package com.zihuan.view.library

import android.content.Context
import kotlinx.android.synthetic.main.layout_def_dialog.view.*

/**
 * 默认的view
 */
class GraceAlertView(context: Context) : AlertBaseView(context) {

    //    标题
    var title = GraceAlertManager.textTitle

    //    内容
    var content = ""
    var type = AlertType.DEFAULT

    //是否显示标题线
    var titleDivider = false

    //线段颜色 背景色
    var dividerColor = GraceAlertManager.textColor
        get() = context.run { resources.getColor(field) }
    var buttonTextColor = GraceAlertManager.buttonColor
        get() = context.run { resources.getColor(field) }
//    var bgcolor = GraceAlertManager.backgroundColor
//        get() = context.run { resources.getColor(field) }
    var titleColor = GraceAlertManager.titleColor
        get() = context.run { resources.getColor(field) }

    var titleSize = GraceAlertManager.titleSize
    var contentSize = GraceAlertManager.contentSize
    var buttonSize = GraceAlertManager.buttonSize

    var contentColor = GraceAlertManager.textColor
        get() = context.run { resources.getColor(field) }

    var okButtonShow = false
    var noButtonShow = false
    var otherButtonShow = false
    private fun dip(value: Float) = (value * resources.displayMetrics.density)

    override fun getLayoutId() = R.layout.layout_def_dialog
    override fun initView() {
        tv_ok.setOnClickListener {
            if (mZhListenerImp.mPositiveListener != null) {
                mZhListenerImp.performOk()
            } else {
                dialog?.dismiss()
            }
        }
        tv_no.setOnClickListener {
            if (mZhListenerImp.mNegativeListener != null) {
                mZhListenerImp.performNo()
            } else {
                dialog?.dismiss()
            }
        }
        tv_other.setOnClickListener {
            if (mZhListenerImp.mNeutralListener != null) {
                mZhListenerImp.performOther()
            } else {
                dialog?.dismiss()
            }
        }

    }

    override fun initData() {
        when (type) {
            AlertType.DEFAULT -> {
                title_lin.visibility = GONE
                et_name.visibility = GONE
            }
            AlertType.EDIT -> {
                title_lin.visibility = VISIBLE
                et_name.visibility = VISIBLE
            }
        }
        tv_title.setTextColor(titleColor)
        tv_content.setTextColor(contentColor)
        tv_no.setTextColor(buttonTextColor)
        tv_ok.setTextColor(buttonTextColor)
        title_lin.setBackgroundColor(dividerColor)
//        rl_background.setBackgroundColor(bgcolor)
        tv_title.text = title
        tv_content.text = content
        tv_title.textSize = titleSize
        tv_content.textSize = contentSize
        tv_ok.textSize = buttonSize
        tv_no.textSize = buttonSize
        tv_other.textSize = buttonSize
        tv_ok.ZVShow { textOk.isNotEmpty() || okButtonShow }.text = textOk
        tv_no.ZVShow { textOk.isNotEmpty() || noButtonShow }.text = textNo
        tv_other.ZVShow { textOther.isNotEmpty() || otherButtonShow }.text = textOther
        title_lin.ZVShow { titleDivider }
    }

    fun getEditText() = et_name.text.toString()
}