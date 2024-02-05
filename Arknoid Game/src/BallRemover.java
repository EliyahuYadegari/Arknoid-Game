/**
 * this class represents block remover.
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game           given game
     * @param remainingBalls number of balls that remain in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * this methode removes a ball from the game when the ball falls out.
     *
     * @param hitter   the "dead area" in the game
     * @param beingHit the ball needed to remove
     */
    @Override
    public void hitEvent(Block hitter, Ball beingHit) {
        beingHit.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
