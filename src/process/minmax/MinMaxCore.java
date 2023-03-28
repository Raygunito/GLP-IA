package process.minmax;

public class MinMaxCore {
    private Tree tree;
    private int coin;
    private int difficulty;
    private boolean playerTurn = false;

    public MinMaxCore(int baseCoin, int difficulty) {
        this.coin = baseCoin;
        this.difficulty = difficulty;
        playerTurn = false;
        this.tree = new Tree(TreeFactory.buildPlayerNode(baseCoin, difficulty));
    }

    public synchronized void process() {
        if (!playerTurn) {
            tree = new Tree(TreeFactory.buildPlayerNode(coin, difficulty));
            coin = Math.max(tree.findMove(), 0);
            playerTurn = true;
        }
    }

    public void playerMove(int coin) {
        this.coin = coin;
        playerTurn = false;
    }

    public boolean isEnded() {
        return (coin <= 0) ? true : false;
    }
    
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

}
