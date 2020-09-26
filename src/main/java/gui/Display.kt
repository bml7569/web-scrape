package gui

import analytics.Analytics
import scraping.Scraper
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.*

class Display : JFrame("Article Analytics") {
    private var analytics: Analytics? = null
    private val panel = JPanel()
    private val urlField = JTextField()
    private val frequencyButton = JButton("Word frequency")
    private val button2 = JButton("Button 2")
    private val urlButton = JButton("Read website")

    init {
        setSize(500, 400)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        contentPane = panel
        layout = GridBagLayout()

        val constraints = GridBagConstraints()

        frequencyButton.setSize(150, 40)
        frequencyButton.addActionListener {
            checkIfUrlSubmitted()
            wordFrequency(analytics!!)
        }
        with(constraints) {
            fill = GridBagConstraints.HORIZONTAL
            gridx = 0
            gridy = 1
        }
        add(frequencyButton, constraints)

        button2.setSize(150, 40)
        with(constraints) {
            fill = GridBagConstraints.HORIZONTAL
            gridx = 1
            gridy = 1
        }
        add(button2, constraints)

        urlField.toolTipText = "URL"
        urlField.setSize(200, 20)
        with(constraints) {
            fill = GridBagConstraints.HORIZONTAL
            gridx = 0
            gridy = 0
        }
        add(urlField, constraints)

        urlButton.setSize(150, 40)
        urlButton.addActionListener { urlSubmitted() }
        with(constraints) {
            fill = GridBagConstraints.HORIZONTAL
            gridx = 1
            gridy = 0
        }
        add(urlButton, constraints)

        pack()
        isVisible = true
    }

    private fun urlSubmitted() {
        val scraper = Scraper(urlField.text)
        if (!scraper.goodURL()) {
            JOptionPane.showMessageDialog(this, "Invalid URL",
                    "Message", JOptionPane.WARNING_MESSAGE)
            return
        }
        scraper.read()

        analytics = Analytics(scraper.article)
        JOptionPane.showMessageDialog(this, "Page successfully scraped")
    }

    private fun checkIfUrlSubmitted() {
        if (analytics == null) {
            JOptionPane.showMessageDialog(this, "Please enter a URL",
                    "Message", JOptionPane.WARNING_MESSAGE)
        }
    }
}

fun main() {
    Display()
}
