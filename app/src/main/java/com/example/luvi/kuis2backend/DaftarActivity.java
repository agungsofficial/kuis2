package com.example.luvi.kuis2backend;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DaftarActivity extends AppCompatActivity {

    TextView testHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        testHasil = (TextView) findViewById(R.id.textView6);
    }

    private class AmbilData extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strUrl) {
            Log.v("yw", "mulai ambil data");
            String result = null;
            StringBuffer sb = new StringBuffer();
            InputStream is = null;
            try {
                URL url = new URL(strUrl[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //timeout
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);

                conn.setRequestMethod("GET");
                conn.connect();

                try {
                    is = new BufferedInputStream(conn.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String inputLine = "";
                    while ((inputLine = br.readLine()) != null) {
                        sb.append(inputLine);
                    }
                    result = sb.toString();
                }
                catch (Exception e) {
                    Log.i("yw", "Error reading InputStream");
                    result = null;
                }
                finally {
                    if (is != null) {
                        try {
                            is.close();
                        }
                        catch (IOException e) {
                            Log.i("yw", "Error closing InputStream");
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(String result) {
            testHasil.setText(result);
        }
    }

    public void onClick(View v) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new AmbilData().execute("http://mobprog.yuliadi.pro:5000/buat_account"); //url jadi parameter
        } else {
            // tampilkan error
            Toast t = Toast.makeText( getApplicationContext(), "Tidak ada koneksi!",Toast.LENGTH_LONG);
            t.show();
        }
    }


}
