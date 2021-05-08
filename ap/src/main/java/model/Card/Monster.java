package model.Card;

import controller.DuelControllers.GameData;
import model.Enums.CardMod;
import model.Enums.MonsterEnums.Attribute;
import model.Enums.Zones;
import model.Gamer;
import view.Printer.Printer;

public class Monster extends Card {
    private int attack;
    private int defence;
    private int level;
    private boolean canBattle;
    private int lastTurnAttacked = 0;
    private Attribute attribute;
    private State state;
    private CardMod cardMod;

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isCanBattle() {
        return canBattle;
    }

    public void setCanBattle(boolean canBattle) {
        this.canBattle = canBattle;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CardMod getCardMod() {
        return cardMod;
    }

    public void setCardMod(CardMod cardMod) {
        this.cardMod = cardMod;
    }

    public int getLastTurnAttacked() {
        return lastTurnAttacked;
    }

    public void handleFlip() {
    }

    public void handleAttack(GameData gameData, int enemyId) {
        Monster defendingMonster = (Monster) gameData.getSecondGamer().getGameBoard().getMonsterCardZone().getCardById(enemyId);

        defendingMonster.handleDefend();
        lastTurnAttacked = gameData.getTurn();
        switch (defendingMonster.getCardMod()) {
            case OFFENSIVE_OCCUPIED:
                attackOffensiveMonster(defendingMonster, gameData);
                break;
            case DEFENSIVE_OCCUPIED:
                attackDefensiveMonster(defendingMonster, gameData);
                break;
            case DEFENSIVE_HIDDEN:
                attackDefensiveHiddenMonster(defendingMonster, gameData);
                break;
        }


    }

    private void attackDefensiveHiddenMonster(Monster defendingMonster, GameData gameData) {
        System.out.print("opponent’s monster card was " + defendingMonster.getName() + " and ");
        attackDefensiveMonster(defendingMonster, gameData);
    }

    private void attackDefensiveMonster(Monster defendingMonster, GameData gameData) {
        int damage;
        if (attack > defendingMonster.getDefence()) {
            defendingMonster.handleDestroy(gameData, false);
            Printer.print("the defense position monster is destroyed");
        } else if (attack < defendingMonster.getDefence()) {
            handleDestroy(gameData, true);
            damage = defendingMonster.getDefence() - attack;
            gameData.getFirstGamer().decreaseLifePoint(damage);
            Printer.print("no card is destroyed and you received " + damage + " battle damage");
        } else {
            Printer.print("no card is destroyed");
        }
    }

    private void attackOffensiveMonster(Monster defendingMonster, GameData gameData) {
        int damage;
        if (attack > defendingMonster.getAttack()) {
            defendingMonster.handleDestroy(gameData, false);
            damage = attack - defendingMonster.getAttack();
            gameData.getSecondGamer().decreaseLifePoint(damage);
            Printer.print("your opponent’s monster is destroyed and your opponent receives " + damage + " battle damage");
        } else if (attack < defendingMonster.getAttack()) {
            handleDestroy(gameData, true);
            damage = defendingMonster.getAttack() - attack;
            gameData.getFirstGamer().decreaseLifePoint(damage);
            Printer.print("Your monster card is destroyed and you received " + damage + " battle damage");
        } else {
            handleDestroy(gameData, true);
            defendingMonster.handleDestroy(gameData, false);
            Printer.print("both you and your opponent monster cards are destroyed and no one receives damage");
        }
    }


    public void handleDefend() {

    }

    public void handleDestroy(GameData gamedata, boolean cardOfAttackingUser) {
        Gamer gamer = gamedata.getSecondGamer();
        if (cardOfAttackingUser)
            gamer = gamedata.getFirstGamer();
        gamer.moveCardFromOneZoneToAnother(this, Zones.MONSTER_CARD_ZONE, Zones.GRAVEYARD_ZONE);
    }

    public void handleSummon() {

    }

    public void handleDirectAttack(GameData gameData) {
        lastTurnAttacked = gameData.getTurn();
        gameData.getSecondGamer().decreaseLifePoint(attack);
        Printer.print("your opponent receives " + attack + " battle damage");
    }

}