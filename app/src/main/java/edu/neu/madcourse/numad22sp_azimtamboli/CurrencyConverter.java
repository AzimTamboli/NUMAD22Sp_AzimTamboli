package edu.neu.madcourse.numad22sp_azimtamboli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter extends AppCompatActivity {
    Button button;
    EditText currencyToBeConverted;
    TextView currencyConverted;
    Spinner convertToDropdown;
    Spinner convertFromDropdown;
    private static final String TAG = "CurrencyConverter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        currencyConverted = (TextView) findViewById(R.id.currency_converted);
        currencyToBeConverted = (EditText) findViewById(R.id.currency_to_be_converted);
        convertToDropdown = (Spinner) findViewById(R.id.to);
        convertFromDropdown = (Spinner) findViewById(R.id.from);
        button = (Button) findViewById(R.id.button);

        String[] dropDownList = {"USD", "INR", "JPY", "CNY", "SDG", "RON", "MKD", "MXN", "CAD",
                "ZAR", "AUD", "NOK", "ILS", "ISK", "SYP", "LYD", "UYU", "YER", "CSD",
                "EEK", "THB", "IDR", "LBP", "AED", "BOB", "QAR", "BHD", "HNL", "HRK",
                "COP", "DKK", "MYR", "SEK", "RSD", "BGN", "DOP", "KRW", "LVL",
                "VEF", "CZK", "TND", "KWD", "VND", "JOD", "NZD", "PAB", "CLP", "PEN",
                "GBP", "DZD", "CHF", "RUB", "UAH", "ARS", "SAR", "EGP", "INR", "PYG",
                "TWD", "TRY", "BAM", "OMR", "SGD", "MAD", "BYR", "NIO", "HKD", "LTL",
                "SKK", "GTQ", "BRL", "EUR", "HUF", "IQD", "CRC", "PHP", "SVC", "PLN" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertToDropdown.setAdapter(adapter);
        convertFromDropdown.setAdapter(adapter);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWebserviceButtonHandler(view);
            }
        });

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView textView1 = (TextView) findViewById(R.id.currency_converted);
        String cnt = (String) textView1.getText();
        outState.putString("count", cnt);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String cnt = savedInstanceState.getString("count");
        TextView textView1 = (TextView) findViewById(R.id.currency_converted);
        textView1.setText(cnt);
    }

    public void callWebserviceButtonHandler(View view) {
        GetData task = new GetData();
        task.execute("https://v6.exchangerate-api.com/");
    }

    private class GetData extends AsyncTask<String, Integer, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            String[] result = new String[2];
            URL url = null;
            HttpURLConnection conn = null;

            System.out.println("Inside doInBackground");

            try {
                //url = new URL("https://jsonplaceholder.typicode.com/posts/1");
                //System.out.println(url);

                String from = convertFromDropdown.getSelectedItem().toString();
                String to = convertToDropdown.getSelectedItem().toString();

                if (from.equals(to)) {
                    result[0] = currencyToBeConverted.getText().toString();
                    return result;
                }

                url = new URL(strings[0] + "v6/b4b17c2eb0f2ffa808ec2a2e/latest/" + from);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();

                InputStream inputStream = conn.getInputStream();

                final String resp = converStreamToString(inputStream);

//                JSONObject jObject = new JSONObject(resp);
//                String jTitle = jObject.getString("title");
//                String jBody = jObject.getString("body");

                JSONObject jObject = new JSONObject(resp);
                String factor = new JSONObject(jObject.getString("conversion_rates")).getString(to);

                double t = Double.parseDouble(factor) * Double.parseDouble(currencyToBeConverted.getText().toString());

                result[0] = String.valueOf(t );

//                result[0] = jTitle;
//                result[1] = jBody;
                return result;


            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException");
                e.printStackTrace();
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                e.printStackTrace();
            }
            result[0] = "Something wrong";
            return result;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "Making Progress...");
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            TextView res = (TextView) findViewById(R.id.currency_converted);
            res.setText(strings[0]);
        }

        private String converStreamToString(InputStream inputStream) {
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            return s.hasNext() ? s.next().replace("," , ",\n") : "";
        }
    }
}