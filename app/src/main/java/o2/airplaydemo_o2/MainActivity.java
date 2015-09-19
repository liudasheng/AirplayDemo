package o2.airplaydemo_o2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.o2.airplay.Airplay;
import com.o2.airplay.AirplayData;

import java.util.jar.Attributes;

public class MainActivity extends ActionBarActivity {

    Airplay Airplay;
    AirplayData AirplayData;

    static TextView textViewName;
    static TextView textViewInfo ;
    static TextView textViewError;

    private class PreparedListener extends Airplay implements com.o2.airplay.Airplay.OnPreparedListener {
        @Override
        public boolean onPrepared(Airplay airplay, int what, int extra) {
            Log.d("dashon", "onPrepared: " + what +" " + extra);
            //textViewInfo.setText("onPrepared: "+what+" "+extra);
            switch (what) {
                default:
                    break;
            }
            return false;
        }
    }

    private class StartedListener extends Airplay implements com.o2.airplay.Airplay.OnStartedListener {
        @Override
        public boolean onStarted(Airplay airplay, int what, int extra) {
            Log.d("dashon", "onStarted: " + what +" " + extra);
            //textViewInfo.setText("onPrepared: "+what+" "+extra);
            switch (what) {
                default:
                    break;
            }
            return false;
        }
    }

    private class PausedListener extends Airplay implements com.o2.airplay.Airplay.OnPausedListener {
        @Override
        public boolean onPaused(Airplay airplay, int what, int extra) {
            Log.d("dashon", "onPaused: " + what +" " + extra);
            //textViewInfo.setText("onPrepared: "+what+" "+extra);
            switch (what) {
                default:
                    break;
            }
            return false;
        }
    }

    private class StoppedListener extends Airplay implements com.o2.airplay.Airplay.OnStoppedListener {
        @Override
        public boolean onStopped(Airplay airplay, int what, int extra) {
            Log.d("dashon", "onStopped: " + what +" " + extra);
            //textViewInfo.setText("onPrepared: "+what+" "+extra);
            switch (what) {
                default:
                    break;
            }
            return false;
        }
    }

    private class InfoListener extends Airplay implements com.o2.airplay.Airplay.OnInfoListener {
        @Override
        public boolean onInfo(Airplay airplay, int what, int extra) {
            Log.d("dashon", "onInfo: " + what +" " + extra);
            textViewInfo.setText("Info: "+what+" "+extra);
            switch (what) {
                case MEDIA_INFO_METADATA_UPDATE:
                    Airplay.GetMetaData(AirplayData);
                    Log.d("dashon", "Meta Title: " + AirplayData.MetaTitle);
                    textViewInfo.setText("Title: "+ AirplayData.MetaTitle);
                default:
                    break;
            }
            return false;
        }
    }

    private class ErrorListener extends Airplay implements com.o2.airplay.Airplay.OnErrorListener {
        @Override
        public boolean onError(Airplay airplay, int what, int extra) {
            Log.d("dashon", "onError: " + what +" " + extra);
            textViewError.setText("Error: "+what+" "+extra);
            switch (what) {
                default:
                    break;
            }
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = (TextView) findViewById(R.id.apname);
        textViewInfo = (TextView) findViewById(R.id.Infotext);
        textViewError = (TextView) findViewById(R.id.Errortext);

        Airplay = new Airplay();
        AirplayData = new AirplayData();

        Airplay.GetAirplayName(AirplayData);
        textViewName.setText("apname: " + AirplayData.HostName);
    }


    public void Start_Airplay(View view){
        Airplay.setOnErrorListener(new ErrorListener());
        Airplay.setOnInfoListener(new InfoListener());
        Airplay.setOnPreparedListener(new PreparedListener());
        Airplay.setOnStartedListener(new StartedListener());
        Airplay.setOnPausededListener(new PausedListener());
        Airplay.setOnStoppededListener(new StoppedListener());
        Airplay.StartAirplay();
    }

    public void Stop_Airplay(View view){
        Airplay.StopAirplay();
    }

    public void Set_HostName(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        //AirplayData airplayData = new AirplayData();
        AirplayData.HostName = editText.getText().toString();
        Log.d("dashon", "SetAirplayName:"+ AirplayData.HostName);
        Airplay.SetAirplayName(AirplayData);

        Airplay.GetAirplayName(AirplayData);
        Log.d("dashon", "GetAirplayName:"+ AirplayData.HostName);
        textViewName.setText("apname: " + AirplayData.HostName);
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

    }
