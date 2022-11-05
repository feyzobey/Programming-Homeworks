public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera> {
    private boolean status;
    private int batteryLife;
    private boolean nightVision;

    public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
        setAlias(alias);
        setMacId(macId);
        this.nightVision = nightVision;
        this.batteryLife = batteryLife;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBatteryLife() {
        return this.batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public boolean getNightVision() {
        return this.nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }

    public void recordOn(boolean isDay) {
        if (getConnectionStatus()) {
            if (isDay || nightVision) {
                if (!status) {
                    setStatus(true);
                    System.out.println("Smart Camera - " + getAlias() + " is turned on now");
                } else {
                    System.out.println("Smart Camera - " + getAlias() + " has been already turned on");
                }
            } else {
                System.out.println("Sorry! Smart Camera - " + getAlias() + " does not have night vision feature.");
            }
        }
    }

    public void recordOff() {
        if (getConnectionStatus()) {
            if (status) {
                setStatus(false);
                System.out.println("Smart Camera - " + getAlias() + " is turned off now");
            } else {
                System.out.println("Smart Camera - " + getAlias() + " has been already turned off");
            }
        }
    }

    public boolean testObject() {
        if (getConnectionStatus()) {
            SmartObjectToString();
            System.out.println("Test is starting for Smart Camera day time");
            recordOn(true);
            recordOff();
            System.out.println("Test is starting for Smart Camera night time");
            recordOn(false);
            recordOff();
            System.out.println("Test completed for Smart Camera");
            return true;
        }
        return false;
    }

    public boolean shutDownObject() {
        if (getConnectionStatus()) {
            SmartObjectToString();
            setStatus(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean controlMotion(boolean hasMotion, boolean isDay) {
        if (hasMotion) {
            System.out.println("Motion detected");

            if (isDay) {
                recordOn(isDay);
            } else {
                setStatus(nightVision);
            }
            return true;

        } else {
            System.out.println("Motion not detected");
            return false;
        }
    }

    @Override
    public int compareTo(SmartCamera smartCamera) {
        return Integer.compare(this.batteryLife, smartCamera.batteryLife);
    }

    @Override
    public String toString() {
        return "SmartCamera -> " + getAlias() + "'s battery life is " + batteryLife + " status "
                + (status ? "is recording" : "is not recording");
    }
}