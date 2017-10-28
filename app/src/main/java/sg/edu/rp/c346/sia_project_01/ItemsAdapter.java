package sg.edu.rp.c346.sia_project_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017420 on 28/10/2017.
 */

public class ItemsAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> items;
    private Context context;
    private TextView tvItems;
    private ImageView iv;

    public ItemsAdapter(Context context, int resource, ArrayList<Item> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        items = objects;
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
        View rowView = inflater.inflate(R.layout.rowitem, parent, false);

        // Get the ImageView object
        iv = (ImageView) rowView.findViewById(R.id.ivItem);
        tvItems = (TextView)rowView.findViewById(R.id.tvItem);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Item item = items.get(position);
        // Set the TextView to show the food
        tvItems.setText(item.getName());
        // Set the image to star or nostar accordingly
        if(item.getName() == "Translate") {
            iv.setImageResource(R.drawable.ic_action_name);
        }
        else if (item.getName() == "Find a Local Experience") {
            iv.setImageResource(R.drawable.ic_action_face);
        }
        else if (item.getName() == "Feedback Form") {
            iv.setImageResource(R.drawable.ic_action_feedback);
        }
        else if (item.getName() == "Contact Us") {
            iv.setImageResource(R.drawable.ic_action_phone);
        }
        else if (item.getName() == "Voucher") {
            iv.setImageResource(R.drawable.ic_action_voucher);
        }
        // Return the nicely done up View to the ListView
        return rowView;
    }
}
