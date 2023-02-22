package process.minmax;

public class MinMaxCore {
    private Tree tree;
    private int coin;
    private int difficulty;
    private boolean playerTurn;
    public MinMaxCore(int baseCoin, int difficulty) {
        this.coin = baseCoin;
        this.difficulty = difficulty;
        playerTurn=false;
        this.tree = new Tree(TreeFactory.buildPlayerNode(baseCoin, difficulty));
    }

    public void process(){
        tree = new Tree(TreeFactory.buildPlayerNode(coin, difficulty));
        coin =  Math.max(tree.findMove(), 0);
        playerTurn=true;
    }
    public void playerMove(int coin){
        this.coin=coin;
        playerTurn=false;
    }

    
    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
