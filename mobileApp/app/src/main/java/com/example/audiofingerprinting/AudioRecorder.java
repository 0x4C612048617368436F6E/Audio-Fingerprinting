package com.example.audiofingerprinting;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

public class AudioRecorder extends Thread {

    private static final int SAMPLERATE = 44100;
    private static final String TAG = "AudioRecorder";

    private final String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private final TapToShazamButtonPressed instance;
    private AudioRecord record = null;
    private boolean recording = true;
    private short[] audioBuffer;
    private long shortRead = 0;
    File pcmFile;

    public AudioRecorder(TapToShazamButtonPressed instance) {
        this.instance = instance;
        if (this.instance == null) {
            Log.e(TAG, "Instance is null");
        }
        File pcmFile = new File(instance.getFilesDir(), "recording.pcm");
    }

    @Override
    public void run() {
        // Give high priority to the recording thread
        Process.setThreadPriority(Process.THREAD_PRIORITY_AUDIO);

        // Use the correct method for AudioRecord
        int bufferSize = AudioRecord.getMinBufferSize(
                SAMPLERATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT
        );

        // Validate permission (optional safety)
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(this.instance, p) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission Granted: " + p);
            } else {
                Log.e(TAG, "Permission NOT Granted: " + p);
            }
        }

        if (bufferSize == AudioRecord.ERROR || bufferSize == AudioRecord.ERROR_BAD_VALUE) {
            bufferSize = SAMPLERATE * 2;
            Log.w(TAG, "Invalid buffer size. Using fallback size: " + bufferSize);
        }

        // Prepare the AudioRecord instance
        audioBuffer = new short[bufferSize / 2];
        record = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLERATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
        );

        if (record.getState() != AudioRecord.STATE_INITIALIZED) {
            Log.e(TAG, "AudioRecord could not be initialized");
            return;
        }

        record.startRecording();
        Log.d(TAG, "Recording started");
        try {
            while (recording) {
                int numberOfShorts = record.read(audioBuffer, 0, audioBuffer.length);
                    //
                shortRead+=numberOfShorts;
                //send of to server using another thread

            }
        } catch (Exception e) {
            Log.e(TAG, "Recording error: " + e.getMessage());
        } finally {
            if (record != null) {
                record.stop();
                record.release();
                Log.d(TAG, "Recording stopped. Total samples read: " + shortRead);
            }
        }
    }

    public void stopRecording() {
        recording = false;
    }
}
