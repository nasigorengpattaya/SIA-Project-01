package sg.edu.rp.c346.sia_project_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Attr;

import java.util.ArrayList;

/**
 * Created by 15017420 on 26/10/2017.
 */

public class AttractionsAdapter extends ArrayAdapter<Attraction> {

    private ArrayList<Attraction> attractions;
    private Context context;
    private TextView tvAttraction;
    private ImageView iv;

    public AttractionsAdapter(Context context, int resource, ArrayList<Attraction> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        attractions = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvAttraction = (TextView) rowView.findViewById(R.id.tvAttraction);
        // Get the ImageView object
        iv = (ImageView) rowView.findViewById(R.id.iv);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Attraction attraction = attractions.get(position);
        // Set the TextView to show the food
        tvAttraction.setText(attraction.getName());
        // Set the image to star or nostar accordingly
        if(attraction.getName() == "Marina Bay") {
            iv.setImageResource(R.drawable.mbs);
        }
        else if (attraction.getName() == "Orchard Road") {
            iv.setImageResource(R.drawable.orchard);
        }
        else if (attraction.getName() == "Little India") {
            iv.setImageResource(R.drawable.littleindia);
        }
        else if (attraction.getName() == "Chinatown") {
            iv.setImageResource(R.drawable.chinatown);
        }
        else if (attraction.getName() == "Clarke Quay") {
            iv.setImageResource(R.drawable.clarkequay);
        }
        else if (attraction.getName() == "Arab Street") {
            iv.setImageResource(R.drawable.arabstreet);
        }
        else if (attraction.getName() == "Museums and Architectural Appreciation") {
            iv.setImageResource(R.drawable.ngs);
        }
        // Return the nicely done up View to the ListView
        return rowView;
    }
}
