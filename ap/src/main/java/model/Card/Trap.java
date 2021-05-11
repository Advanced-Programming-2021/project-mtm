package model.Card;

import com.google.gson.annotations.Expose;
import controller.DuelControllers.GameData;
import model.Enums.LabelsForActivatingTraps;
import model.Enums.SpellCardMods;
import model.Enums.SpellsAndTraps.TrapTypes;

public abstract class Trap extends SpellAndTraps {

    @Expose
    TrapTypes trapType;

    @Expose
    LabelsForActivatingTraps activationLabel;

    public TrapTypes getTrapType() {
        return trapType;
    }

    public void setTrapType(TrapTypes trapType) {
        this.trapType = trapType;
    }

    public LabelsForActivatingTraps getActivationLabel() {
        return activationLabel;
    }

    public void setActivationLabel(LabelsForActivatingTraps activationLabel) {
        this.activationLabel = activationLabel;
    }

    public boolean labelExists() {
        return false;
    }

    public void counter() {

    }

    public boolean canActivateOnOtherPlayerTurn(GameData gameData){return true;}

}
