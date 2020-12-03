package com.vaulert.arch_workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class UpvoteStoryWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val service: String
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        return try {
            // 投票操作
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 10) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }
}