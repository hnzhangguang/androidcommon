package com.yy.app.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.logger.LogUtil;
import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * public abstract class AsyncTask<Params, Progress, Result>{}
 * #Params：开始异步任务执行时传入的参数类型；
 * #Progress：异步任务执行过程中，返回下载进度值的类型；
 * #Result：异步任务执行完成后，返回的结果类型；
 * #如果AsyncTask确定不需要传递具体参数，那么这三个泛型参数可以用Void来代替。
 * <p>
 * <p>
 * onPreExecute() :
 * ->在主线程中，用于进行界面初始化操作
 * <p>
 * doInBackground(Params... params)
 * -> 此方法在WorkerThread执行，用来处理耗时任务,此方法内不能更新UI
 * <p>
 * onProgressUpdate(Progress... values)
 * -> 当在后台任务中调用了publishProgress(Progress…)方法后，这个方法就很快会被调用，
 * #方法中携带的参数就是在后台任务中传递过来的。 => 在这个方法中可以对UI进行操作，在主线程中进行
 * <p>
 * onPostExecute(Result result)
 * #当doInBackground(Params…)执行完毕并通过return语句进行返回时，这个方法就很快会被调用。
 * #返回的数据来进行一些UI操作，在主线程中进行
 * <p>
 * 上面几个方法的调用顺序：
 * onPreExecute() –> doInBackground() –> publishProgress() –> onProgressUpdate() –> onPostExecute()
 * <p>
 * <p>
 * onCancelled(Result result)
 * -> 在主线程中执行，当异步任务取消时，onCancelled()会被调用，这个时候onPostExecute()则不会被调用
 * <p>
 * 使用注意事项：
 * 1,异步任务的实例必须在UI线程中创建，即AsyncTask对象必须在UI线程中创建。
 * ->execute(Params… params)方法必须在UI线程中调用
 * 2,不能在doInBackground(Params… params)中更改UI组件的信息
 * 3,一个任务实例只能执行一次，如果执行第二次将会抛出异常
 * 4,内存泄漏，静态内部类持有外部类的引用。如果AsyncTask被声明为Activity的非静态的内部类，那么AsyncTask会保留一个对创建了AsyncTask的Activity
 * 的引用。如果Activity已经被销毁，AsyncTask的后台线程还在执行，它将继续在内存里保留这个引用，导致Activity无法被回收，引起内存泄露。
 * 5,生命周期：activity distort 进行回收cancel（）
 */
public class AsyncTaskActivity extends BaseActivity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Button btn_asyncTask;
    private static final String url = "http://photocdn.sohu.com/20110927/Img320705637.jpg";

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_async_task);
    }

    @Override
    public void initView() {
        super.initView();


        btn_asyncTask = findViewById(R.id.btn_asyncTask);
        mImageView = findViewById(R.id.imagview);
        mProgressBar = findViewById(R.id.progressbar);
    }

    @Override
    public void initListener() {
        super.initListener();

        btn_asyncTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 设置传递进去的参数
                        new MyAsyncTask().execute(url);
                    }
                });
    }

    // <url类型 ，进度值类型，返回值类型>
    public class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        public MyAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LogUtil.e("MyAsyncTask->onPreExecute");
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        // 可变参数 只取出一个参数就是第0位
        protected Bitmap doInBackground(String... params) {
            LogUtil.e("MyAsyncTask->doInBackground");
            String url = params[0]; // 获取传递进来的参数
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                // 通过decodeStream解析输入流转化为Bitmap
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            // 将bitmap作为返回值返回
            return bitmap;
        }

        // 更新 用publishProgress方法 可以执行此方法
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            LogUtil.e("MyAsyncTask->onPostExecute");
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }
    }
}
