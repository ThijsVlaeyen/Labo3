package domain.model;

public class Lendable extends RequestState {

    public Lendable(PartyEquipment partyEquipment) {
        super(partyEquipment);
    }

    @Override
    public void loan(){
        this.partyEquipment.setState(this.partyEquipment.getLoan());
    }

    @Override
    public void remove(){
        this.partyEquipment.setState(this.partyEquipment.getRemove());
    }
}
