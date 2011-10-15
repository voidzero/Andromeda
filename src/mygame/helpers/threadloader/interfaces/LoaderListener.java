/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.helpers.threadloader.interfaces;

/**
 *
 * @author Dansion
 */
public interface LoaderListener {
    public void onComplete();
    public void onProgress(float progress);
}
