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

    public void load()
    {

    }

    public void repair()
    {

    }

    public void remove()
    {

    }

    public void returnEquipment()
    {

    }

    public RequestState getLoan()
    {
        return loanAble;
    }

    public void setLoan(RequestState state)
    {
        this,loanAble = state;
    }

    public RequestState getRemove()
    {
        return removed;
    }

    public void setRemove()
    {

    }

    public RequestState getRepair()
    {

    }

    public void setRepair(RequestState state)
    {

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
