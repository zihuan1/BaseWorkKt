package com.zihuan.view.library;


public class ZDialogListenerImp {
    ZDialogListener mPositiveListener;
    ZDialogListener mNegativeListener;
    ZDialogListener mNeutralListener;

    public void setOkButton(ZDialogListener clickListener) {
        mPositiveListener = clickListener;
    }

    public void setNoButton(ZDialogListener clickListener) {
        mNegativeListener = clickListener;
    }

    public void setOtherButton(ZDialogListener clickListener) {
        mNeutralListener = clickListener;
    }

    public void performOk() {
        if (mPositiveListener != null)
            mPositiveListener.onClick();
    }

    public void performNo() {
        if (mNegativeListener != null)
            mNegativeListener.onClick();
    }

    public void performOther() {
        if (mNeutralListener != null)
            mNeutralListener.onClick();
    }
}
