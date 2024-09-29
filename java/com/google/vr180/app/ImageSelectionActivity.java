package com.google.vr180.app;

import static android.hardware.SensorManager.getOrientation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.util.SizeF;
import android.widget.Button;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;

import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;

import android.widget.FrameLayout;
import android.widget.Toast;

import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.vr180.common.logging.Log;
import com.google.vr180.common.media.StereoMode;
import com.google.vr180.media.photo.PhotoWriter;

import android.util.DisplayMetrics;
import android.view.WindowMetrics;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;


public class ImageSelectionActivity extends AppCompatActivity {

    private static final String TAG = "ImageSelectionActivity";
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private Button convertButton;
    private FrameLayout adContainerView;

    private AdView adView;

    private void initializeMobileAdsSdk() {
        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(this, initializationStatus -> {});

                    // Load an ad on the main thread.
                    runOnUiThread(this::loadBanner);
                })
                .start();
    }

    // Get the ad size with screen width.
    public AdSize getAdSize() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int adWidthPixels = displayMetrics.widthPixels;

        if (VERSION.SDK_INT >= VERSION_CODES.R) {
            WindowMetrics windowMetrics = this.getWindowManager().getCurrentWindowMetrics();
            adWidthPixels = windowMetrics.getBounds().width();
        }

        float density = displayMetrics.density;
        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    private void loadBanner() {
        // [START create_ad_view]
        // Create a new ad view.
        adView = new AdView(this);
        String AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741";
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setAdSize(getAdSize());

        // Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);
        // [END create_ad_view]

        // [START load_ad]
        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        // [END load_ad]
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);

        recyclerView = findViewById(R.id.recyclerView);
        convertButton = findViewById(R.id.convertButton);
        adContainerView = findViewById(R.id.adContainer);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        imageAdapter = new ImageAdapter(this);
        recyclerView.setAdapter(imageAdapter);

        // Load images from the specified directory
        loadImages();

        initializeMobileAdsSdk();

        // Set up conversion button click listener
        convertButton.setOnClickListener(v -> convertSelectedImages());
    }

    private void loadImages() {
        // Check permission to read external storage
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            File dir = new File("/storage/emulated/0/DCIM/Camera/");
            File[] files = dir.listFiles((file, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png"));
            if (files != null) {
                List<String> imagePaths = new ArrayList<>();
                for (File file : files) {
                    imagePaths.add(file.getAbsolutePath());
                }
                imageAdapter.setImages(imagePaths);
            }
        }
    }

    public String getNewPath(String originalPath) {
        // Define the old and new directory names
        String oldDirectory = "/Camera";
        String newDirectory = "/VR180";

        // Replace old directory with new directory in the original path
        String newPath = originalPath.replace(oldDirectory, newDirectory);

        // Append the "_vr180" suffix before the file extension
        int dotIndex = newPath.lastIndexOf('.');
        if (dotIndex != -1) {
            newPath = newPath.substring(0, dotIndex) + "_vr180" + newPath.substring(dotIndex);
        }

        return newPath;
    }


    private void convertSelectedImages() {
        List<String> selectedPositions = imageAdapter.getSelectedPaths();
        if (selectedPositions.isEmpty()) {
            Toast.makeText(this, "No images selected for conversion", Toast.LENGTH_SHORT).show();
            return;
        }

        for (String path: selectedPositions) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);  // Replace 'path' with the actual file path if different
            if (bitmap == null) {
                Log.e(TAG, "Failed to decode image file.");
                return;
            }

            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int bytesPerPixel = 4; // for ARGB_8888
            int stride = bitmap.getWidth() * bytesPerPixel;


            // Convert the scaled bitmap to a byte array
            ByteBuffer bitmapBuffer = ByteBuffer.allocate(bitmap.getByteCount());
            bitmap.copyPixelsToBuffer(bitmapBuffer);
            byte[] data = bitmapBuffer.array();

            // Close the Bitmap to free resources
            bitmap.recycle();

            int stereoMode = StereoMode.LEFT_RIGHT;
            //    stereoReprojectionConfig == null
            //        ? StereoMode.MONO
            //        : stereoReprojectionConfig.getStereoMode();
            if (PhotoWriter.nativeWriteVRPhotoToFile(
                    data,
                    width,
                    height,
                    stride,
                    84.0f,
                    70.0f,
                    new float[] {0, 0, 0},
                    stereoMode,
                    getNewPath(path))) {
                // Trigger media scanner to update database.
                MediaScannerConnection.scanFile(this.convertButton.getContext(), new String[] {getNewPath(path)}, null, null);
            } else {
                Log.e(TAG, "Failed to capture photo: " + path);
            }
        }

        // Convert each selected image

        Toast.makeText(this, "Selected images have been converted", Toast.LENGTH_SHORT).show();
    }
}

