package sg.edu.rp.c346.sia_project_01;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static sg.edu.rp.c346.sia_project_01.QrCodeActivity.WIDTH;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionsFragment extends Fragment {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Attraction> attractions;
    ImageView imageView;
    TextView tvAttraction;
    Thread thread;
    public final static int WIDTH = 500;
    Bitmap bitmap;
    GoogleTranslateActivity translator;
    String language = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attractions, container, false);

        lv = (ListView)view.findViewById(R.id.listAttractions);
        imageView = (ImageView)view.findViewById(R.id.imageView);
        tvAttraction = (TextView)view.findViewById(R.id.tvAttraction);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Attraction selectedItem = attractions.get(position);
//                String attraction = selectedItem.getName();
//                String address = selectedItem.getAddress();
//                Intent i = new Intent(getActivity(), QrCodeActivity.class);
//                i.putExtra("attraction", attraction);
//                i.putExtra("address", address);
//                startActivity(i);
//
//            }
//        });

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

        try {
            bitmap = encodeAsBitmap("http://www.siahopon.com");

            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        return view;
    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, WIDTH, 0, 0, w, h);
        return bitmap;
    }

    private class English extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected void onError(Exception ex) {
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                translator = new GoogleTranslateActivity("AIzaSyDBZoK0ullNEILhsnyUh1BHthcdsLOCqjU");
                Thread.sleep(1000);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            translatedEnglish();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void translatedEnglish(){
//        String translatetomalay = translateedittext.getText().toString();//get the value of text
//        String translatetochinese = chineseText.getText().toString();
        tvAttraction = (TextView)getView().findViewById(R.id.tvAttraction);
        if (language.equals("en")) {
            String tv = translator.translte(tvAttraction.getText().toString(), "ms", "en");
            tvAttraction.setText(tv);
        } else if (language.equals("ms")) {
            String tv = translator.translte(tvAttraction.getText().toString(), "en", "ms");
            tvAttraction.setText(tv);
        }
    }

}
