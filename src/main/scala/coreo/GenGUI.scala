package coreo

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}

import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import java.awt.*

class GenGUI(s: String) {

  import javax.swing.*, java.awt.event.*, java.awt.*

  val frmGen = new JFrame(s"Define Frame")

  private def defGen(): Unit = {
    val layout = BorderLayout()
    frmGen.setLayout(layout);

    // button
    val northPanel = JPanel();
    northPanel.setPreferredSize(new Dimension(200, 50))
    northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS)); // Vertical alignment
    val name = JTextField("MyFrm_")
    northPanel.add(name)
    frmGen.add(northPanel, BorderLayout.NORTH)

    val westPanel = JPanel()
    westPanel.setPreferredSize(new Dimension(200, 300))
    westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.X_AXIS)); // Vertical alignment
    val atoms = JTextArea("")
    westPanel.add(JScrollPane(atoms), BorderLayout.WEST)
    frmGen.add(westPanel, BorderLayout.WEST)


    val eastPanel = JPanel();
    eastPanel.setPreferredSize(new Dimension(400, 300))
    eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS)); // Vertical alignment
    val output = JTextArea("")
    eastPanel.add(JScrollPane(output), BorderLayout.EAST)
    frmGen.add(eastPanel, BorderLayout.EAST)

    val southPanel = JPanel()
    southPanel.setPreferredSize(new Dimension(200, 50))
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS)); // Vertical alignment
    val generate = JButton("Generate")
    generate.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent) = {

        val txt = atoms.getText
        val out = Defs.gen(txt, name.getText)
        output.setText(out)

      }
    })
    southPanel.add(generate)
    frmGen.add(southPanel, BorderLayout.SOUTH)
  }

  defGen()
  frmGen.pack()
  frmGen.setVisible(true)

}
