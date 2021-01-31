package com.zihuan.view.library

import android.content.Context
import kotlinx.android.synthetic.main.layout_def_dialog.view.*

/**
 * 默认的加载中的view
 */
class GraceLoadingView(context: Context) : AlertBaseView(context) {



    override fun getLayoutId() = R.layout.layout_loading
    override fun initView() {


    }

    override fun initData() {
    }

}