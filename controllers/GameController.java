package TicTacToe.controllers;

import TicTacToe.exceptions.BotCountException;
import TicTacToe.exceptions.DuplicatePlayerSymbolException;
import TicTacToe.exceptions.PlayersAndDimensionCountMismatchException;
import TicTacToe.models.Game;
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
    void makeMove(Game game){

    }
    void checkState(Game game){

    }
    void getWinner(Game game){

    }
    void printBoard(Game game){

    }
}
