package domain.model;

public class Removed extends RequestState {

    public Removed(PartyEquipment partyEquipment) {
        super(partyEquipment);
    }

    @Override
    public String toString(){
        return super.toString() + "Removed";
    }
}
