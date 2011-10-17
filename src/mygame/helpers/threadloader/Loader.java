/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers.threadloader;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.engine.gui.Panel;
import mygame.engine.gui.ProgressBar;
import mygame.engine.gui.Text;
import mygame.engine.nodes.GroupNode;
import mygame.helpers.Gui;
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
    private LoaderPanelTask prog_task = null;
    private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(10);
    private Future status = null;
    private Loader loader = null;
    private LoaderPanel panel = LoaderPanel.getInstance();
    private float progress = 0.0f;
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
            prog_task = panel.addTask("Task");
        }
    }

    public void setProgress(float current_progress) {
        progress = current_progress;
        Callable call = new Callable() {
            public Object call() throws Exception {

                panel.setProgress(prog_task, progress);

                if(listener != null) {
                    listener.onProgress(progress);
                }

                return 0;
            }
        };

        Share.getInstance().app.enqueue(call);
    }

    public void checkStatus() {
        try {
            if(status.isDone()) {
                if(listener != null) {
                    Callable call = new Callable() {
                        public Object call() throws Exception {
                            listener.onComplete();

                            return 0;
                        }
                    };

                    Share.getInstance().app.enqueue(call);
                }
                panel.removeTask(prog_task);

                done = true;
            }

            if(status.isDone() || status.isCancelled()) {
                panel.removeTask(prog_task);
                done = true;
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

/*
 * @author Dennis Alberts
 * @usage Private gui panel for ThreadedLoader
 */

class LoaderPanel extends GroupNode {
    private static LoaderPanel INSTANCE = new LoaderPanel();
    private ProgressBar bar = new ProgressBar();
    private Panel loaderPanel = new Panel("ThrededLoaderPanel", "Textures/threaded_loader_panel.png");
    private ArrayList <LoaderPanelTask> tasks = new <LoaderPanelTask> ArrayList();
    private Text caption = new Text("no running tasks...");

    private LoaderPanel() {
        super("ThrededLoaderPanel");

        Share.getInstance().guiNode.attachChild(this);

        attachChild(loaderPanel);
        attachChild(bar);
        attachChild(caption);

        loaderPanel.showCloseButton(false);
        loaderPanel.setLocalTranslation(Gui.getRightX() / 2 - 272, Gui.getTopY() - 112, 0);

        bar.setLocalTranslation(Gui.getRightX() / 2 - 256, Gui.getTopY() - 64, 0);

        caption.setLocalTranslation(Gui.getRightX() / 2 - caption.getLineWidth() / 2, Gui.getTopY() - 72, 0);
    }

    public static LoaderPanel getInstance() {
        return INSTANCE;
    }

    public void setProgress(LoaderPanelTask task, float progress) {
        tasks.get(tasks.indexOf(task)).progress = progress;
        update();
    }

    public LoaderPanelTask addTask(String name) {
        LoaderPanelTask task = new LoaderPanelTask();
        task.name = name;
        task.progress = 0.0f;

        tasks.add(task);
        update();
        return task;
    }

    public void removeTask(LoaderPanelTask task) {
        tasks.remove(task);
        update();
    }

    public void update() {
        int task_amount = tasks.size();

        if(task_amount > 0) {
            if(!Share.getInstance().guiNode.hasChild(this)) {
                Share.getInstance().guiNode.attachChild(this);
            }

            float total_max_progress = task_amount * 1.0f;
            float current_total_progress = 0.00f;

            for(int i = 0;i < task_amount; i++) {
                current_total_progress += tasks.get(i).progress;
            }

            current_total_progress = current_total_progress / total_max_progress;

            bar.setProgress(current_total_progress);

            caption.setText(task_amount + " running task(s)...");
        }
        else {
            if(Share.getInstance().guiNode.hasChild(this)) {
                Share.getInstance().guiNode.detachChild(this);
            }

            bar.setProgress(0.00f);

            caption.setText("no running tasks...");
        }
    }
}

class LoaderPanelTask {
    public String name;
    public float progress;
}