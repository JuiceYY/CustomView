package cn.istary.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.istary.customview.R;
import cn.istary.customview.widget.CheckView;

public class CheckViewActivity extends AppCompatActivity{

    private CheckView mCheckView;
    private Button mCheckButton;
    private Button mUncheckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkview);
        mCheckView = findViewById(R.id.checkview);
        mCheckButton = findViewById(R.id.check_check);
        mUncheckButton = findViewById(R.id.check_uncheck);

        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckView.check();
            }
        });

        mUncheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckView.unCheck();
            }
        });
    }

}
