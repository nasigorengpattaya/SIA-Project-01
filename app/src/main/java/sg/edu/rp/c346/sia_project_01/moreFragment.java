package sg.edu.rp.c346.sia_project_01;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class moreFragment extends Fragment {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Item> items;

    public moreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        lv = (ListView)view.findViewById(R.id.lvMenu);

        items = new ArrayList<Item>();
        items.add(new Item("Translate"));
        items.add(new Item("Find a Local Experience"));
        items.add(new Item("Feedback Form"));
        items.add(new Item("Contact Us"));
        items.add(new Item("Voucher"));

        aa = new ItemsAdapter(getActivity(), R.layout.row, items);

        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.airbnb.com/s/Singapore/experiences?allow_override%5B%5D=&place_id=ChIJyY4rtGcX2jERIKTarqz3AAQ")));
                }
            }
        });

        return view;
    }

}
