package com.obilet.utils;

import org.junit.Test;

public class VideoRecorderTest {
    
    @Test
    public void testVideoRecording() throws Exception {
        VideoRecorder recorder = VideoRecorder.getInstance();
        
        // Start recording
        recorder.startRecording("TestVideo");
        
        // Record for 5 seconds
        Thread.sleep(5000);
        
        // Stop recording
        recorder.stopRecording();
    }
}
