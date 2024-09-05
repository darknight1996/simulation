package org.example.render.impl;

import org.example.render.LogRenderer;

import javax.swing.*;

public class TextPandeLogRenderer implements LogRenderer {

    private final JTextPane textPane;

    public TextPandeLogRenderer(final JTextPane textPane) {
        this.textPane = textPane;
    }

    @Override
    public void render(final String text) {
        final String existedText = textPane.getText();
        final String newText = existedText  + text + "\n";

        textPane.setText(newText);

        textPane.setCaretPosition(textPane.getDocument().getLength());
    }
}
