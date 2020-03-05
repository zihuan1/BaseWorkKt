package com.zihuan.view.mydialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.view.library.*
import kotlinx.android.synthetic.main.activity_main.*
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
            noButtonShow = true
//            type=ZHDialogType.EDIT
            okButton("确认") {
//                toast(getEditText())
                toast("okButton")
            }
//            noButton {
//                toast("noButton")
//            }
            otherButton {
                toast("otherButton")
            }
        }.show()
        tv_1.setOnClickListener {
            dialog.show()
        }
        tv_2.setOnClickListener {
            zAlert(SendView(this)) {

            }.show()
        }
    }


}
