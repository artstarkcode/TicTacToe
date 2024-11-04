package TicTacToe.models;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Long id;

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
}
