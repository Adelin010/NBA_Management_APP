package com.example.nba.services;

import java.util.List;
import java.util.stream.Collectors;
import static java.util.function.Predicate.not;

import com.example.nba.error.AlreadyExistingException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.Sponsor;
import com.example.nba.repos.RepoDB;

public class SponsorS {
     private final Repo<Sponsor> rs;
    private final boolean isQ;

    public SponsorS(Repo<Sponsor> rs){
        this.rs = rs;
        if(rs instanceof RepoDB)
            isQ = true;
        else 
            isQ = false;
    }

    /*
     * CRUD Opperations
     */
    public void add(Sponsor sponsor)throws InexistenteInstance{
        //check the integrity of the primary key        
        Sponsor s = rs.get(sponsor.getId());
        if(s != null)
            throw new AlreadyExistingException("The sponsor id already exists registred. Violation of the primary key constraint...");
        //add the instance
        rs.add(sponsor);
    }

    public void delete(Integer id){
        rs.delete(id);
    }

    public Sponsor getById(Integer id ){
        return rs.get(id);
    }
    public List<Sponsor> getAll(){
        return rs.getAll();
    }

    
    /**
     * Filters after the name 
     * @param name : String 
     * @return Sponsor if there is a sponsor or null
     */
    public Sponsor getByName(String name){
        if(isQ){
            var list = ((RepoDB<Sponsor>)rs).getByColumn("name", name);
            if(list.size() == 0)
                return null;
            else return list.get(0);
        }else{
            var list = rs.getAll();
            return list.stream().filter((var sponsor) -> sponsor.getName().equals(name)).findFirst().orElse(null);
        }
    }

    /**
     * Filters the sponsors after an age intervall 
     * @param start
     * @param end
     * @return list of sponsors
     */
    public List<Sponsor> filterByAgeInterval(int start, int end){

        //define the intervall to filter with only one end
        int endI = end == -1 ? 200 : end;
        int startI = start == -1 ? 0 : start;

        if(isQ){
            var list = ((RepoDB<Sponsor>)rs).getByRange("age", start, end);
            if(list.size() == 0)
                return null;
            else return list;
        }else{
            var players = rs.getAll();
            return players.stream().filter((var sponsor) -> (sponsor.getAge() >= startI && sponsor.getAge() <= endI)).collect(Collectors.toList());
        }
    }

    public List<Sponsor> getTheCompanies(){
        if(isQ){
            var list = ((RepoDB<Sponsor>)rs).getByColumn("pf", 0);
            if(list.size() == 0)
                return null;
            else return list;
        }else{
            return rs.getAll().stream().filter(not(Sponsor::isPF)).collect(Collectors.toList());
        }
    }
}
