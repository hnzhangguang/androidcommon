package com.yy.app.components.listview.recyclerview;

import android.os.Bundle;
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


/**
 * RecyclerView 的使用
 * https://www.jianshu.com/p/b4bb52cdbeb7
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
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
//        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager2);

        recyclerView.setLayoutManager(layoutManager);

        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);


    }

    private void initFruits() {

        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.add);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.add);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.add);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.add);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.add);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.add);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.add);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.add);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.add);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.add);
            fruitList.add(mango);

        }


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
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fruit_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
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

