package Nazmun;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
    private final StringProperty roomNo;
    private final StringProperty roomType;
    private final StringProperty rate;
    private final StringProperty occupancyStatus;

    public Room(String roomNo, String roomType, String rate, String occupancyStatus) {
        this.roomNo = new SimpleStringProperty(roomNo);
        this.roomType = new SimpleStringProperty(roomType);
        this.rate = new SimpleStringProperty(rate);
        this.occupancyStatus = new SimpleStringProperty(occupancyStatus);
    }

    public String getRoomNo() { return roomNo.get(); }
    public void setRoomNo(String v) { roomNo.set(v); }
    public StringProperty roomNoProperty() { return roomNo; }

    public String getRoomType() { return roomType.get(); }
    public void setRoomType(String v) { roomType.set(v); }
    public StringProperty roomTypeProperty() { return roomType; }

    public String getRate() { return rate.get(); }
    public void setRate(String v) { rate.set(v); }
    public StringProperty rateProperty() { return rate; }

    public String getOccupancyStatus() { return occupancyStatus.get(); }
    public void setOccupancyStatus(String v) { occupancyStatus.set(v); }
    public StringProperty occupancyStatusProperty() { return occupancyStatus; }
}