package dat.startcode.model.services;

public class CarportMath {
    public CarportMath() {
    }

    public static int getRafters(int totalLength) {
        int rafters = (int) Math.ceil(totalLength /59.0) + 1;
        return rafters;
    }

    public static int getspacing(int totalLength, int rafters) {
        int mellemrum = totalLength /(rafters -1);
        return mellemrum;
    }

    public static int getShedPlanks(int shedLength, int width){
        int perimeter = (shedLength*2)+((width-70)*2);

        int amountOfPlanks = (int) (java.lang.Math.ceil((perimeter/8.0)/50.0)*50);
        return amountOfPlanks;
    }

    public static int getPosts(int length, int width) {
        int posts = 0;

        length -= 200;
        if(length>620) posts += 8;
        else if(length>310) posts += 6;
        else posts += 4;
        width -=70;
        if(width>310) posts +=1;

        return posts;
    }

    public static int getPostsWithShed(int length, int width, int shedLength){
        int posts = 5;

        length -= 100;
        if(length>620) posts += 6;
        else if(length>310) posts += 4;
        else posts += 2;

        if(shedLength>310) posts +=2;
        width -=70;
        if(width>310) posts +=2;

        return posts;
    }

    public static int[] getStern(int length){
        int plank[] = new int[2];

        int shortPlankAmount = (int) Math.ceil(length /360.0);
        int longPlankAmount = (int) Math.ceil(length /540.0);

        if(shortPlankAmount==longPlankAmount){
            plank[0] = shortPlankAmount*2;
            plank[1] = 360;
        }
        else{
            plank[0] = longPlankAmount*2;
            plank[1] = 540;
        }

        return plank;
    }

    public static int[] getLooseWood(int length){
        int looseWood[] = new int[2];
        looseWood[0]  = 1;

        while (length>=310){
            length = length/2;
            looseWood[0]++;
        }
        looseWood[0] = looseWood[0]*2;

        if(length>240){
            looseWood[1] = (int) Math.ceil((length-240) /30.0)*30+240;
        }
        else looseWood[1] = 240;


        return looseWood;
    }

    public static int[] getRoofingSheets(int length, int width){
        int lengthCounter = 0;
        int ekstraLengthCounter = 0;
        int newLength = length;
        while (newLength> 600){

            newLength = newLength-600;
            if(newLength> 340 && newLength<600){
                lengthCounter++;
                newLength = 0;
            }
            lengthCounter++;
        }

        if(newLength> 0){
            ekstraLengthCounter = 1;
        }

        int multiplyer = (int) Math.ceil(width/100.0);

        int roofingSheets[] = new int[2];
        roofingSheets[0] = lengthCounter * multiplyer;
        roofingSheets[1] = ekstraLengthCounter * multiplyer;

        return roofingSheets;
    }

    public static int getPerforatedTape(int length, int width){
        int perforatedTape = 1;

        int a = length*length;
        int b = width*width;
        int c = (int) Math.sqrt(a+b);
        if(c>1000){
            perforatedTape+=2;
        }
        else if(c>500){
            perforatedTape++;
        }

        return perforatedTape;
    }

}
