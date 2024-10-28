package com.zihuan.view.library.source

import android.content.Context
import android.widget.TextView
import com.zihuan.view.library.AlertBaseView
import com.zihuan.view.library.GraceAlertManager
import com.zihuan.view.library.R
import com.zihuan.view.library.ZVShow

/**
 * 默认的view
 */
class GraceAlertView(context: Context) : AlertBaseView(context) {

    //    标题
    var title = GraceAlertManager.textTitle

    //    内容
    var content = ""

    var buttonTextColor = GraceAlertManager.buttonColor
        get() = context.run { resources.getColor(field) }

    var titleColor = GraceAlertManager.titleColor
        get() = context.run { resources.getColor(field) }

    var titleSize = GraceAlertManager.titleSize
    var contentSize = GraceAlertManager.contentSize
    var buttonSize = GraceAlertManager.buttonSize

    var contentColor = GraceAlertManager.textColor
        get() = context.run { resources.getColor(field) }

    private lateinit var tv_ok: TextView
    private lateinit var tv_no: TextView
    private lateinit var tv_other: TextView
    private lateinit var tv_title: TextView
    private lateinit var tv_content: TextView


    var okButtonShow = false
    var noButtonShow = false
    var otherButtonShow = false
    override fun getLayoutId() = R.layout.layout_def_dialog
    override fun initView() {
        tv_ok = findViewById(R.id.tv_ok)
        tv_no = findViewById(R.id.tv_no)
        tv_other = findViewById(R.id.tv_other)
        tv_title = findViewById(R.id.tv_title)
        tv_content = findViewById(R.id.tv_content)

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
        tv_title.setTextColor(titleColor)
        tv_content.setTextColor(contentColor)
        tv_no.setTextColor(buttonTextColor)
        tv_ok.setTextColor(buttonTextColor)
        tv_title.text = title
        tv_content.text = content
        tv_title.textSize = titleSize
        tv_content.textSize = contentSize
        tv_ok.textSize = buttonSize
        tv_no.textSize = buttonSize
        tv_ok.ZVShow { textOk.isNotEmpty() || okButtonShow }.text = textOk
        tv_no.ZVShow { textOk.isNotEmpty() || noButtonShow }.text = textNo
    }

}