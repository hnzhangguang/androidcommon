package net.designpattern.singleton;


/**
 * 7,枚举数据类型实现单例模式
 */
public class EnumSingleton {

    private enum MyEnumSingleton {
        singletonFactory;

        private MySingleton instance;

        private MyEnumSingleton() {//枚举类的构造方法在类加载是被实例化
            instance = new MySingleton();
        }

        public MySingleton getInstance() {
            return instance;
        }
    }

    public static MySingleton getInstance() {
        return MyEnumSingleton.singletonFactory.getInstance();
    }
}

class MySingleton {//需要获实现单例的类，比如数据库连接Connection

    public MySingleton() {
    }
}  