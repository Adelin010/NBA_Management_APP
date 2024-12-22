package com.example.nba.services;

import java.time.LocalDate;
import java.util.List;

import com.example.nba.error.AlreadyExistingException;
import com.example.nba.error.AttributeFaultedException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.Season;
import com.example.nba.repos.RepoDB;

public class SeasonS {
    private final Repo<Season> rs;
    private final boolean isQ;

    public SeasonS(Repo<Season> rs){
        this.rs = rs;
        if(rs instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(Season season)throws InexistenteInstance{
        //check the integrity of the primary key        
        Season s = rs.get(season.getId());
        if(s != null)
            throw new AlreadyExistingException("The season id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rs.add(season);
    }

    public void delete(Integer id){
        rs.delete(id);
    }

    public Season getById(Integer id ){
        return rs.get(id);
    }
    public List<Season> getAll(){
        return rs.getAll();
    }

    /*
     * Filter after the year of the season
     */
    public Season getByYear(int year)throws AttributeFaultedException{
        //additional check for the year 
        if(year < 1990 || year > LocalDate.now().getYear())
            throw new AttributeFaultedException("The year %d for the SeasonS::getByYear() method is not in the correct range...".formatted(year));

        if(isQ){
            var list = ((RepoDB<Season>)rs).getByColumn("year", year);
            if(list.size() == 0)
                return null;
            else return list.get(0);
        }else{
            for(var s: rs.getAll()){
                if(s.getYear() == year)
                    return s;
            }
            //if not find
            return null;
        }
    }
    /*
     * Filter after the name
     */
    public Season getByName(String name){
        if(isQ){
            var list = ((RepoDB<Season>)rs).getByColumn("name", name);
            if(list.size() == 0)
                return null;
            else return list.get(0);

        }else{
            for(var s: rs.getAll()){
                if(s.getName().equals(name))
                    return s;
            }
            //if not find
            return null;
        }
    }
}
