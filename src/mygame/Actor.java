/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author Dansion
 */
public class Actor {
    private String a;
    private String h;
    public void main(String name){
         System.out.println( "new object" );
         a = name;
    }

    public void show() {
        System.out.println(a);
    }
}
