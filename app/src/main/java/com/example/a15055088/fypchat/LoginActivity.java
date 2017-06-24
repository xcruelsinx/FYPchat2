package com.example.a15055088.fypchat;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import android.app.AlertDialog.Builder;
import java.io.IOException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URLConnection;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    static Context context;
    String NAME = null, PASSWORD = null, EMAIL = null;
    private static final HostnameVerifier DUMMY_VERIFIER = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    final String LOG = "LoginActivity";
    final String TAG = "SelfSignedCrtCertificate";
    SelfSignedCrtCertificate cert;
    Button btnLogin;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginActivity.context = getApplicationContext();

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int tmp;
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String data = "";
        BackGround b = new BackGround();
        b.execute(username, password);

        /*
        HashMap postData = new HashMap();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        postData.put("username",username);
        postData.put("password",password);
        PostResponseAsyncTask task1 = new PostResponseAsyncTask(LoginActivity.this,
                Log.d(LOG, result);
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
        );

        task1.execute("https://192.168.1.6/pw.php");
        */
    }

    private class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String data = "";
            int tmp;
            try {
                HttpsURLConnection urlConnection = cert.setUpHttpsConnection("https://192.168.1.6/moodle/login/mobileLogin.php");
                urlConnection.setHostnameVerifier(DUMMY_VERIFIER);
                // Log the server response code
                String URLparameter = "username="+ username +"&password="+ password;
                urlConnection.setRequestMethod("POST");
                //urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                //urlConnection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                //urlConnection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                os.write(URLparameter.getBytes());
                os.flush();
                os.close();

                InputStream is = urlConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                urlConnection.disconnect();
                return data;

            } catch (ProtocolException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }

        }
        protected void onPostExecute(String s) {
            String err;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");
                EMAIL = user_data.getString("email");

                Intent i = new Intent(context, Home.class);
                i.putExtra("name", NAME);
                i.putExtra("password", PASSWORD);
                i.putExtra("email", EMAIL);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Username or password is incorrect";
                Toast.makeText(LoginActivity.this, err, Toast.LENGTH_LONG).show();
            }



        }
    }

}


