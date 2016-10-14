
import java.util.ArrayList;

/**
 * Created by lijiang on 2016/10/9.
 */
public class PokerDemo {
    public static void main(String[] args) {
        PlayPoker playPoker = new PlayPoker();
        ArrayList<Poker> arrayList = playPoker.generatePokers();//生成一副扑克牌
        playPoker.crossShuffle(arrayList);
        playPoker.tailRandomShuffle(arrayList);
        playPoker.randomShuffle3(arrayList);
        TexasPoker texasPoker = new TexasPoker();
        texasPoker.deleteKing(arrayList);
        texasPoker.getOrderlyPokers(arrayList);
        System.out.print("玩家A的牌：");
        for (Poker p : texasPoker.playerAPokers) {
            System.out.print(p.getSuit() + p.getPoint() + " ");
        }
        System.out.println();
        System.out.print("玩家B的牌：");
        for (Poker p : texasPoker.playerBPokers) {
            System.out.print(p.getSuit() + p.getPoint() + " ");
        }
        System.out.println();
        System.out.print("公共牌：");
        for (Poker p : texasPoker.publicPokers) {
            System.out.print(p.getSuit() + p.getPoint() + " ");
        }
        System.out.println();
        System.out.print("玩家A可用的牌：");
        for (Poker p : texasPoker.playerACanUsePokers) {
            System.out.print(p.getPoint() + p.getSuit() + " ");
        }
        System.out.println();
        System.out.print("玩家B可用的牌：");
        for (Poker p : texasPoker.playerBCanUsePokers) {
            System.out.print(p.getPoint() + p.getSuit() + " ");
        }
        System.out.println();
        if (texasPoker.isSameSuit(texasPoker.playerACanUsePokers) == false) {
            texasPoker.commonPokers(texasPoker.playerACanUsePokers);
        } else {
            texasPoker.royalSameSuitStraight(texasPoker.playerACanUsePokers);
        }
        if (texasPoker.isSameSuit(texasPoker.playerBCanUsePokers) == false) {
            texasPoker.commonPokers(texasPoker.playerBCanUsePokers);
        } else {
            texasPoker.royalSameSuitStraight(texasPoker.playerBCanUsePokers);
        }
        switch (texasPoker.pokerALevel) {
            case 1:
                System.out.println("玩家A的牌型：高牌");
                break;
            case 2:
                System.out.println("玩家A的牌型：一对");
                break;
            case 3:
                System.out.println("玩家A的牌型：两对");
                break;
            case 4:
                System.out.println("玩家A的牌型：三条");
                break;
            case 5:
                System.out.println("玩家A的牌型：顺子");
                break;
            case 6:
                System.out.println("玩家A的牌型：同花");
                break;
            case 7:
                System.out.println("玩家A的牌型：葫芦");
                break;
            case 8:
                System.out.println("玩家A的牌型：四条");
                break;
            case 9:
                System.out.println("玩家A的牌型：同花顺");
                break;
            case 10:
                System.out.println("玩家A的牌型：皇家同花顺");
        }
        switch (texasPoker.pokerBLevel) {
            case 1:
                System.out.println("玩家B的牌型：高牌");
                break;
            case 2:
                System.out.println("玩家B的牌型：一对");
                break;
            case 3:
                System.out.println("玩家B的牌型：两对");
                break;
            case 4:
                System.out.println("玩家B的牌型：三条");
                break;
            case 5:
                System.out.println("玩家B的牌型：顺子");
                break;
            case 6:
                System.out.println("玩家B的牌型：同花");
                break;
            case 7:
                System.out.println("玩家B的牌型：葫芦");
                break;
            case 8:
                System.out.println("玩家B的牌型：四条");
                break;
            case 9:
                System.out.println("玩家B的牌型：同花顺");
                break;
            case 10:
                System.out.println("玩家B的牌型：皇家同花顺");
        }
    }
}
