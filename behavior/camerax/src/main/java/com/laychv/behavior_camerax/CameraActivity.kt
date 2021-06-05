package com.laychv.behavior_camerax

import android.os.Bundle
import android.util.Size
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        setUpCamera()
    }

    private fun setUpCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            initCamera(cameraProvider)
        }, ContextCompat.getMainExecutor(this))
    }

    private fun initCamera(cameraProvider: ProcessCameraProvider) {
        // 宽高比/旋转
        val build = Preview.Builder()
            .build()

        // 图片
        val capture = ImageCapture.Builder()
            .build()

        // 图片分辨率
        val analysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .build()

        // 视频
        val video = VideoCapture.Builder()
            .build()

        // 前置摄像头 / 后置摄像头
        val selector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(this as LifecycleOwner, selector, capture)
//        build.setSurfaceProvider(previewView.createSurfaceProvider())
    }
}
