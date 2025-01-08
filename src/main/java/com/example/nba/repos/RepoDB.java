package com.example.nba.repos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.nba.interfaces.Entity;
import com.example.nba.interfaces.Repo;
import com.example.nba.model.Found;
import com.example.nba.model.Game;
import com.example.nba.model.NBAPlayer;
import com.example.nba.util.DBUtil;

public class RepoDB<T extends Entity> implements Repo<T> {

    private final DBUtil<T> dbUtil;
    private final Class<T> type;
    private final Connection conn;
    private final String table;

    public RepoDB(Connection conn, Class<T> type, String table){
        this.conn = conn;
        this.type = type;
        this.table = table;
        this.dbUtil = new DBUtil<>(this.conn, this.type, this.table);
    }
 

    @Override
    public void add(T obj) {
        
        try{
            dbUtil.addObject(obj);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }

    @Override
    public void update(T obj) {
        
        try{
            dbUtil.updateObject(obj);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }   
    }

    @Override
    public T get(Integer id) {
        try{
            dbUtil.getObject(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;   
    }

    @Override
    public void delete(Integer id) {
        try{
            dbUtil.removeObject(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }    
    }

    @Override
    public List<T> getAll() {
        try{
            return dbUtil.getAllObjects();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }    
        return new ArrayList<>();
    }

    public List<T> sortByColumn(String column, boolean desc){
        try {
            return dbUtil.sortByColumn(column, desc);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public <G> List<T> getByColumn(String column, G value){
        try {
            return dbUtil.getByColumn(column, value);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public <G> List<T> getByRange(String column, G start, G end){
        try {
            return dbUtil.getByRange(column, start, end);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<T> getByLogicalOr(String[] cond){
        try {
            return dbUtil.getByLogicalOr(cond);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<T> getByLogicalAnd(String[] cond){
        try {
            return dbUtil.getByLogicalAnd(cond);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    

    /*
     * Specific methods for other things
     */
    @SuppressWarnings("unchecked")
    public List<Found> getTeamFounds(String teamName){
        String query = """
                select f.* from Found f
                join Team t on t.id = f.team_id
                where t.name like '%s'
                """.formatted(teamName);
        try{
            List<T> list = dbUtil.instatiate(conn.createStatement().executeQuery(query));
            if(list.size() != 0 && list.get(0) instanceof Found)
                return (List<Found>)list;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return new ArrayList<>();

    }
    
    @SuppressWarnings("unchecked")
    public List<Found> getSponsorFound(String sponsorName){
        String query = """
                select f.* from Found f
                join Sponsor s on s.id = f.sponsor_id
                where t.name like '%s'
                """.formatted(sponsorName);
        try{
            List<T> list = dbUtil.instatiate(conn.createStatement().executeQuery(query));
            if(list.size() != 0 && list.get(0) instanceof Found)
                return (List<Found>)list;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return new ArrayList<>();

    }

    @SuppressWarnings("unchecked")
    public List<Game> getGamesPerTeam(String teamName){
        String q = """
                    select g.* from Team t
                    join Game g on g.team1_id = t.id or g.team2_id = t.id
                    where t.name like '%s'
                """.formatted(teamName);
        try{
            List<T> list = dbUtil.instatiate(conn.createStatement().executeQuery(q));
            if(list.size() != 0 && list.get(0) instanceof Game)
                return (List<Game>)list;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return new ArrayList<>();

    }

    public int getPointsOfWinningTeam(String teamName){
        String q = """
                select sum(g.score_team1) as winning_points from Team t
                join Game g on g.team1_id = t.id
                where t.name like '%s' and g.score_team1 > g.score_team2 
                UNION
                select sum(g.score_team2) as winning_points from Team t
                join Game g on g.team2_id = t.id
                where t.name like '%s' and g.score_team2 > g.score_team1

                """.formatted(teamName, teamName);
        try{
            var res = conn.createStatement().executeQuery(q);
            //get the first value
            int amount = 0;
            for(int i = 1; i <= 2; ++i){
                res.next();
                String val = res.getString(1);
                if(val == null)
                    continue;
                amount += Integer.parseInt(val);
            }
            return amount;

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public List<NBAPlayer> sortPlayersByAge(boolean desc){
        String q = """
                select * from Player
                order by age %s
                """.formatted(desc ? "desc" : "");
        
        try{
            var res = conn.createStatement().executeQuery(q);
            List<T> list = dbUtil.instatiate(res);
            if(list.size() != 0)
                return(List<NBAPlayer>)list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public List<NBAPlayer> getPlayersByRange(int start, int end){
        String q = """
                select * from Player
                where age between %d and %d
                """.formatted(start,end);
        System.out.println(q);
        try{
            
            return (List<NBAPlayer>)dbUtil.instatiate(conn.createStatement().executeQuery(q));
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
