/*
Author: Ruite Guo
School: Univeristy of Wisconsin-Madison
 */


public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
      xxPos=xP;
      yyPos=yP;
      xxVel=xV;
      yyVel=yV;
      mass=m;
      imgFileName=img;

    }

    public Planet(Planet p) {
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p) {
       double pX = p.xxPos;
       double pY = p.yyPos;

       double dx = pX-xxPos;
       double dy = pY - yyPos;

       double dr = Math.sqrt(dx*dx + dy*dy);

        return dr;
    }


    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance (p);

         double G = 6.67 * Math.pow(10, -11);
        double Force =   G * mass * p.mass/(distance * distance);

        return Force;
    }

    public double calcForceExertedByX (Planet p) {

        double totalF = calcForceExertedBy(p);

        double distance = calcDistance(p);
        return (totalF) * (p.xxPos-xxPos)/distance;

    }

    public double calcForceExertedByY (Planet p) {

        double totalF = calcForceExertedBy(p);

        double distance = calcDistance(p);

        return (totalF) * (p.yyPos-yyPos)/distance;
    }

    public double calcNetForceExertedByX (Planet [] pArray) {


        double [] forceArray = new double [pArray.length];

        double netX = 0;
        for (int i = 0; i<pArray.length; i++ ) {
            if (comparePlanet(pArray[i])) {
                continue;
            }

            forceArray[i] = calcForceExertedByX(pArray[i]);

            netX += forceArray[i];
        }

        return netX;

    }

    public double calcNetForceExertedByY (Planet [] pArray) {

        double [] forceArray = new double [pArray.length];

        double netY = 0;
        for (int i = 0; i<pArray.length; i++ ) {
            if (comparePlanet(pArray[i])) {
                continue;
            }

            forceArray[i] = calcForceExertedByY(pArray[i]);

            netY += forceArray[i];
        }

        return netY;
    }

    private boolean comparePlanet (Planet p) {
        if (xxPos == p.xxPos
                && yyPos == p.yyPos
                && mass == p.mass
                && xxVel == p.xxVel
                && yyVel == p.yyVel
                && mass == p.mass
                && imgFileName.equals(p.imgFileName)) {
            return true;
        }

        return false;
    }

    public void update (double seconds, double xF, double yF ) {

        double aX = xF / mass;

        double aY = yF / mass;

        // calculate new velocity

        xxVel = xxVel + aX * seconds;

        yyVel = yyVel + aY * seconds;

        // calculate new position

        xxPos = xxPos + seconds * xxVel;
        yyPos = yyPos + seconds * yyVel;


    }

    public void draw () {
        int waitTimeMilliseconds = 100;
        waitTimeMilliseconds = waitTimeMilliseconds - 1;
        if (waitTimeMilliseconds < 1) {
            waitTimeMilliseconds = 10;
        }
        double scaleFactor = 1.0E9;
        StdDraw.picture(xxPos/scaleFactor, yyPos/scaleFactor,"./images/" +imgFileName);
        //StdDraw.show(waitTimeMilliseconds);
    }




}
