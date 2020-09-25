package com.vaulert.arch_workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadLogWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    /**
     * 耗时的任务，在doWork()方法中执行
     */
    override fun doWork(): Result {
        Log.e("UploadLogWorker", "doWork()")
        return Result.success()
    }
}