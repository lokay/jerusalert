package com.example.admin.jerusalert;

        import android.content.Intent;
        import android.os.Message;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;

        import java.util.Timer;
        import java.util.TimerTask;
        import java.util.logging.Handler;


public class plscActivity extends AppCompatActivity {
    static Handler handler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plsc);
//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg){
//                Intent intent = new Intent().setClass(plscActivity.this, MainActivity.class);
//                startActivity(intent);
//                }
//            };
//        handler.sendEmptyMesssageDelayed(0,2000);
////        int secondsDelayed = 1;
////        handler = new Handler().postDelayed(new Runnable() {
////            public void run() {
////                startActivity(new Intent(getApplicationContext(), MainActivity.class));
////                finish();
////            }
////        }, secondsDelayed * 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}