package frc.robot.applications;

public class Calcs {

    private double seno1 = 0;
    private double seno2 = 0;
    private double mag1 = 0;
    private double mag2 = 0;
    public double lt, rt;
    public double L_stickY, L_stickX, R_stickY, R_stickX;
    public double spd = 1;
    public int pov;
    public boolean an_L = true;
    public boolean an_R = true;


    public Calcs(double L_stickY, double L_stickX, double R_stickY, double R_stickX, double lt, double rt) {
        this.L_stickX = L_stickX;
        this.L_stickY = -L_stickY;
        this.lt = lt;
        this.rt = rt;
        this.R_stickX = R_stickX;
        this.R_stickY = -R_stickY;


    }
    public void analogicValues() {
        this.mag1 = Math.hypot(L_stickX, L_stickY);
        this.mag2 = Math.hypot(R_stickX, R_stickY);
        this.seno1 =  L_stickY / mag1;
        this.seno2 =  R_stickY / mag2;
        this.lt = -lt;
    }

    public void  activityAnalog(){
        double motors[] = new double[2];
        if (mag1 > 0.04) {
            an_L = true;
            an_R = false;
        }
        else if (mag2 > 0.04) {
            an_R = true;
            an_L = false;

        }
        else {
            motors[0] = 0.0;
            motors[1] = 0.0;
        }

    }
    public double [] setdriver(){

        double motors[] = new double[2];
        analogicValues();
        activityAnalog();

        //left analogic
        //quad1. x> 0 e y>0
        if(an_L == true){
            if (L_stickX >= 0 && L_stickY >= 0) {
                motors[0] = mag1;
                motors[1] = seno1*mag1;
            }
            // quad2. x<0 e y>0
            else if (L_stickX < 0 && L_stickY >= 0) {
                motors[0] = (2 * seno1 - 1) * mag1;
                motors[1] = mag1;
            }
            // quad3. x>0 e y<0
            else if (L_stickX >= 0 && L_stickY < 0) {
                motors[0] = -mag1;
                motors[1] =(2 * seno1 + 1) * mag1;
            }
            // quad4. x<0 e y<0
            else if (L_stickX < 0 && L_stickY < 0) {
                motors[0] = seno1* mag1;
                motors[1] = -mag1;
            }

        }
        else if (an_R == true){
            if (R_stickX >= 0 && R_stickY >= 0) {
                motors[0] = -seno2 * mag2;
                motors[1] = -mag2;
            }
            //quad2. x<0 e y>0
            else if (R_stickX < 0 && R_stickY >= 0) {
                motors[0] = -mag2;
                motors[1] = -seno2 * mag2;
            }
            //quad3. x>0 e y<0
            else if (R_stickX >= 0 && R_stickY < 0) {
                motors[0] = -seno2 * mag2;
                motors[1] = mag2;
            }
            //quad4. x<0 e y<0
            else if (R_stickX < 0 && R_stickY < 0) {
                motors[0] = mag2;
                motors[1] = -seno2* mag2;
            }
        }

        return motors;
    }

    public static double[] calcPov ( int pov, double spd){
        double motors[] = new double[2];

        switch (pov) {
            case 0:
                motors[0] = 1;
                motors[1] = 1;
                break;

            case 45:
                motors[0] = 1;
                motors[1] = 0;
                break;

            case 90:
                motors[0] = 1;
                motors[1] = -1;
                break;

            case 135:
                motors[0] = -1;
                motors[1] = 0;
                break;

            case 180:
                motors[0] = -1;
                motors[1] = -1;
                break;

            case 225:
                motors[0] = 0;
                motors[1] = -1;
                break;

            case 270:
                motors[0] = -1;
                motors[1] = 1;
                break;

            case 315:
                motors[0] = 0;
                motors[1] = 1;
                break;

            default:
                motors[0] = 0;
                motors[1] = 0;

        }
        motors[0] *=spd;
        motors[1] *=spd;
        return motors;

    }
}

/*if(lt !=0.04){
    lt*=spd;

}
else if(lt >=0.04 && L_stickX >=0.04){
    motors[1] = lt *spd;
    motors[0] = lt * (1 - L_stickX)*spd ;
}
else if(L_stickX < 0.04 && lt > 0.04){
    motors[1] = lt* (1 + L_stickX) *spd;
    motors[0] = lt *spd;
}
if(rt!=0.04){
    rt *=spd;
}
else if(L_stickX > 0.04 && rt > 0.04){
    motors [1] = rt* (1 - L_stickX)*spd ;
    motors[0] = rt*spd;
}
else if(L_stickX < 0.04 && rt > 0.04){
    motors[1] = rt*spd ;
    motors[0] = rt * (1 + L_stickX)*spd;
}*/