package cs301.birthdaycake;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener{
    private CakeView newCakeView;
    private CakeModel newCakeModel;

    public CakeController(CakeView cakeV) {
        newCakeView = cakeV;
        newCakeModel = cakeV.getCakeView();
    }

    public void onClick(View v) {
        Log.d("me", "test");
        newCakeModel.setLit(false);
        newCakeView.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        newCakeModel.setHasCandle(b);
        newCakeView.invalidate();
    }

    // SeekBar
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d("Seekbar", "It works");
        newCakeModel.candlesOnCake = i;
        newCakeView.invalidate();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        newCakeModel.x = motionEvent.getX();
        newCakeModel.y = motionEvent.getY();
        newCakeView.invalidate();
        return true;
    }
}


