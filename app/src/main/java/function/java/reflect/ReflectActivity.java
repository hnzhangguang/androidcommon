package function.java.reflect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectActivity extends AppCompatActivity {


    TextView txt;
    Button button;

    public void logg(Object object) {
        Log.e("mmmmm", object + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_reflect);


    }


    public void onclick() {


        //获取Class对象的三种方式

        //1 Object ——> getClass();
        //2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
        //3 通过Class类的静态方法：forName（String  className）(常用)

        //第一种方式获取Class对象
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        logg(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        logg("stuClass == stuClass2 ? " + (stuClass == stuClass2));//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName("com.android.java.reflect.Student");
            //注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            logg("stuClass3 == stuClass2 ? " + (stuClass3 == stuClass2));//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
 *
 * 1.获取构造方法：
 *      1).批量的方法：
 *          public Constructor[] getConstructors()：所有"公有的"构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

 *      2).获取单个的方法，并调用：
 *          public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 *          public Constructor getDeclaredConstructor(Class... parameterTypes)
 * :获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 *
 *          调用构造方法：
 *          Constructor-->newInstance(Object... initargs)
 */


        try {
            //1.加载Class对象
            Class clazz = Class.forName("com.android.java.reflect.Student");
            //2.获取所有公有构造方法
            logg("**********************所有公有构造方法*********************************");
            Constructor[] conArray = clazz.getConstructors();
            for (Constructor c : conArray) {
                logg(c);
            }

            logg("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
            conArray = clazz.getDeclaredConstructors();
            for (Constructor c : conArray) {
                logg(c);
            }


            logg("*****************获取公有、无参的构造方法*******************************");
            Constructor con = clazz.getConstructor();
            //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
            //2>、返回的是描述这个无参构造函数的类对象。
            logg("con = " + con);
            //调用构造方法
            Object obj = con.newInstance();


            logg("******************获取私有构造方法，并调用*******************************");
            con = clazz.getDeclaredConstructor(char.class);
            logg(con);
            //调用构造方法
            con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
            obj = con.newInstance('男');
            if (obj instanceof Student) {
                Student s = (Student) obj;
                logg(s.toString());
            }


            //2.获取字段
            logg("************获取所有公有的字段********************");
            Field[] fieldArray = stuClass.getFields();
            for (Field f : fieldArray) {
                logg(f);
            }


            logg("************获取所有的字段(包括私有、受保护、默认的)********************");
            fieldArray = stuClass.getDeclaredFields();
            for (Field f : fieldArray) {
                logg(f);
            }


            logg("*************获取公有字段**并调用***********************************");
            Field f = stuClass.getField("name");
            //获取一个对象
            obj = stuClass.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
            //为字段设置值
            f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
            //验证
            Student stu = (Student) obj;
            logg("验证姓名：" + stu.name);


            logg("**************获取私有字段****并调用********************************");
            f = stuClass.getDeclaredField("name");
            logg(f);
            f.setAccessible(true);//暴力反射，解除私有限定
            f.set(obj, "18888889999");
            logg("验证电话：" + stu);

            //由此可见
            //调用字段时：需要传递两个参数：
            //Object obj = stuClass.getConstructor().newInstance();
            //产生Student对象--》Student stu = new Student();
            //为字段设置值
            //f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
            //第一个参数：要传入设置的对象，第二个参数：要传入实参


            //2.获取所有公有方法
            logg("***************获取所有的”公有“方法*******************");
            Method[] methods = stuClass.getMethods();
            Method[] methodArray = stuClass.getMethods();
            for (Method m : methodArray) {
                logg(m);
            }
            logg("***************获取所有的方法，包括私有的*******************");
            methodArray = stuClass.getDeclaredMethods();
            for (Method m : methodArray) {
                logg(m);
            }
            logg("***************获取公有的show1()方法*******************");
            Method m = stuClass.getMethod("show1", String.class);
            logg(m);
            //实例化一个Student对象
            obj = stuClass.getConstructor().newInstance();
            Object result = m.invoke(obj, "刘德华");// result为方法返回值
            logg(result);

            logg("***************获取私有的show4()方法******************");
            m = stuClass.getDeclaredMethod("show4", int.class);
            logg(m);
            m.setAccessible(true);//解除私有限定
            result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
            logg(result);


            logg("***************获取私有的show5()方法  调用没有参数的方法  *****************");
            m = stuClass.getDeclaredMethod("show5");
            logg(m);
            m.setAccessible(true);//解除私有限定
            result = m.invoke(obj);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
            logg(result);


            //2、获取main方法 - 调用静态方法
            logg("***************获取私有的获取main方法******************");
            Method methodMain = clazz.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型，
            result = methodMain.invoke(null, (Object) new String[]{"a", "b", "c"});//方式一
            logg(result);


            logg("***************通过反射运行配置文件内容******************");
            //通过反射运行配置文件内容
            //通过反射获取Class对象
            stuClass = Class.forName(getValue("className"));//"cn.fanshe.Student"
            //2获取show()方法
            m = stuClass.getMethod(getValue("methodName"), String.class);//show
            //3.调用show()方法
            result = m.invoke(stuClass.getConstructor().newInstance(), "zhangguang");
            logg(result);


            String str = null;
            str = String.format("Hi,%s", "王力");
            logg(str);
            str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
            logg(str);


        } catch (Exception e) {
            logg(e);
            e.printStackTrace();
        }


    }


    //此方法接收一个key，在配置文件中获取相应的value
    public String getValue(String key) throws IOException {

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("className", "com.android.java.reflect.Student");
            jsonObject.put("methodName", "show1");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject.optString(key);


    }


}
