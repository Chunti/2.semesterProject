package dat.startcode.model.services;

public class SVGGenerater {

    int length;
    int width;
    int shedLength;
    int totalLength;

    int rafters;
    int spacing;
    
    final int offset = 50;
    final int sideSpace = 35;
    final int shedOffset = 30;
    

    public SVGGenerater(int length, int width, int shedLenght) {
        this.length = length;
        this.width = width;
        this.shedLength = shedLenght;
        totalLength = length+shedLenght;

        rafters = CarportMath.getRafters(totalLength);
        spacing = CarportMath.getspacing(totalLength,rafters);
    }
    
    public String getFullSVG(){
        String box1 = "0 0 " + (totalLength+offset) + " " + width;
        String box2 = "0 0 " + (totalLength+offset+25) + " " + (width+offset+45);

        SVG innerSvg = createSVGBox(offset,offset,box1);

        innerSvg.addRect(0,0,width,totalLength,3);                                              //roof

        if(shedLength!= 0){
            applyShed(innerSvg);
        }

        applyRem(innerSvg);

        applyPillar(innerSvg);

        applyPerforatedTape(innerSvg);

        applyRafters(innerSvg);

        SVG svg = createSVGBox(0, 0, box2);

        svg.addSvg(innerSvg);
        applyArrows(svg);

        applyRafterArrows(svg);
        return svg.toString();
    }

    private SVG createSVGBox(int x, int y, String box) {
        return new SVG(x, y, box, 100, 100 );
    }

    private void applyRafterArrows(SVG svg) {
        for (int x = 0; x < rafters-1; x++)
        {
            int newPos = (int) ((spacing+1.5)*(x+1));
            svg.addArrowLine(newPos-5,30,offset+newPos,30);
            svg.addText((offset/2)+newPos,15,"0",spacing);
        }
    }

    private void applyRafters(SVG innerSvg) {
        for (int x = 0; x < rafters; x++)
        {
            innerSvg.addRect(spacing * x, 0, width, 5,1);
        }
    }

    private void applyArrows(SVG svg) {
        svg.addArrowDefs();

        svg.addArrowLine(offset-10,offset,offset-10,width+offset+17);
        svg.addText(offset-15,(width/2)+offset,"-90",width);

        svg.addArrowLine(offset,width+offset+30,totalLength+offset+25,width+offset+30);
        svg.addText((totalLength/2)+offset,width+offset+45,"0",totalLength);
    }

    private void applyPerforatedTape(SVG innerSvg) {
        innerSvg.addStrokeLine(spacing,sideSpace,length-shedOffset,width-sideSpace);
        innerSvg.addStrokeLine(spacing,width-sideSpace,length-shedOffset,sideSpace);
    }

    private void applyPillar(SVG innerSvg) {
        int spacing;
        innerSvg.addRect(100,sideSpace-2,10,10,2);
        innerSvg.addRect(100,width-sideSpace-2,10,10,2);

        if(shedLength == 0){
            innerSvg.addRect(length-shedOffset,sideSpace-2,10,10,2);
            innerSvg.addRect(length-shedOffset,width-sideSpace-2,10,10,2);
            spacing = length-shedOffset-100;
            if(width>310){
                innerSvg.addRect(length-10,width/2,10,10,2);
            }
        }
        else{
            innerSvg.addRect(length - 130 - shedOffset, sideSpace -2,10,10,2);
            innerSvg.addRect(length - 130 - shedOffset, width - sideSpace -2,10,10,2);

            spacing = length-130-shedOffset-100;
        }

        if(spacing>310){
            innerSvg.addRect(100+(spacing/2),sideSpace-2,10,10,2);
            innerSvg.addRect(100+(spacing/2),width-sideSpace-2,10,10,2);
        }

    }

    private void applyRem(SVG innerSvg) {
        innerSvg.addRect(0,sideSpace,5,totalLength,1);
        innerSvg.addRect(0,width-sideSpace,5,totalLength,1);
    }

    private void applyShed(SVG innerSvg) {

        innerSvg.addRect(length - shedOffset, sideSpace, width -(sideSpace *2)+5, shedLength,3);

        innerSvg.addRect(length - shedOffset, sideSpace -2,10,10,2);
        innerSvg.addRect(totalLength - shedOffset -10, sideSpace -2,10,10,2);
        innerSvg.addRect(length - shedOffset, width - sideSpace -2,10,10,2);
        innerSvg.addRect(totalLength - shedOffset -10, width - sideSpace -2,10,10,2);

        if(width -(sideSpace *2)>310){
            innerSvg.addRect(length - shedOffset,(width /2)-2,10,10,2);
            innerSvg.addRect(totalLength -40,(width /2)-2,10,10,2);
        }
        if(shedLength >310){
            innerSvg.addRect((length - shedOffset)+(shedLength /2), sideSpace -2,10,10,2);
            innerSvg.addRect((length - shedOffset)+(shedLength /2), width - sideSpace -2,10,10,2);
        }

    }
    
}
