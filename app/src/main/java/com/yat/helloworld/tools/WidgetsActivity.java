package com.yat.helloworld.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

public class WidgetsActivity extends Fragment {

    ListView listOfGroupNames;
    String[] groupNames = {"Kareem", "Soliman", "Gemy", "Amira", "Nourhan", "Saher", "Fatma", "Abdelrahman Ahmed", "Abdelrahman Hassan"
            , "Alaa mohamed", "Eid ibrahim", "Mahmoud Mohamed", " Maureen Maged", "Merna samy", "Tasneem", "Youssef", "Eslam Medhat"};

    String[] groubJobDescription = {"Android Developer", "IOS Developer", "Java Developer", "PHP Developer",
            "Node.JS Developer", "Flutter Developer", "Kotlin Developer", "Network Developer",
            "Xamrin Developer", "XML Developer", "Go Developer", "Perl Developer",
            "Android Developer", "IOS Developer", "Web Developer", "Security Developer", "Front-end Developer",};

    Integer[] imgid = {
            R.drawable.multiapplogo
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_widgets, container, false);


        listOfGroupNames = v.findViewById(R.id.lv_groupName);


        MyListAdapter adapter = new MyListAdapter(getActivity(), groupNames, groubJobDescription, imgid);
        listOfGroupNames = v.findViewById(R.id.lv_groupName);
        listOfGroupNames.setAdapter(adapter);

//        ArrayAdapter<String> adabterNames = new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1,
//                android.R.id.text1,groupNames);
//
//        listOfGroupNames.setAdapter(adabterNames);
//
        listOfGroupNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(getContext(), groupNames[position], Toast.LENGTH_SHORT).show();

            }
        });


        return v;

    }


}