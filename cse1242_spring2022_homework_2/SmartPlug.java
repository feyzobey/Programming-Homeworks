import java.util.Calendar;

public class SmartPlug extends SmartObject implements Programmable {
    private boolean status;
    private Calendar programTime;
    private boolean programAction;

    public SmartPlug(String alias, String macId) {
        programTime = Calendar.getInstance();
        setAlias(alias);
        setMacId(macId);
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Calendar getProgramTime() {
        return this.programTime;
    }

    public void setProgramTime(Calendar programTime) {
        this.programTime = programTime;
    }

    public boolean getProgramAction() {
        return this.programAction;
    }

    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }

    public void turnOn() {
        if (getConnectionStatus()) {
            if (!status) {
                setStatus(true);
                setProgramAction(false);
                System.out.println(
                         "SmartPlug - " + getAlias() + " is turned on now (Current time: "
                                + programTimeToString()
                                + ")");
            } else {
                System.out.println(
                         "SmartPlug - " + getAlias() + " has been already turned on");
            }
        }
    }

    public void turnOff() {
        if (getConnectionStatus()) {
            if (status) {
                setStatus(false);
                setProgramAction(true);
                System.out.println("SmartPlug - " + getAlias() + "is turned off now (Current time: "
                        + programTimeToString() + ")");
            } else {
                System.out.println("SmartPlug - " + getAlias() + "has been already turned off");
            }
        }
    }

    public boolean testObject() {
        if (getConnectionStatus() && !status) {
            SmartObjectToString();
            turnOn();
            turnOff();
            System.out.println("Test completed for SmartPlug");
            return true;
        }
        return false;

    }

    public boolean shutDownObject() {
        if (getConnectionStatus() && status) {
            SmartObjectToString();
            turnOff();
            return true;
        }
        return false;
    }

    @Override
    public void setTimer(int seconds) {
        if (getConnectionStatus()) {
            setProgramTime(programTime);
            if (status) {
                System.out.println("SmartPlug - " + getAlias() + " will be turned off " + seconds
                        + " seconds later! (Current time: " + programTimeToString() + ")");
            } else {
                System.out.println("SmartPlug - " + getAlias() + " will be turned on " + seconds
                        + " seconds later! (Current time: " + programTimeToString() + ")");
            }
            programTime.add(Calendar.SECOND, seconds);

        }
    }

    @Override
    public void cancelTimer() {
        if (getConnectionStatus()) {
            programTime = null;
        }
    }

    @Override
    public void runProgram() {
        boolean isTimeProperly = (programTime != null);
        if (isTimeProperly && getConnectionStatus() && programTimeToString().equals(currentTime())) {
            System.out.println("RunProgram -> SmartPlug " + getAlias());
            if (programAction) {
                turnOn();
            } else {
                turnOff();
            }
            programTime = null;
        }
    }

    public String programTimeToString() {
        return programTime.get(Calendar.HOUR_OF_DAY) + ":" + programTime.get(Calendar.MINUTE) + ":"
                + programTime.get(Calendar.SECOND);
    }
}