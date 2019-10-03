package domain.model;

public abstract class RequestState {
    PartyEquipment partyEquipment;
    public RequestState(PartyEquipment partyEquipment){
        this.partyEquipment = partyEquipment;
    }

    public void loan(){
        throw new IllegalStateException("you cannot use this method in this state");
    }

    public void returnEquipment(boolean damaged){
        throw new IllegalStateException("you cannot use this method in this state");
    }

    public void repair(){
        throw new IllegalStateException("you cannot use this method in this state");
    }

    public void remove(){
        throw new IllegalStateException("you cannot use this method in this state");
    }

    public PartyEquipment getRequestState(){
        return this.partyEquipment;
    }

}
