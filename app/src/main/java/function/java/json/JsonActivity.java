package function.java.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_json2);
    }


    public void paraseJson() {


        String s = "{'A':{'a':'1' ,'aa':'11'},'B':{'b':'2' ,'bb':'22'}}";
        JSONObject jsonObject1 = (JSONObject) JSON.parse(s);
        Iterator<String> iterator1 = jsonObject1.keys();

        try {

            while (iterator1.hasNext()) {
                String next = iterator1.next();
                String value = jsonObject1.getString(next);

                Log.e("mmmm", next + " -> " + value + "\n");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
