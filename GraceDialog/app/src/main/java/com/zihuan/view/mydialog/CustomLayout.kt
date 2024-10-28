package com.zihuan.view.mydialog

import android.content.Context
import android.view.LayoutInflater
import com.zihuan.view.library.AlertBaseView
import kotlinx.android.synthetic.main.dialog_send_view.view.*

class CustomLayout : AlertBaseView {
    constructor(context: Context) : super(context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_send_view, this, false)
        createLayoutCustom(view)
    }

    override fun getLayoutId() = 0

    override fun initView() {
        tvOk.setOnClickListener {
            mZhListenerImp.performOk()
            dialog?.dismiss()
        }
        tvNo.setOnClickListener {
            mZhListenerImp.performNo()
            dialog?.dismiss()
        }
    }

    override fun initData() {
    }

    override fun isAttachedToWindow(): Boolean {

        return super.isAttachedToWindow()

    }
}