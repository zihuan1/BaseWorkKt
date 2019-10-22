package com.zihuan.app.mydialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zihuan.app.library.ZHMDialog;

public class MainActivity extends AppCompatActivity {
    ZHMDialog mZHMDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mZHMDialog = ZHMDialog.materialBuilder(this)
                .setCancelText("取消")
                .setConfirmText("确认")
                .setTitle("提示o(∩_∩)o ")
                .setContent("你好")
                .show();
        mZHMDialog.setOnDialogListener(new ZHMDialog.OnDialogImp(){

        });
    }


    public void showdialg(View view) {
        mZHMDialog.show();
    }
}
