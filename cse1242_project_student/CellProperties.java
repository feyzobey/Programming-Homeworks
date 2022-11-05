import java.io.Serializable;
import javafx.scene.layout.StackPane;

public class CellProperties extends StackPane implements Serializable {
    public static final long serialVersionUID = 1L;
    private int cellId;
    private String type;
    private String property;

    public CellProperties(int cellId, String type, String property) {
        this.cellId = cellId;
        this.type = type;
        this.property = property;
    }

    public int getCellId() {
        return this.cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

}
