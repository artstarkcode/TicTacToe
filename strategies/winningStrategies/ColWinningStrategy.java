package TicTacToe.strategies.winningStrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {
    Map<Integer, HashMap<Symbol, Integer>> colMap = new HashMap<>();
    public boolean checkWinner(Board board, Move move){
        int col = move.getCell().getCol();
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        if (!colMap.containsKey(col)){
            colMap.put(col, new HashMap<>());
        }
        Map<Symbol, Integer> currColMap = colMap.get(col);
        if (!currColMap.containsKey(symbol)){
            currColMap.put(symbol, 0);
        }
        currColMap.put(symbol, currColMap.get(symbol) + 1);
        if (currColMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }
}
