package main.java.gamelogic;

public class RuleSet {
    public enum PlayerType { PLAYER_VS_PLAYER, PLAYER_VS_AI, AI_VS_AI }
    public enum ShipForm { LINE_SHIPS, MIXED_SHIPS }
    public enum ShipAdjacency { ALLOWED, NOT_ALLOWED }

    private PlayerType playerType;
    private ShipForm shipForm;
    private ShipAdjacency shipAdjacency;

    public RuleSet(PlayerType playerType, ShipForm shipForm, ShipAdjacency shipAdjacency) {
        this.playerType = playerType;
        this.shipForm = shipForm;
        this.shipAdjacency = shipAdjacency;
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
}
