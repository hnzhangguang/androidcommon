package datastruct.binarytree;


import android.util.Log;

/**
 * 线索二叉树
 */
public class ThreadedBinaryTree {

    private static String[] array = {"A", "B", "C", "", "", "D", "", "", "E", "", "F", "", ""};
    private static int arrayIndex = 0;

    /**
     * 全局node，始终指向刚刚访问过的结点
     */
    private static ThreadedBinaryNode preNode;


    public static void logg(Object o) {
        Log.e("mmmm", o + "");
    }


    /**
     * 1.参考创建二叉树,前序遍历输入
     */
    public ThreadedBinaryNode createThreadedBinaryTree() {
        String data = "";
        if (arrayIndex < array.length) {
            data = array[arrayIndex];

            arrayIndex++;
        }

        ThreadedBinaryNode node = null;

        //      data为空表示没有这个孩子
        if (data == null || data.equals("")) {
            return node;
        } else {
            node = new ThreadedBinaryNode(data);
            logg(data + "节点");

            ThreadedBinaryNode Lchild = createThreadedBinaryTree();
            ThreadedBinaryNode Rchild = createThreadedBinaryTree();

            if (null != Lchild) {
                logg(data + "的左孩子是:" + Lchild.getData());
            }

            if (null != Rchild) {
                logg(data + "的右孩子是:" + Rchild.getData());
            }


            node.setLchild(Lchild);
            node.setRchild(Rchild);

            node.setLtag(PointerTag.LINK);
            node.setRtag(PointerTag.LINK);

            return node;
        }
    }

    /**
     * 2.创建头结点，左孩子指向根节点
     *
     * @param rootNode
     */
    public static ThreadedBinaryNode createHeadNode(ThreadedBinaryNode rootNode) {
        ThreadedBinaryNode headNode = new ThreadedBinaryNode();

        headNode.setLtag(PointerTag.LINK);
        headNode.setRtag(PointerTag.THREAD);

        //      右孩子先指向自己，如果根节点不为null，指向中序遍历的最后一个结点，为null不用变
        headNode.setRchild(headNode);

        if (rootNode != null) {
            //          根结点不为null，头结点的左孩子指向根结点
            headNode.setLchild(rootNode);

            preNode = headNode;

            //          开始中序遍历根结点
            inOrderTraverse(rootNode);

            //          中序遍历的最后一个结点的后继指向头结点
            preNode.setRtag(PointerTag.THREAD);
            preNode.setRchild(headNode);

            //          头结点的右孩子指向最后一个结点
            headNode.setRchild(preNode);

        } else {
            //          根节点为null 左孩子指向自己
            headNode.setLchild(headNode);
        }

        return headNode;
    }

    /**
     * 3.中序遍历线索化
     */
    public static void inOrderTraverse(ThreadedBinaryNode node) {
        if (node != null) {
            //          递归左孩子线索化
            inOrderTraverse(node.getLchild());

            //          结点处理
            if (null == node.getLchild()) {
                //              如果左孩子为空，设置tag为线索 THREAD，并把lchild指向刚刚访问的结点
                node.setLtag(PointerTag.THREAD);
                node.setLchild(preNode);
            }

            if (null == preNode.getRchild()) {
                //              如果preNode的右孩子为空，设置tag为线索THREAD
                preNode.setRtag(PointerTag.THREAD);
                preNode.setRchild(node);
            }

            //          此处和前后两个递归的顺序不能改变，和结点处理同属一个级别
            preNode = node;
            //          System.out.print(node.getData());

            //          递归右孩子线索化
            inOrderTraverse(node.getRchild());

        }
    }

    /**
     * 4.中序遍历 非递归方式
     *
     * @param headNode
     */
    public static void inOrderTraverseNotRecursion(ThreadedBinaryNode headNode) {
        ThreadedBinaryNode node = headNode.getLchild();

        while (headNode != node) {

            //          最左
            while (node.getLtag() == PointerTag.LINK) {
                node = node.getLchild();
            }

            System.out.print(node.getData());

            //          根
            while (node.getRtag() == PointerTag.THREAD && node.getRchild() != headNode) {
                node = node.getRchild();

                System.out.print(node.getData());
            }

            //          右，不能打印是因为该子树下可能还存在最左
            node = node.getRchild();
        }
    }


    public void main(String[] args) {

        arrayIndex = 0;
        //      创建二叉树，约定前序输入
        ThreadedBinaryNode rootNode = createThreadedBinaryTree();
        //      创建头结点，并中序遍历线索化
        ThreadedBinaryNode headNode = createHeadNode(rootNode);
        //      中序遍历 非递归方式输出
        inOrderTraverseNotRecursion(headNode);

    }

}

class ThreadedBinaryNode {
    private String data;

    private ThreadedBinaryNode lchild;
    private ThreadedBinaryNode rchild;

    private PointerTag ltag;
    private PointerTag rtag;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ThreadedBinaryNode getLchild() {
        return lchild;
    }

    public void setLchild(ThreadedBinaryNode lchild) {
        this.lchild = lchild;
    }

    public ThreadedBinaryNode getRchild() {
        return rchild;
    }

    public void setRchild(ThreadedBinaryNode rchild) {
        this.rchild = rchild;
    }

    public PointerTag getLtag() {
        return ltag;
    }

    public void setLtag(PointerTag ltag) {
        this.ltag = ltag;
    }

    public PointerTag getRtag() {
        return rtag;
    }

    public void setRtag(PointerTag rtag) {
        this.rtag = rtag;
    }


    public ThreadedBinaryNode(String data) {
        super();
        this.data = data;
    }

    public ThreadedBinaryNode() {
        super();
    }

    @Override
    public String toString() {
        return "ThreadedBinaryNode [data=" + data + ", ltag=" + ltag
                + ", rtag=" + rtag + "]";
    }


}

/**
 * LINK :表示指向左右孩子的指针
 * THREAD：表示指向前驱后继的线索
 *
 * @author cmdsm
 */
enum PointerTag {
    LINK, THREAD
}