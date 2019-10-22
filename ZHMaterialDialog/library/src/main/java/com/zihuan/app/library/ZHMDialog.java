package com.zihuan.app.library;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * zihuan
 */

public class ZHMDialog {

    private Dialog dialog;

    private static Context mContext;
    private String title = "Title";
    private String content = "";
    private boolean isCancelable = true;
    private int type = 1;//0编辑 1 文字提示
    private boolean isCancelableTouchOutside = false;
    private int animType = 7;
    //是否显示标题线
    private boolean titleDivider = false;
    private int dividerColor, buttonTextColor, bgcolor;//线段颜色 背景色

    private String confirmText = "", cancelText = "", otherlText = "";


    public ZHMDialog() {

    }

    public ZHMDialog builder() {
        buttonTextColor = ContextCompat.getColor(mContext, R.color.orange);
        dividerColor = buttonTextColor;
        bgcolor = ContextCompat.getColor(mContext, R.color.ffff);
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog, null);
        dialog.setContentView(view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        int sty = 0;
        if (animType == ZHMDAnimUtils.AnimLeft) {
            sty = R.style.AnimLeft;
        } else if (animType == ZHMDAnimUtils.AnimRight) {
            sty = R.style.AnimRight;
        } else if (animType == ZHMDAnimUtils.AnimUp) {
            sty = R.style.AnimUp;

        } else if (animType == ZHMDAnimUtils.AnimDown) {
            sty = R.style.AnimDown;

        } else if (animType == ZHMDAnimUtils.AnimLeftRight) {
            sty = R.style.AnimLeftRight;

        } else if (animType == ZHMDAnimUtils.AnimUpDown) {
            sty = R.style.AnimUpDown;

        } else if (animType == ZHMDAnimUtils.AnimFadeInOut) {
            sty = R.style.AnimFadeInOut;
        } else if (animType == ZHMDAnimUtils.AnimZoomInOut) {
            sty = R.style.AnimZoomInOut;
        }
        dialog.getWindow().getAttributes().windowAnimations = sty;

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtContent = view.findViewById(R.id.txtContent);
        TextView btnConfirm = view.findViewById(R.id.tv_yes);
        TextView btnCancel = view.findViewById(R.id.tv_clare);
        TextView tv_other = view.findViewById(R.id.tv_other);
        RelativeLayout rl_background = view.findViewById(R.id.rl_background);
        final EditText editName = view.findViewById(R.id.et_name);
        View title_lin = view.findViewById(R.id.title_lin);
        rl_background.setBackgroundColor(bgcolor);
        btnConfirm.setTextColor(buttonTextColor);
        btnCancel.setTextColor(buttonTextColor);
        txtTitle.setTextColor(buttonTextColor);

        if (titleDivider) {
            title_lin.setVisibility(View.VISIBLE);
            title_lin.setBackgroundColor(dividerColor);
        } else {
            title_lin.setVisibility(View.GONE);
        }
        switch (type) {
            case 0:
                title_lin.setVisibility(View.VISIBLE);
                editName.setVisibility(View.VISIBLE);
                break;
            case 1:
                title_lin.setVisibility(View.GONE);
                editName.setVisibility(View.GONE);
                break;
        }

        if (TextUtils.isEmpty(title)) {
            txtTitle.setVisibility(View.GONE);
        } else {
            txtTitle.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
        }

        if (TextUtils.isEmpty(content)) {
            txtContent.setVisibility(View.GONE);
        } else {
            txtContent.setVisibility(View.VISIBLE);
            txtContent.setText(content);
        }
        if (TextUtils.isEmpty(otherlText)) {
            tv_other.setVisibility(View.GONE);
        } else {
            tv_other.setVisibility(View.VISIBLE);
            tv_other.setText(otherlText);
        }
        if (TextUtils.isEmpty(confirmText)) {
            btnConfirm.setVisibility(View.GONE);
        } else {
            btnConfirm.setVisibility(View.VISIBLE);
            btnConfirm.setText(confirmText);
        }
        if (TextUtils.isEmpty(cancelText)) {
            btnCancel.setVisibility(View.GONE);
        } else {
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setText(cancelText);
        }
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogListener != null)
                    dialogListener.onConfirm(dialog);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogListener != null)
                    dialogListener.onCancel(dialog);
                dialog.dismiss();

            }
        });
        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogListener != null)
                    dialogListener.onOther(dialog);
                dialog.dismiss();

            }
        });
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelableTouchOutside);
        return this;
    }


    private onDialogListener dialogListener;


    static ZHMDialog ZHMDialog;

    public ZHMDialog show() {
        if (dialog == null) {
            builder();
        }
        dialog.show();
        return this;
    }

    public static ZHMDialog materialBuilder(Context context) {
        mContext = context;
        ZHMDialog = new ZHMDialog();
        return ZHMDialog;
    }

    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }


    public ZHMDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ZHMDialog setContent(String content) {
        this.content = content;
        return this;
    }


    // 点击其他区域是否能取消
    public ZHMDialog cancelable(boolean isCancelable, boolean isCancelableTouchOutside) {
        this.isCancelable = isCancelable;
        this.isCancelableTouchOutside = isCancelableTouchOutside;
        return this;
    }


    public ZHMDialog type(int type) {
        this.type = type;
        return this;
    }

    //设置确定文字
    public ZHMDialog setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    //设置取消文字
    public ZHMDialog setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    //设置其他文字
    public ZHMDialog setOtherText(String otherlText) {
        this.otherlText = otherlText;
        return this;
    }

    //设置监听
    public ZHMDialog setOnDialogListener(onDialogListener ondialogListener) {
        this.dialogListener = ondialogListener;
        return this;
    }

    public ZHMDialog setBackGround(int bgcolor) {
        this.bgcolor = bgcolor;
        return this;
    }

    public ZHMDialog animation(int animType) {
        this.animType = animType;
        return this;
    }

    public ZHMDialog color(int buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
        return this;
    }

    public ZHMDialog divider(boolean isDivider, int dividerColor) {
        this.titleDivider = isDivider;
        this.dividerColor = dividerColor;
        return this;
    }


    public interface onDialogListener {

        void onConfirm(Dialog materialDialog);

        void onCancel(Dialog materialDialog);

        void onOther(Dialog materialDialog);

    }

    public static class OnDialogImp implements onDialogListener{

        @Override
        public void onConfirm(Dialog materialDialog) {

        }

        @Override
        public void onCancel(Dialog materialDialog) {

        }

        @Override
        public void onOther(Dialog materialDialog) {

        }
    }

}
