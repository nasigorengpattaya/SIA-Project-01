package sg.edu.rp.c346.sia_project_01;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etUsername, etPassword;
    TextView tvUser, tvPassword;
    private ProgressDialog progress = null;

    String username = "hiiamnew60@gmail.com";
    String password = "qwerty12345";

    String usernamePref = "";
    GoogleTranslateActivity translator;
    String language = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        String [] list = new String[] { "English", "Bahasa Melayu" };

        btnLogin = (Button)findViewById(R.id.btnlogin);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select preferred language")
                // Set the list of items easily by just supplying an
                //  array of the items
                .setItems(list, new DialogInterface.OnClickListener() {
                    // The parameter "which" is the item index
                    // clicked, starting from 0
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            dialog.dismiss();
                        } else if (which == 1) {
                            new English().execute();
                            Bundle bundle = new Bundle();
                            bundle.putString("language", "ms");
                            // set Fragmentclass Arguments
                            AttractionsFragment fragobj = new AttractionsFragment();
                            fragobj.setArguments(bundle);
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
        String pref = sharedPref.getString("username", "");

        etUsername.setText(pref);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPassword.getText().toString().equals(password) && etUsername.getText().toString().equals(username)) {

                    username = usernamePref;
                    final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
                    progress.setTitle("Connecting");
                    progress.setMessage("Logging in...");
                    progress.show();

                    Runnable progressRunnable = new Runnable() {

                        @Override
                        public void run() {
                            progress.cancel();
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 2000);

                    SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", usernamePref);
                    editor.commit();

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle(R.string.string_titleDialog);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.string_failLogin)
                            .setNegativeButton(R.string.string_cancelDialog, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    // Create the AlertDialog object and return it
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
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
            progress = ProgressDialog.show(LoginActivity.this, null, "Translating...");
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

    public void translatedEnglish(){
//        String translatetomalay = translateedittext.getText().toString();//get the value of text
//        String translatetochinese = chineseText.getText().toString();
        tvUser = (TextView)findViewById(R.id.tvUser);
        String tv = translator.translte(tvUser.getText().toString(), "en", "ms");
        tvUser.setText(tv);

        tvPassword = (TextView)findViewById(R.id.tvPass);
        String pass = translator.translte(tvPassword.getText().toString(), "en", "ms");
        tvPassword.setText(pass);
    }
}
