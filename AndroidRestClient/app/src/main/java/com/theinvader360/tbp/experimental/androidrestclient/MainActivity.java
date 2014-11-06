package com.theinvader360.tbp.experimental.androidrestclient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import hello.Greeting;

public class MainActivity extends Activity {

    private EditText hostEditText;
    private EditText nameEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hostEditText = (EditText) findViewById(R.id.host_value);
        nameEditText = (EditText) findViewById(R.id.name_value);
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpRequestTask(hostEditText.getText().toString(), nameEditText.getText().toString()).execute();
            }
        });

        SharedPreferences prefs = getPreferences(0);
        hostEditText.setText(prefs.getString("host", null));
        nameEditText.setText(prefs.getString("name", null));
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getPreferences(0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("host", hostEditText.getText().toString());
        editor.putString("name", nameEditText.getText().toString());
        editor.commit();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Greeting> {

        String url;

        HttpRequestTask(String host, String name) {
            url = "http://";
            url += host;
            url += ":8080/greeting";
            if (name != null) url += "?name=" + name;
        }

        @Override
        protected Greeting doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                return restTemplate.getForObject(url, Greeting.class);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Greeting greeting) {
            Toast toast;
            if (greeting != null) toast = Toast.makeText(MainActivity.this, greeting.getId() + " - " + greeting.getContent(), Toast.LENGTH_SHORT);
            else toast = Toast.makeText(MainActivity.this, "Communication Error!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
            toast.show();
        }
    }

}
