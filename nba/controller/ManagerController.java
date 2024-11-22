package nba.controller;

import java.util.List;

import nba.model.Manager;
import nba.service.ManagerService;

public class ManagerController {
     public ManagerService ms;

    public ManagerController(ManagerService ms){
        this.ms = ms;
    }

    public void add(String name,Integer teamId){
        Manager manager = new Manager(name,teamId);
        ms.add(manager);
    }

    public Manager getById(Integer id){
        return ms.getById(id);
    }
    public List<Manager> getAll(){
        return ms.getAll();
    }
    public Manager getByName(String name){
        return ms.getByName(name);
    }
    public void delete(Integer id){
        ms.delete(id);
    }
}
