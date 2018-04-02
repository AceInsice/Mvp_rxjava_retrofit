package yxtk.com.utillibrary.network.progress;

/**
 * Created by libo on 2018/3/30.
 */

public class ProgressBean {
    private long progressReaded;
    private long contentLength;
    private boolean isDone;

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getProgressReaded() {
        return progressReaded;
    }

    public void setProgressReaded(long progressReaded) {
        this.progressReaded = progressReaded;
    }
}
