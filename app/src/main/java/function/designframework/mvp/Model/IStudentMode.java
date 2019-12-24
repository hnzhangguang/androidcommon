package function.designframework.mvp.Model;


import java.util.List;

import function.designframework.mvp.Bean.Student;

/**
 * 作者：
 * 模型抽象类
 * 对模型层的抽象
 */
public interface IStudentMode {

    /**
     * 查询所有学生
     *
     * @param listener
     */
    void query(onQueryListener listener);

    /**
     * 添加学生
     *
     * @param listener
     */
    void addStudent(onAddStudentListener listener);

    /**
     * 删除学生
     *
     * @param listener
     */
    void deleteStudent(onDeleteStudentListener listener);

    /**
     * 查询学生回调
     */
    interface onQueryListener {
        void onComplete(List<Student> students);
    }

    /**
     * 添加学生回调
     */
    interface onAddStudentListener {
        void onComplete();
    }

    /**
     * 删除学生回调
     */
    interface onDeleteStudentListener {
        void onComplete();
    }
}
