package function.java.ref;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class JavaReferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_java_reference);
    }


    public void strongRef() {

        Object obj = new Object();  // 强引用


    }


    /**
     * 软引用（SoftReference ）
     */
    public void softRef() {

        String value = new String("sy");
        SoftReference sfRefer = new SoftReference(value);

        sfRefer.get();//可以获得引用对象值


    }

    /**
     * 弱引用（WeakReference）
     */
    public void WeakRef() {

        String value = new String("sy");
        WeakReference weakRefer = new WeakReference(value);

        System.gc();

        weakRefer.get();//null


    }


}
