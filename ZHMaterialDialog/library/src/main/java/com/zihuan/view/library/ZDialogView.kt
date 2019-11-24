package com.zihuan.view.library

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 * 默认提供的view
 */
class ZDialogView(context: Context) : ZBaseView(context) {


    //    标题
    var title = ZDialogManage.textTitle
    //    内容
    var content = ""
    var type = ZHDialogType.DEFAULT
    //是否显示标题线
    var titleDivider = false
    //线段颜色 背景色
    var dividerColor = ZDialogManage.textColor
        get() = context.run { resources.getColor(field) }
    var buttonTextColor = ZDialogManage.buttonColor
        get() = context.run { resources.getColor(field) }
    var bgcolor = ZDialogManage.backgroundColor
        get() = context.run { resources.getColor(field) }
    lateinit var txtTitle: TextView
    lateinit var txtContent: TextView
    lateinit var tv_ok: TextView
    lateinit var tv_no: TextView
    lateinit var tv_other: TextView
    lateinit var title_lin: View
    lateinit var rl_background: View
    lateinit var et_name: EditText
    override fun getLayoutId() = R.layout.layout_dialog
    override fun initView() {
        txtTitle = mView.findViewById(R.id.txtTitle)
        txtContent = mView.findViewById<TextView>(R.id.txtContent)
        tv_ok = mView.findViewById<TextView>(R.id.tvOk)
        tv_no = mView.findViewById<TextView>(R.id.tv_no)
        tv_other = mView.findViewById<TextView>(R.id.tv_other)
          title_lin = mView.findViewById<View>(R.id.title_lin)
          rl_background = mView.findViewById<View>(R.id.rl_background)
          et_name = mView.findViewById<EditText>(R.id.et_name)
        tv_ok.setOnClickListener {
            mZhListenerImp.performOk()
        }
        tv_no.setOnClickListener {
            mZhListenerImp.performNo()
        }
        tv_other.setOnClickListener {
            mZhListenerImp.performOther()
        }
    }
    override fun initData() {
        tv_ok.setTextColor(buttonTextColor)
        tv_no.setTextColor(buttonTextColor)
        txtTitle.setTextColor(buttonTextColor)
        title_lin.setBackgroundColor(dividerColor)
        rl_background.setBackgroundColor(bgcolor)
        when (type) {
            ZHDialogType.DEFAULT -> {
                title_lin.visibility = GONE
                et_name.visibility = GONE
            }
            ZHDialogType.EDIT -> {
                title_lin.visibility = VISIBLE
                et_name.visibility = VISIBLE
            }
        }
        txtTitle.text = title
        txtContent.text = content
        tv_ok.ZVShow { textOk.isEmpty() }.text = textOk
        tv_no.ZVShow { textNo.isEmpty() }.text = textNo
        tv_other.ZVShow { textOther.isEmpty() }.text = textOther
        title_lin.visibility = if (titleDivider) VISIBLE else GONE
    }

}