package edu.newpaltz.compsci.hellofusion;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.Fusiontables.Query.Sql;

public class MainActivity extends AppCompatActivity {
    EditText id1;
    EditText lat;

    EditText lon;
    EditText name;


    private static  HttpTransport HTTP_TRANSPORT;

    private static  JsonFactory JSON_FACTORY;

    private static Fusiontables fusiontables;

    private static FileDataStoreFactory dataStoreFactory;

    private static final String SERVICE_ACCOUNT_EMAIL = "drhaaland@gmail.com";
    private String setFusion()
    {
        String f="112";
        try {
            HTTP_TRANSPORT = new NetHttpTransport();
            JSON_FACTORY = new JacksonFactory();
            GoogleCredential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
                    .setServiceAccountPrivateKeyId("330010208408-ub5sja5pkjids7mj30oj34lhj9dgn0dc.apps.googleusercontent.com")
                    .build();
            fusiontables = new Fusiontables.Builder(HTTP_TRANSPORT,JSON_FACTORY,credential).setApplicationName("Fusiontable Database").build();
            Sql sql = fusiontables.query().sql("SELECT Location FROM 1ZUuTLagJJctcsD_eFgAnkZOPIjA5su1enshkxxNE WHERE number=12");
            f=sql.toString();

        }

        catch(Throwable t)
        {
            t.printStackTrace();
        }

        return f;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupInput();
        setupButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void setupInput()
    {
        id1 = (EditText)findViewById(R.id.editID);
        lat = (EditText)findViewById(R.id.editLat);
        lon = (EditText)findViewById(R.id.editLongi);
        name = (EditText)findViewById(R.id.editType);

    }

    private void setupButton()
    {
        Button inBtn = (Button) findViewById(R.id.btnIn);
        inBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                toast.makeText(MainActivity.this, id1.getText(), toast.LENGTH_SHORT).show();
                toast.makeText(MainActivity.this,setFusion(),toast.LENGTH_SHORT).show();
                toast.makeText(MainActivity.this, lat.getText(), toast.LENGTH_SHORT).show();
                toast.makeText(MainActivity.this, lon.getText(), toast.LENGTH_SHORT).show();
                toast.makeText(MainActivity.this, name.getText(), toast.LENGTH_SHORT).show();
            }
        });


        //ClientID 330010208408-ub5sja5pkjids7mj30oj34lhj9dgn0dc.apps.googleusercontent.com
    }
}
