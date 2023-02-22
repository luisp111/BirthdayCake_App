package cs301.birthdaycake;

public class CakeModel {
    public boolean Candlelit = true;
    public int candlesOnCake = 2;
    public boolean CakeFrosting = true;
    public boolean hasCandle = true;
    public float CheckersX = 0;
    public float CheckersY = 0;


    public void setLit(boolean lit) {
        this.Candlelit = lit;
    }

    public boolean getLit() {
        return Candlelit;
    }

    public void setHasCandle(boolean candle) {
        this.hasCandle = candle;
    }

    public boolean getHasCandle() {
        return hasCandle;
    }

    // Setter method for SeekBar
    public int getNumCandles() {
        return candlesOnCake;
    }

    // Getter method for SeekBar
    public void setNumCandles(int numCandles) {
        this.candlesOnCake = numCandles;
    }
}