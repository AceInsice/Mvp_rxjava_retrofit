package yxtk.com.utillibrary.network.progress;

/**
 * Created by libo on 2018/3/30.
 */

public interface ProgressListen {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
