package com.example.nba.services;

import com.example.nba.model.Manager;
import com.example.nba.repos.Repo;
import java.util.List;

public class Security {
    
    private final Repo<Manager> rm;

    public Security(Repo<Manager> rm){
        this.rm = rm;
    }

    public boolean auth(String managerName, String password){
        List<Manager> managers = rm.getAll();
        Manager possible = null;
        /*
         * Find possibility after name
         */
        for(Manager m: managers){
            if(m.getName() == managerName){
                possible = m;
                break;
            }
        }
        if(possible != null){
            /*
             * Compare the encrypted password
             */
            if(possible.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
