package function.java.regularexpression;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegularExpressionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_regular_expression);
    }


    /**
     * 判断是否是一个数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }


    public static boolean isNumeric2(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
