package net.designpattern.observer;


/**
 * 被观察者实现类
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

}  