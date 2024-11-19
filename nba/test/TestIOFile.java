package nba.test;

import java.util.List;

import nba.model.NBAPlayer;
import nba.util.FReader;
import nba.util.FWriter;

public class TestIOFile {


    public void test() throws Exception{

        //Test the existance of all entities
        FReader<NBAPlayer> fr = new FReader<>("build/nba/db/player.txt", NBAPlayer.class);
        List<NBAPlayer> players = fr.initListElements();
        int id = 1;
        for(NBAPlayer p: players){
            assert p.getId() == id : "Wrong Id at pbject player";
            id++;
        }
        //Test the k-nth entity in the file
        NBAPlayer player = fr.getKthEntry(5);
        assert player.getId() == 5 : "Id not 5";
        assert player.getAge() == 23 : "Not the good age";


        //Test The FileWriter newLineBytes
        FWriter<NBAPlayer> fw = new FWriter<>("build/nba/db/test.txt", NBAPlayer.class);
        //check the new line bytes size
        String os = System.getProperty("os.name").toLowerCase();
        int  line = os.contains("win")  ? 2 : 1;
        assert fw.getNewLineBytes() == line : "New Line number of bytes wrong";

        //check the appending of the FWriter
        fw.append("7,Adelin");
        //check the update method 
        fw.update(2, "2,Updated");
        fw.update(7, "7,Updated");

    }


    
}
