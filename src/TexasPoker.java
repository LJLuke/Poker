
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by lijiang on 2016/10/10.
 */
public class TexasPoker {
    private final int Huang_Jia_Tong_Hua_Shun = 10;
    private final int Tong_Hua_Shun = 9;
    private final int Si_Tiao = 8;
    private final int Hu_Lu = 7;
    private final int Tong_Hua = 6;
    private final int Shun_Zi = 5;
    private final int San_Tiao = 4;
    private final int Liang_Dui = 3;
    private final int Yi_Dui = 2;
    private final int Gao_Pai = 1;

    public int pokerALevel = Gao_Pai;
    public int pokerBLevel = Gao_Pai;


    ArrayList<Poker> playerAPokers = new ArrayList();
    ArrayList<Poker> playerBPokers = new ArrayList();
    ArrayList<Poker> publicPokers = new ArrayList();
    ArrayList<Poker> playerACanUsePokers = new ArrayList();
    ArrayList<Poker> playerBCanUsePokers = new ArrayList();

    public void deleteKing(ArrayList<Poker> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            String king = arrayList.get(i).getKing();
            if (king != null && king.equals("大王"))
                arrayList.remove(i);
            if (king != null && king.equals("小王"))
                arrayList.remove(i);
        }
    }

    public void getOrderlyPokers(ArrayList<Poker> arrayList) {
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(arrayList.size() - i);
            if (i % 4 == 1) {
                playerAPokers.add(arrayList.get(index));
                arrayList.remove(index);
            } else if (i % 4 == 2) {
                playerBPokers.add(arrayList.get(index));
                arrayList.remove(index);
            } else {
                publicPokers.add(arrayList.get(index));
                arrayList.remove(index);
            }
        }


        playerACanUsePokers.addAll(playerAPokers);
        playerACanUsePokers.addAll(publicPokers);
        playerBCanUsePokers.addAll(playerBPokers);
        playerBCanUsePokers.addAll(publicPokers);

        changeStringToInt(playerACanUsePokers);
        changeStringToInt(playerBCanUsePokers);

        for (int i = 0; i < playerACanUsePokers.size(); i++) {
            for (int j = i + 1; j < playerACanUsePokers.size(); j++) {
                if (playerACanUsePokers.get(i).getOder() < playerACanUsePokers.get(j).getOder()) {
                    Collections.swap(playerACanUsePokers, j, i);
                }
            }
        }
        for (int i = 0; i < playerBCanUsePokers.size(); i++) {
            for (int j = i + 1; j < playerBCanUsePokers.size(); j++) {
                if (playerBCanUsePokers.get(i).getOder() < playerBCanUsePokers.get(j).getOder()) {
                    Collections.swap(playerBCanUsePokers, j, i);
                }
            }
        }
    }

    public void changeStringToInt(ArrayList<Poker> arrayList) {
        for (Poker p : arrayList) {
            if (p.getPoint().equals("2"))
                p.setOder(2);
            if (p.getPoint().equals("3"))
                p.setOder(3);
            if (p.getPoint().equals("4"))
                p.setOder(4);
            if (p.getPoint().equals("5"))
                p.setOder(5);
            if (p.getPoint().equals("6"))
                p.setOder(6);
            if (p.getPoint().equals("7"))
                p.setOder(7);
            if (p.getPoint().equals("8"))
                p.setOder(8);
            if (p.getPoint().equals("9"))
                p.setOder(9);
            if (p.getPoint().equals("10"))
                p.setOder(10);
            if (p.getPoint().equals("J"))
                p.setOder(11);
            if (p.getPoint().equals("Q"))
                p.setOder(12);
            if (p.getPoint().equals("K"))
                p.setOder(13);
            if (p.getPoint().equals("A"))
                p.setOder(14);
        }
    }

    public boolean isSameSuit(ArrayList<Poker> arrayList) {
        boolean isSameSuit = false;
        int hearts = 0;
        int blackHead = 0;
        int plumBlossom = 0;
        int diamonds = 0;
        for (Poker p : arrayList) {
            String suit = p.getSuit();
            if (suit.equals("♥"))
                hearts++;
            if (suit.equals("♠"))
                blackHead++;
            if (suit.equals("♣"))
                plumBlossom++;
            if (suit.equals("♦"))
                diamonds++;
        }
        if (hearts >= 5) {
            isSameSuit = true;
            if (arrayList.containsAll(playerACanUsePokers) == true)
                pokerALevel = Tong_Hua;
            else pokerBLevel = Tong_Hua;
            removeTrashPoker(hearts, "♥", arrayList);
        }
        if (blackHead >= 5) {
            isSameSuit = true;
            if (arrayList.containsAll(playerACanUsePokers) == true)
                pokerALevel = Tong_Hua;
            else pokerBLevel = Tong_Hua;
            removeTrashPoker(blackHead, "♠", arrayList);
        }
        if (plumBlossom >= 5) {
            isSameSuit = true;
            if (arrayList.containsAll(playerACanUsePokers) == true)
                pokerALevel = Tong_Hua;
            else pokerBLevel = Tong_Hua;
            removeTrashPoker(plumBlossom, "♣", arrayList);
        }
        if (diamonds >= 5) {
            isSameSuit = true;
            if (arrayList.containsAll(playerACanUsePokers) == true)
                pokerALevel = Tong_Hua;
            else pokerBLevel = Tong_Hua;
            removeTrashPoker(diamonds, "♦", arrayList);
        }
        return isSameSuit;
    }

    public void removeTrashPoker(int number, String suit, ArrayList<Poker> arrayList) {
        if (number == 5) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (!arrayList.get(i).getSuit().equals(suit)) {
                    arrayList.remove(i);
                }
            }
        }
        if (number == 6) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (arrayList.get(j).getSuit().equals(suit) == false) {
                    arrayList.remove(j);
                }
            }
            int point = arrayList.get(0).getOder();
            if (point != (arrayList.get(1).getOder() + 1)) {
                for (int j = 1; j < 6; j++) {
                    if (arrayList.get(j).getOder() != (arrayList.get(j + 1).getOder() + 1)) {
                        arrayList.remove(5);
                        break;
                    }
                }
            }
        }
        if (number == 7) {
            arrayList.remove(6);
            arrayList.remove(5);
        }
    }

    public boolean isSameSuitStraight(ArrayList<Poker> arrayList) {
        boolean isSameSuit = isSameSuit(arrayList);
        boolean isSameSuitStraight = false;
        if (isSameSuit == true) {
            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                if (arrayList.get(i).getOder() != (arrayList.get(i + 1).getOder() + 1)) {
                    flag = false;
                    break;
                }
            }
            while (flag) {
                isSameSuitStraight = true;
                if (arrayList.containsAll(playerACanUsePokers) == true)
                    pokerALevel = Tong_Hua_Shun;
                else
                    pokerBLevel = Tong_Hua_Shun;
            }
        }
        return isSameSuitStraight;
    }

    public ArrayList<Poker> royalSameSuitStraight(ArrayList<Poker> arrayList) {
        boolean isSameSuitStraight = isSameSuitStraight(arrayList);
        if (isSameSuitStraight == true) {
            for (int i = 0; i < 5; i++) {
                if (arrayList.get(0).getOder() == 14) {
                    if (arrayList.containsAll(playerACanUsePokers) == true)
                        pokerALevel = Huang_Jia_Tong_Hua_Shun;
                    else
                        pokerBLevel = Huang_Jia_Tong_Hua_Shun;
                }
            }
        }
        return arrayList;
    }

    public boolean isTwain(ArrayList<Poker> arrayList) {
        boolean isTwain = false;
        int[] repetitionNumber = {1, 1, 1, 1, 1, 1, 1};
        int count = 1;
        int temp = arrayList.get(0).getOder();
        for (int i = 1; i < 7; i++) {
            if (temp != arrayList.get(i).getOder()) {
                temp = arrayList.get(i).getOder();
                repetitionNumber[i] = count;
                count = 1;
            } else {
                count++;
                if (i == 6 && arrayList.get(4).getOder() != arrayList.get(5).getOder())
                    repetitionNumber[i] = 2;
            }
        }
        int tempArr;
        for (int j = 0; j < repetitionNumber.length; j++) {
            for (int i = j + 1; i < repetitionNumber.length; i++) {
                if (repetitionNumber[j] < repetitionNumber[i]) {
                    tempArr = repetitionNumber[i];
                    repetitionNumber[i] = repetitionNumber[j];
                    repetitionNumber[j] = tempArr;
                }
            }
        }
        if (repetitionNumber[0] == 2) {
            isTwain = true;
            if (arrayList.containsAll(playerACanUsePokers) == true) {
                pokerALevel = Yi_Dui;
                if (repetitionNumber[1] == 2) {
                    pokerALevel = Liang_Dui;
                }
            } else {
                pokerBLevel = Yi_Dui;
                if (repetitionNumber[1] == 2) {
                    pokerBLevel = Liang_Dui;
                }
            }
        }
        if (repetitionNumber[0] == 3) {
            isTwain = true;
            if (arrayList.containsAll(playerACanUsePokers) == true) {
                pokerALevel = San_Tiao;
                if (repetitionNumber[1] == 3 || repetitionNumber[1] == 2)
                    pokerALevel = Hu_Lu;
            } else {
                pokerBLevel = San_Tiao;
                if (repetitionNumber[1] == 3 || repetitionNumber[1] == 2) {
                    pokerBLevel = Hu_Lu;
                }
            }
        }
        if (repetitionNumber[0] == 4) {
            isTwain = true;
            if (arrayList.containsAll(playerACanUsePokers) == true)
                pokerALevel = Si_Tiao;
            else
                pokerBLevel = Si_Tiao;
        }
        return isTwain;
    }

    public boolean isStraight(ArrayList<Poker> arrayList) {
        boolean isTwain = isTwain(arrayList);
        boolean isStraight = false;
        if (isTwain == false) {
            boolean flag = true;
            for (int i = 2; i < 4; i++) {
                if (arrayList.get(i).getOder() != (arrayList.get(i + 1).getOder() + 1)) {
                    flag = false;
                    break;
                } else if (arrayList.get(1).getOder() != (arrayList.get(2).getOder() + 1)
                        || arrayList.get(5).getOder() != (arrayList.get(6).getOder() + 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag == true)
                if (arrayList.containsAll(playerACanUsePokers) == true) {
                    isStraight = true;
                    pokerALevel = Shun_Zi;
                } else {
                    isStraight = true;
                    pokerBLevel = Shun_Zi;
                }
        }
        return isStraight;
    }

    public ArrayList<Poker> commonPokers(ArrayList<Poker> arrayList) {
        boolean isStraight = isStraight(arrayList);
        if (isStraight == false)
            if (arrayList.containsAll(playerACanUsePokers) == true) {
                arrayList.remove(6);
                arrayList.remove(5);
            } else {
                arrayList.remove(6);
                arrayList.remove(5);
            }
        return arrayList;
    }
}


