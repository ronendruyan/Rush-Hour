package exFinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class gameBoard extends JFrame {
    private static final int GRID_SIZE = 8;
    private static final int CELL_SIZE = 80;
    private Vehicle selectedVehicle;
    private int dragOffsetX, dragOffsetY;
    
    private Vehicle[] vehicles;
    
    public gameBoard() {
        setTitle("Rush Hour");
        setSize(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        setResizable(false);
        
        // Initialize vehicles
        vehicles = new Vehicle[] {
            new Vehicle(2, 2, 2, true, Color.RED), // Red car
            new Vehicle(0, 0, 3, false, Color.YELLOW),
            // Add more vehicles here
        };
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                handleMousePressed(me.getX() / CELL_SIZE, me.getY() / CELL_SIZE);
            }
            
            public void mouseReleased(MouseEvent me) {
                handleMouseReleased();
            }
        });
        
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                handleMouseDragged(me.getX() / CELL_SIZE, me.getY() / CELL_SIZE);
            }
        });
        add(panel);
        
    }
    private void handleMousePressed(int x, int y) {
        for (Vehicle v : vehicles) {
            if (v.containsPoint(x, y)) {
                selectedVehicle = v;
                dragOffsetX = x - v.x;
                dragOffsetY = y - v.y;
                break;
            }
        }
    }
    
    private void handleMouseDragged(int x, int y) {
        if (selectedVehicle != null) {
            int newX = x - dragOffsetX;
            int newY = y - dragOffsetY;
            
            if (selectedVehicle.horizontal) {
                newX = Math.max(0, Math.min(newX, GRID_SIZE - selectedVehicle.length));
                if (isSpaceFree(newX, selectedVehicle.y, selectedVehicle)) {
                    selectedVehicle.x = newX;
                }
            } else {
                newY = Math.max(0, Math.min(newY, GRID_SIZE - selectedVehicle.length));
                if (isSpaceFree(selectedVehicle.x, newY, selectedVehicle)) {
                    selectedVehicle.y = newY;
                }
            }
            repaint();
        }
    }
    
    private void handleMouseReleased() {
        selectedVehicle = null;
    }
    private void drawGame(Graphics g) {
        // Draw grid
        g.setColor(Color.BLACK);
        for (int i = 0; i <= GRID_SIZE; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE);
            g.drawLine(0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE, i * CELL_SIZE);
        }
        
        // Draw vehicles
        for (Vehicle v : vehicles) {
            g.setColor(v.color);
            if (v.horizontal) {
                g.fillRect(v.x * CELL_SIZE, v.y * CELL_SIZE, v.length * CELL_SIZE, CELL_SIZE);
            } else {
                g.fillRect(v.x * CELL_SIZE, v.y * CELL_SIZE, CELL_SIZE, v.length * CELL_SIZE);
            }
        }
    }
//    private void handleClick(int x, int y) {
//        for (Vehicle v : vehicles) {
//            if (v.containsPoint(x, y)) {
//                if (v.horizontal) {
//                    if (x == v.x && v.x > 0 && isSpaceFree(v.x - 1, v.y)) {
//                        v.x--; // Move left
//                    } else if (x == v.x + v.length - 1 && v.x + v.length < GRID_SIZE && isSpaceFree(v.x + v.length, v.y)) {
//                        v.x++; // Move right
//                    }
//                } else {
//                    if (y == v.y && v.y > 0 && isSpaceFree(v.x, v.y - 1)) {
//                        v.y--; // Move up
//                    } else if (y == v.y + v.length - 1 && v.y + v.length < GRID_SIZE && isSpaceFree(v.x, v.y + v.length)) {
//                        v.y++; // Move down
//                    }
//                }
//                repaint();
//                break;
//            }
//        }
//    }
    private boolean isSpaceFree(int x, int y, Vehicle exclude) {
        for (Vehicle v : vehicles) {
            if (v != exclude && v.occupiesSpace(x, y)) {
                return false;
            }
        }
        return true;
    }
}
