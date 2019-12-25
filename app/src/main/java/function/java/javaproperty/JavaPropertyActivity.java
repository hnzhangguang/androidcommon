package function.java.javaproperty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Collection;
import java.util.HashSet;

public class JavaPropertyActivity extends AppCompatActivity {

    Button btn_cest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_java_property);
        initview();
    }


    void initview() {
        btn_cest = null;
        btn_cest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
    }


    public void test() {


        Person p = new CellegeStudent();


        int age = p.age;
        p.print();

        Log.e("mmmm", age + "");


        // 基本类型的和其包装类型比较的时候 + String,都是比较的值
        int a = 11;
        Integer b = Integer.valueOf(11);
        Log.e("mmmm", "a==b? " + (a == b));  // true


        //
        Collection c = new HashSet();

        CellegeStudent n1 = new CellegeStudent();
        CellegeStudent n2 = new CellegeStudent();

        c.add(n1);
        Log.e("mmmm", "------------------------------------------------------------------");
        c.add(n2);
        Log.e("mmmm", "------------");
        Log.e("mmmm", n1.equals(n2) + "");  // true
        Log.e("mmmm", "------------");
        Log.e("mmmm", n1.hashCode() + "");
        Log.e("mmmm", n2.hashCode() + "");
        Log.e("mmmm", c + "");   //里面是有2个对象的 因为hashcode 不同导致的,所以重写equil()的时候必须重写hashcode()


    }


}
