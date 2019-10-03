package domain.model;

import java.util.concurrent.locks.ReadWriteLock;

public class PartyEquipment  {
    RequestState loanAble;
    RequestState lend;
    RequestState damaged;
    RequestState removed;

    private double price;
    public String name;
    public int id;
    private RequestState state;

    public PartyEquipment(double price, String name)
    {
        this.price = price;
        this.name = name;
        loanAble = new Lendable(this);
        lend = new Lend(this);
        damaged = new Damaged(this);
        removed = new Removed(this);

        state = loanAble;
    }

    public double getPrice()
    {
        return price;
    }

    public void setState(RequestState state)
    {
        this.state = state;
    }

    public RequestState getState()
    {
        return state;
    }

    public void loan()
    {
        state.loan();
    }

    public void repair()
    {
        state.repair();
    }

    public void remove()
    {
        state.remove();
    }

    public void returnEquipment(boolean damaged)
    {
        state.returnEquipment(damaged);
    }

    public RequestState getLend()
    {
        return lend;
    }

    public void setLend(RequestState state)
    {
        this.lend = state;
    }

    public RequestState getRemove()
    {
        return removed;
    }

    public void setRemove(RequestState state)
    {
        this.removed = state;
    }

    public void setDamaged(RequestState damaged){
        this.damaged = damaged;
    }

    public RequestState getDamaged(){
        return this.damaged;
    }

    public void setLoanAble(RequestState loanable){
        this.loanAble = loanable;
    }

    public RequestState getLoanAble(){
        return this.loanAble;
    }

    public double getFine()
    {
        if(state == damaged) return price/3;
        return 0;
    }

    public int getId()
    {
        return id;
    }
}
