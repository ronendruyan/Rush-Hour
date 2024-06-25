package exFinal;

import java.awt.Color;

public class Vehicle {
    int x, y, length;
    boolean horizontal;
    Color color;
    
    Vehicle(int x, int y, int length, boolean horizontal, Color color) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.horizontal = horizontal;
        this.color = color;
    }

    boolean containsPoint(int px, int py) {
        if (horizontal) {
            return py == y && px >= x && px < x + length;
        } else {
            return px == x && py >= y && py < y + length;
        }
    }
    
    boolean occupiesSpace(int px, int py) {
        if (horizontal) {
            return py == y && px >= x && px < x + length;
        } else {
            return px == x && py >= y && py < y + length;
        }
    }
}