
package com.cocoonshu.example.flashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.app.Activity;

public class MainActivity extends Activity {

    private static final int  HANDLE_UPDATE_LIGHT_STATE = 0x0001;
    private static final long LIGHT_INTERVAL            = 1000L;
    
    private Handler   mAnimationHandler = null;
    private ImageView mImgLight         = null;
    private boolean   mLightState       = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupViews();
        setupAnimations();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimationHandler.sendEmptyMessage(HANDLE_UPDATE_LIGHT_STATE);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mAnimationHandler.removeMessages(HANDLE_UPDATE_LIGHT_STATE);
    }
    
    private void setupViews() {
        mImgLight = (ImageView) findViewById(R.id.ImageViewLight); 
    }
    
    private void setupAnimations() {
        mAnimationHandler = new Handler() {
            
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                case HANDLE_UPDATE_LIGHT_STATE:
                    mLightState =! mLightState;
                    updateLightDrawable();
                    mAnimationHandler.sendEmptyMessageDelayed(HANDLE_UPDATE_LIGHT_STATE, LIGHT_INTERVAL);
                    break;
                }
            }
            
        };
    }

    protected void updateLightDrawable() {
        if (mLightState) {
            mImgLight.setImageResource(R.drawable.ligth_on);
        } else {
            mImgLight.setImageResource(R.drawable.ligth_off);
        }
    }
}
