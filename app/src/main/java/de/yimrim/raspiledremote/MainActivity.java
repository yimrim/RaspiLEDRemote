package de.yimrim.raspiledremote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Switch s1;
    private String ip = "192.168.178.29";
    private Socket s;
    private PrintWriter printWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1 = findViewById(R.id.s1);
        s1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        myTask m = new myTask();
        m.execute();
    }

    class myTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                s = new Socket(ip,5000);
                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write("test");
                printWriter.flush();
                printWriter.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

}
