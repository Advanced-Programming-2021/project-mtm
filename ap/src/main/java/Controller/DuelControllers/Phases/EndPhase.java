package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;
import View.GetInput;
import View.Printer.Printer;

public class EndPhase extends AllPhases{

    public EndPhase(GameData gameData) {
        super.gameData = gameData;
    }

    public String run(GameData gamedata) {
        while (true) {
            String command;
            command = GetInput.getString();
             if (command.matches("next phase")) {
                break;
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
        return "";
    }

    public String run(){
        return "";
    }
}
