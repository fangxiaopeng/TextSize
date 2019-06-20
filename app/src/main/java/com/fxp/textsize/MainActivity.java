package com.fxp.textsize;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTextSize();

        setContentView(R.layout.activity_main);

        context = this;

        findViewById(R.id.text_size_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextSizeDialog();
            }
        });
    }

    /**
     * @Description: 设置主题-字体大小
     * 在 onCreate 中 setContentView 之前执行
     *
     * @Author:  fxp
     * @Date:    2019-06-19   14:52
     * @param
     * @return   void
     * @exception/throws
     */
    private void initTextSize(){
        int textSize = PreferencesUtil.getInstance(this).getInt("TextSizeSet", 0);
        switch (textSize){
            case 0:
                setTheme(R.style.Default_TextSize_Small);
                break;
            case 1:
                setTheme(R.style.Default_TextSize_Middle);
                break;
            case 2:
                setTheme(R.style.Default_TextSize_Big);
                break;
            default:
                break;
        }
    }

    public void showTextSizeDialog() {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(getString(R.string.title_text_size_dialog))
                .setSingleChoiceItems(new String[]{getString(R.string.text_size_small), getString(R.string.text_size_middle),
                                getString(R.string.text_size_big)}, PreferencesUtil.getInstance(this).getInt("TextSizeSet"),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PreferencesUtil.getInstance(context).putInt("TextSizeSet", which);
                                dialog.dismiss();
                                ((Activity)context).recreate();
                            }
                        })
                .create();
        dialog.show();
    }
}
