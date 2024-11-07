package TicTacToe.models;

public class Bot extends Player{
    BotDifficultyLevel botDifficultyLevel;
    public Bot(Long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(id, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
