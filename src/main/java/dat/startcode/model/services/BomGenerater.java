package dat.startcode.model.services;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Order;

public class BomGenerater {

    int length;
    int width;
    int shedLength;
    int totalLength;
    BoM bom;

    public BomGenerater(Order order) {

        this.length = order.getLength();
        this.width = order.getWidth();
        this.shedLength = order.getShedLength();
        totalLength = length + shedLength;

        bom = new BoM(order.getOrderId());
    }

    public String createBoM(){

        applyStern();

        applyLooseWood();
        applySpecificMaterial(8,2); //Rem
        applyRafters();
        applyPillars();
        if(shedLength>0){
            applySpecificMaterial(5,1); //Z til døren
            applyShedMaterial();
            applySpecificMaterial(19,1);
            applySpecificMaterial(25,1);
            applySpecificMaterial(26,2);
        }
        applyRoofingSheets();
        applyPerforatedTape();
        return bom.toString();
    }

    private void applySpecificMaterial(int itemId, int amount){
        bom.addInsert(itemId,amount);
    }

    private void applyStern(){
        int[] underSternWidth = CarportMath.getStern(width);
        int[] underSternLength = CarportMath.getStern(totalLength);

        int[] overSternWidth = CarportMath.getStern(width);
        int[] overSternLength = CarportMath.getStern(totalLength);

        if(underSternWidth[1] == 360) bom.addInsert(1,underSternWidth[0]);
        if(underSternWidth[1] == 540) bom.addInsert(2,underSternWidth[0]);

        if(underSternLength[1] == 360) bom.addInsert(1,underSternLength[0]);
        if(underSternLength[1] == 540) bom.addInsert(2,underSternLength[0]);

        if(overSternWidth[1] == 360){
            bom.addInsert(3,overSternWidth[0]/2);
            if(shedLength>0) bom.addInsert(12,overSternWidth[0]/2);
        }
        if(overSternWidth[1] == 540){
            bom.addInsert(4,overSternWidth[0]/2);
            if(shedLength>0) bom.addInsert(11,overSternWidth[0]/2);
        }

        if(overSternLength[1] == 360) {
            bom.addInsert(3,overSternLength[0]);
            if(shedLength>0) bom.addInsert(12,overSternLength[0]);
        }
        if(overSternLength[1] == 540){
            bom.addInsert(4,overSternLength[0]);
            if(shedLength>0) bom.addInsert(11,overSternLength[0]);
        }


    }

    private void applyLooseWood(){

        int[] looseWoodLength = CarportMath.getLooseWood(shedLength,2);
        int[] looseWoodWidth = CarportMath.getLooseWood(width,3);

        if(looseWoodLength[1] == 240) bom.addInsert(7,looseWoodLength[0]);
        else bom.addInsert(6,looseWoodLength[0]);

        if(looseWoodWidth[1] == 240) bom.addInsert(7,looseWoodWidth[0]);
        else bom.addInsert(6,looseWoodWidth[0]);

        bom.addInsert(27,(looseWoodLength[0]+looseWoodWidth[0])*2);

    }

    private void applyRafters(){
        int rafters = CarportMath.getRafters(totalLength);
        bom.addInsert(8, rafters);
        bom.addInsert(17,rafters);
        bom.addInsert(18,rafters);
    }

    private void applyPillars() {
        int posts;
        if(shedLength == 0) posts = CarportMath.getPosts(length,width);
        else posts = CarportMath.getPostsWithShed(length,width,shedLength);


        bom.addInsert(9,posts);

        int squareDiscs = (((posts-4)*2)+4);
        int boardBolt = (posts*2)+4;

        bom.addInsert(21,boardBolt);
        bom.addInsert(22,squareDiscs);


    }

    private void applyShedMaterial() {

        int planks = CarportMath.getShedPlanks(shedLength,width);
        bom.addInsert(10,planks);

        int screws = 0;
        do{
            screws++;
            planks-=100;

        }while(planks>=0);
        bom.addInsert(23,screws);
        bom.addInsert(24,screws);
    }

    private void applyRoofingSheets(){
        int[] roofingSheets = CarportMath.getRoofingSheets(totalLength,width);

        int area = (totalLength*width)/3;
        bom.addInsert(13,roofingSheets[0]);
        bom.addInsert(14,roofingSheets[1]);

        int screws = 0;
        do{
            screws++;
            area-=160000;

        }while(area>0);
        bom.addInsert(15,screws);

    }

    private void applyPerforatedTape(){
        int PerforatedTape = CarportMath.getPerforatedTape(length,width);
        bom.addInsert(16,PerforatedTape);
        bom.addLastInsert(20,PerforatedTape+1);
    }
}
