package org.example.ui;

import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;
import org.example.render.impl.TextPaneWorldMapRenderer;
import org.example.simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextPane textPane;
    private JButton startButton;
    private JButton stopButton;

    private Simulation simulation;

    public MainFrame() {
        init();
        initTextPane();
        initButtons();
        initSimulation();
        this.setVisible(true);
    }

    private void init() {
        this.setTitle("Simulation");
        this.setSize(1000, 800);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initTextPane() {
        textPane = new JTextPane();
        textPane.setFont(new Font("", Font.PLAIN, 30));
        textPane.setFocusable(false);
        textPane.setCursor(getCursor());
        this.add(textPane);
    }

    private void initButtons() {
        final JPanel grid = new JPanel(new GridLayout(1, 2, 10, 10) );

        startButton = new JButton("start");
        startButton.addActionListener(e -> simulation.start());
        stopButton = new JButton("stop");
        stopButton.addActionListener(e -> simulation.stop());
        grid.add(startButton);
        grid.add(stopButton);

        final JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(grid);
        this.add(flow, BorderLayout.SOUTH);
    }

    private void initSimulation() {
        final WorldMapRenderer worldMapRenderer = new TextPaneWorldMapRenderer(textPane);
        final WorldMap worldMap = new WorldMap(10, 10, worldMapRenderer);
        simulation = new Simulation(worldMap, worldMapRenderer);
    }
}
