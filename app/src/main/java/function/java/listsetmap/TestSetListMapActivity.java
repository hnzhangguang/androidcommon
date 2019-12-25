package function.java.listsetmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.app.logger.LogUtil;
import com.yy.app.R;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.TreeSet;

public class TestSetListMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_set_list_map);
        findViewById(R.id.iterator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iterator();
            }
        });
        findViewById(R.id.hashset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashset();
            }
        });
        findViewById(R.id.LinkedHashSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedHashSet();
            }
        });
        findViewById(R.id.TreeSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TreeSet();
            }
        });
        findViewById(R.id.EnumSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnumSet();
            }
        });
        findViewById(R.id.ListIterator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListIterator();
            }
        });
        findViewById(R.id.foreach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foreach();
            }
        });
        findViewById(R.id.Hashtable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hashtable();
            }
        });
        findViewById(R.id.fastjson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paraseJson();
            }
        });
        findViewById(R.id.ArrayList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList();
            }
        });
        findViewById(R.id.Stack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stack();
            }
        });
        findViewById(R.id.LinkedList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList();
            }
        });
        findViewById(R.id.ArrayDeque).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayDeque();
            }
        });
    }


    public void ArrayDeque() {

        ArrayDeque stack = new ArrayDeque();
        //依次将三个元素push入"栈"
        stack.push("疯狂Java讲义");
        stack.push("轻量级Java EE企业应用实战");
        stack.push("疯狂Android讲义");

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        LogUtil.e("mmmm", stack);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        LogUtil.e(stack.peek());

        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        LogUtil.e(stack);

        //pop出第一个元素，输出：疯狂Android讲义
        LogUtil.e(stack.pop());

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        LogUtil.e(stack);


    }

    public void LinkedList() {

        LinkedList books = new LinkedList();

        //将字符串元素加入队列的尾部(双端队列)
        books.offer("疯狂Java讲义");

        //将一个字符串元素加入栈的顶部(双端队列)
        books.push("轻量级Java EE企业应用实战");

        //将字符串元素添加到队列的头(相当于栈的顶部)
        books.offerFirst("疯狂Android讲义");

        for (int i = 0; i < books.size(); i++) {
            LogUtil.e(books.get(i));
        }

        //访问、并不删除栈顶的元素
        LogUtil.e(books.peekFirst());
        //访问、并不删除队列的最后一个元素
        LogUtil.e(books.peekLast());
        //将栈顶的元素弹出"栈"
        LogUtil.e(books.pop());
        //下面输出将看到队列中第一个元素被删除
        LogUtil.e(books);
        //访问、并删除队列的最后一个元素
        LogUtil.e(books.pollLast());
        //下面输出将看到队列中只剩下中间一个元素：
        //轻量级Java EE企业应用实战
        LogUtil.e(books);


    }


    public void Stack() {


        Stack v = new Stack();
        //依次将三个元素push入"栈"
        v.push("疯狂Java讲义");
        v.push("轻量级Java EE企业应用实战");
        v.push("疯狂Android讲义");

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        LogUtil.e(v);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        LogUtil.e(v.peek());

        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        LogUtil.e(v);

        //pop出第一个元素，输出：疯狂Android讲义
        LogUtil.e(v.pop());

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        LogUtil.e(v);


    }


    public void ArrayList() {

        List books = new ArrayList();
        //向books集合中添加三个元素
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));
        LogUtil.e(books);

        //将新字符串对象插入在第二个位置
        books.add(1, new String("疯狂Ajax讲义"));
        for (int i = 0; i < books.size(); i++) {
            LogUtil.e(books.get(i));
        }

        //删除第三个元素
        books.remove(2);
        LogUtil.e(books);

        //判断指定元素在List集合中位置：输出1，表明位于第二位
        LogUtil.e(books.indexOf(new String("疯狂Ajax讲义")));  //①
        //将第二个元素替换成新的字符串对象
        books.set(1, new String("LittleHann"));
        LogUtil.e(books);

        //将books集合的第二个元素（包括）
        //到第三个元素（不包括）截取成子集合
        LogUtil.e(books.subList(1, 2));//[)


    }


    public void EnumSet() {

        //创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
        EnumSet es1 = EnumSet.allOf(Season.class);
        //输出[SPRING,SUMMER,FALL,WINTER]
        LogUtil.e(es1 + "");

        //创建一个EnumSet空集合，指定其集合元素是Season类的枚举值。
        EnumSet es2 = EnumSet.noneOf(Season.class);
        //输出[]
        LogUtil.e(es2);
        //手动添加两个元素
        es2.add(Season.WINTER);
        es2.add(Season.SPRING);
        //输出[SPRING,WINTER]
        LogUtil.e(es2);

        //以指定枚举值创建EnumSet集合
        EnumSet es3 = EnumSet.of(Season.SUMMER, Season.WINTER);
        //输出[SUMMER,WINTER]
        LogUtil.e(es3);

        EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
        //输出[SUMMER,FALL,WINTER]
        LogUtil.e(es4);

        //新创建的EnumSet集合的元素和es4集合的元素有相同类型，
        //es5的集合元素 + es4集合元素 = Season枚举类的全部枚举值
        EnumSet es5 = EnumSet.complementOf(es4);
        //输出[SPRING]
        LogUtil.e(es5);


    }


    public void TreeSet() {
        TreeSet nums = new TreeSet();
        //向TreeSet中添加四个Integer对象
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);

        //输出集合元素，看到集合元素已经处于排序状态
        LogUtil.e(nums + "");//[-9, 2, 5, 10]

        //输出集合里的第一个元素
        LogUtil.e(nums.first() + "");//-9

        //输出集合里的最后一个元素
        LogUtil.e(nums.last() + "");//10

        //返回小于4的子集，不包含4
        LogUtil.e(nums.headSet(4) + "");//[-9, 2]

        //返回大于5的子集，如果Set中包含5，子集中还包含5
        LogUtil.e(nums.tailSet(5) + "");//[5, 10]

        //返回大于等于-3，小于4的子集。
        LogUtil.e(nums.subSet(-3, 4) + "");//[2]


        // 自定义排序的情况
        TreeSet ts = new TreeSet(new Comparator() {
            //根据M对象的age属性来决定大小
            public int compare(Object o1, Object o2) {
                M m1 = (M) o1;
                M m2 = (M) o2;
                return m1.age > m2.age ? -1
                        : m1.age < m2.age ? 1 : 0;
            }
        });
        ts.add(new M(5));
        ts.add(new M(-3));
        ts.add(new M(9));
        LogUtil.e(ts.toString());


    }


    class M {
        int age;

        public M(int age) {
            this.age = age;
        }

        public String toString() {
            return "M[age:" + age + "]";
        }
    }


    public void LinkedHashSet() {
        LinkedHashSet books = new LinkedHashSet();
        books.add("Java");
        books.add("LittleHann");
        LogUtil.e(books.toString());

        //删除 Java
        books.remove("Java");
        //重新添加 Java
        books.add("Java");
        LogUtil.e(books.toString());  // [LittleHann, Java]

    }


    public void hashset() {

        HashSet books = new HashSet();
        //分别向books集合中添加两个A对象，两个B对象，两个C对象
        books.add(new A1());
        books.add(new A1());

        books.add(new B1());
        books.add(new B1());

        books.add(new C1());
        books.add(new C1());
        LogUtil.e("" + books.toString());

    }


    //类A的equals方法总是返回true,但没有重写其hashCode()方法。不能保证当前对象是HashSet中的唯一对象
    class A1 {
        public boolean equals(Object obj) {
            return true;
        }
    }

    //类B的hashCode()方法总是返回1,但没有重写其equals()方法。不能保证当前对象是HashSet中的唯一对象
    class B1 {
        public int hashCode() {
            return 1;
        }
    }

    //类C的hashCode()方法总是返回2,且有重写其equals()方法
    class C1 {
        public int hashCode() {
            return 2;
        }

        public boolean equals(Object obj) {
            return true;
        }
    }


    public void paraseJson() {


        String s = "{'A':{'a':'1' ,'aa':'11'},'B':{'b':'2' ,'bb':'22'}}";
        JSONObject jsonObject1 = (JSONObject) JSON.parse(s);  // 这个json 必须是 com.alibaba.fastjson
        // .JSONObject 对象
        Iterator<String> iterator1 = jsonObject1.keySet().iterator();

        try {

            while (iterator1.hasNext()) {
                String next = iterator1.next();
                String value = jsonObject1.getString(next);

                LogUtil.e(next + " -> " + value + "\n");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * 遍历方面的使用方法
     */
    public void iterator() {

        //创建一个集合
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");


        //获取books集合对应的迭代器
        Iterator it = books.iterator();
        while (it.hasNext()) {
            //it.next()方法返回的数据类型是Object类型，
            //需要强制类型转换
            String book = (String) it.next();
            LogUtil.e(book);
            if (book.equals("疯狂Java讲义")) {
                //从集合中删除上一次next方法返回的元素 , 可以真正的删除的!!!!!!!!
                it.remove();
            }
            //对book变量赋值，不会改变集合元素本身
            book = "测试字符串";
        }
        LogUtil.e(books.toString());   //[疯狂Android讲义, 轻量级Java EE企业应用实战]  ,只有两条了删除了一条


    }


    /**
     * 遍历方面的使用方法_ foreach
     */
    public void foreach() {


        //创建一个集合
        Collection books = new HashSet();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));

        for (Object obj : books) {
            //此处的book变量也不是集合元素本身
            String book = (String) obj;
            LogUtil.e(book);
            if (book.equals("疯狂Android讲义")) {
                //下面代码会引发ConcurrentModificationException异常
                //books.remove(book);
            }
        }
        LogUtil.e(books.toString());


    }


    public void ListIterator() {
        String[] books = {
                "疯狂Java讲义",
                "轻量级Java EE企业应用实战"
        };
        List bookList = new ArrayList();
        for (int i = 0; i < books.length; i++) {
            bookList.add(books[i]);
        }

        // 可以正向/反向遍历
        ListIterator lit = bookList.listIterator();
        while (lit.hasNext()) {
            LogUtil.e(lit.next() + "");
        }


        LogUtil.e("=======下面开始反向迭代=======");
        while (lit.hasPrevious()) {     // iterator必须走到了后边才能反向遍历,否则只能是空
            LogUtil.e(lit.previous() + "");
        }

    }


    public void Hashtable() {


        Hashtable ht = new Hashtable();
        ht.put(new A(60000), "疯狂Java讲义");
        ht.put(new A(87563), "轻量级Java EE企业应用实战");
        ht.put(new A(1232), new B());
        LogUtil.e(ht.toString());

        //只要两个对象通过equals比较返回true，
        //Hashtable就认为它们是相等的value。
        //由于Hashtable中有一个B对象，
        //它与任何对象通过equals比较都相等，所以下面输出true。
        LogUtil.e("" + ht.containsValue("测试字符串"));  //①

        //只要两个A对象的count相等，它们通过equals比较返回true，且hashCode相等
        //Hashtable即认为它们是相同的key，所以下面输出true。
        LogUtil.e("" + ht.containsKey(new A(87563)));   //②

        //下面语句可以删除最后一个key-value对
        ht.remove(new A(1232));    //③

        //通过返回Hashtable的所有key组成的Set集合，
        //从而遍历Hashtable每个key-value对
        for (Object key : ht.keySet()) {
            LogUtil.e(key + "---->");
            LogUtil.e(ht.get(key) + "\n");
        }


    }


    class A {
        int count;

        public A(int count) {
            this.count = count;
        }

        //根据count的值来判断两个对象是否相等。
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj != null &&
                    obj.getClass() == A.class) {
                A a = (A) obj;
                return this.count == a.count;
            }
            return false;
        }

        //根据count来计算hashCode值。
        public int hashCode() {
            return this.count;
        }
    }

    class B {
        //重写equals()方法，B对象与任何对象通过equals()方法比较都相等
        public boolean equals(Object obj) {
            return true;
        }
    }


}
