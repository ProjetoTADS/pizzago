package stringdevelopment.com.br.gopizza;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                telaLogin();
            }
        }, SPLASH_TIME_OUT);
   }




    private void telaLogin(){
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }
}
