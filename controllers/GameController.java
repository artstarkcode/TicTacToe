package TicTacToe.controllers;

import TicTacToe.exceptions.BotCountException;
import TicTacToe.exceptions.DuplicatePlayerSymbolException;
import TicTacToe.exceptions.PlayersAndDimensionCountMismatchException;
import TicTacToe.models.Game;
import TicTacToe.models.GameState;
import TicTacToe.models.Player;
import TicTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension,
                   List<Player> players,
                   List<WinningStrategy> winningStrategies) throws BotCountException, PlayersAndDimensionCountMismatchException, DuplicatePlayerSymbolException {
        return Game.getBuilder().setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void undo(Game game){
        game.undo();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }
    void getWinner(Game game){

    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
}
