package com.techsol.cedc.METEFLA.googlevision;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

public class GraphicTracker extends Tracker<Barcode> {
    private CameraOverlay mOverlay;
    private BarcodeGraphic mGraphic;
    private DetectorListener mDetectorListener;

    GraphicTracker(CameraOverlay overlay, BarcodeGraphic graphic) {
        mOverlay = overlay;
        mGraphic = graphic;
    }

    public void setDetectorListener(DetectorListener detectorListener) {
        this.mDetectorListener = detectorListener;
    }

    /**
     * Start tracking the detected item instance within the item overlay.
     */
    @Override
    public void onNewItem(int id, Barcode item) {
        mGraphic.setId(id);
        if(mDetectorListener != null) {
            mDetectorListener.onObjectDetected(item);
        }
    }

    /**
     * Update the position/characteristics of the item within the overlay.
     */
    @Override
    public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item) {
        mOverlay.add(mGraphic);
        mGraphic.updateItem(item);
    }

    /**
     * Hide the graphic when the corresponding face was not detected.  This can happen for
     * intermediate frames temporarily, for example if the face was momentarily blocked from
     * view.
     */
    @Override
    public void onMissing(Detector.Detections<Barcode> detectionResults) {
        mOverlay.remove(mGraphic);
    }

    /**
     * Called when the item is assumed to be gone for good. Remove the graphic annotation from
     * the overlay.
     */
    @Override
    public void onDone() {
        mOverlay.remove(mGraphic);
    }
    
    public interface DetectorListener {
        /**
         * Multiple events can be fired depending the number of barcodes identified,
         * So you may want to build a Map<K,V> to add the detected objects and finish the
         * activity when the user is satisfied. @see {@link java.util.Map}
         *
         * @param data Barcode parsed object will contain different kinds of data depending
         *             on the scanned barcode content.
         */
        void onObjectDetected(Barcode data);
    }
}

