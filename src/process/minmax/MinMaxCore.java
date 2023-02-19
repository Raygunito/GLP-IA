package process.minmax;

public class MinMaxCore {
    private Tree tree;
    private int coin;
    private int difficulty;
    public MinMaxCore(int baseCoin, int difficulty) {
        this.coin = baseCoin;
        this.difficulty = difficulty;
        this.tree = new Tree(TreeFactory.buildPlayerNode(baseCoin, difficulty));
    }

    public void process(){
        tree = new Tree(TreeFactory.buildPlayerNode(coin, difficulty));
        coin =  Math.max(tree.findMove(), 0);
    }

    
    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
