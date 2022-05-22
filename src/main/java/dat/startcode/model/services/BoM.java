package dat.startcode.model.services;

public class BoM {

    StringBuilder BoM = new StringBuilder();
    int orderId;

    private final String headerTemplate = "insert into bom (orderId, itemId, amount) values";

    private final String mySQLInsert ="(%d,%d,%d)";
    private final String addSeperation = ", \n";

    public BoM(int orderId){
        this.orderId = orderId;
        BoM.append(headerTemplate);
    }

    public void addInsert(int itemId, int amount) {
        BoM.append(String.format(mySQLInsert,orderId,itemId,amount));
        BoM.append(addSeperation);
    }

    public void addLastInsert(int itemId, int amount) {
        BoM.append(String.format(mySQLInsert,orderId,itemId,amount));
    }

    @Override
    public String toString()
    {
        return BoM.toString() + ";" ;
    }

}
