
/*

Author: Ruite Guo
School: Univeristy of Wisconsin-Madison
 */

public class NBody {

    public static void main (String [] args) {

        if (args.length != 3) {
            System.out.println("Please supply T, dt and filename.");
            System.exit(0);
        }

        double T = 0;
        double dT = 0;

        try {
             T = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            System.out.println(" the provided T cannot be parsed to double.");
            System.exit(0);
        }


        try {
             dT = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(" the provided dT cannot be parsed to double.");
            System.exit(0);
        }


        String fileName = args[2];

        Double radius = readRadius(fileName);

        Planet [] pArray = readPlanets(fileName);

        double scaleFactor = 1.0E9;



        StdDraw.setScale(-radius/scaleFactor, radius/scaleFactor);

        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(0,0,"./images/starfield.jpg");
        for (Planet P : pArray) {
            P.draw();
        }
        StdDraw.show(10);





        double time = 0;

        StdAudio.play("audio/2001.mid");
        StdDraw.clear();
       // StdDraw.picture(0,0,"./images/starfield.jpg");

       while (time <= T) {




           double [] xForces = new double [pArray.length];
           double [] yForces = new double [pArray.length];

           for (int i = 0; i<pArray.length;i++) {
               xForces[i] = pArray[i].calcNetForceExertedByX(pArray);
               yForces[i] = pArray[i].calcNetForceExertedByY(pArray);
           }

           for (int i = 0; i<pArray.length;i++) {
               pArray[i].update(dT, xForces[i], yForces[i]);
           }
           StdDraw.picture(0,0,"./images/starfield.jpg");

           for (int i = 0; i<pArray.length;i++) {

               pArray[i].draw();


           }
           StdDraw.show(10);
           time += dT;
       }








    }



    public static double readRadius (String dir) {


        In dirr = new In (dir);

        int planetNum = dirr.readInt();

        Double radius = dirr.readDouble();

        if (radius == (double) planetNum) {
            radius = dirr.readDouble();
        }

        return radius;

    }

    public static Planet [] readPlanets (String dir) {
        In in = new In (dir);

        int planetNum = in.readInt();

        Planet [] pArray = new Planet [planetNum];

        String [] words = in.readAllStrings();


            for (int i = 0; i< planetNum; i++) {
                double x = Double.parseDouble(words[1 + i*6]);
                double y = Double.parseDouble(words[2 + i*6]);
                double xV = Double.parseDouble(words[3 + i * 6 ]);
                double yV = Double.parseDouble(words[4 + i * 6]) ;
                double mass = Double.parseDouble(words[5 + i * 6]);
                String imgFile = words[6 + i*6];

                pArray [i]= new Planet (x,y,xV,yV,mass,imgFile);

            }



        return pArray;

    }
}
