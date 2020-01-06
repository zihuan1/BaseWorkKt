package com.zihuan.view.mydialog

import android.content.Context
import com.zihuan.view.library.ZBaseView
import kotlinx.android.synthetic.main.dialog_send_view.view.*

class SendView(context: Context) : ZBaseView(context) {

    var logisticsName = ""
    var logisticsSku = ""
    override fun getLayoutId() = R.layout.dialog_send_view

    override fun initData() {
    }

    override fun initView() {
        tvNo.setOnClickListener {
            if (mZhListenerImp.mPositiveListener != null) {
                mZhListenerImp.performOk()
            } else {
                dialog?.dismiss()
            }
        }
        tvOk.setOnClickListener {
            if (mZhListenerImp.mPositiveListener != null) {
                mZhListenerImp.performOk()
            } else {
                dialog?.dismiss()
            }
        }
    }
}