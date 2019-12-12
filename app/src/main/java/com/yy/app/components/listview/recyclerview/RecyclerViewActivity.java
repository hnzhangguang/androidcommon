package com.yy.app.components.listview.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * RecyclerView 的使用
 * https://www.jianshu.com/p/b4bb52cdbeb7
 * <p>
 * 1, setHasFixedSize(true) 的作用?
 * 2, layoutManager 的分类: (LinearLayoutManager,GridLayoutManager,StaggeredGridLayoutManager)
 * 3, RecyclerView.Adapter: RecyclerView.ViewHolder, onCreateViewHolder(),onBindViewHolder(),
 * getItemCount()
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)
        // ，并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()
        // 。（其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged()去整体刷新一下）
        recyclerView.setHasFixedSize(true);

        // 1, LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);  // 设置方向


        // 2, LinearLayoutManager.HORIZONTAL
        //        layoutManager = new LinearLayoutManager(this);
        //        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        // 3, GridLayoutManager
        // layoutManager = new GridLayoutManager(this, 5);

        // 4, StaggeredGridLayoutManager(瀑布流布局)
        //        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(3,
        //        StaggeredGridLayoutManager.VERTICAL);
        //        recyclerView.setLayoutManager(layoutManager2);

        recyclerView.setLayoutManager(layoutManager);

        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);


    }

    private void initFruits() {

        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);

        }


    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }


}


/**
 * Adapter 适配器
 */
class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    // 性能优化Holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruitname);
        }

    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    //    onCreateViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fruit_item,
                parent, false);
        return new ViewHolder(view);
    }

    //    onBindViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    // getItemCount
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}

