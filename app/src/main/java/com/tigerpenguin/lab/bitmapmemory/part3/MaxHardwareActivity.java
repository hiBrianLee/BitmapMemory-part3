/*
 * Copyright (C) 2014 Brian Lee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tigerpenguin.lab.bitmapmemory.part3;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MaxHardwareActivity extends Activity {

    private FrameLayout mainLayout;
    private ImageView pictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_hardware);
        mainLayout = (FrameLayout) findViewById(R.id.mainLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        pictureView = new ImageView(this);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // must do this before setting the image
            pictureView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        int width = getResources().getDimensionPixelSize(R.dimen.imageWidth);
        int height = getResources().getDimensionPixelSize(R.dimen.imageHeight);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        pictureView.setLayoutParams(layoutParams);
        pictureView.setImageResource(R.drawable.dessert);
        mainLayout.addView(pictureView);

        pictureView.setOnClickListener(new PictureClickListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainLayout.removeView(pictureView);
        pictureView = null;
    }

    private void startOtherActivity() {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }

    private class PictureClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startOtherActivity();
        }
    }
}
