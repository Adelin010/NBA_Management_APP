package nba.controller;

import nba.service.PlayerService;
import java.util.List;
import nba.model.NBAPlayer;
import nba.model.Positions;

public class PlayerController {
    private final PlayerService playerService;
    
    public PlayerController(PlayerService pr){
        playerService = pr;
    }

    public void addPlayer(int id, String name, int age, double salary, String position, int teamId){
        if(name.length() < 3){
            System.out.println("Name to short imposible for beeing a human name reenter the infos...");
            return;
        }
        if(age < 16){
            System.out.println("Underaged reenter the infos...");
            return;
        }
        if(salary < 100.00 || salary > 10000.00){
            System.out.println("Salary out of range reenter the infos...");
            return;
        }
        //Check the position 
        boolean positionChecked = false;
        for(Positions pos: Positions.values()){
            if(pos.name().equals(position)){
                positionChecked = true;
                break;
            }
        }
        if(!positionChecked){
            System.out.print("Position must be one of the values(" );
            for(Positions p: Positions.values())
                System.out.print(p.name() + " ");
            System.out.print(")");
            return;
        }

        //Creating the player
        NBAPlayer player = new NBAPlayer(id, name, age, salary, position);
        playerService.addPlayer(player, teamId <= 0 ? -1 : teamId);
    }

    public NBAPlayer getPlayerById(int id){
        if(id <= 0){
            System.out.println("Id of the player must be a positive non 0 value...");
            return null;
        }
        return playerService.getPlayerById(id);
    }

    public List<NBAPlayer> getPlayersByName(String name){
        return playerService.getPlayersByName(name);
    }

    public List<NBAPlayer> getPlayersBySalary(double salary){
        return playerService.getPlayersBySalary(salary);
    }

    public List<NBAPlayer> getPlayersByPosition(String position){
        return playerService.getPlayersByPosition(position);
    }
    
}
