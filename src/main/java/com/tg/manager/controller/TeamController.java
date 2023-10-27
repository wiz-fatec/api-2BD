package com.tg.manager.controller;

import com.tg.manager.model.TeamModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TeamController {
    static Calendar cal = GregorianCalendar.getInstance();

    protected static void addTeam() {
        for (int i = 1; i < 4; i++) {
            TeamModel.addTeam(i, (int) cal.get(Calendar.YEAR));
        }
    }



}
