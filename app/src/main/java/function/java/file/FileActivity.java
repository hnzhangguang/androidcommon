package function.java.file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


/**
 * 文件基本操作类
 */
public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_file);
    }


    /**
     * 普通的拷贝文件
     *
     * @param source
     * @param dest
     */
    public static void copyFileByStream(File source, File dest) {


        try {

            InputStream is = new FileInputStream(source);
            OutputStream os = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }


        } catch (Exception e) {


        }


    }


    /**
     * nio 类提供的transferTo 和 transferFrom 方法实现文件拷贝
     *
     * @param source
     * @param dest
     */
    public static void copyFileByChannel(File source, File dest) {


        try {

            FileChannel sourceChannel = new FileInputStream(source).getChannel();
            FileChannel targetChannel = new FileOutputStream(dest).getChannel();
            for (long count = sourceChannel.size(); count > 0; ) {


                long transferred = sourceChannel.transferTo(sourceChannel.position(), count,
                        targetChannel);

                sourceChannel.position(sourceChannel.position() + transferred);

                count -= transferred;


            }


        } catch (Exception e) {

        }


    }


}
