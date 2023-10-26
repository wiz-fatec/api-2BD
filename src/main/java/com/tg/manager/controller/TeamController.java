package com.tg.manager.controller;

import com.tg.manager.model.TeamModel;

import java.util.Date;

public class TeamController {

    private static Date year = new Date();

    public static void addTeam() {
        for (int i = 1; i < 4; i++) {
            TeamModel.addTeam(i, year.getYear());
        }
    }



}
