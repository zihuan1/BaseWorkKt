package com.zihuan.view.library

object ZDialogManage {


    /** 默认动画*/
    var defAnim = ZHAnimUtils.AnimFadeInOut

    /**默认标题**/
    var textTitle = ""
    /**默认按钮文案**/
    var textOk = ""
    /**默认按钮文案**/
    var textNo = "取消"
    /**默认按钮文案**/
    var textOther = ""

    /**文字默认色值**/
    var textColor = R.color.orange
    var buttonColor = R.color.orange
    var backgroundColor = android.R.color.white

    /** 是否可取消**/
    var isCancelable = true
    /** 点击外部可取消**/
    var isCancelableTouchOutside = true

    /**配置自定义动画**/
    fun configAnim() {

    }

}