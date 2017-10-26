package sg.edu.rp.c346.sia_project_01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    GoogleTranslateActivity translator;
    TextView tvName, tvHotel, tvDuration, tvplan;
    Button btnNext, btnTranslate, btnEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        tvName = (TextView) findViewById(R.id.tvname);
        tvHotel = (TextView) findViewById(R.id.tvHotel);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        btnTranslate = (Button) findViewById(R.id.btnTranslate);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnEnglish = (Button) findViewById(R.id.btnEnglish);

        btnTranslate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new EnglishToTagalog().execute();
                btnTranslate.setVisibility(View.INVISIBLE);
                btnEnglish.setVisibility(View.VISIBLE);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new English().execute();
                btnTranslate.setVisibility(View.VISIBLE);
                btnEnglish.setVisibility(View.INVISIBLE);
            }
        });

    }

    private class EnglishToTagalog extends AsyncTask<Void, Void, Void> {
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
            //start the progress dialog
            progress = ProgressDialog.show(HomeActivity.this, null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();
            super.onPostExecute(result);
            translated();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
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
            //start the progress dialog
            progress = ProgressDialog.show(HomeActivity.this, null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();
            super.onPostExecute(result);
            translatedEnglish();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void translated(){
//        String translatetomalay = translateedittext.getText().toString();//get the value of text
//        String translatetochinese = chineseText.getText().toString();
        tvName = (TextView) findViewById(R.id.tvname);
        tvHotel = (TextView) findViewById(R.id.tvHotel);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        tvplan = (TextView) findViewById(R.id.tvplan);
        btnTranslate = (Button) findViewById(R.id.btnTranslate);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnEnglish = (Button) findViewById(R.id.btnEnglish);

        String name = translator.translte(tvName.getText().toString(), "en", "ms");
        String hotel = translator.translte(tvHotel.getText().toString(), "en", "ms");
        String duration = translator.translte(tvDuration.getText().toString(), "en", "ms");
        String plan = translator.translte(tvplan.getText().toString(), "en", "ms");
        String next = translator.translte(btnNext.getText().toString(), "en", "ms");
        String translate = translator.translte(btnTranslate.getText().toString(), "en", "ms");
        String translateEnglish = translator.translte(btnEnglish.getText().toString(), "en", "ms");

        tvName.setText(name);
        tvHotel.setText(hotel);
        tvDuration.setText(duration);
        tvplan.setText(plan);
        btnNext.setText(next);
        btnTranslate.setText(translate);
        btnEnglish.setText(translateEnglish);

    }

    public void translatedEnglish(){
//        String translatetomalay = translateedittext.getText().toString();//get the value of text
//        String translatetochinese = chineseText.getText().toString();
        tvName = (TextView) findViewById(R.id.tvname);
        tvHotel = (TextView) findViewById(R.id.tvHotel);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        tvplan = (TextView) findViewById(R.id.tvplan);
        btnTranslate = (Button) findViewById(R.id.btnTranslate);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnEnglish = (Button) findViewById(R.id.btnEnglish);

        String name = translator.translte(tvName.getText().toString(), "ms", "en");
        String hotel = translator.translte(tvHotel.getText().toString(), "ms", "en");
        String duration = translator.translte(tvDuration.getText().toString(), "ms", "en");
        String plan = translator.translte(tvplan.getText().toString(), "ms", "en");
        String next = translator.translte(btnNext.getText().toString(), "ms", "en");
        String translate = translator.translte(btnTranslate.getText().toString(), "ms", "en");
        String translateEnglish = translator.translte(btnEnglish.getText().toString(), "ms", "en");

        tvName.setText(name);
        tvHotel.setText(hotel);
        tvDuration.setText(duration);
        tvplan.setText(plan);
        btnNext.setText(next);
        btnTranslate.setText(translate);
        btnEnglish.setText(translateEnglish);
    }
}
