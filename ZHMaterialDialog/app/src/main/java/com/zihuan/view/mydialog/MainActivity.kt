package com.zihuan.view.mydialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zihuan.view.library.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var mZDialog: ZDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        传统用法
        mZDialog = ZDialog.materialBuilder(this)
                .setCancelText("取消")
                .setConfirmText("确认")
                .setTitle("提示o(∩_∩)o ")
                .setContent("你好")
//                .show()
        mZDialog.setOnDialogListener(object : ZDialog.OnDialogImp() {

        })
        //        kotlin风格用法
        var dialog = defZAlert {
            title = "hello kotlin"
            content = "kt😄"
            okButton("确认") {
                toast("okButton")
            }
            noButton {
                toast("noButton")
            }
            otherButton {
                toast("otherButton")
            }
        }.show()
        tv_yes.setOnClickListener {
            dialog.show()
        }
        alert {

        }
    }


}
