package function.designframework.mvp.View;


import java.util.List;

import function.designframework.mvp.Bean.Student;

/**
 * 作者：
 * 视图类
 * 对视图方法的抽象
 */
public interface IStudentView {
    /**
     * 展示学生
     *
     * @param list
     */
    void showStudent(List<Student> list);

    /**
     * 刷新学生
     */
    void refreshStudent();
}
