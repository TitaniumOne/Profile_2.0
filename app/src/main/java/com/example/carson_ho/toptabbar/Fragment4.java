package com.example.carson_ho.toptabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment4 extends Fragment {

    View view;
    MainActivity mainActivity;

    private Button btnCountManger;

    private List<Fruit> fruitList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment4,container,false);
        mainActivity = (MainActivity)getActivity();

        initFruits();
        FruitAdapter adapter = new FruitAdapter(mainActivity,R.layout.fruit_item,fruitList);

        ListView listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(mainActivity,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(toolbar);

        //账户管理按钮
        btnCountManger = (Button)view.findViewById(R.id.btn_countManager);
        btnCountManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity,CountManger.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void initFruits()
    {
        Fruit fruit1 = new Fruit("兑换积分",R.mipmap.money,R.mipmap.arrow);
        fruitList.add(fruit1);
        Fruit fruit2 = new Fruit("信用值历史记录",R.mipmap.history,R.mipmap.arrow);
        fruitList.add(fruit2);
        Fruit fruit3 = new Fruit("系统设置",R.mipmap.gear,R.mipmap.arrow);
        fruitList.add(fruit3);

    }
}
