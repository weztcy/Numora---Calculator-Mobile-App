package com.example.kalkulator;

public class history {
    private final String mInput1;
    private final String mSimbolHitung;
    private final String mInput2;
    private final String mHasilCount;

    public history(String Input1, String SimbolHitung, String Input2, String HasilCount) {
        mInput1 = Input1;
        mSimbolHitung = SimbolHitung;
        mInput2 = Input2;
        mHasilCount = HasilCount;
    }

    public String getInput1() {
        return mInput1;
    }

    public String getSimbolHitung() {
        return mSimbolHitung;
    }

    public String getInput2() {
        return mInput2;
    }

    public String getHasilCount() {
        return mHasilCount;
    }
}