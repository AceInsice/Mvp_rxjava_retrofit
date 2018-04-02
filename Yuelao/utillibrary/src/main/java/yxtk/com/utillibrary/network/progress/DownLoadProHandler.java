package yxtk.com.utillibrary.network.progress;

import android.os.Looper;
import android.os.Message;

/**
 * Created by libo on 2018/3/30.
 */

public abstract class DownLoadProHandler extends ProgressHandler {
    private static final int DOWNLOAD_PROGRESS = 1;
    protected ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    protected void sendMessage(ProgressBean progressBean) {
        mHandler.obtainMessage(DOWNLOAD_PROGRESS,progressBean).sendToTarget();

    }

    @Override
    protected void handleMessage(Message message){
        switch (message.what){
            case DOWNLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean)message.obj;
                onProgress(progressBean.getProgressReaded(),progressBean.getContentLength(),progressBean.isDone());
        }
    }
}
