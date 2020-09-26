package gui

import analytics.Analytics
import com.github.sh0nk.matplotlib4j.PyCommand
import com.github.sh0nk.matplotlib4j.PythonConfig
import com.google.common.base.Joiner

fun wordFrequency(analytics: Analytics) {
    val frequencies = analytics.wordFrequency()
    val scriptLines = mutableListOf<String>()

    scriptLines.add("""
        |import numpy as np
        |import matplotlib.pyplot as plt
    """.trimMargin())

    scriptLines.add("x = [${commaSeparated(frequencies.keys)}]")
    scriptLines.add("y = [${commaSeparated(frequencies.values)}]")

    scriptLines.add("""
        |plt.bar(x, y)
        |plt.show()
    """.trimMargin())

    val command = PyCommand(PythonConfig.systemDefaultPythonConfig())
    command.execute(Joiner.on('\n').join(scriptLines))
}

fun wordCount(analytics: Analytics) {
    val counts = analytics.countWords()

    val scriptLines = mutableListOf<String>()

    scriptLines.add("""
        |import numpy as np
        |import matplotlib.pyplot as plt
    """.trimMargin())

    scriptLines.add("x = [${commaSeparated(counts.keys)}]")
    scriptLines.add("y = [${commaSeparated(counts.values)}]")

    scriptLines.add("""
        |plt.bar(x, y)
        |plt.show()
    """.trimMargin())

    val command = PyCommand(PythonConfig.systemDefaultPythonConfig())
    command.execute(Joiner.on('\n').join(scriptLines))
}

fun <T> commaSeparated(collection: Collection<T>): String {
    var result = ""
    for (item in collection) {
        result += if (item is Number) {
            item.toString()
        } else {
            ("'${item.toString()}'")
        } + ','
    }
    return result
}