package net.designpattern.observer;


/**
 * 观察者1号
 */
public class Observer1 implements Observer {

    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}  