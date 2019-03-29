package cn.istary.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import cn.istary.customview.R;
import cn.istary.customview.widget.RadarView;

public class RadarActivity extends AppCompatActivity {

    private RadarView mRadarView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        mRadarView = findViewById(R.id.radarview);
        mButton = findViewById(R.id.radar_refresh);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mRadarView.set(6, new float[]{3,4,5,6,7,8}, 10, new String[]{"a", "b", "c", "d", "e", "f"}, 6);
                Random random = new Random();
                int itemCount = 6;
                float[] values = new float[itemCount];
                int maxValue = random.nextInt(20);
                for (int i = 0; i < itemCount; i++) {
                    values[i] = random.nextFloat() % maxValue;
                }
                String[] titles = new String[]{"a", "b", "c", "d", "e", "f"};
                int layer = random.nextInt(4) + 4;
                mRadarView.set(itemCount, values, maxValue, titles, layer);

            }
        });
    }
}
