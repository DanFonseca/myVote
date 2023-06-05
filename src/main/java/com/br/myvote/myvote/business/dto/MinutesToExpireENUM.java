package com.br.myvote.myvote.business.dto;

public enum MinutesToExpireENUM {
    DEFAULT_MINUTES_TO_EXPIRE(1);

    private final int minutes;

    MinutesToExpireENUM(Integer minutes){
        this.minutes = minutes;
    }

    public int getMinutes(){
        return minutes;
    }
}
