package com.zihuan.app.library

import android.app.Dialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView


/**
 * zihuan
 */

class ZHMDialogKt {

    lateinit var dialog: Dialog
    var title = "Title"
    var content = ""
    var isCancelable = true
    var type = 1//0编辑 1 文字提示
    var isCancelableTouchOutside = false
    private var animType = 7
    //是否显示标题线
    var titleDivider = false
    var dividerColor: Int = 0
    var buttonTextColor: Int = 0
    var bgcolor: Int = 0//线段颜色 背景色
    //设置确定文字
    var confirmText = ""
    //设置其他文字
    var otherText = ""
    //设置取消文字
    var cancelText = ""


    fun builder(): ZHMDialogKt {
        buttonTextColor = ContextCompat.getColor(mContext, R.color.orange)
        dividerColor = buttonTextColor
        bgcolor = ContextCompat.getColor(mContext, android.R.color.white)
        dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog, null)
        dialog.setContentView(view)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog.window.attributes = lp
        var sty = when (animType) {
            ZHMDAnimUtils.AnimLeft -> R.style.AnimLeft
            ZHMDAnimUtils.AnimRight -> R.style.AnimRight
            ZHMDAnimUtils.AnimUp -> R.style.AnimUp
            ZHMDAnimUtils.AnimDown -> R.style.AnimDown
            ZHMDAnimUtils.AnimLeftRight -> R.style.AnimLeftRight
            ZHMDAnimUtils.AnimUpDown -> R.style.AnimUpDown
            ZHMDAnimUtils.AnimFadeInOut -> R.style.AnimFadeInOut
            else -> R.style.AnimZoomInOut
        }
        dialog.window.attributes.windowAnimations = sty

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtContent = view.findViewById<TextView>(R.id.txtContent)
        val btnConfirm = view.findViewById<TextView>(R.id.tv_yes)
        val btnCancel = view.findViewById<TextView>(R.id.tv_clare)
        val tv_other = view.findViewById<TextView>(R.id.tv_other)
        val rl_background = view.findViewById<RelativeLayout>(R.id.rl_background)
        val editName = view.findViewById<EditText>(R.id.et_name)
        val title_lin = view.findViewById<View>(R.id.title_lin)
        rl_background.setBackgroundColor(bgcolor)
        btnConfirm.setTextColor(buttonTextColor)
        btnCancel.setTextColor(buttonTextColor)
        txtTitle.setTextColor(buttonTextColor)
        title_lin.setBackgroundColor(dividerColor)
        when (type) {
            0 -> {
                title_lin.visibility = View.VISIBLE
                editName.visibility = View.VISIBLE
            }
            1 -> {
                title_lin.visibility = View.GONE
                editName.visibility = View.GONE
            }
        }
        title_lin.visibility = if (titleDivider) View.VISIBLE else View.GONE

        txtTitle.visibility = if (title.isEmpty()) View.GONE else View.VISIBLE
        txtTitle.text = title

        txtContent.visibility = if (content.isEmpty()) View.GONE else View.VISIBLE
        txtContent.text = content

        tv_other.visibility = if (otherText.isEmpty()) View.GONE else View.VISIBLE
        tv_other.text = otherText

        btnConfirm.visibility = if (confirmText.isEmpty()) View.GONE else View.VISIBLE
        btnConfirm.text = confirmText

        btnCancel.visibility = if (cancelText.isEmpty()) View.GONE else View.VISIBLE
        btnCancel.text = cancelText

        btnConfirm.setOnClickListener {

        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        tv_other.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCancelableTouchOutside)
        return this
    }

    inline fun confirmClick(click: () -> Unit) {
        dialog.dismiss()
    }

    fun cancelClick() {
        dialog.dismiss()

    }

    fun otherClick() {
        dialog.dismiss()

    }

    fun show(): ZHMDialogKt {
        builder()
        dialog.show()
        return this
    }

    companion object {
        private lateinit var mContext: Context
        lateinit var ZHMDialog: ZHMDialogKt
        fun materialBuilder(context: Context): ZHMDialogKt {
            mContext = context
            ZHMDialog = ZHMDialogKt()
            return ZHMDialog
        }
    }
}

fun Context.zhalert(init: ZHMDialogKt.() -> Unit): ZHMDialogKt {
    return ZHMDialogKt.materialBuilder(this).apply { init() }
}
