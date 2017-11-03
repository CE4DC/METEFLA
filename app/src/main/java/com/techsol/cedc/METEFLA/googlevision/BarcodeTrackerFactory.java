package com.techsol.cedc.METEFLA.googlevision;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {
    private GraphicTracker.DetectorListener mDetectorListener;
    private CameraOverlay mCameraOverlay;

    public BarcodeTrackerFactory(CameraOverlay overlay, GraphicTracker.DetectorListener detectorListener) {
        mCameraOverlay = overlay;
        mDetectorListener = detectorListener;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        BarcodeGraphic graphic = new BarcodeGraphic(mCameraOverlay);
        GraphicTracker graphicTracker = new GraphicTracker(mCameraOverlay, graphic);
        if(mDetectorListener != null) graphicTracker.setDetectorListener(mDetectorListener);
        return graphicTracker;
    }
}

class BarcodeGraphic extends CameraOverlay.Graphic {
    private int mId;

    private Paint mRectPaint;
    private volatile Barcode mBarcode;

    BarcodeGraphic(CameraOverlay overlay) {
        super(overlay);

        final int selectedColor = Color.GREEN;

        mRectPaint = new Paint();
        mRectPaint.setColor(selectedColor);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(4.0f);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Barcode getBarcode() {
        return mBarcode;
    }

    /**
     * Updates the barcode instance from the detection of the most recent frame.  Invalidates the
     * relevant portions of the overlay to trigger a redraw.
     */
    void updateItem(Barcode barcode) {
        mBarcode = barcode;
        postInvalidate();
    }

    /**
     * Draws the barcode annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        Barcode barcode = mBarcode;
        if (barcode == null) {
            return;
        }

        // Draws the bounding box around the barcode.
        RectF rect = new RectF(barcode.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, mRectPaint);
    }
}
