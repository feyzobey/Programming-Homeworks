import java.util.Calendar;

public abstract class SmartObject {
    private String alias;
    private String macId;
    private String IP;
    private boolean connectionStatus;

    // public SmartObject() {

    // }

    public boolean getConnectionStatus() {
        return this.connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public boolean connect(String IP) {
        setIP(IP);
        System.out.println(alias + " connection established");
        return connectionStatus = true;
    }

    public boolean disconnect() {
        return connectionStatus = false;
    }

    public void SmartObjectToString() {
        System.out.println("This is SmartObject device " + alias);
        System.out.println("\tMacId: " + macId);
        System.out.println("\tIP:" + IP);
    }

    public boolean controlConnection() {
        if (!connectionStatus) {
            System.out.println("This device is not connected. SmartCamera -> " + alias);
            return false;
        }
        return true;
    }

    public abstract boolean testObject();

    public abstract boolean shutDownObject();

    public String currentTime() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        int second = Calendar.getInstance().get(Calendar.SECOND);
        return hour + ":" + minute + ":" + second;
    }
}