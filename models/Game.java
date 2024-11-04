package TicTacToe.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private List<Move> moves;
    private GameState gameState;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public Game(Builder builder){
        this.board = builder.board;
        this.gameState = builder.gameState;
        this.players = builder.players;
        this.moves = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }
    public static class Builder{
        private List<Player> players;
        private Board board;
        private GameState gameState;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setGameState(GameState gameState) {
            this.gameState = gameState;
            return this;
        }
        boolean validate(){
            return true;
        }
        public Game build(){
//            if (validate()){
                return new Game(this);
//            }
        }
    }
}
