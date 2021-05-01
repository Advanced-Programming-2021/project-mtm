package Model;

import Model.Board.*;
import View.Printer.Printer;

public class AllBoards {
    DeckZone deckZone = new DeckZone();
    FieldZone fieldZone = new FieldZone();
    GraveYard graveYard = new GraveYard();
    MonsterCardZone monsterCardZone = new MonsterCardZone();
    SpellAndTrapCardZone spellAndTrapCardZone = new SpellAndTrapCardZone();
    Hand hand = new Hand();

    void sendFromOneZoneToOther(Zones firstZone, Zones secondZone, int id) {
        secondZone.addCard(firstZone.removeCard(id));
    }

}
