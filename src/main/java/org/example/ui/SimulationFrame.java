package org.example.ui;

import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;
import org.example.render.impl.TextPaneWorldMapRenderer;
import org.example.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {

    private static final int FRAME_WIDTH = 620;
    private static final int FRAME_HEIGHT = 460;
    private static final int FRAME_X = 400;
    private static final int FRAME_Y = 200;

    private JTextPane textPane;
    private JButton startButton;
    private JButton stopButton;

    private Simulation simulation;

    public SimulationFrame() {
        initFrame();
        initComponents();
        initSimulation();
        this.setVisible(true);
    }

    private void initFrame() {
        this.setTitle("Simulation");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocation(FRAME_X, FRAME_Y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        initTextPane();
        initControlPanel();
    }

    private void initTextPane() {
        textPane = new JTextPane();
        textPane.setFont(new Font("", Font.PLAIN, 30));
        textPane.setFocusable(false);
        textPane.setCursor(getCursor());
        this.add(textPane, BorderLayout.CENTER);
    }

    private void initControlPanel() {
        final JPanel buttonPanel = createButtonPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        startButton = new JButton("Start");
        startButton.addActionListener(e -> onStartSimulation());

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> onStopSimulation());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        return buttonPanel;
    }

    private void onStartSimulation() {
        simulation.start();
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    private void onStopSimulation() {
        simulation.stop();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    private void initSimulation() {
        final WorldMapRenderer worldMapRenderer = new TextPaneWorldMapRenderer(textPane);
        final WorldMap worldMap = new WorldMap(20, 10);
        simulation = new Simulation(worldMap, worldMapRenderer);
    }

}
