package function.java.set_map_list;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.TextView;

import com.app.logger.LogUtil;
import com.yy.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SetMapListActivity extends BaseActivity {


    Button btn_test;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_set_map_list);

        initPermission();


    }

    @Override
    public void initContentViewXml() {

    }


    public void test() {


    }


    private void initPermission() {
        String[] permissions = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.DISABLE_KEYGUARD,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS

        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }


    protected void getAllContacts() {

        ContentResolver cr = SetMapListActivity.this.getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null,
                ContactsContract.Contacts._ID + " DESC");
        while (cursor.moveToNext()) {
            LogUtil.e(cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)) + " , " + cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
        }
        cursor.close();
    }


    public void testTreeMap() {

        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        tm.put(0, "zero");
        tm.put(1, "one");
        tm.put(3, "three");
        tm.put(2, "two");
        // LogUtil.e(tm);// {0=zero, 1=one, 2=two, 3=three}
        // LogUtil.e(tm.keySet());// [0, 1, 2, 3]
        // LogUtil.e(tm.values());// [zero, one, two, three]

        Set<Integer> keys = tm.keySet();// set本身就是一个集合
        for (Integer key : keys) {
            LogUtil.e("学号：" + key + ",姓名：" + tm.get(key) + "\t");
        }


        LogUtil.e("________________________________________________");


        TreeMap<Teacher, String> treemap = new TreeMap<Teacher, String>();
        treemap.put(new Teacher(3), "疯狂java讲义");
        treemap.put(new Teacher(-5), "andior教程");
        treemap.put(new Teacher(9), "平凡的世界");
        LogUtil.e("完整的treemap:" + treemap);
        LogUtil.e("map排序中第一个key-value键值对:" + treemap.firstEntry());
        LogUtil.e("map中排序的第一个key值:" + treemap.firstKey());
        LogUtil.e("map中在比new（2）小的子map:" + treemap.headMap(new Teacher(2)));
        LogUtil.e("map中比new（12）小的最大的键值对:" + treemap.lowerEntry(new Teacher(12)));


        LogUtil.e("***********************************************************");

        Map<String, String> map = new TreeMap<>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o2.compareTo(o1); //用正负表示大小值   // 倒序
            }
        });
        //以上4行可用下面一行lambda表达式代替
        //Map<String,String> map1 = new TreeMap<>((o1,o2)->o2.compareTo(o1));
        map.put("zdef", "rfgh");
        map.put("asrg", "zfg");
        map.put("rgd", "dfgh");
        map.put("cbf", "gddf");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            LogUtil.e("key:" + entry.getKey() + ",:value:" + entry.getValue());
        }


        //将Map转为List
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        }); //重新排序
        for (Map.Entry<String, String> entry : list) {
            LogUtil.e("key:" + entry.getKey() + ",:value:" + entry.getValue());
        }


    }


}
