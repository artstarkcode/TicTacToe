package TicTacToe;

import TicTacToe.controllers.GameController;
import TicTacToe.exceptions.BotCountException;
import TicTacToe.exceptions.DuplicatePlayerSymbolException;
import TicTacToe.exceptions.PlayersAndDimensionCountMismatchException;
import TicTacToe.models.*;
import TicTacToe.strategies.winningStrategies.ColWinningStrategy;
import TicTacToe.strategies.winningStrategies.DiagonalWinningStrategy;
import TicTacToe.strategies.winningStrategies.RowWinningStrategy;
import TicTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BotCountException, PlayersAndDimensionCountMismatchException, DuplicatePlayerSymbolException {
        GameController gameController = new GameController();
        System.out.println("GAME IS STARTING NOW");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the no of players ideally 2 for Tic Tac Toe");
        int dimension = scanner.nextInt() + 1;
        List<Player> players = new ArrayList<>();
        System.out.println("ENTER ALL " + (dimension - 1) +" PLAYER DETAILS");
        for (int i = 1; i < dimension; ++i){
            System.out.println("ENTER PLAYER:" + i + " DETAILS");
            System.out.println("Enter Name");
            String name = scanner.next();
            System.out.println("Enter Symbol");
            char symbol = scanner.next().charAt(0);
            System.out.println("Enter Player Type (human/bot)");
            String playerType = scanner.next();
            if (playerType.equalsIgnoreCase("HUMAN")){
                players.add(new Player((long) i, name, new Symbol(symbol), PlayerType.HUMAN));
            } else {
                System.out.println("Enter Bot Difficulty Level!(easy/hard)");
                String difficultyLevel = scanner.next();
                BotDifficultyLevel botDifficultyLevelType;
                if (difficultyLevel.equalsIgnoreCase("HARD")){
                    botDifficultyLevelType = BotDifficultyLevel.HARD;
                }
                else {
                    botDifficultyLevelType = BotDifficultyLevel.EASY;
                }
                players.add(new Bot((long) i, name, new Symbol(symbol), botDifficultyLevelType));
            }
        }
        List<WinningStrategy> winningStrategies = List.of(new DiagonalWinningStrategy(),
                new ColWinningStrategy(),
                new RowWinningStrategy());
        Game game = gameController.startGame(dimension, players, winningStrategies);
        while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            //1. Print Board
            //2. Ask nextplayer to make a move.
            //4. Ask to do Undo
            gameController.printBoard(game);
            System.out.println("Does anyone wants to UNDO(Y/N)");
            String undoAnswer = scanner.next();
            if (undoAnswer.equalsIgnoreCase("Y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }
    }
}