package TicTacToe.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Long id;
    Scanner scanner = new Scanner(System.in);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Player(Long id, String name, Symbol symbol, PlayerType playerType){
        this.playerType = playerType;
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }
    public boolean validateMove(Board board, int row, int col){
        if (row < 0 || row >= board.getSize() ||
                col < 0 || col >= board.getSize()) {
            return false;
        }
        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return true;
        }
        return false;
    }
    public Move makeMove(Board board){
        System.out.println("Please enter the row where you want to make move:");
        int currRow = scanner.nextInt();
        System.out.println("Please enter the row where you want to make move:");
        int currCol = scanner.nextInt();
        if (!validateMove(board, currRow, currCol)){
            System.out.println("Invalid move at row: " + currRow + ", col:" + currCol + ", Please try again");
            return null;
        }
        Cell currentMoveCell = board.getBoard().get(currRow).get(currCol);
        currentMoveCell.setPlayer(this);
        currentMoveCell.setCellState(CellState.FILLED);
        Move currentMove = new Move(currentMoveCell, this);
        return currentMove;
    }
}
