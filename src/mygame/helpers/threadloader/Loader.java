/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers.threadloader;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.helpers.Share;
import mygame.helpers.threadloader.interfaces.LoaderListener;
import mygame.helpers.threadloader.interfaces.LoaderTask;

/**
 *
 * @author Dansion
 */
public class Loader {
    public LoaderTask task = null;
    public LoaderListener listener = null;
    public Share share = Share.getInstance();
    private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(10);
    private Future status = null;
    private Loader loader = null;
    private boolean done = true;

    public Loader() {
        loader = this;
    }

    public void run() {
        if(task != null) {
            Callable call = new Callable() {
                public Object call() throws Exception {
                    done = false;

                    task.task(loader);

                    return 0;
                }
            };
            status = exec.submit(call);
        }
    }

    public void setProgress(float progress) {
        if(listener != null) {
            listener.onProgress(progress);
        }
    }

    public void checkStatus() {
        try {
            if(status.isDone()) {
                listener.onComplete();
                done = true;
            }

            if(status.isDone() || status.isCancelled()) {
                this.finalize();
            }
        } catch (Throwable ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE,"Finalizing error.", ex);
        }
    }

    public boolean isActive() {
        return !done;
    }

    public void stop() {
        exec.shutdown();
    }
}
