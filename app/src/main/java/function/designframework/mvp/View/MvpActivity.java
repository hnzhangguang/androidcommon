package function.designframework.mvp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.yy.app.R;

import java.util.List;

import function.designframework.mvp.Adapter.StudentAdapter;
import function.designframework.mvp.Bean.Student;
import function.designframework.mvp.Presenter.StudentPresenter;

/**
 * 作者：
 * 视图类
 * 对视图抽象类的实现
 */
public class MvpActivity extends AppCompatActivity implements IStudentView, View.OnClickListener {

    private ListView lv;
    private StudentAdapter adapter;
    private Button bt_add, bt_delete;
    private StudentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        lv = (ListView) findViewById(R.id.lv);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);

        //中间者类
        presenter = new StudentPresenter(this);
        //查询学生
        presenter.queryStudent();
    }

    /**
     * 展示学生
     *
     * @param list
     */
    @Override
    public void showStudent(List<Student> list) {
        adapter = new StudentAdapter(this, list);
        lv.setAdapter(adapter);
    }


    /**
     * 刷新学生界面
     */
    @Override
    public void refreshStudent() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //添加学生
            case R.id.bt_add:
                presenter.addStudent();
                break;
            //删除学生
            case R.id.bt_delete:
                presenter.deleteStudent();
                break;
        }
    }
}
