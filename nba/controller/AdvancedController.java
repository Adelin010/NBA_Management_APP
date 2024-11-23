package nba.controller;

import nba.service.AdvancedService;
import nba.model.*;

public class AdvancedController {
 
    private AdvancedService advS;

    public AdvancedController(AdvancedService advS){
        this.advS = advS;
    }

    public Manager managerWinningTeam(Integer gameId){
        return advS.managerWinningTeam(gameId);
    }
}
