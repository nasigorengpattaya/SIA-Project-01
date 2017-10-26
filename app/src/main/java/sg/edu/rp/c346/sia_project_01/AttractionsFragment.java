package sg.edu.rp.c346.sia_project_01;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionsFragment extends Fragment {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Attraction> attractions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attractions, container, false);

        lv = (ListView)view.findViewById(R.id.listAttractions);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Attraction selectedItem = attractions.get(position);
                String attraction = selectedItem.getName();
                String address = selectedItem.getAddress();
                Intent i = new Intent(getActivity(), QrCodeActivity.class);
                i.putExtra("attraction", attraction);
                i.putExtra("address", address);
                startActivity(i);

            }
        });

        attractions = new ArrayList<Attraction>();
        attractions.add(new Attraction("Marina Bay", "http://www.siahopon.com/walkabout/#marinabay"));
        attractions.add(new Attraction("Orchard Road", "http://www.siahopon.com/walkabout/#orchardroad"));
        attractions.add(new Attraction("Little India", "http://www.siahopon.com/walkabout/#littleindia"));
        attractions.add(new Attraction("Chinatown", "http://www.siahopon.com/walkabout/#chinatown"));
        attractions.add(new Attraction("Clarke Quay", "http://www.siahopon.com/walkabout/#boatquayclarkequay"));
        attractions.add(new Attraction("Arab Street", "http://www.siahopon.com/walkabout/#arabstreetkampongglam"));
        attractions.add(new Attraction("Museums and Architectural Appreciation", "http://www.siahopon.com/walkabout/#museumsarchitecturalappreciation"));

        aa = new AttractionsAdapter(getActivity(), R.layout.row, attractions);

        lv.setAdapter(aa);

        return view;
    }

}
