import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lijiang on 2016/10/9.
 */
public class PlayPoker {
    static ArrayList<Poker> arrayList = new ArrayList();
    String[] colors = {"♠", "♥", "♣", "♦"};
    String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public ArrayList<Poker> generatePokers() {
        for (String color : colors) {
            for (String number : numbers) {
                Poker poker = new Poker();
                poker.setSuit(color);
                poker.setPoint(number);
                arrayList.add(poker);
            }
        }
        Poker poker1 = new Poker();
        poker1.setKing("大王");
        arrayList.add(poker1);
        Poker poker2 = new Poker();
        poker2.setKing("小王");
        arrayList.add(poker2);
        return arrayList;
    }

    public void crossShuffle(ArrayList arrayList) {
        for (int j = 0; j < 3; j++) {
            int index = 26;
            for (int i = 1; i < 52; i = i + 2) {
                arrayList.add(i, arrayList.get(index));
                ++index;
                arrayList.remove(index);
            }
        }
    }
    public void tailRandomShuffle(ArrayList arrayList){
        Random random = new Random();
        for (int i = 52;i > 0;i--) {
            int index = 53;
            arrayList.add(random.nextInt(i),arrayList.get(index));
            arrayList.remove(index+1);
            index--;
        }
    }
    public void randomShuffle3(ArrayList arrayList){
        int index = arrayList.size()-1;
        while (index>0){
            int x = (int)(Math.random()*100)%index;
            arrayList.add(x,arrayList.get(index));
            arrayList.remove(index+1);
            index--;
        }
    }
}

