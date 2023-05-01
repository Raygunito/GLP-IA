package process.minmax;

/**
 * The MinMaxCore class represents the core of a min-max algorithm for a
 * two-player game.
 * It creates a game tree and applies the min-max algorithm to determine the
 * best move for the AI player.
 */
public class MinMaxCore {
    /**
     * The game tree used by the min-max algorithm.
     */
    private Tree tree;
    /**
     * The number of coins left in the game.
     */
    private int coin;
    /**
     * The difficulty level of the game.
     */
    private int difficulty;
    /**
     * A boolean indicating whether it is currently the player's turn.
     */
    private boolean playerTurn = false;

    /**
     * Creates a new MinMaxCore object with the specified base coin value and
     * difficulty level.
     * 
     * @param baseCoin   the base coin value
     * @param difficulty the difficulty level
     */
    public MinMaxCore(int baseCoin, int difficulty) {
        this.coin = baseCoin;
        this.difficulty = difficulty;
        playerTurn = false;
        this.tree = new Tree(TreeFactory.buildPlayerNode(baseCoin, difficulty));
    }

    /**
     * Applies the min-max algorithm to the game tree to determine the best move for
     * the AI player.
     * If it is not currently the player's turn, creates a new game tree and
     * determines the best move.
     */
    public synchronized void process() {
        if (!playerTurn) {
            tree = new Tree(TreeFactory.buildPlayerNode(coin, difficulty));
            coin = Math.max(tree.findMove(), 0);
            playerTurn = true;
        }
    }

    /**
     * Updates the state of the game when the player makes a move.
     * 
     * @param coin the number of coins the player chooses to remove
     */
    public void playerMove(int coin) {
        this.coin = coin;
        playerTurn = false;
    }

    /**
     * Determines whether the game has ended.
     * 
     * @return true if the game has ended (i.e. there are no more coins left), false
     *         otherwise
     */
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

    public int getDifficulty() {
        return difficulty;
    }
}
