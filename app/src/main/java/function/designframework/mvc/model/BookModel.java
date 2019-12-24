package function.designframework.mvc.model;

import com.yy.app.R;

import java.util.ArrayList;
import java.util.List;

import function.designframework.mvc.bean.Book;

/**
 * 作者：
 * 模型层
 * 对数据库的操作
 */
public class BookModel {

    private static List<Book> list = new ArrayList<>();

    /**
     * 模拟本地数据库
     */
    static {
        list.add(new Book("Java从入门到精通", R.drawable.ic_action_about));
        list.add(new Book("Android从入门到精通", R.drawable.ic_action_about));
        list.add(new Book("Java从入门到精通", R.drawable.ic_action_about));
        list.add(new Book("Android从入门到精通", R.drawable.ic_action_about));
    }

    /**
     * 添加书本
     *
     * @param name
     * @param image
     */
    public void addBook(String name, int image) {
        list.add(new Book(name, image));
    }

    /**
     * 删除书本
     */
    public void deleteBook() {
        list.remove(list.size() - 1);
    }

    /**
     * 查询数据库所有书本
     *
     * @return
     */
    public List<Book> query() {
        return list;
    }

}