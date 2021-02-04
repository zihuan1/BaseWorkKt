package com.zihuan.view.library

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout

/**
 * 所有view的跟布局
 */
class GraceAlertParentView : FrameLayout {

    private var mAnimation: Array<Int>
    private var mShowAnimation: Animation
    private var mDismissAnimation: Animation
    private var mChildView: AlertBaseView

    constructor(context: Context, childView: AlertBaseView, anim: Array<Int>) : super(context) {
        addView(childView)
        setBackgroundColor(childView.backGround)
        mChildView = childView
        mAnimation = anim
        mShowAnimation = AnimationUtils.loadAnimation(context, mAnimation[0])
        mDismissAnimation = AnimationUtils.loadAnimation(context, mAnimation[1])
        mDismissAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                visibility = View.GONE
                if (childView.outside) {
                    isClickable = true
                }
            }

            override fun onAnimationStart(animation: Animation?) {
                if (childView.outside) {
                    isClickable = false
                }
            }

        })
        childView.dialog = this
    }


    fun show() {
        visibility = View.VISIBLE
        startAnimation(mShowAnimation)
    }

    fun dismiss() {
        startAnimation(mDismissAnimation)
        mChildView.onDismiss()
    }
}