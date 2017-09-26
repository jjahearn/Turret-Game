package com.mygdx.game;

/**
 * class to store variables relevant to one save file instance of the game
 */

public class SaveData{ //will most likely implement serializable
    private int coinys;

    public boolean save(){
        //save to file
        return false;
    }

    public int getCoinys() {
        return coinys;
    }

    public void addCoinys(int amount) {
        this.coinys += amount;
    }

    public SaveData(){
        coinys = 0;
    }
}
