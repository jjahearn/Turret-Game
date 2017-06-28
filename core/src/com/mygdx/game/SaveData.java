package com.mygdx.game;

/**
 * class to hold variables relevant to one save file instance of the game
 */

public class SaveData{ //will most likely implement serializable
    private int currency;

    public boolean save(){
        //save to file
        return false;
    }

    public int getCurrency() {
        return currency;
    }

    public void addCurrency(int amount) {
        this.currency += amount;
    }

    public SaveData(){
        currency = 0;
    }
}
