package TicTacToe.strategies.winningStrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    Map<Integer, HashMap<Symbol, Integer>> rowMap = new HashMap<>();
    public boolean checkWinner(Board board, Move move){
        int row = move.getCell().getRow();
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        if (!rowMap.containsKey(row)){
            rowMap.put(row, new HashMap<>());
        }
        Map<Symbol, Integer> currRowMap = rowMap.get(row);
        if (!currRowMap.containsKey(symbol)){
            currRowMap.put(symbol, 0);
        }
        currRowMap.put(symbol, currRowMap.get(symbol) + 1);
        if (currRowMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }
}
