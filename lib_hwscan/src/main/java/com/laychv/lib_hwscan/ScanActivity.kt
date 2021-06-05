package com.laychv.lib_hwscan

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.huawei.hms.hmsscankit.RemoteView
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions
import com.laychv.lib_hwscan.databinding.ActivityScanBinding

class ScanActivity : Activity() {

    lateinit var mBinding: ActivityScanBinding
    private var remoteView: RemoteView? = null
    var mScreenWidth = 0
    var mScreenHeight = 0

    companion object {
        const val REQUEST_CODE_SCAN_DEFAULT_MODE = 1
        const val SCAN_RESULT = "scanResult"
        private const val SCAN_FRAME_SIZE = 300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_scan
        )
        // 1. 获取屏幕密度计算 viewfinder 的矩形
        val dm = resources.displayMetrics
        // 2. 获取屏幕尺寸
        val density = dm.density
        mScreenWidth = dm.widthPixels
        mScreenHeight = dm.heightPixels
        val scanFrameSize = (SCAN_FRAME_SIZE * density)
        // 3. 计算 viewinder 的矩形，放在布局中央
        val rect = Rect()
        apply {
            rect.left = (mScreenWidth / 2 - scanFrameSize / 2).toInt()
            rect.right = (mScreenWidth / 2 + scanFrameSize / 2).toInt()
            rect.top = (mScreenHeight / 2 - scanFrameSize / 2).toInt()
            rect.bottom = (mScreenHeight / 2 + scanFrameSize / 2).toInt()
        }
        // 4. 初始化RemoteView, 并且设置回调监听，这里可能设置扫码选项
        remoteView = RemoteView.Builder().setContext(this).setBoundingBox(rect)
            .setFormat(HmsScan.ALL_SCAN_TYPE).build()
        remoteView?.onCreate(savedInstanceState)
        remoteView?.setOnResultCallback { result ->
            if (result != null && result.isNotEmpty() && result[0] != null && !TextUtils.isEmpty(
                    result[0].getOriginalValue()
                )
            ) {
                val intent = Intent()
                intent.apply {
                    putExtra(SCAN_RESULT, result[0])
                }
                setResult(Activity.RESULT_OK, intent)
                this.finish()
            }
        }
        // 5. 添加 RemoteView 至布局.
        val params = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        startDefaultMode(mBinding.rim1)
        mBinding.rim1.addView(remoteView, params)
    }

    private fun startDefaultMode(view: View) {
        // 扫码选项参数
        val options =
            HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.ALL_SCAN_TYPE).create()
        ScanUtil.startScan(
            this, REQUEST_CODE_SCAN_DEFAULT_MODE,
            options
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK || data == null) {
            return
        }
        when (requestCode) {
            REQUEST_CODE_SCAN_DEFAULT_MODE -> {
                val hmsScan: HmsScan? =
                    data.getParcelableExtra(ScanUtil.RESULT) // 获取扫码结果 ScanUtil.RESULT
                if (!TextUtils.isEmpty(hmsScan?.getOriginalValue())) {
                    mBinding.tvResult.text = hmsScan?.getOriginalValue()
                }
            }
        }
    }

    // 6. 管理RemoteView 的生命周期
    override fun onStart() {
        super.onStart()
        remoteView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        remoteView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        remoteView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        remoteView?.onDestroy()
    }

    override fun onStop() {
        super.onStop()
        remoteView?.onStop()
    }
}