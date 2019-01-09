package com.jacobhicks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        GooLagoon jframe = new GooLagoon("SQUIDWARD HAS ALL THE TALENT");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        jframe.setBounds(0,0,200, 220);
        jframe.setVisible(true);

    }
}

class GooLagoon extends JFrame {
    static final ImageIcon[] squid = {new ImageIcon("sand.png"), new ImageIcon("squid.gif"), new ImageIcon("squid4.gif"), new ImageIcon("squid8.gif"), new ImageIcon("squid16.gif"), new ImageIcon("squid32.gif"), new ImageIcon("squid64.gif"), new ImageIcon("squid128.gif"), new ImageIcon("squid256.gif"), new ImageIcon("squid512.gif"), new ImageIcon("squid1024.gif")};
    static int[][] gb = new int[4][4];
    int score = 0;
    static JLabel[][] tiles = new JLabel[10][10];
    Listener inp = new Listener(this);
    public GooLagoon(String s) {
        super(s);
        addKeyListener(inp);
        for(int row = 0; row < gb.length; row++) {
            for(int col = 0; col < gb.length; col++) {
                tiles[row][col] = new JLabel(squid[0]);
                tiles[row][col].setBounds( col * 50,  row * 50, 50, 50);
                add(tiles[row][col]);
                tiles[row][col].setVisible(true);
            }
        }
        gb[gb.length/2][gb[0].length/2] = 1;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int row = 0; row < gb.length; row++) {
            for(int col = 0; col < gb.length; col++) {
                ImageIcon temp = squid[gb[row][col]];
                tiles[row][col].setIcon(temp);
            }
        }
    }
}

class Listener implements KeyListener {
    int[][] gb;
    GooLagoon gl;
    public Listener(GooLagoon gl) {
        this.gb = gl.gb;
        this.gl = gl;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 39) {
            for(int row = 0; row < gb.length; row++) {
                for(int col = gb[row].length - 1; col >= 0; col--) {
                    int tmp = gb[row][col];
                    gl.score = Math.max(gl.score, tmp);
                    if(tmp != 0) {
                        for (int movecol = col + 1; movecol < gb[row].length; movecol++) {
                            if (tmp == gb[row][movecol]) {
                                gb[row][col] = 0;
                                gb[row][movecol]++;
                                break;
                            } else if (gb[row][movecol] != 0) {
                                gb[row][col] = 0;
                                gb[row][movecol - 1] = tmp;
                                break;
                            } else if (movecol == gb.length - 1) {
                                gb[row][col] = 0;
                                gb[row][movecol] = tmp;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(e.getKeyCode() == 37) {
            for(int row = 0; row < gb.length; row++) {
                for(int col = 0; col < gb[row].length; col++) {
                    int tmp = gb[row][col];
                    gl.score = Math.max(gl.score, tmp);
                    if(tmp != 0) {
                        for (int movecol = col - 1; movecol >= 0; movecol--) {
                            if (tmp == gb[row][movecol]) {
                                gb[row][col] = 0;
                                gb[row][movecol]++;
                                break;
                            } else if (gb[row][movecol] != 0) {
                                gb[row][col] = 0;
                                gb[row][movecol + 1] = tmp;
                                break;
                            } else if (movecol == 0) {
                                gb[row][col] = 0;
                                gb[row][movecol] = tmp;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(e.getKeyCode() == 38) {
            for(int row = 0; row < gb.length; row++) {
                for(int col = gb[row].length - 1; col >= 0; col--) {
                    int tmp = gb[row][col];
                    gl.score = Math.max(gl.score, tmp);
                    if(tmp != 0) {
                        for (int moverow = row - 1; moverow >= 0; moverow--) {
                            if (tmp == gb[moverow][col]) {
                                gb[row][col] = 0;
                                gb[moverow][col]++;
                                break;
                            } else if (gb[moverow][col] != 0) {
                                gb[row][col] = 0;
                                gb[moverow + 1][col] = tmp;
                                break;
                            } else if (moverow == 0) {
                                gb[row][col] = 0;
                                gb[moverow][col] = tmp;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(e.getKeyCode() == 40) {
            for(int row = gb.length - 1; row >= 0; row--) {
                for(int col = gb[row].length - 1; col >= 0; col--) {
                    int tmp = gb[row][col];
                    gl.score = Math.max(gl.score, tmp);
                    if(tmp != 0) {
                        for (int moverow = row + 1; moverow < gb[row].length; moverow++) {
                            if (tmp == gb[moverow][col]) {
                                gb[row][col] = 0;
                                gb[moverow][col]++;
                                break;
                            } else if (gb[moverow][col] != 0) {
                                gb[row][col] = 0;
                                gb[moverow - 1][col] = tmp;
                                break;
                            } else if (moverow == gb.length - 1) {
                                gb[row][col] = 0;
                                gb[moverow][col] = tmp;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else return;
        int x, y;
        do {
            y = (int)(Math.random() * gb.length);
            x = (int)(Math.random() * gb[0].length);
        } while(gb[y][x] != 0);
        gb[y][x] = (int)(Math.random() * gl.score / 4) + 1;
        gl.repaint();
    }
}