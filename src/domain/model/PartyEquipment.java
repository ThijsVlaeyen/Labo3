package domain.model;

public class PartyEquipment  {
    RequestState loanAble;
    RequestState lend;
    RequestState demaged;
    RequestState removed;

    private double price;
    public String name;
    public int id;
    private RequestState state;

    public PartyEquipment(double price, String name)
    {
        this.price = price;
        this.name = name;
        loanAble = new Lendable();
        lend = new Lend();
        demaged = new Damaged();
        removed = new Removed();

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

    public void returnEquipment()
    {
        state.returnEquipment();
    }

    public RequestState getLoan()
    {
        return loanAble;
    }

    public void setLoan(RequestState state)
    {
        this.loanAble = state;
    }

    public RequestState getRemove()
    {
        return removed;
    }

    public void setRemove(RequestState state)
    {
        this.removed = state;
    }

    public double getFine()
    {
        if(state == demaged) return price/3;
        return 0;
    }

    public int getId()
    {
        return id;
    }
}
