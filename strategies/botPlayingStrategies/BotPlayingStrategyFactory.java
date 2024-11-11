package TicTacToe.strategies.botPlayingStrategies;

import TicTacToe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStategy(BotDifficultyLevel botDifficultyLevel){
        switch (botDifficultyLevel){
            case BotDifficultyLevel.EASY -> {
                return new EasyBotPlayingStrategy();
            }
            case BotDifficultyLevel.HARD -> {
                return new HardBotPlayingStrategy();
            }
        }
        return null;
    }
}
