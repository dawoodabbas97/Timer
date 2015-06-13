package dawoodabbas.timer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    long u;
    long t = 10;
    TextView t1;
    TextView n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        t=10;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.textview);
        n = (TextView) findViewById(R.id.textview2);

        spider();
    }

    public void spider()
    {
        String url2="http://spider.nitt.edu/~vishnu/time.php";
        new getUrl().execute(url2);
        Timer();
    }
    public void clicked(View v){

        finish();
        System.exit(0);


    }

public void Timer() {
    new CountDownTimer(((1000*t)+100),1000) {

        public void onTick(long millisUntilFinished) {
            t1.setText(String.valueOf(millisUntilFinished / 1000));

        }

        public void onFinish() {
            t=u;
            spider();
        }
    }.start();
}


        public class getUrl extends AsyncTask<String,Integer,Long> {
            @Override
            protected Long doInBackground(String... p) {
                long to = 0;
                try {
                    URL url = new URL(p[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    try {
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setRequestProperty("Accept-Encoding", "identity");
                        InputStreamReader input = new InputStreamReader(conn.getInputStream());
                        BufferedReader in = new BufferedReader(input);
                        String i = in.readLine();

                        to = Long.parseLong(i);


                    } finally {
                        conn.disconnect();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return to;

            }

            @Override
            protected void onPostExecute(Long a) {
                n.setText(String.valueOf(a));
                u = a % 10;
            }
        }}
