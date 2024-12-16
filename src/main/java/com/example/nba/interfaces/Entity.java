package com.example.nba.interfaces;

public interface Entity extends IdBounded{
    /**
     * Returns a String with all the values of an object in a comma separated form
     * if flag = 1 it returns all the value but the id
     * @param flag
     * @return
     */
    String seq(boolean flag);
}
