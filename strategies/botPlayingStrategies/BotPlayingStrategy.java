package TicTacToe.strategies.botPlayingStrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
