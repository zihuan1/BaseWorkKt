package com.zihuan.view.library

import android.content.Context
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.layout_def_dialog.view.*
import kotlinx.android.synthetic.main.layout_loading.view.*

/**
 * 默认的加载中的view
 */
class GraceLoadingView(context: Context) : AlertBaseView(context) {

    override fun getLayoutId() = R.layout.layout_loading

    var showText = true
        set(value) {
            tvContent.visibility = if (value) View.VISIBLE else View.GONE
        }
    var content = "正在加载"
        set(value) {
            tvContent.text = value
        }
    var speed = 0L
        set(value) {
            loadingRing.setDuration(value)
        }

    var ringColor = 0f
    var ringAnimator = 0f

    override fun initView() {
    }

    override fun initData() {
        loadingRing.startAnim()
//        Log.e("显示", "显示")
    }

    override fun onDismiss() {
        loadingRing.stopAnim()
//        Log.e("隐藏", "隐藏")
    }
}