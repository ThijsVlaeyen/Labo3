package domain.model;

public class Damaged extends RequestState {

    public Damaged(PartyEquipment partyEquipment) {
        super(partyEquipment);
    }

    @Override
    public void repair(){
        this.partyEquipment.setState(this.partyEquipment.getLoanAble());
    }

    @Override
    public void remove(){
        this.partyEquipment.setState(this.partyEquipment.getRemove());
    }

    @Override
    public String toString(){
        return super.toString() + "damaged";
    }
}
