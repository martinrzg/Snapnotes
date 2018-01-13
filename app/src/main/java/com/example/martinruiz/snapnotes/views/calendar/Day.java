package com.example.martinruiz.snapnotes.views.calendar;

/**
 * Created by isaac on 11/14/17.
 */

public enum Day{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static Day stringToDay(String day){
        switch (day){
            case "Monday": return MONDAY;
            case "Tuesday": return TUESDAY;
            case "Wednesday": return WEDNESDAY;
            case "Thursday": return THURSDAY;
            case "Friday": return FRIDAY;
            case "Saturday": return SATURDAY;
            case "Sunday": return SUNDAY;
            default:return MONDAY;
        }
    }

    public int getPosition(){
        switch (this){
            case MONDAY: return 0;
            case TUESDAY: return 1;
            case WEDNESDAY: return 2;
            case THURSDAY: return 3;
            case FRIDAY: return 4;
            case SATURDAY: return 5;
            case SUNDAY: return 6;
            default:return 0;
        }
    }

    public boolean isOdd(){
        return  this == MONDAY |
                this == WEDNESDAY |
                this == FRIDAY |
                this == SUNDAY;
    }

    public boolean isEven(){
        return  this == TUESDAY |
                this == THURSDAY |
                this == SATURDAY;
    }

    @Override
    public String toString(){
        switch (this){
            case MONDAY: return "Mon";
            case TUESDAY: return "Tue";
            case WEDNESDAY: return "Wed";
            case THURSDAY: return "Thu";
            case FRIDAY: return "Fri";
            case SATURDAY: return "Sat";
            case SUNDAY: return "Sun";
            default: return "null";
        }
    }
}
