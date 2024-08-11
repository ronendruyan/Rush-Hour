package exFinal;

public interface Movable {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    int getSize();
    boolean isValidMove(int newX,int newY);
    boolean isVertical();
    String getPath();
}