package com.example.nba.services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.nba.error.AlreadyExistingException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.NBAPlayer;
import com.example.nba.model.NBATeam;
import com.example.nba.repos.RepoDB;

public class PlayerS {
    private final Repo<NBAPlayer> rp;
    private final Repo<NBATeam> rt;
    private final boolean isQ;
    
    public PlayerS(Repo<NBAPlayer> rp, Repo<NBATeam> rt){
        this.rp = rp;
        this.rt = rt;
        if(rp instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(NBAPlayer player)throws InexistenteInstance{
        // Check for the integrity of the foreign key
        NBATeam t = rt.get(player.getTeamId());
        if(t == null)
            throw new InexistenteInstance("The Team assigned to the player is null. Foreign key violation...");
        
        NBAPlayer p = rp.get(player.getId());
        if(p != null)
            throw new AlreadyExistingException("The Player id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rp.add(player);
    }

    public void delete(Integer id){
        rp.delete(id);
    }

    public NBAPlayer getById(Integer id ){
        return rp.get(id);
    }
    public List<NBAPlayer> getAll(){
        return rp.getAll();
    }
    /*
     * SORT OPPERATION
     */
    /**
     * 
     * @return list of the NBAPlayers sorted by the age
     */
    public List<NBAPlayer> sortByAge(){
        if(isQ){
            //use the function in the repoDB
            return null;
        }else{
            List<NBAPlayer> players = rp.getAll();
            return players.stream().sorted((NBAPlayer p1, NBAPlayer p2) -> Integer.compare(p1.getAge(), p2.getAge())).collect(Collectors.toList());
        }
    }

    public List<NBAPlayer> sortBySalary(){
        if(isQ){
            //use the function in the repoDB
            return null;
        }else{
            List<NBAPlayer> players = rp.getAll();
            return players.stream().sorted((NBAPlayer p1, NBAPlayer p2) -> Double.compare(p1.getSalary(), p2.getSalary())).collect(Collectors.toList());
        }
    }



    /**
     * FILTERS OPPERATION
     */
    /**
     * Returns a list which is either with the elements resulting from the filtering or 
     * A list empty if there is no element to fit the category of filtering 
     * @param start
     * @param end
     * @return List of NBAPlayers
     */
    public List<NBAPlayer> filterByAgeInterval(int start, int end){

        //define the intervall to filter with only one end
        int endI = end == -1 ? 200 : end;
        int startI = start == -1 ? 0 : start;

        if(isQ){
            return null;
        }else{
            var players = rp.getAll();
            return players.stream().filter((var player) -> (player.getAge() >= startI && player.getAge() <= endI)).collect(Collectors.toList());
        }
    }

    /**
     * Returns a list which is either with the elements resulting from the filtering or 
     * A list empty if there is no element to fit the category of filtering
     * @param start
     * @param end
     * @return List of NBAPlayers
     */
    public List<NBAPlayer> filterBySalaryInterval(double start, double end){

        //define the intervall to filter with only one end
        double endI = end == -1 ? Double.MAX_VALUE : end;
        double startI = start == -1 ? 0.00 : start;

        if(isQ){
            return null;
        }else{
            var players = rp.getAll();
            return players.stream().filter((var player) -> (player.getSalary() >= startI && player.getSalary() <= endI)).collect(Collectors.toList());
        }
    }

    public List<NBAPlayer> getByName(String name){
        if(isQ){
            return ((RepoDB<NBAPlayer>)rp).getByColumn("name", name);
        }else{
            var list = rp.getAll();
            return list.stream().filter((var p) -> p.getName().equals(name)).collect(Collectors.toList());
        }
    }

}
