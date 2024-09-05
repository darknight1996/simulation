package org.example.ui;

import org.example.map.WorldMap;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;
import org.example.render.impl.TextPandeLogRenderer;
import org.example.render.impl.TextPaneWorldMapRenderer;
import org.example.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {

    private static final int FRAME_WIDTH = 620;
    private static final int FRAME_HEIGHT = 840;
    private static final int FRAME_X = 400;
    private static final int FRAME_Y = 200;

    private JTextPane mapTextPane;
    private JTextPane logTextPane;
    private JScrollPane scrollPane;
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
        initMapTextPane();
        initLogTextPane();
        initControlPanel();
        initSplitPane();
    }

    private void initMapTextPane() {
        mapTextPane = new JTextPane();
        mapTextPane.setFont(new Font("", Font.PLAIN, 30));
        mapTextPane.setFocusable(false);
        mapTextPane.setCursor(getCursor());
        mapTextPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 2));  // Adjust preferred size
    }

    private void initLogTextPane() {
        logTextPane = new JTextPane();
        logTextPane.setFont(new Font("", Font.PLAIN, 20));
        logTextPane.setFocusable(false);
        logTextPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 2));

        scrollPane = new JScrollPane(logTextPane);
    }

    private void initControlPanel() {
        final JPanel buttonPanel = createButtonPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initSplitPane() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mapTextPane, scrollPane);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(0.5);
        splitPane.setOneTouchExpandable(false);
        splitPane.setContinuousLayout(false);
        splitPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT - 80));

        this.add(splitPane, BorderLayout.CENTER);
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
        final WorldMapRenderer worldMapRenderer = new TextPaneWorldMapRenderer(mapTextPane);
        final LogRenderer logRenderer = new TextPandeLogRenderer(logTextPane);
        final WorldMap worldMap = new WorldMap(20, 10);
        simulation = new Simulation(worldMap, worldMapRenderer, logRenderer);
    }

}
