package com.zihuan.view.library;


public class GraceAlertListenerImp {
    public GraceAlertListener mPositiveListener;
    public GraceAlertListener mNegativeListener;
    public GraceAlertListener mNeutralListener;

    public void setOkButton(GraceAlertListener clickListener) {
        mPositiveListener = clickListener;
    }

    public void setNoButton(GraceAlertListener clickListener) {
        mNegativeListener = clickListener;
    }

    public void setOtherButton(GraceAlertListener clickListener) {
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
