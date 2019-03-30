package cn.istary.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.istary.customview.R;
import cn.istary.customview.widget.CameraView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private CameraView mCameraView;
    private Button mButtonDefault;
    private Button mButtonClip;
    private Button mButtonRotateX;
    private Button mButtonLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCameraView = findViewById(R.id.camera_view);
        mButtonDefault = findViewById(R.id.btn_default);
        mButtonClip = findViewById(R.id.btn_clip);
        mButtonRotateX = findViewById(R.id.btn_rotatex);
        mButtonLocation = findViewById(R.id.btn_camera_location);
        mButtonDefault.setOnClickListener(this);
        mButtonClip.setOnClickListener(this);
        mButtonRotateX.setOnClickListener(this);
        mButtonLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_default:
                mCameraView.setMode(CameraView.DEFAULT);
                break;
            case R.id.btn_clip:
                mCameraView.setMode(CameraView.CLIP);
                break;
            case R.id.btn_rotatex:
                mCameraView.setMode(CameraView.CAMERA_ROTATEX);
                break;
            case R.id.btn_camera_location:
                mCameraView.setMode(CameraView.CAMERA_LOCATION);
                break;
        }
    }
}
