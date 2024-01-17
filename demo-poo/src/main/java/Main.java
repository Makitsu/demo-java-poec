import com.jmd.test.poo.monster.*;
import org.joda.time.LocalDate;


import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {


    public static void main(String[] args) {


        LocalDate anotherDate = LocalDate.fromDateFields(new Date());

        AirMonster roucool = new AirMonster("roucool",5,10,
                true,2, LocalDate.of(1995,1,1));
        WaterMonster carapuce = new WaterMonster("carapuce",5,10,
                3, LocalDate.parse());
        WaterMonster anotherCarapuce = new WaterMonster("carapuce",5,10,
                1, LocalDate.of(1998,1,1));
        AirMonster anotherRoucool = new AirMonster("roucool",5,10,
                true,10, LocalDate.of(1989,1,1));
        List<Monster> monsters = Arrays.asList(roucool,carapuce,anotherCarapuce,anotherRoucool);

        MonsterBirthdateComparator comparator = new MonsterBirthdateComparator();

        monsters.sort(comparator);

        monsters.forEach(ms -> System.out.println(monsters.indexOf(ms) +" -> "+ms));
    }
}