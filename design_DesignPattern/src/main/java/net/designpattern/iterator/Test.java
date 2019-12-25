package net.designpattern.iterator;

public class Test {

    public static void main(String[] args) {
        Collection collection = new MyCollection();
        java.util.Iterator it = collection.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}  