package TicTacToe.models;

import TicTacToe.exceptions.BotCountException;
import TicTacToe.exceptions.DuplicatePlayerSymbolException;
import TicTacToe.exceptions.PlayersAndDimensionCountMismatchException;
import TicTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    private List<Player> players;
    private Board board;
    private int nextPlayerIndex;
    private List<Move> moves;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;
    private Scanner scanner = new Scanner(System.in);
    private Player winner;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    private Game() {}

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

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
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

    private Game(Builder builder) {
        this.board = new Board(builder.dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.players = builder.players;
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.winningStrategies = builder.winningStrategies;
    }

    public static class Builder {
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        public Builder() {
            players = new ArrayList<>();
            winningStrategies = new ArrayList<>();
            dimension = 0;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private void validatePlayersAndDimension() throws PlayersAndDimensionCountMismatchException {
            if (players.size() != (dimension - 1) || dimension <= 0) {
                throw new PlayersAndDimensionCountMismatchException();
            }
        }

        private void validatePlayersSymbol() throws DuplicatePlayerSymbolException {
            Set<Symbol> symbolSet = new HashSet<>();
            for (Player player : players) {
                if (symbolSet.contains(player.getSymbol())) {
                    throw new DuplicatePlayerSymbolException();
                }
                symbolSet.add(player.getSymbol());
            }
        }

        private void validateBotCount() throws BotCountException {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount += 1;
                }
            }
            if (botCount > 1) {
                throw new BotCountException();
            }
        }

        boolean validate() throws PlayersAndDimensionCountMismatchException, DuplicatePlayerSymbolException, BotCountException {
            validateBotCount();
            validatePlayersSymbol();
            validatePlayersAndDimension();
            return true;
        }

        public Game build() throws BotCountException, PlayersAndDimensionCountMismatchException, DuplicatePlayerSymbolException {
            validate();
            return new Game(this);
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }
    public void printBoard(){
        board.printBoard();
    }
    private boolean checkWinner(Move currentMove){
        for (WinningStrategy winningStrategy : winningStrategies){
            if (winningStrategy.checkWinner(this.board, currentMove)){
                return true;
            }
        }
        return false;
    }
    public void makeMove(){
        Move currentMove = players.get(nextPlayerIndex).makeMove(board);
        if (currentMove != null){
            moves.add(currentMove);
            nextPlayerIndex = (nextPlayerIndex + 1) % (board.getSize() - 1);
            if (checkWinner(currentMove)){
                gameState = GameState.WIN;
                setWinner(currentMove.getPlayer());
                System.out.println(currentMove.getPlayer().getName() + " has WON the game. Congratulations!!!");
                board.printBoard();
            }
            else if (moves.size() == board.getSize() * board.getSize()){
                gameState = GameState.DRAWN;
                System.out.println("Game was drawn with no winners!!! Exiting ...");
            }
        }
        else{
            System.out.println("Invalid move. Please try again.");
        }
    }
    public void undo(){
        if (!moves.isEmpty()){
            Move lastMove = moves.getLast();
            Cell lastCell = lastMove.getCell();
            lastCell.setCellState(CellState.EMPTY);
            lastCell.setPlayer(null);
            for (WinningStrategy winningStrategy : winningStrategies){
                winningStrategy.handleUndo(board, lastMove);
            }
            nextPlayerIndex = ((nextPlayerIndex - 1) + players.size()) % players.size();
            moves.removeLast();
        }
        else{
            System.out.println("There are no moves to Undo!");
        }
    }
}
