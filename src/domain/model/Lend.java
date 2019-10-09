package domain.model;

public class Lend extends RequestState {

    public Lend(PartyEquipment partyEquipment) {
        super(partyEquipment);
    }

    @Override
    public void returnEquipment(boolean damaged){
        if (damaged){
            this.partyEquipment.setState(this.partyEquipment.getDamaged());
        }else{
            this.partyEquipment.setState(this.partyEquipment.getLoanAble());
        }
    }
    @Override
    public String toString(){
        return super.toString() + "lend";
    }
}
