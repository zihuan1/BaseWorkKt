package com.zihuan.view.library

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View

/***
 * 动画
 */
object ZHAnimUtils {
    /**
     * 左进左出
     */
    val AnimLeft = 1
    /**
     * 右进右出
     */
    val AnimRight = 2

    /**
     * 上进上出
     */
    val AnimUp = 3

    /**
     * 下进下出
     */
    val AnimDown = 4

    /**
     * 左入右出
     */
    val AnimLeftRight = 5

    /**
     * 上入下出
     */
    val AnimUpDown = 6

    /**
     * 淡入淡出
     */
    val AnimFadeInOut = 7

    /**
     * 缩放出入
     */
    val AnimZoomInOut = 8
}

/**
 * dialog类型
 */
enum class ZHDialogType {
    /**默认类型*/
    DEFAULT,
    /**编辑类型*/
    EDIT
}


inline fun <T : ZBaseView> Context.zAlert(view: T, noinline init: T.() -> Unit) =
        ZDialogKt<T>(this).apply {
            setView(view)
            init(getView())
        }

inline fun <T : ZBaseView> Context.zAlert(noinline init: T.() -> Unit) = ZDialogKt<T>(this).apply { init(getView()) }

inline fun <T : ZBaseView> Fragment.zAlert(noinline init: T.() -> Unit) = context?.zAlert(init)

inline fun <T : ZBaseView> View.zAlert(noinline init: T.() -> Unit) = context?.zAlert(init)

/**默认的扩展方法*/
inline fun Context.defZAlert(noinline init: ZDialogView.() -> Unit) = zAlert(init)

inline fun Fragment.defZAlert(noinline init: ZDialogView.() -> Unit) = zAlert(init)

inline fun View.defZAlert(noinline init: ZDialogView.() -> Unit) = zAlert(init)


interface OnConfirmListener {
    fun onPositive(text: String, onClicked: () -> Unit)
    fun onNegative(text: String, onClicked: () -> Unit)
    fun onNeutral(text: String, onClicked: () -> Unit)
}


inline fun OnConfirmListener.okButton(text: String = ZDialogManager.textOk, noinline handler: () -> Unit) = onPositive(text, handler)
inline fun OnConfirmListener.noButton(text: String = ZDialogManager.textNo, noinline handler: () -> Unit) = onNegative(text, handler)
inline fun OnConfirmListener.otherButton(text: String = ZDialogManager.textOther, noinline handler: () -> Unit) = onNeutral(text, handler)

internal fun <T : View> T.ZVShow(term: () -> Boolean) = apply { visibility = if (term()) View.VISIBLE else View.GONE }