package Observer;

import Interfaces.iObserver;
import java.util.Random;

// Джуниор, ищет работу подбрасывая монетку
public class Junior implements iObserver{

    private String name;

    public Junior(String name){

        this.name = name;
    }
    @Override
    public void receiveOffer(String nameCompany, int salary) {
        if (new Random().nextBoolean()){
            System.out.println(String.format("Junior %s: I accept the offer! (company, salary) = %s, %d", name, nameCompany, salary));
        }
        else{
            System.out.println(String.format("Junior %s: Ok. I don't accept the offer! (company, salary) = %s, %d", name, nameCompany, salary));
        }
    }
}
