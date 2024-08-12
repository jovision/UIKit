package com.jovision.uikit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jovision.uikit.util.ToastKit;
import com.jovision.uikit.widget.sweetdialog.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ToastKit.showBottomToastSafe(MainActivity.this, "底部toast");

            }
        });
    }
}