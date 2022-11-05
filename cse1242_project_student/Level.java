import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Level {
    static final DataFormat cellPanes = new DataFormat("Cell's on the grid pane");
    private int levelNumber;
    private int counter;
    private GridPane level;
    private ArrayList<CellProperties> cellList;
    private ArrayList<ImageView> images;
    private CellProperties draggingCell;
    private CellProperties targetCell;
    private CellProperties starterCell;
    private CellProperties endCell;
    private CellProperties movingToCell;
    private CellProperties comeFromCell;
    private CellProperties temp;
    private Path path = new Path();
    private Circle circle = new Circle(10, Color.BLUEVIOLET);
    private boolean isLevelCompleted = false;
    private boolean animation = false;
    private int mX;
    private int mY;
    private int lX;
    private int lY;
    private int hlX;

    // ctor
    public Level(int levelNumber) throws Exception {
        this.levelNumber = levelNumber;
        level = new GridPane();
        cellList = new ArrayList<CellProperties>();
        counter = 0;
        levelConstructor();
        levelEvents();
    }

    public int getLevelNumber() {
        return this.levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public GridPane getLevel() {
        return this.level;
    }

    public void setLevel(GridPane level) {
        this.level = level;
    }

    public ArrayList<CellProperties> getCellList() {
        return this.cellList;
    }

    public void setCellList(ArrayList<CellProperties> cellList) {
        this.cellList = cellList;
    }

    public ArrayList<ImageView> getImages() {
        return this.images;
    }

    public void setImages(ArrayList<ImageView> images) {
        this.images = images;
    }

    public CellProperties getDraggingCell() {
        return this.draggingCell;
    }

    public void setDraggingCell(CellProperties draggingCell) {
        this.draggingCell = draggingCell;
    }

    public CellProperties getTargetCell() {
        return this.targetCell;
    }

    public void setTargetCell(CellProperties targetCell) {
        this.targetCell = targetCell;
    }

    public CellProperties getStarterCell() {
        return this.starterCell;
    }

    public void setStarterCell(CellProperties starterCell) {
        this.starterCell = starterCell;
    }

    public CellProperties getEndCell() {
        return this.endCell;
    }

    public void setEndCell(CellProperties endCell) {
        this.endCell = endCell;
    }

    public CellProperties getMovingToCell() {
        return this.movingToCell;
    }

    public void setMovingToCell(CellProperties movingToCell) {
        this.movingToCell = movingToCell;
    }

    public CellProperties getComeFromCell() {
        return this.comeFromCell;
    }

    public void setComeFromCell(CellProperties comeFromCell) {
        this.comeFromCell = comeFromCell;
    }

    public CellProperties getTemp() {
        return this.temp;
    }

    public void setTemp(CellProperties temp) {
        this.temp = temp;
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public boolean getIsLevelCompleted() {
        return this.isLevelCompleted;
    }

    public void setIsLevelCompleted(boolean isLevelCompleted) {
        this.isLevelCompleted = isLevelCompleted;
    }

    public static DataFormat getCellpanes() {
        return cellPanes;
    }

    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getmY() {
        return mY;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }

    public int getlX() {
        return lX;
    }

    public void setlX(int lX) {
        this.lX = lX;
    }

    public int getlY() {
        return lY;
    }

    public void setlY(int lY) {
        this.lY = lY;
    }

    public int gethlX() {
        return hlX;
    }

    public void sethlX(int hlX) {
        this.hlX = hlX;
    }

    public boolean getAnimation() {
        return this.animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    public GridPane levelConstructor() throws Exception {
        File levelTxtFile = new File("./levels/level" + getLevelNumber() + ".txt");
        Scanner scanner = new Scanner(levelTxtFile);
        while (scanner.hasNextLine()) {
            String[] lines = scanner.nextLine().split(",");

            int cellId = Integer.parseInt(lines[0]) - 1;
            String type = lines[1];
            String property = lines[2];

            CellProperties cell = new CellProperties(cellId, type, property);
            switch (type) {
                case "Empty":
                    if (property.equals("none"))
                        cell.getChildren()
                                .add(new ImageView(new Image("./projectImages/EmptyNone.png", 100, 100, true, true)));
                    else if (property.equals("Free"))
                        cell.getChildren()
                                .add(new ImageView(new Image("./projectImages/EmptyFree.png", 100, 100, true, true)));
                    break;
                case "Pipe":
                    if (property.equals("Vertical"))
                        cell.getChildren()
                                .add(new ImageView(
                                        new Image("./projectImages/PipeVertical.png", 100, 100, true, true)));
                    else if (property.equals("Horizontal"))
                        cell.getChildren()
                                .add(new ImageView(
                                        new Image("./projectImages/PipeHorizontal.png", 100, 100, true, true)));
                    else if (property.equals("00"))
                        cell.getChildren()
                                .add(new ImageView(new Image("./projectImages/Pipe00.png", 100, 100, true, true)));
                    else if (property.equals("01"))
                        cell.getChildren()
                                .add(new ImageView(new Image("./projectImages/Pipe01.png", 100, 100, true, true)));
                    else if (property.equals("10"))
                        cell.getChildren()
                                .add(new ImageView(new Image("./projectImages/Pipe10.png", 100, 100, true, true)));
                    else if (property.equals("11"))
                        cell.getChildren()
                                .add(new ImageView(new Image("./projectImages/Pipe11.png", 100, 100, true, true)));
                    break;
                case "Starter":
                    if (property.equals("Vertical"))
                        cell.getChildren().add(new ImageView(
                                new Image("./projectImages/StarterVertical.png", 100, 100, true, true)));
                    else if (property.equals("Horizontal"))
                        cell.getChildren().add(
                                new ImageView(
                                        new Image("./projectImages/StarterHorizontal.png", 100, 100, true, true)));
                    break;
                case "End":
                    if (property.equals("Vertical"))
                        cell.getChildren().add(
                                new ImageView(new Image("./projectImages/EndVertical.png", 100, 100, true, true)));
                    else if (property.equals("Horizontal"))
                        cell.getChildren()
                                .add(new ImageView(
                                        new Image("./projectImages/EndHorizontal.png", 100, 100, true, true)));
                    break;
                case "PipeStatic":
                    if (property.equals("Vertical"))
                        cell.getChildren()
                                .add(new ImageView(
                                        new Image("./projectImages/PipeStaticVertical.png", 100, 100, true, true)));
                    else if (property.equals("Horizontal"))
                        cell.getChildren().add(
                                new ImageView(
                                        new Image("./projectImages/PipeStaticHorizontal.png", 100, 100, true, true)));
                    else if (property.equals("01"))
                        cell.getChildren()
                                .add(new ImageView(
                                        new Image("./projectImages/PipeStatic01.png", 100, 100, true, true)));
                    break;
            }

            cellList.add(cell);
            level.add(cell, cellId % 4, cellId / 4);
        }
        scanner.close();
        return level;
    }

    private void dragDetected(MouseEvent e, CellProperties cell) {
        // onlY empty and pipe cells can move
        if (!((CellProperties) cell).getType().equals("Starter") && !((CellProperties) cell).getType().equals("End")
                && !((CellProperties) cell).getType().equals("PipeStatic")
                && !((CellProperties) cell).getProperty().equals("Free")
                && !isLevelCompleted) {
            Dragboard dragBoard = cell.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.put(cellPanes, (CellProperties) cell);
            dragBoard.setContent(content);
            draggingCell = (CellProperties) cell;
        } else {
            e.consume();
        }
    }

    private void dragOver(DragEvent e, CellProperties cell) {
        Dragboard dragBoard = e.getDragboard();
        // cells can onlY move to free spaces and to left, right, down or up, not
        // diagonal
        if (e.getGestureSource() != (CellProperties) cell && dragBoard.hasContent(cellPanes)
                && ((CellProperties) cell).getProperty().equals("Free")
                && (((CellProperties) cell).getCellId() == draggingCell.getCellId() + 1
                        || ((CellProperties) cell).getCellId() == draggingCell.getCellId() - 1
                        || ((CellProperties) cell).getCellId() == draggingCell.getCellId() + 4
                        || ((CellProperties) cell).getCellId() == draggingCell.getCellId() - 4)) {
            e.acceptTransferModes(TransferMode.ANY);
        } else {
            e.consume();
        }
    }

    private void dragDropped(DragEvent e, CellProperties cell) {
        boolean dragCompleted = false;
        Dragboard dragBoard = e.getDragboard();
        targetCell = (CellProperties) cell;

        // if target cell is valid swap cells
        if (dragBoard.hasContent(cellPanes) && !(targetCell.getType().equals("Starter"))
                && !(targetCell.getType().equals("End")) && !(targetCell.getType().equals("PipeStatic"))) {
            getLevel().getChildren().remove(targetCell);
            getLevel().getChildren().remove(draggingCell);
            getLevel().add(targetCell, draggingCell.getCellId() % 4, draggingCell.getCellId() / 4);
            getLevel().add(draggingCell, targetCell.getCellId() % 4, targetCell.getCellId() / 4);
            int temp = targetCell.getCellId();
            targetCell.setCellId(draggingCell.getCellId());
            cellList.set(draggingCell.getCellId(), targetCell);
            draggingCell.setCellId(temp);
            cellList.set(temp, draggingCell);
            dragCompleted = true;
            counter++;
        }
        e.setDropCompleted(dragCompleted);
        e.consume();
    }

    private void dragDone(DragEvent e) {
        System.out.println(counter);
        // after each move control to is the level completed
        comeFromCell = null;
        movingToCell = null;
        findStarterAndEndCell();
        determineWhereToMove();
        checkLevelCompleted();

        // if level completed is true
        if (isLevelCompleted) {
            // add circle on pane and create animation
            level.getChildren().add(circle);

            PathTransition pathTransition = new PathTransition();
            pathTransition.setNode(circle);
            pathTransition.setPath(path);
            pathTransition.setDuration(Duration.millis(4000));
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(false);
            pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    animation = true;
                }
            });
            pathTransition.play();
            Text gameOverText = new Text();
            gameOverText.setTextAlignment(TextAlignment.CENTER);
            gameOverText.setText("You Won! CONGRATILATIONS!!");
        } else {
            // clear circle road which in pane
            path.getElements().clear();
        }

        e.consume();
    }

    public void levelEvents() {
        getLevel().getChildren().forEach(cell -> cell.setOnDragDetected(e -> dragDetected(e, (CellProperties) cell)));
        getLevel().getChildren().forEach(cell -> cell.setOnDragOver(e -> dragOver(e, (CellProperties) cell)));
        getLevel().getChildren().forEach(cell -> cell.setOnDragDropped(e -> dragDropped(e, (CellProperties) cell)));
        getLevel().getChildren().forEach(cell -> cell.setOnDragDone(e -> dragDone(e)));
    }

    // find cell with specific cell id
    public CellProperties getCell(int cellId) {
        CellProperties cell = null;
        for (int i = 0; i < cellList.size(); i++) {
            if (cellId == i) {
                cell = cellList.get(i);
            }
        }
        return cell;
    }

    // find starter and end cell
    public void findStarterAndEndCell() {
        // find starter cell
        for (int i = 0; i < cellList.size(); i++) {
            if (cellList.get(i).getType().equals("Starter")) {
                starterCell = cellList.get(i);
            }
        }

        // find end cell
        for (int i = 0; i < cellList.size(); i++) {
            if (cellList.get(i).getType().equals("End")) {
                endCell = cellList.get(i);
            }
        }
    }

    public void determineWhereToMove() {

        // if starter cell is vertical
        if (starterCell.getProperty().equals("Vertical")) {
            movingToCell = getCell(starterCell.getCellId() + 4);
            comeFromCell = starterCell;
            // determine the next path is appropriate or not
            if (movingToCell.getProperty().equals("00")
                    || movingToCell.getProperty().equals("01")
                    || movingToCell.getProperty().equals("Vertical")) {

                // determine and create starterCell's coordinate
                switch (starterCell.getCellId()) {
                    case 0:
                        mX = 100;
                        mY = 0;
                        lX = 100;
                        lY = 100;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 1:
                        mX = 200;
                        mY = 0;
                        lX = 200;
                        lY = 100;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 2:
                        mX = 300;
                        mY = 0;
                        lX = 300;
                        lY = 100;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 3:
                        mX = 400;
                        mY = 0;
                        lX = 400;
                        lY = 100;
                        hlX = 400;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 4:
                        mX = 100;
                        mY = 100;
                        lX = 100;
                        lY = 200;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 5:
                        mX = 200;
                        mY = 100;
                        lX = 200;
                        lY = 200;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 6:
                        mX = 300;
                        mY = 100;
                        lX = 300;
                        lY = 200;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 7:
                        mX = 400;
                        mY = 100;
                        lX = 400;
                        lY = 200;
                        hlX = 400;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 8:
                        mX = 100;
                        mY = 200;
                        lX = 100;
                        lY = 300;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 9:
                        mX = 200;
                        mY = 200;
                        lX = 200;
                        lY = 300;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 10:
                        mX = 300;
                        mY = 200;
                        lX = 300;
                        lY = 300;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 11:
                        mX = 400;
                        mY = 200;
                        lX = 400;
                        lY = 300;
                        hlX = 400;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 12:
                        mX = 100;
                        mY = 300;
                        lX = 100;
                        lY = 400;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 13:
                        mX = 200;
                        mY = 300;
                        lX = 200;
                        lY = 400;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 14:
                        mX = 300;
                        mY = 300;
                        lX = 300;
                        lY = 400;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                    case 15:
                        mX = 400;
                        mY = 300;
                        lX = 400;
                        lY = 400;
                        hlX = 400;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new LineTo(lX, lY));
                        break;
                }
                // create path animation
                if (movingToCell.getProperty().equals("00") && path != null) {
                    mY += 50;
                    lY += 50;
                    hlX -= 50;
                    path.getElements().add(new MoveTo(mX, mY));
                    path.getElements().add(new LineTo(lX, lY));
                    path.getElements().add(new HLineTo(hlX));
                    lX -= 50;
                }
                if (movingToCell.getProperty().equals("01") && path != null) {
                    mY += 50;
                    lY += 50;
                    hlX += 50;
                    path.getElements().add(new MoveTo(mX, mY));
                    path.getElements().add(new LineTo(lX, lY));
                    path.getElements().add(new HLineTo(hlX));
                    lX += 50;
                }
                if (movingToCell.getProperty().equals("Vertical") && path != null) {
                    mY += 50;
                    lY += 100;
                    path.getElements().add(new MoveTo(mX, mY));
                    path.getElements().add(new LineTo(lX, lY));
                }
            }
        }

        // if starter cell is horizontal
        if (starterCell.getProperty().equals("Horizontal")) {
            // determine the next path is appropriate or not
            movingToCell = getCell(starterCell.getCellId() + 4);
            comeFromCell = starterCell;
            if (movingToCell.getProperty().equals("01")
                    || movingToCell.getProperty().equals("11")
                    || movingToCell.getProperty().equals("Horizontal")) {

                // determine and create starterCell's coordinate
                switch (starterCell.getCellId()) {
                    case 0:
                        mX = 100;
                        mY = 0;
                        // lX = 100;
                        // lY = 100;
                        hlX = 0;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 0;
                        break;
                    case 1:
                        mX = 200;
                        mY = 0;
                        lX = 200;
                        lY = 100;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 100;
                        break;
                    case 2:
                        mX = 300;
                        mY = 0;
                        lX = 300;
                        lY = 100;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 200;
                        break;
                    case 3:
                        mX = 400;
                        mY = 0;
                        lX = 400;
                        lY = 100;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 300;
                        break;
                    case 4:
                        mX = 100;
                        mY = 100;
                        lX = 100;
                        lY = 200;
                        hlX = 0;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 0;
                        break;
                    case 5:
                        mX = 200;
                        mY = 100;
                        lX = 200;
                        lY = 200;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 100;
                        break;
                    case 6:
                        mX = 300;
                        mY = 100;
                        lX = 300;
                        lY = 200;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 200;
                        break;
                    case 7:
                        mX = 400;
                        mY = 100;
                        lX = 400;
                        lY = 200;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 300;
                        break;
                    case 8:
                        mX = 100;
                        mY = 200;
                        lX = 100;
                        lY = 300;
                        hlX = 0;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 0;
                        break;
                    case 9:
                        mX = 200;
                        mY = 200;
                        lX = 200;
                        lY = 300;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 100;
                        break;
                    case 10:
                        mX = 300;
                        mY = 200;
                        lX = 300;
                        lY = 300;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 200;
                        break;
                    case 11:
                        mX = 400;
                        mY = 200;
                        lX = 400;
                        lY = 300;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 300;
                        break;
                    case 12:
                        mX = 100;
                        mY = 300;
                        lX = 100;
                        lY = 400;
                        hlX = 0;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 0;
                        break;
                    case 13:
                        mX = 200;
                        mY = 300;
                        lX = 200;
                        lY = 400;
                        hlX = 100;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 100;
                        break;
                    case 14:
                        mX = 300;
                        mY = 300;
                        lX = 300;
                        lY = 400;
                        hlX = 200;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 200;
                        break;
                    case 15:
                        mX = 400;
                        mY = 300;
                        lX = 400;
                        lY = 400;
                        hlX = 300;
                        path.getElements().add(new MoveTo(mX, mY));
                        path.getElements().add(new HLineTo(hlX));
                        lX = 300;
                        break;
                }
                // create path animation
                if (movingToCell.getProperty().equals("11") && path != null) {
                    mX -= 50;
                    hlX -= 50;
                    lY += 50;
                    path.getElements().add(new MoveTo(mX, mY));
                    path.getElements().add(new HLineTo(hlX));
                    path.getElements().add(new LineTo(lX, lY));
                    lX -= 50;
                }
                if (movingToCell.getProperty().equals("01") && path != null) {
                    mX -= 50;
                    hlX -= 50;
                    lY -= 50;
                    path.getElements().add(new MoveTo(mX, mY));
                    path.getElements().add(new HLineTo(hlX));
                    path.getElements().add(new LineTo(lX, lY));
                    lX -= 50;
                }
                if (movingToCell.getProperty().equals("Horizontal") && path != null) {
                    mX -= 50;
                    hlX -= 100;
                    path.getElements().add(new MoveTo(mX, mY));
                    path.getElements().add(new HLineTo(hlX));
                    lX -= 100;
                }
            }
        }
    }

    public void checkLevelCompleted() {
        // control comeFromCell and movingToCell is null or not
        if (comeFromCell != null && movingToCell != null) {

            System.out.println("comeFromCell " + comeFromCell.getCellId());
            System.out.println("Current Cell " + movingToCell.getCellId());

            // determine movingToCell type
            switch (movingToCell.getProperty()) {
                case "Vertical":
                    // if the ball is coming from up
                    if (comeFromCell.getCellId() == movingToCell.getCellId() - 4) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() + 4).getProperty().equals("00")
                                || getCell(movingToCell.getCellId() + 4).getProperty().equals("01")
                                || getCell(movingToCell.getCellId() + 4).getProperty().equals("Vertical")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() + 4);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("00") && path != null) {
                                mY += 100;
                                lY += 50;
                                hlX -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 50;
                            }
                            if (movingToCell.getProperty().equals("01") && path != null) {
                                mY += 100;
                                lY += 50;
                                hlX += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX += 50;
                            }
                            if (movingToCell.getProperty().equals("Vertical") && path != null) {
                                mY += 100;
                                lY += 100;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                            }
                        } else {
                            return;
                        }
                    }

                    // if the ball is coming from down
                    if (comeFromCell.getCellId() == movingToCell.getCellId() + 4) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() - 4).getProperty().equals("11")
                                || getCell(movingToCell.getCellId() - 4).getProperty().equals("10")
                                || getCell(movingToCell.getCellId() - 4).getProperty().equals("Vertical")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() - 4);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("11") && path != null) {
                                mY -= 100;
                                lY -= 50;
                                hlX += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX += 50;

                            }
                            if (movingToCell.getProperty().equals("10") && path != null) {
                                mY -= 100;
                                lY -= 50;
                                hlX -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 50;

                            }
                            if (movingToCell.getProperty().equals("Vertical") && path != null) {
                                if (movingToCell.getType().equals("Pipe")
                                        || movingToCell.getType().equals("PipeStatic")) {
                                    mY -= 100;
                                    lY -= 100;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new LineTo(lX, lY));
                                }
                                if (movingToCell.getType().equals("End")) {
                                    mY -= 100;
                                    lY -= 35;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new LineTo(lX, lY));

                                }
                            }
                        } else {
                            return;
                        }
                    }
                    break;

                case "Horizontal":
                    // if the ball is coming from left
                    if (comeFromCell.getCellId() == movingToCell.getCellId() - 1) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() + 1).getProperty().equals("00")
                                || getCell(movingToCell.getCellId() + 1).getProperty().equals("10")
                                || getCell(movingToCell.getCellId() + 1).getProperty().equals("Horizontal")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() + 1);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("00") && path != null) {
                                System.out.println(lX);
                                mX += 100;
                                hlX += 50;
                                lX += 50;
                                lY -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("10") && path != null) {
                                mX += 100;
                                hlX += 50;
                                lX += 50;
                                lY += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("Horizontal") && path != null) {
                                if (movingToCell.getType().equals("Pipe")
                                        || movingToCell.getType().equals("PipeStatic")) {
                                    mX += 100;
                                    hlX += 100;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new HLineTo(hlX));
                                    lX += 100;
                                }
                                if (movingToCell.getType().equals("End")) {
                                    mX += 100;
                                    hlX += 35;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new HLineTo(hlX));

                                }
                            }
                        } else {
                            return;
                        }
                    }

                    // if the ball is coming from right
                    if (comeFromCell.getCellId() == movingToCell.getCellId() + 1) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() - 1).getProperty().equals("01")
                                || getCell(movingToCell.getCellId() - 1).getProperty().equals("11")
                                || getCell(movingToCell.getCellId() - 1).getProperty().equals("Horizontal")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() - 1);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("11") && path != null) {
                                mX -= 100;
                                hlX -= 50;
                                lX -= 50;
                                lY += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("01") && path != null) {
                                mX -= 100;
                                hlX -= 50;
                                lX -= 50;
                                lY -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));
                            }
                            if (movingToCell.getProperty().equals("Horizontal") && path != null) {
                                mX -= 100;
                                hlX -= 100;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 100;
                            }
                        } else {
                            return;
                        }
                    }
                    break;

                case "00":
                    // if the ball is coming from up
                    if (comeFromCell.getCellId() == movingToCell.getCellId() - 4) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() - 1).getProperty().equals("01")
                                || getCell(movingToCell.getCellId() - 1).getProperty().equals("11")
                                || getCell(movingToCell.getCellId() - 1).getProperty().equals("Horizontal")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() - 1);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("11") && path != null) {
                                mX -= 50;
                                mY += 50;
                                hlX -= 50;
                                lX -= 50;
                                lY += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("01") && path != null) {
                                mX -= 50;
                                mY += 50;
                                hlX -= 50;
                                lX -= 50;
                                lY -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));
                            }
                            if (movingToCell.getProperty().equals("Horizontal") && path != null) {
                                mX -= 50;
                                mY += 50;
                                hlX -= 100;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 100;
                            }
                        } else {
                            return;
                        }
                    }

                    // if the ball is coming from left
                    if (comeFromCell.getCellId() == movingToCell.getCellId() - 1) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() - 4).getProperty().equals("10")
                                || getCell(movingToCell.getCellId() - 4).getProperty().equals("11")
                                || getCell(movingToCell.getCellId() - 4).getProperty().equals("Vertical")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() - 4);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("10") && path != null) {
                                mX += 50;
                                mY -= 50;
                                lY -= 50;
                                hlX -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 50;

                            }
                            if (movingToCell.getProperty().equals("11") && path != null) {
                                mX += 50;
                                mY -= 50;
                                lY -= 50;
                                hlX += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX += 50;
                            }
                            if (movingToCell.getProperty().equals("Vertical") && path != null) {
                                if (movingToCell.getType().equals("Pipe")
                                        || movingToCell.getType().equals("PipeStatic")) {
                                    mX += 50;
                                    mY -= 50;
                                    lY -= 100;

                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new LineTo(lX, lY));
                                }
                                if (movingToCell.getType().equals("End")) {
                                    mX += 50;
                                    mY -= 50;
                                    lY -= 35;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new LineTo(lX, lY));

                                }

                            }
                        } else {
                            return;
                        }
                    }
                    break;

                case "01":
                    // if the ball is coming from up
                    if (comeFromCell.getCellId() == movingToCell.getCellId() - 4) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() + 1).getProperty().equals("00")
                                || getCell(movingToCell.getCellId() + 1).getProperty().equals("10")
                                || getCell(movingToCell.getCellId() + 1).getProperty().equals("Horizontal")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() + 1);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("10") && path != null) {
                                mX += 50;
                                mY += 50;
                                hlX += 50;
                                lX += 50;
                                lY += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("00") && path != null) {
                                mX += 50;
                                mY += 50;
                                hlX += 50;
                                lX += 50;
                                lY -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("Horizontal") && path != null) {
                                if (movingToCell.getType().equals("Pipe")
                                        || movingToCell.getType().equals("PipeStatic")) {
                                    mX += 50;
                                    mY += 50;
                                    hlX += 100;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new HLineTo(hlX));
                                    lX += 100;
                                }
                                if (movingToCell.getType().equals("End")) {
                                    mX += 50;
                                    mY += 50;
                                    hlX += 35;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new HLineTo(hlX));

                                }
                            }
                        } else {
                            return;
                        }
                    }

                    // if the ball is coming from right
                    if (comeFromCell.getCellId() == movingToCell.getCellId() + 1) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() - 4).getProperty().equals("10")
                                || getCell(movingToCell.getCellId() - 4).getProperty().equals("11")
                                || getCell(movingToCell.getCellId() - 4).getProperty().equals("Vertical")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() - 4);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("10") && path != null) {
                                mX -= 50;
                                mY -= 50;
                                lY -= 50;
                                hlX -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(-50));
                                lX -= 50;
                            }
                            if (movingToCell.getProperty().equals("11") && path != null) {
                                mX -= 50;
                                mY -= 50;
                                lY -= 50;
                                hlX += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(-50));
                                lX += 50;
                            }
                            if (movingToCell.getProperty().equals("Vertical") && path != null) {
                                if (movingToCell.getType().equals("Pipe")
                                        || movingToCell.getType().equals("PipeStatic")) {
                                    mX -= 50;
                                    mY -= 50;
                                    lY -= 100;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new LineTo(lX, lY));
                                }
                                if (movingToCell.getType().equals("End")) {
                                    mX -= 50;
                                    mY -= 50;
                                    lY -= 35;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new LineTo(lX, lY));
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    break;

                case "10":
                    // if the ball is coming from left
                    if (comeFromCell.getCellId() == movingToCell.getCellId() - 1) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() + 4).getProperty().equals("00")
                                || getCell(movingToCell.getCellId() + 4).getProperty().equals("01")
                                || getCell(movingToCell.getCellId() + 4).getProperty().equals("Vertical")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() + 4);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("00") && path != null) {
                                mX += 50;
                                mY += 50;
                                lY += 50;
                                hlX -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 50;
                            }
                            if (movingToCell.getProperty().equals("01") && path != null) {
                                mX += 50;
                                mY += 50;
                                lY += 50;
                                hlX += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX += 50;

                            }
                            if (movingToCell.getProperty().equals("Vertical") && path != null) {
                                mX += 50;
                                mY += 50;
                                lY += 100;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                            }
                        } else {
                            return;
                        }
                    }

                    // if the ball is coming from down
                    if (comeFromCell.getCellId() == movingToCell.getCellId() + 4) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() - 1).getProperty().equals("01")
                                || getCell(movingToCell.getCellId() - 1).getProperty().equals("11")
                                || getCell(movingToCell.getCellId() - 1).getProperty().equals("Horizontal")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() - 1);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("01") && path != null) {
                                mX -= 50;
                                mY -= 50;
                                hlX -= 50;
                                lX -= 50;
                                lY -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));
                            }
                            if (movingToCell.getProperty().equals("11") && path != null) {
                                mX -= 50;
                                mY -= 50;
                                hlX -= 50;
                                lX -= 50;
                                lY += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("Horizontal") && path != null) {
                                mX -= 50;
                                mY -= 50;
                                hlX -= 100;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 100;
                            }
                        } else {
                            return;
                        }
                    }
                    break;

                case "11":
                    // if the ball is coming from down
                    if (comeFromCell.getCellId() == movingToCell.getCellId() + 4) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() + 1).getProperty().equals("00")
                                || getCell(movingToCell.getCellId() + 1).getProperty().equals("10")
                                || getCell(movingToCell.getCellId() + 1).getProperty().equals("Horizontal")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() + 1);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("00") && path != null) {
                                mX += 50;
                                mY -= 50;
                                hlX += 50;
                                lX += 50;
                                lY -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("10") && path != null) {
                                mX += 50;
                                mY -= 50;
                                hlX += 50;
                                lX += 50;
                                lY += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new HLineTo(hlX));
                                path.getElements().add(new LineTo(lX, lY));

                            }
                            if (movingToCell.getProperty().equals("Horizontal") && path != null) {
                                if (movingToCell.getType().equals("Pipe")
                                        || movingToCell.getType().equals("PipeStatic")) {
                                    mX += 50;
                                    mY -= 50;
                                    hlX += 100;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new HLineTo(hlX));
                                    lX += 100;
                                }
                                if (movingToCell.getType().equals("End")) {
                                    mX += 50;
                                    mY -= 50;
                                    hlX += 35;
                                    path.getElements().add(new MoveTo(mX, mY));
                                    path.getElements().add(new HLineTo(hlX));

                                }
                            }
                        } else {
                            return;
                        }
                    }

                    // if the ball is coming from right
                    if (comeFromCell.getCellId() == movingToCell.getCellId() + 1) {
                        // determine the next path is appropriate or not
                        if (getCell(movingToCell.getCellId() + 4).getProperty().equals("00")
                                || getCell(movingToCell.getCellId() + 4).getProperty().equals("01")
                                || getCell(movingToCell.getCellId() + 4).getProperty().equals("Vertical")) {
                            temp = movingToCell;
                            movingToCell = getCell(movingToCell.getCellId() + 4);
                            comeFromCell = temp;
                            // determine and create the coordinate and path for animation
                            if (movingToCell.getProperty().equals("00") && path != null) {
                                mX -= 50;
                                mY += 50;
                                lY += 50;
                                hlX -= 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX -= 50;
                            }
                            if (movingToCell.getProperty().equals("01") && path != null) {
                                mX -= 50;
                                mY += 50;
                                lY += 50;
                                hlX += 50;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                                path.getElements().add(new HLineTo(hlX));
                                lX += 50;

                            }
                            if (movingToCell.getProperty().equals("Vertical") && path != null) {
                                mX -= 50;
                                mY += 50;
                                lY += 100;
                                path.getElements().add(new MoveTo(mX, mY));
                                path.getElements().add(new LineTo(lX, lY));
                            }
                        } else {
                            return;
                        }
                    }
                    break;
            }
            if (movingToCell.getCellId() == endCell.getCellId()) {
                System.out.println("Current Cell: " + movingToCell.getCellId());
                System.out.println("End Cell: " + endCell.getCellId());
                isLevelCompleted = true;
                System.out.println(isLevelCompleted);
            } else {
                checkLevelCompleted();
            }
        } else {
            return;
        }
    }
}