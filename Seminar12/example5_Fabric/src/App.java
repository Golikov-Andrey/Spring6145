import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import Fabric.GemGenerator;
import Fabric.GoldGenerator;
import Fabric.ItemGenerator;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        ItemGenerator f1 = new GoldGenerator();
        f1.openReward();
        ItemGenerator f2 = new GemGenerator();
        f2.openReward();

        List<ItemGenerator> generatorList = new ArrayList<>();
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GoldGenerator());
        generatorList.add(new GemGenerator());

        Random rnd = ThreadLocalRandom.current();

        for(int i =0;i<10;i++)
        {
            int index = rnd.nextInt(10);
            generatorList.get(index).openReward();
        }
    }
}
