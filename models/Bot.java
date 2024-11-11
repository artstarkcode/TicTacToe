package TicTacToe.models;

import TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategy;
import TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;
    public Bot(Long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(id, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStategy(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board){
        Move move =  this.botPlayingStrategy.makeMove(board);
        if (move != null){
            move.getCell().setCellState(CellState.FILLED);
            move.getCell().setPlayer(this);
            move.setPlayer(this);
            return move;
        }
        return null;
    }
}
