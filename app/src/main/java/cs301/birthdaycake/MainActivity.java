package cs301.birthdaycake;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        findViewById(R.id.cakeview);
        CakeView cakeV = findViewById(R.id.cakeview);
        CakeController cakeController = new CakeController(cakeV);
        Button blowOutButton = (Button) findViewById(R.id.button2);
        blowOutButton.setOnClickListener(cakeController);
        CompoundButton canSwitch = (CompoundButton)findViewById(R.id.CandleSwitch);
        canSwitch.setOnCheckedChangeListener(cakeController);
        SeekBar mySeekBar = (SeekBar) findViewById(R.id.seekBar4); //
        mySeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) cakeController); //

        cakeV.setOnTouchListener(cakeController);
    }


}