package datastruct.arithmetic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yy.app.R;

import java.util.Scanner;

public class ArithmeticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic);

        findViewById(R.id.兔子总数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                兔子总数();
            }
        });
        findViewById(R.id.素数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                素数();
            }
        });
        findViewById(R.id.水仙花数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                水仙花数();
            }
        });
        findViewById(R.id.分解质因数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                分解质因数();
            }
        });
        findViewById(R.id.最大公约数和最小公倍数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                最大公约数和最小公倍数();
            }
        });
        findViewById(R.id.几个数相加).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                几个数相加();
            }
        });
        findViewById(R.id.完数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                完数();
            }
        });
        findViewById(R.id.一球从100米高度自由落下).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                一球从100米高度自由落下();
            }
        });
        findViewById(R.id.一个数字中无重复数字的三位数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                一个数字中无重复数字的三位数();
            }
        });
        findViewById(R.id.完全平方数).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                完全平方数();
            }
        });
        findViewById(R.id.猴子吃桃问题).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                猴子吃桃问题();
            }
        });
        findViewById(R.id.乒乓球队进行比赛).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                乒乓球队进行比赛();
            }
        });
        findViewById(R.id.打印图案菱形).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                打印图案菱形();
            }
        });
        findViewById(R.id.前20项之和).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                前20项之和();
            }
        });
        findViewById(R.id.阶乘的和).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                阶乘的和();
            }
        });
        findViewById(R.id.利用递归方法求5的阶乘).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                利用递归方法求5的阶乘();
            }
        });
        findViewById(R.id.打印出杨辉三角形).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                打印出杨辉三角形();
            }
        });
        findViewById(R.id.有n个人围成一圈顺序排号).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                有n个人围成一圈顺序排号();
            }
        });


    }

    /**
     * 题目：
     * 编写一个函数，输入n为偶数时，调用函数求1/2+1/4+...+1/n,
     * 当输入n为奇数时，调用函数1/1+1/3+...+1/n(利用指针函数)
     */
    public void test() {

        sum(33);

    }


    public static double sum(int n) {
        double sum = 0;
        if (n % 2 == 0) {
            for (int i = 2; i <= n; i += 2) {
                sum += (double) 1 / i;
            }
        } else {
            for (int i = 1; i <= n; i += 2) {
                sum += (double) 1 / i;
            }
        }
        return sum;
    }


    /**
     * 题目：有n个人围成一圈，顺序排号。
     * 从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
     */
    public void 有n个人围成一圈顺序排号() {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        boolean[] arr = new boolean[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = true;
        }
        int leftCount = n;
        int index = 0;
        int countNum = 0;
        while (leftCount > 1) {
            if (arr[index] == true) {
                countNum++;
                if (countNum == 3) {
                    arr[index] = false;
                    leftCount--;
                    countNum = 0;
                }
            }
            index++;
            if (index == n) {
                index = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == true) {
                System.out.println(i + 1);
            }
        }


    }


    /**
     * 题目：打印出杨辉三角形（要求打印出10行如下图）1 11 12 1
     * 1 33 1 1 4 64 11 5 1010 5 1
     */
    public void 打印出杨辉三角形() {

        int[][] a = new int[10][10];
        for (int i = 0; i < 10; i++) {
            a[i][i] = 1;
            a[i][0] = 1;
        }
        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < i; j++) {
                a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 2 * (10 - i) - 1; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();


        }
    }

    /**
     * 题目：利用递归方法求5!。
     */
    public void 利用递归方法求5的阶乘() {
        System.out.println(fac(5));
    }

    public static int fac(int i) {
        if (i == 1) return 1;
        else {
            return i * fac(i - 1);
        }
    }

    /**
     * 题目：求1+2!+3!+...+20!的和
     */
    public void 阶乘的和() {


        long sum = 0, ver = 1;
        for (int i = 1; i <= 20; i++) {
            ver = ver * i;
            sum += ver;
        }
        System.out.println(sum);


    }

    /**
     * 题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前20项之和。
     */
    public void 前20项之和() {

        double sum = 0, ver = 2;
        for (int i = 1; i <= 10; i++) {
            sum += ver / i;
            ver += i;
        }
        System.out.println(sum);


    }

    /**
     * 题目：打印出如下图案（菱形）
     * <p>
     * **
     * ****
     * ******
     * ****
     * **
     */
    public void 打印图案菱形() {

        int H = 7, W = 7;//高和宽必须是相等的奇数
        for (int i = 0; i < (H + 1) / 2; i++) {
            for (int j = 0; j < W / 2 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k < (i + 1) * 2; k++) {
                System.out.print('*');
            }
            System.out.println();
        }
        for (int i = 1; i <= H / 2; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= W - 2 * i; k++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }


    /**
     * 题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。
     * 已抽签决定比赛名单。有人向队员打听比赛的名单。
     * a说他不和x比，c说他不和x,z比，请编程序找出三队赛手的名单。
     */

    public void 乒乓球队进行比赛() {


        for (char i = 'x'; i <= 'z'; i++) {
            for (char j = 'x'; j <= 'z'; j++) {
                if (i != j) {
                    for (char k = 'x'; k <= 'z'; k++) {
                        if (i != k && j != k) {
                            if (i != 'x' && j != 'x' && j != 'z') {
                                System.out.println("a:" + i + "\nb:" + j + "\nc:" + k);
                            }
                        }
                    }
                }
            }
        }


    }


    /**
     * 题目：猴子吃桃问题：
     * 猴子第一天摘下若干个桃子，当即吃了一半，还不瘾，又多吃了一个
     * 第二天早上又将剩下的桃子吃掉一半，又多吃了一个。以后每天早上都吃了前一天剩下  的一半零一个。
     * 到第10天早上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。
     */
    public void 猴子吃桃问题() {

        int x = 1;
        for (int i = 10; i > 1; i--) {
            x = (x + 1) * 2;
        }
        System.out.println(x);


    }

    /**
     * 题目：一个整数，它加上100后是一个完全平方数，
     * 再加上168又是一个完全平方数，请问该数是多少？
     */
    public void 完全平方数() {

        for (int i = -100; i < 10000; i++) {
            if (Math.sqrt(i + 100) % 1 == 0 && Math.sqrt(i + 268) % 1 == 0) {
                System.out.println(i);
            }
        }


    }

    /**
     * 题目：有1、2、3、4四个数字，能组成多少个互不相同且一个数字中无重复数字的三位数？并把他们都输入。
     */
    public void 一个数字中无重复数字的三位数() {

        int count = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k < 5; k++) {
                    if (i != j && j != k && i != k) {
                        count++;
                        System.out.println(i * 100 + j * 10 + k);
                    }
                }
            }
        }
        System.out.println(count);


    }


    /**
     * 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；
     * 再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高？
     */
    public void 一球从100米高度自由落下() {


        double h = 100;
        double s = 100;
        for (int i = 1; i <= 10; i++) {
            h = h / 2;
            s = s + 2 * h;
        }
        System.out.println(s);
        System.out.println(h);


    }

    /**
     * 题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。
     * 例如6=1＋2＋3.编程
     * 找出1000以内的所有完数。
     */
    public void 完数() {
        for (int i = 1; i <= 1000; i++) {
            int t = 0;
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    t += j;
                }
            }
            if (t == i) {
                System.out.println(i);
            }
        }

    }

    /**
     * 题目：求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。
     * 例如2+22+222+2222+22222(此时共有5个数相加)，几个数相加有键盘控制。
     */
    public void 几个数相加() {

        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int n = input.nextInt();
        int sum = 0, b = 0;
        for (int i = 0; i < n; i++) {
            b += a;
            sum += b;
            a = a * 10;
        }
        System.out.println(sum);


    }


    /**
     * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
     * 假如兔子都不死，问每个月的兔子总数为多少？
     * //这是一个菲波拉契数列问题
     */
    public void 兔子总数() {

        int f1 = 1, f2 = 1, f;
        int M = 30;
        System.out.println(1);
        System.out.println(2);
        for (int i = 3; i < M; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println(f2);
        }

    }


    /**
     * 题目：判断101-200之间有多少个素数，并输出所有素数。
     * 程序分析：判断素数的方法：
     * 用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。
     */
    public void 素数() {
        int count = 0;
        for (int i = 101; i < 200; i += 2) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println(count);
    }


    /**
     * 题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。
     * 例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     */
    public void 水仙花数() {
        int a, b, c;
        for (int i = 101; i < 1000; i++) {
            a = i % 10;
            b = i / 10 % 10;
            c = i / 100;
            if (a * a * a + b * b * b + c * c * c == i)
                System.out.println(i);
        }
    }


    /**
     * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
     * 程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：
     * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
     * (2)如果n <> k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数你n,重复执行第一步。
     * (3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
     */
    public void 分解质因数() {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = 2;
        while (n >= k) {
            if (n == k) {
                System.out.println(k);
                break;
            } else if (n % k == 0) {
                System.out.println(k);
                n = n / k;
            } else {
                k++;
            }
        }

    }


    public void 最大公约数和最小公倍数() {

        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        int i = gongyinshu(a, b);
        System.out.println("最小公因数" + i);
        System.out.println("最大公倍数" + a * b / i);

    }


    public int gongyinshu(int a, int b) {
        if (a < b) {
            int t = b;
            b = a;
            a = t;
        }
        while (b != 0) {
            if (a == b) {
                return a;
            }

            int x = b;
            b = a % b;
            a = x;
        }
        return a;


    }


}
