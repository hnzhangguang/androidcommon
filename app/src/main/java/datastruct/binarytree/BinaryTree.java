package datastruct.binarytree;


import android.util.Log;

/**
 * 创建二叉树, 前序, 中序, 后序 遍历
 */
public class BinaryTree {

    private static String[] array = {"A", "B", "D", "H", "", "", "I", "", "", "E", "", "J", "", "",
            "C", "F", "", "K", "", "", "G", "", ""};
    private static int arrayIndex = 0;

    //  创建一棵二叉树，约定用户遵照前序遍历的方式输入数据
    //  不使用迭代是因为迭代必须要知道这棵树有多深，
    //  递归只需要输入就可以自行决定深度
    //  type:结点类型 0 根节点 1左孩子 2右孩子
    public TreeNode createBinaryTree(int type, String parentData) {
        switch (type) {
            case 0:
                logg("根节点:");
                break;
            case 1:
                logg(parentData + "的左孩子:");
                break;
            case 2:
                logg(parentData + "的右孩子:");
                break;
        }

        //      可以使用手动输入也可以放到数组里
        //      Scanner sc = new Scanner(System.in);
        //      String data = sc.nextLine();

        String data = "";
        if (arrayIndex < array.length) {
            data = array[arrayIndex];
            logg(data);

            arrayIndex++;
        } else {
            System.out.println();
        }

        TreeNode node = null;

        //      data为空表示没有这个孩子
        if (data == null || data.equals("")) {
            return node;
        } else {
            node = new TreeNode(data);
            node.setLchild(createBinaryTree(1, node.getData()));
            node.setRchild(createBinaryTree(2, node.getData()));

            return node;
        }

    }


    //  前序遍历
    public static void preOrderTraverse(TreeNode node) {
        if (node != null) {
            //          根，左，右
            logg(node.getData());
            preOrderTraverse(node.getLchild());
            preOrderTraverse(node.getRchild());
        }
    }

    //  中序遍历
    public static void inOrderTraverse(TreeNode node) {
        if (node != null) {
            //          左，根，右
            inOrderTraverse(node.getLchild());
            logg(node.getData());
            inOrderTraverse(node.getRchild());
        }
    }

    //  后序遍历
    public static void postOrderTraverse(TreeNode node) {
        if (node != null) {
            //          左，右，根
            postOrderTraverse(node.getLchild());
            postOrderTraverse(node.getRchild());
            logg(node.getData());
        }
    }

    //


    public void main(String[] args) {

        arrayIndex = 0;
        // 创建一颗二叉树
        TreeNode rootNode = createBinaryTree(0, "");


        System.out.println();
        logg("前序遍历：");
        preOrderTraverse(rootNode);

        System.out.println();
        logg("中序遍历：");
        inOrderTraverse(rootNode);

        System.out.println();
        logg("后序遍历：");
        postOrderTraverse(rootNode);
    }

    public static void logg(Object o) {
        Log.e("mmmm", o + "");
    }


}


/**
 * 二叉树结点
 *
 * @author cmdsm
 */
class TreeNode {
    private String data;

    private TreeNode lchild;
    private TreeNode rchild;


    public TreeNode() {
        super();
    }

    public TreeNode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TreeNode getLchild() {
        return lchild;
    }

    public void setLchild(TreeNode lchild) {
        this.lchild = lchild;
    }

    public TreeNode getRchild() {
        return rchild;
    }

    public void setRchild(TreeNode rchild) {
        this.rchild = rchild;
    }

    @Override
    public String toString() {
        return "TreeNode [data=" + data + ", lchild=" + lchild + ", rchild=" + rchild + "]";
    }


}  