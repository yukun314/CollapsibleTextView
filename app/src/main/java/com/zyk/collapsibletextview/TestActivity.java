package com.zyk.collapsibletextview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {
    private CollapsibleTextView mTextView;
    private int times = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {
        mTextView = (CollapsibleTextView) findViewById(R.id.activity_test_textview);
        mTextView.setImage(R.drawable.down,R.drawable.up);
        mTextView.getTextView().setTextColor(Color.BLUE);
        mTextView.getTextView().setTextSize(23);
        mTextView.setMaxLines(4);
        mTextView.setText("第一行初始化的内容\n第二行初始化的内容\n");
        Button mButton = (Button) findViewById(R.id.activity_test_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mTextView.getTextView().getText()+"第"+times+"次添加的内容\n";
                mTextView.setText(str);
                times+=1;
            }
        });
    }

}
