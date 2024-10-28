package com.zihuan.view.library.source

import android.content.Context
import android.view.View
import android.widget.TextView
import com.zihuan.view.library.AlertBaseView
import com.zihuan.view.library.R

/**
 * 默认的加载中的view
 */
class GraceLoadingView(context: Context) : AlertBaseView(context) {

    override fun getLayoutId() = R.layout.layout_loading

    private val tvContent by lazy { findViewById<TextView>(R.id.tvContent) }

    private val loadingRing by lazy { findViewById<LoadingRing>(R.id.loadingRing) }
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