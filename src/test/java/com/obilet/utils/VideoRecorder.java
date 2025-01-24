package com.obilet.utils;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder {
    private ScreenRecorder screenRecorder;
    private static VideoRecorder instance;
    private String currentRecordingPath;

    private VideoRecorder() {}

    public static VideoRecorder getInstance() {
        if (instance == null) {
            instance = new VideoRecorder();
        }
        return instance;
    }

    public void startRecording(String testName) throws Exception {
        File file = new File("test-recordings");
        if (!file.exists()) {
            file.mkdirs();
        }

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        currentRecordingPath = "test-recordings/" + testName + "/" + timestamp + ".avi";
        File movieFile = new File(currentRecordingPath);
        movieFile.getParentFile().mkdirs();

        // Ekran boyutunu al
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        Rectangle captureSize = new Rectangle(0, 0, 1920, 1080);
        
        // Ekran çözünürlüğünü kontrol et
        DisplayMode dm = gd.getDisplayMode();
        if (dm.getWidth() < 1920 || dm.getHeight() < 1080) {
            captureSize = new Rectangle(0, 0, dm.getWidth(), dm.getHeight());
        }

        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        screenRecorder = new ScreenRecorder(gc,
                captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null,
                movieFile);

        screenRecorder.start();
    }

    public void stopRecording() throws Exception {
        if (screenRecorder != null) {
            screenRecorder.stop();
        }
    }

    public String getCurrentRecordingPath() {
        return currentRecordingPath;
    }
}
