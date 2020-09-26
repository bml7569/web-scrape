package gui

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTextField

class Display : JFrame("Article Analytics") {
    private val panel = JPanel()
    private val urlField = JTextField()
    private val frequencyButton = JButton("Word frequency")
    private val button2 = JButton("Button 2")

    init {
        setSize(500, 400)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        contentPane = panel
        layout = GridBagLayout()

        val constraints = GridBagConstraints()

        frequencyButton.setSize(150, 40)
        frequencyButton.addActionListener { frequency() }
        with(constraints) {
            fill = GridBagConstraints.HORIZONTAL
            weightx = 0.5
            gridx = 0
            gridy = 1
        }
        add(frequencyButton, constraints)

        button2.setSize(150, 40)
        with(constraints) {
            fill = GridBagConstraints.HORIZONTAL
            weightx = 0.5
            gridx = 1
            gridy = 1
        }
        add(button2, constraints)

        urlField.toolTipText = "URL"
        urlField.setSize(200, 20)
        with(constraints) {
            fill = GridBagConstraints.HORIZONTAL
            weightx = 0.0
            gridwidth = 2
            gridx = 0
            gridy = 0
        }
        add(urlField, constraints)

        pack()
        isVisible = true
    }

    private fun frequency() {

    }
}

fun main() {
    Display()
}
