/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

class ShapeData {

    private ShapeData next;
    private String shapeType;
    private double x;
    private double y;
    private double width;
    private double height;
    private Color curretColor;
    private Color currenStrokeColor;
    private BasicStroke currentStroke;

    public ShapeData(String shapeType, double x, double y, double width, double height, Color curretColor, Color currenStrokeColor, BasicStroke currentStroke) {
        this.shapeType = shapeType;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.curretColor = curretColor;
        this.currenStrokeColor = currenStrokeColor;
        this.currentStroke = currentStroke;
    }

    public String getShapeType() {
        return shapeType;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Color getCurretColor() {
        return curretColor;
    }

    public Color getCurrenStrokeColor() {
        return currenStrokeColor;
    }

    public BasicStroke getCurrentStroke() {
        return currentStroke;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCurretColor(Color curretColor) {
        this.curretColor = curretColor;
    }

    public void setCurrenStrokeColor(Color currenStrokeColor) {
        this.currenStrokeColor = currenStrokeColor;
    }

    public void setCurrentStroke(BasicStroke currentStroke) {
        this.currentStroke = currentStroke;
    }

    public ShapeData getNext() {
        return next;
    }

    public void setNext(ShapeData next) {
        this.next = next;
    }

}

class ShapeList {

    private ShapeData head;

    public ShapeData getHead() {
        return head;
    }

    public void setHead(ShapeData head) {
        this.head = head;
    }

    public ShapeList() {
        this.head = null;
    }
    
    public void insertEnding(String shapeType, double x, double y, double width, double height, Color curretColor, Color currenStrokeColor, BasicStroke currentStroke) {
        if (head == null) {
            head = new ShapeData(shapeType, x, y, width, height, curretColor, currenStrokeColor, currentStroke);

        } else {
            ShapeData current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            if (current.getNext() == null) {
                ShapeData newnode = new ShapeData(shapeType, x, y, width, height, curretColor, currenStrokeColor, currentStroke);
                current.setNext(newnode);
                newnode.setNext(null);
            }
        }
    }

    public ShapeData checkSelectedShape(int x, int y, double scale) {
        int count = 0;
        int check = 0;
        ShapeData current = head;
        if (current != null) {
            while (current.getNext() != null) {
                if (x >= current.getX()*scale && y >= current.getY()*scale && x <= current.getX()*scale + current.getWidth()*scale && y <= current.getY()*scale + current.getHeight()*scale) {
                    count++;
                }
                current = current.getNext();
            }
            if (x >= current.getX()*scale && y >= current.getY()*scale && x <= current.getX()*scale + current.getWidth()*scale && y <= current.getY()*scale + current.getHeight()*scale) {
                count++;
            }

            current = head;
            while (current.getNext() != null) {
                if (x >= current.getX()*scale && y >= current.getY()*scale && x <= current.getX()*scale + current.getWidth()*scale && y <= current.getY()*scale + current.getHeight()*scale) {
                    check++;
                    if (count == check) {
                        return current;
                    }
                }
                current = current.getNext();
            }
            if (x >= current.getX()*scale && y >= current.getY()*scale && x <= current.getX()*scale + current.getWidth()*scale && y <= current.getY()*scale + current.getHeight()*scale) {
                check++;
                if (count == check) {
                    return current;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    public void removeThis(double x, double y) {
        int count = 0;
        int check = 0;
        if (head == null) {
            System.out.println("List is already empty!!");
        } else {
            if (head.getNext() == null) {
                if (x == head.getX() && y == head.getY()) {
                    head = null;
                }
            } else {
                ShapeData current = head.getNext();
                ShapeData prev = head;

                if (x == head.getX() && y == head.getY()) {
                    count++;
                }
                while (current.getNext() != null) {
                    if (current.getX() == x && current.getY() == y) {
                        count++;
                    }
                    current = current.getNext();
                    prev = prev.getNext();
                }
                if (current.getX() == x && current.getY() == y) {
                    count++;
                }

                current = head.getNext();
                prev = head;
                if (x == head.getX() && y == head.getY()) {
                    check++;
                    if (count == check) {
                        head = current;
                        return;
                    }
                }
                while (current.getNext() != null) {
                    if (current.getX() == x && current.getY() == y) {
                        check++;
                        if (count == check) {
                            prev.setNext(current.getNext());
                            return;
                        }
                    }
                    current = current.getNext();
                    prev = prev.getNext();

                }
                if (current.getX() == x && current.getY() == y) {
                    check++;
                    if (count == check) {
                        prev.setNext(current.getNext());
                    }
                } else {
                    return;
                }
            }
        }
    }

    public ShapeData getLast() {
        if (head == null) {
            return null;

        } else {
            ShapeData current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            if (current.getNext() == null) {
                return current;
            } else {
                return null;
            }
        }
    }
}

public class MainBuilder extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int currentX;
    private int currentY;
    private double currentWidth;
    private double currentHeight;
    private ShapeList shapeSinglyList;
    private String drawingShape = "Rectangle";
    private double currentScaleFactor = 1.0;
    private Color currentColor;
    private Color currentStrokeColor;
    private BasicStroke currentStroke = new BasicStroke(0.0f);
    private ShapeData currentSelection = null;

    public MainBuilder() {
        shapeSinglyList = new ShapeList();
        initComponents();
        setInitialLayout();
    }

    private void setInitialLayout() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        workspacePanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                try {
                    ShapeData current = shapeSinglyList.getHead();
                    if (current != null) {
                        while (current.getNext() != null) {
                            drawingShape = current.getShapeType();
                            currentX = (int) current.getX();
                            currentY = (int) current.getY();
                            currentWidth = current.getWidth();
                            currentHeight = current.getHeight();
                            currentColor = current.getCurretColor();
                            currentStrokeColor = current.getCurrenStrokeColor();
                            currentStroke = current.getCurrentStroke();
                            drawShape(g2d);

                            current = current.getNext();
                        }
                        drawingShape = current.getShapeType();
                        currentX = (int) current.getX();
                        currentY = (int) current.getY();
                        currentWidth = current.getWidth();
                        currentHeight = current.getHeight();
                        currentColor = current.getCurretColor();
                        currentStrokeColor = current.getCurrenStrokeColor();
                        currentStroke = current.getCurrentStroke();
                        drawShape(g2d);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        JScrollPane scrollPane = new JScrollPane(workspacePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setPreferredSize(new Dimension(400, 300)); // Set the preferred size of the viewport

        workspacePanel.setBackground(Color.WHITE);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(rightPanel, BorderLayout.EAST);

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    currentSelection = shapeSinglyList.checkSelectedShape(e.getX(), e.getY(), currentScaleFactor);
                    if (currentSelection != null) {
                        shapeName.setText(currentSelection.getShapeType());
                        strokeComboBox.setSelectedIndex((int)currentSelection.getCurrentStroke().getLineWidth());
                        xSpinner.setValue(currentSelection.getX());
                        ySpinner.setValue(currentSelection.getY());
                        widthSpinner.setValue(currentSelection.getWidth());
                        heightSpinner.setValue(currentSelection.getHeight());
                    } else {
                        shapeName.setText("--");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

// Register the MouseListener to the workspacePanel
        workspacePanel.addMouseListener(mouseListener);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workspacePanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        sa = new javax.swing.JLabel();
        lblHexagon = new javax.swing.JLabel();
        lblSquare = new javax.swing.JLabel();
        lblTriangle = new javax.swing.JLabel();
        lblLine = new javax.swing.JLabel();
        lblCircle = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        rectanglePanel = new javax.swing.JPanel();
        shapeName = new javax.swing.JLabel();
        lblDownload = new javax.swing.JLabel();
        lblRemove = new javax.swing.JLabel();
        transformPanel = new javax.swing.JPanel();
        xSpinner = new javax.swing.JSpinner();
        ySpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        widthSpinner = new javax.swing.JSpinner();
        heightSpinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        nothingPanel = new javax.swing.JPanel();
        strokeComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblColorPickerStroke = new javax.swing.JLabel();
        lblColorPicker = new javax.swing.JLabel();
        zoomOut = new javax.swing.JLabel();
        zoomIn = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1500, 900));
        setPreferredSize(new java.awt.Dimension(1500, 900));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workspacePanel.setBackground(new java.awt.Color(255, 255, 255));
        workspacePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                workspacePanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                workspacePanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout workspacePanelLayout = new javax.swing.GroupLayout(workspacePanel);
        workspacePanel.setLayout(workspacePanelLayout);
        workspacePanelLayout.setHorizontalGroup(
            workspacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        workspacePanelLayout.setVerticalGroup(
            workspacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        getContentPane().add(workspacePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 770, 800));

        leftPanel.setBackground(new java.awt.Color(231, 231, 231));
        leftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saMouseClicked(evt);
            }
        });
        leftPanel.add(sa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 790, 10, 10));

        lblHexagon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/hexagon.png"))); // NOI18N
        lblHexagon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHexagon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHexagonMouseClicked(evt);
            }
        });
        leftPanel.add(lblHexagon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        lblSquare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/square.png"))); // NOI18N
        lblSquare.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSquare.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSquareMouseClicked(evt);
            }
        });
        leftPanel.add(lblSquare, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        lblTriangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/triangle.png"))); // NOI18N
        lblTriangle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTriangle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTriangleMouseClicked(evt);
            }
        });
        leftPanel.add(lblTriangle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        lblLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/line.png"))); // NOI18N
        lblLine.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLineMouseClicked(evt);
            }
        });
        leftPanel.add(lblLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        lblCircle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCircle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/circle.png"))); // NOI18N
        lblCircle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCircle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCircleMouseClicked(evt);
            }
        });
        leftPanel.add(lblCircle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        getContentPane().add(leftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 800));

        rightPanel.setBackground(new java.awt.Color(231, 231, 231));
        rightPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rectanglePanel.setBackground(new java.awt.Color(231, 231, 231));

        shapeName.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        shapeName.setForeground(new java.awt.Color(27, 81, 115));
        shapeName.setToolTipText("");

        lblDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/download.png"))); // NOI18N
        lblDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDownload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDownloadMouseClicked(evt);
            }
        });

        lblRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/remove.png"))); // NOI18N
        lblRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemoveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout rectanglePanelLayout = new javax.swing.GroupLayout(rectanglePanel);
        rectanglePanel.setLayout(rectanglePanelLayout);
        rectanglePanelLayout.setHorizontalGroup(
            rectanglePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rectanglePanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(shapeName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblDownload)
                .addGap(18, 18, 18)
                .addComponent(lblRemove)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        rectanglePanelLayout.setVerticalGroup(
            rectanglePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rectanglePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(rectanglePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRemove)
                    .addComponent(lblDownload)
                    .addComponent(shapeName))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        rightPanel.add(rectanglePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, -1));

        transformPanel.setBackground(new java.awt.Color(231, 231, 231));

        xSpinner.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        xSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        xSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                xSpinnerStateChanged(evt);
            }
        });

        ySpinner.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        ySpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        ySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ySpinnerStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(27, 81, 115));
        jLabel1.setText("Transform");

        jPanel1.setBackground(new java.awt.Color(206, 206, 206));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(27, 81, 115));
        jLabel2.setText("X:");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(27, 81, 115));
        jLabel3.setText("Y:");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(27, 81, 115));
        jLabel4.setText("Width:");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(27, 81, 115));
        jLabel5.setText("Height:");

        widthSpinner.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        widthSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        widthSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                widthSpinnerStateChanged(evt);
            }
        });

        heightSpinner.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        heightSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        heightSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                heightSpinnerStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(206, 206, 206));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 5));

        jPanel3.setBackground(new java.awt.Color(206, 206, 206));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(206, 206, 206));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 5));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(190, 190, 190)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout transformPanelLayout = new javax.swing.GroupLayout(transformPanel);
        transformPanel.setLayout(transformPanelLayout);
        transformPanelLayout.setHorizontalGroup(
            transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(transformPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(transformPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(xSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(transformPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(ySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        transformPanelLayout.setVerticalGroup(
            transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transformPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(transformPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))))
                .addGap(3, 3, 3)
                .addGroup(transformPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transformPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3))
                    .addComponent(ySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rightPanel.add(transformPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 380, 210));

        nothingPanel.setBackground(new java.awt.Color(231, 231, 231));

        strokeComboBox.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        strokeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5" }));
        strokeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strokeComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(27, 81, 115));
        jLabel7.setText("Appearance");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(27, 81, 115));
        jLabel8.setText("Fill:");

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(27, 81, 115));
        jLabel9.setText("Stroke:");

        lblColorPickerStroke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/color-picker.png"))); // NOI18N
        lblColorPickerStroke.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblColorPickerStroke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblColorPickerStrokeMouseClicked(evt);
            }
        });

        lblColorPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/color-picker.png"))); // NOI18N
        lblColorPicker.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblColorPicker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblColorPickerMouseClicked(evt);
            }
        });

        zoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/zoom-in.png"))); // NOI18N
        zoomOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zoomOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zoomOutMouseClicked(evt);
            }
        });

        zoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/zoom-out.png"))); // NOI18N
        zoomIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zoomIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zoomInMouseClicked(evt);
            }
        });

        reset.setForeground(new java.awt.Color(27, 81, 115));
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nothingPanelLayout = new javax.swing.GroupLayout(nothingPanel);
        nothingPanel.setLayout(nothingPanelLayout);
        nothingPanelLayout.setHorizontalGroup(
            nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nothingPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nothingPanelLayout.createSequentialGroup()
                        .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, nothingPanelLayout.createSequentialGroup()
                                .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(zoomOut))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(strokeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(nothingPanelLayout.createSequentialGroup()
                                        .addComponent(zoomIn)
                                        .addGap(18, 18, 18)
                                        .addComponent(reset)))))
                        .addGap(18, 18, 18)
                        .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblColorPickerStroke)
                            .addComponent(lblColorPicker))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        nothingPanelLayout.setVerticalGroup(
            nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nothingPanelLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nothingPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(strokeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblColorPickerStroke)))
                    .addComponent(lblColorPicker))
                .addGap(48, 48, 48)
                .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(nothingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(zoomOut)
                        .addComponent(zoomIn))
                    .addComponent(reset))
                .addContainerGap(283, Short.MAX_VALUE))
        );

        rightPanel.add(nothingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 380, 480));

        getContentPane().add(rightPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(856, 0, 380, 800));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblSquareMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSquareMouseClicked
        try {
            setCurrentShape("Rectangle");
            shapeSinglyList.insertEnding("Rectangle", 200, 100, 200, 200, Color.RED, Color.RED, new BasicStroke(0.0f));
            currentSelection = shapeSinglyList.getLast();
            shapeName.setText(currentSelection.getShapeType());
            setInitialTransformValues();
            workspacePanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblSquareMouseClicked

    private void saMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saMouseClicked

    }//GEN-LAST:event_saMouseClicked

    private void lblTriangleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTriangleMouseClicked

        try {
            setCurrentShape("Triangle");
            shapeSinglyList.insertEnding("Triangle", 200, 100, 200, 200, Color.RED, Color.RED, new BasicStroke(0.0f));
            currentSelection = shapeSinglyList.getLast();
            shapeName.setText(currentSelection.getShapeType());
            setInitialTransformValues();
            workspacePanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblTriangleMouseClicked

    private void lblLineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLineMouseClicked
        try {
            setCurrentShape("Line");
            shapeSinglyList.insertEnding("Line", 200, 100, 200, 200, Color.RED, Color.RED, new BasicStroke(0.0f));
            currentSelection = shapeSinglyList.getLast();
            shapeName.setText(currentSelection.getShapeType());
            setInitialTransformValues();
            workspacePanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblLineMouseClicked

    private void strokeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strokeComboBoxActionPerformed
        try {
            if (currentSelection != null) {
                currentSelection.setCurrentStroke(new BasicStroke(strokeComboBox.getSelectedIndex()));
                workspacePanel.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_strokeComboBoxActionPerformed

    private void xSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_xSpinnerStateChanged
        try {
            if (currentSelection != null) {
                currentSelection.setX((double) xSpinner.getValue());
                workspacePanel.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_xSpinnerStateChanged

    private void ySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ySpinnerStateChanged
        try {
            if (currentSelection != null) {
                currentSelection.setY((double) ySpinner.getValue());
                workspacePanel.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ySpinnerStateChanged

    private void widthSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_widthSpinnerStateChanged
        try {
            if (currentSelection != null) {
                currentSelection.setWidth((double) widthSpinner.getValue());
                workspacePanel.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_widthSpinnerStateChanged

    private void heightSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_heightSpinnerStateChanged
        try {
            if (currentSelection != null) {
                currentSelection.setHeight((double) heightSpinner.getValue());
                workspacePanel.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_heightSpinnerStateChanged

    private void lblDownloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDownloadMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg"));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();

            // Capture the content of the middle panel as an image
            BufferedImage image = new BufferedImage(workspacePanel.getWidth(), workspacePanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            workspacePanel.paint(g);
            g.dispose();

            // Save the image to the selected file
            String fileExtension = getFileExtension(filePath);
            try {
                ImageIO.write(image, fileExtension, new File(filePath));
                JOptionPane.showMessageDialog(null, "Shape downloaded successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to download the shape.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_lblDownloadMouseClicked

    private void lblRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoveMouseClicked
        try {
            if (currentSelection != null) {
                shapeSinglyList.removeThis(currentSelection.getX(), currentSelection.getY());
                currentSelection = shapeSinglyList.getLast();
                if (currentSelection == null) {
                    shapeName.setText("--");
                } else {
                    shapeName.setText(currentSelection.getShapeType());
                }
                workspacePanel.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblRemoveMouseClicked

    private void lblColorPickerStrokeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblColorPickerStrokeMouseClicked
        try {
            Color selectedColor = JColorChooser.showDialog(this, "Choose a Color", Color.WHITE);
            if (currentSelection != null) {
                if (selectedColor != null) {
                    currentSelection.setCurrenStrokeColor(selectedColor);
                    workspacePanel.repaint();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblColorPickerStrokeMouseClicked


    private void lblColorPickerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblColorPickerMouseClicked
        try {
            Color selectedColor = JColorChooser.showDialog(this, "Choose a Color", Color.WHITE);
            if (currentSelection != null) {
                if (selectedColor != null) {
                    currentSelection.setCurretColor(selectedColor);
                    workspacePanel.repaint();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblColorPickerMouseClicked

    private void lblHexagonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHexagonMouseClicked
        try {
            setCurrentShape("Hexagon");
            shapeSinglyList.insertEnding("Hexagon", 200, 100, 200, 200, Color.RED, Color.RED, new BasicStroke(0.0f));
            currentSelection = shapeSinglyList.getLast();
            shapeName.setText(currentSelection.getShapeType());
            setInitialTransformValues();
            workspacePanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblHexagonMouseClicked

    private void workspacePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workspacePanelMouseClicked


    }//GEN-LAST:event_workspacePanelMouseClicked

    private void workspacePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workspacePanelMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_workspacePanelMousePressed

    private void lblCircleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCircleMouseClicked
        // TODO add your handling code here:
        try {
            setCurrentShape("Circle");
            shapeSinglyList.insertEnding("Circle", 200, 100, 200, 200, Color.RED, Color.RED, new BasicStroke(0.0f));
            currentSelection = shapeSinglyList.getLast();
            shapeName.setText(currentSelection.getShapeType());
            setInitialTransformValues();
            workspacePanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblCircleMouseClicked

    private void zoomOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zoomOutMouseClicked
        // TODO add your handling code here:
        currentScaleFactor -= 0.1;
        // Trigger repaint to redraw the shapes with the updated scale factor
        workspacePanel.setPreferredSize(new Dimension(workspacePanel.getWidth() - 100, workspacePanel.getHeight() - 100));
        workspacePanel.revalidate();
        workspacePanel.repaint();
    }//GEN-LAST:event_zoomOutMouseClicked

    private void zoomInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zoomInMouseClicked
        // TODO add your handling code here:
        currentScaleFactor += 0.1;
        workspacePanel.setPreferredSize(new Dimension(workspacePanel.getWidth() + 100, workspacePanel.getHeight() + 100));
        workspacePanel.revalidate();
        // Trigger repaint to redraw the shapes with the updated scale factor
        workspacePanel.repaint();
    }//GEN-LAST:event_zoomInMouseClicked

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        workspacePanel.setPreferredSize(new Dimension(700,800));
        currentScaleFactor = 1.0;
        workspacePanel.revalidate();
        // Trigger repaint to redraw the shapes with the updated scale factor
        workspacePanel.repaint();
    }//GEN-LAST:event_resetActionPerformed

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "png"; // Default to PNG if extension is not found
    }

    private void setInitialConfiguration() {
        setColor(Color.RED);
        setStrokeColor(Color.RED);
        setCurrentStroke(strokeComboBox.getSelectedIndex());
        setCurrentX((double) xSpinner.getValue());
        setCurrentY((double) ySpinner.getValue());
        setCurrentWidth((double) widthSpinner.getValue());
        setCurrentHeight((double) heightSpinner.getValue());
    }

    private void setInitialTransformValues() {
        strokeComboBox.setSelectedIndex(0);
        xSpinner.setValue(200.0);
        ySpinner.setValue(100.0);
        widthSpinner.setValue(200.0);
        heightSpinner.setValue(200.0);
        setInitialConfiguration();
    }

    private void setCurrentShape(String shape) {
        drawingShape = shape;
    }

    private void setColor(Color color) {
        currentColor = color;
    }

    private void setStrokeColor(Color color) {
        currentStrokeColor = color;
    }

    private void setCurrentStroke(int stroke) {
        currentStroke = new BasicStroke(stroke);
    }

    private void setCurrentScaleFactor(double scaleFactor) {
        currentScaleFactor = scaleFactor;
    }

    private void setCurrentX(double x) {
        currentX = (int) x;
    }

    private void setCurrentY(double y) {
        currentY = (int) y;
    }

    private void setCurrentWidth(double width) {
        currentWidth = width;
    }

    private void setCurrentHeight(double height) {
        currentHeight = height;
    }

    private void zoomIn(){
        
    }
    private void drawShape(Graphics2D g2d) {
        try {
            currentWidth *= currentScaleFactor;
            currentHeight *= currentScaleFactor;
            currentX *= currentScaleFactor;
            currentY *= currentScaleFactor;
            g2d.setColor(currentColor);
            g2d.setStroke(currentStroke);

            switch (drawingShape) {
                case "Rectangle":
                    g2d.drawRect((int) currentX, (int) currentY, (int) currentWidth, (int) currentHeight);
                    g2d.fillRect((int) currentX, (int) currentY, (int) currentWidth, (int) currentHeight);
                    break;
                case "Triangle":
                    Polygon triangle = new Polygon();
                    triangle.addPoint((int) currentX, (int) currentY);
                    triangle.addPoint((int) (currentX + currentWidth), (int) currentY);
                    triangle.addPoint((int) (currentX + (currentWidth / 2)), (int) (currentY + currentHeight));
                    g2d.drawPolygon(triangle);
                    g2d.fill(triangle);
                    break;
                case "Circle":
                    g2d.drawOval((int) currentX, (int) currentY, (int) currentWidth, (int) currentHeight);
                    g2d.fillOval((int) currentX, (int) currentY, (int) currentWidth, (int) currentHeight);
                    break;
                case "Line":
                    g2d.drawLine((int) currentX, (int) currentY, (int) (currentX + currentWidth), (int) (currentY + currentHeight));
                    break;
                case "Hexagon":
                    Polygon hexagon = createHexagon(currentX, currentY, currentWidth, currentHeight);
                    g2d.drawPolygon(hexagon);
                    g2d.fill(hexagon);
                    break;
                default:
                    break;
            }

            // Set the stroke color separately
            g2d.setColor(currentStrokeColor);
            g2d.setStroke(currentStroke);

            // Redraw the shape with the stroke color
            switch (drawingShape) {
                case "Rectangle":
                    g2d.drawRect((int) currentX, (int) currentY, (int) currentWidth, (int) currentHeight);
                    break;
                case "Triangle":
                    Polygon triangle = new Polygon();
                    triangle.addPoint((int) currentX, (int) currentY);
                    triangle.addPoint((int) (currentX + currentWidth), (int) currentY);
                    triangle.addPoint((int) (currentX + (currentWidth / 2)), (int) (currentY + currentHeight));
                    g2d.drawPolygon(triangle);
                    break;
                case "Circle":
                    g2d.drawOval((int) currentX, (int) currentY, (int) currentWidth, (int) currentHeight);
                    break;
                case "Line":
                    g2d.drawLine((int) currentX, (int) currentY, (int) (currentX + currentWidth), (int) (currentY + currentHeight));
                    break;
                case "Hexagon":
                    Polygon hexagon = createHexagon(currentX, currentY, currentWidth, currentHeight);
                    g2d.drawPolygon(hexagon);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private Polygon createHexagon(double x, double y, double width, double height) {
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        double halfWidth = width / 2;
        double halfHeight = height / 2;

        double sideLength = Math.min(halfWidth, halfHeight);

        // Calculate the points of the hexagon
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI / 6.0 * i;
            xPoints[i] = (int) (x + halfWidth + sideLength * Math.cos(angle));
            yPoints[i] = (int) (y + halfHeight + sideLength * Math.sin(angle));
        }

        return new Polygon(xPoints, yPoints, 6);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainBuilder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner heightSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCircle;
    private javax.swing.JLabel lblColorPicker;
    private javax.swing.JLabel lblColorPickerStroke;
    private javax.swing.JLabel lblDownload;
    private javax.swing.JLabel lblHexagon;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblRemove;
    private javax.swing.JLabel lblSquare;
    private javax.swing.JLabel lblTriangle;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel nothingPanel;
    private javax.swing.JPanel rectanglePanel;
    private javax.swing.JButton reset;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel sa;
    private javax.swing.JLabel shapeName;
    private javax.swing.JComboBox<String> strokeComboBox;
    private javax.swing.JPanel transformPanel;
    private javax.swing.JSpinner widthSpinner;
    private javax.swing.JPanel workspacePanel;
    private javax.swing.JSpinner xSpinner;
    private javax.swing.JSpinner ySpinner;
    private javax.swing.JLabel zoomIn;
    private javax.swing.JLabel zoomOut;
    // End of variables declaration//GEN-END:variables
}
