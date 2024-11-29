package com.example.nba.controller;

import com.example.nba.service.AdvancedService;
import com.example.nba.model.*;

public class AdvancedController {
 
    private AdvancedService advS;

    public AdvancedController(AdvancedService advS){
        this.advS = advS;
    }

    public Manager managerWinningTeam(Integer gameId){
        return advS.managerWinningTeam(gameId);
    }
}
