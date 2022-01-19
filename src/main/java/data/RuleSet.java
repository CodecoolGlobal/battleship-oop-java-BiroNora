package main.java.data;

import main.java.utility.FileInOut;

public class RuleSet {
    public enum PlayerType { PLAYER_VS_PLAYER, PLAYER_VS_AI, AI_VS_AI }
    public enum ShipForm { LINE_SHIPS, MIXED_SHIPS }
    public enum ShipAdjacency { ALLOWED, NOT_ALLOWED }
    public enum ShipPlacement { RANDOM, MANUAL }

    private final String pathName = "options.txt";

    private PlayerType playerType;
    private ShipForm shipForm;
    private ShipAdjacency shipAdjacency;
    private ShipPlacement shipPlacement;

    public RuleSet() {
        if(!readFromFile()) {
            this.playerType = PlayerType.PLAYER_VS_AI;
            this.shipForm = ShipForm.LINE_SHIPS;
            this.shipAdjacency = ShipAdjacency.NOT_ALLOWED;
            this.shipPlacement = ShipPlacement.RANDOM;
        }
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public ShipForm getShipForm() {
        return shipForm;
    }

    public void setShipForm(ShipForm shipForm) {
        this.shipForm = shipForm;
    }

    public ShipAdjacency getShipAdjacency() {
        return shipAdjacency;
    }

    public void setShipAdjacency(ShipAdjacency shipAdjacency) {
        this.shipAdjacency = shipAdjacency;
    }

    public ShipPlacement getShipPlacement() {
        return shipPlacement;
    }

    public void setShipPlacement(ShipPlacement shipPlacement) {
        this.shipPlacement = shipPlacement;
    }

    public boolean readFromFile() {
        String[] lines = FileInOut.readLines(pathName);
        if(lines.length == 4) {
            this.playerType = PlayerType.values()[Integer.parseInt(lines[0])];
            this.shipForm = ShipForm.values()[Integer.parseInt(lines[1])];
            this.shipAdjacency = ShipAdjacency.values()[Integer.parseInt(lines[2])];
            this.shipPlacement = ShipPlacement.values()[Integer.parseInt(lines[3])];
            return true;
        } else {
            return false;
        }
    }

    public void writeToFile() {
        String[] lines = new String[4];
        lines[0] = String.valueOf(this.playerType.ordinal());
        lines[1] = String.valueOf(this.shipForm.ordinal());
        lines[2] = String.valueOf(this.shipAdjacency.ordinal());
        lines[3] = String.valueOf(this.shipPlacement.ordinal());
        FileInOut.writeLinesToFile(lines, pathName);
    }
}
