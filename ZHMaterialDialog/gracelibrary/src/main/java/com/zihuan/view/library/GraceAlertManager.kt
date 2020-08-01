package com.zihuan.view.library

object GraceAlertManager {


    /** 默认动画*/
    var defAnim = GraceAlertUtils.AnimFadeInOut

    /**默认标题**/
    var textTitle = ""

    /**默认按钮文案**/
    var textOk = "确定"

    /**默认按钮文案**/
    var textNo = "取消"

    /**默认按钮文案**/
    var textOther = ""

    /**文字默认色值**/
    var titleColor = R.color.orange
    var textColor = R.color.orange
    var buttonColor = R.color.orange
    var backgroundColor = android.R.color.white

    var contentSize = 14f
    var titleSize = 16f
    var buttonSize = 14f

    /**
     * 快速设置默认色值
     */
    var defColor = R.color.orange
        set(value) {
            titleColor = value
            textColor = value
            buttonColor = value
        }

    /** 是否可取消**/
    var isCancelable = true

    /** 点击外部可取消**/
    var isCancelableTouchOutside = true

    /**配置自定义动画**/
    fun configAnim() {

    }

}