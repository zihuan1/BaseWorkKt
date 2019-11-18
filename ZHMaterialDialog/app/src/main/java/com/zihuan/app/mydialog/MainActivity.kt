package com.zihuan.app.mydialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.zihuan.app.library.ZHMDialog
import com.zihuan.app.library.zhalert

class MainActivity : AppCompatActivity() {
    lateinit var mZHMDialog: ZHMDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        传统用法
        mZHMDialog = ZHMDialog.materialBuilder(this)
                .setCancelText("取消")
                .setConfirmText("确认")
                .setTitle("提示o(∩_∩)o ")
                .setContent("你好")
                .show()
        mZHMDialog.setOnDialogListener(object : ZHMDialog.OnDialogImp() {

        })
//        kotlin风格用法
        zhalert {
            title = "hello kotlin"
            confirmText = "确认"
            cancelText = "取消"
            content = "kt"
            confirmClick {

            }
        }.show()
    }


    fun showdialg(view: View) {
        mZHMDialog.show()
    }
}
