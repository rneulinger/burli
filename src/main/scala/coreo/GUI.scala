package coreo

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}

import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import java.awt.*

/**
 * explorer
 *
 * @param ui to work on
 */
class GUI(ui: PwRoot) {

  import javax.swing.*, java.awt.event.*, java.awt.*
  val mainFrame = new JFrame(s"UI ${ui.nameOfApp}")
  //val frmModel = new JFrame(s"UI ${ui.nameOfApp}")


  // Create a tabbed pane
  val tabbedPane = new JTabbedPane();

  // Create panels for tabs
  val frmModel = new JPanel();

  val frmGen = new JPanel();
  frmGen.add(new JLabel("Frame Generatro"));

  // Add tabs to the tabbed pane
  tabbedPane.addTab("Tab 1", frmModel);
  tabbedPane.addTab("Tab 2", frmGen);

  // Add the tabbed pane to the frame
  mainFrame.add(tabbedPane);

  var currentFrm: FRM = _

  private def mkUI(): Unit = {
    val layout = BorderLayout()
    frmModel.setLayout(layout)

    // button
    val northPanel = JPanel()
    northPanel.setPreferredSize(new Dimension(200, 30))
    northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS)); // Vertical alignment
    frmModel.add(northPanel, BorderLayout.NORTH)


    val urls = JComboBox[String]()
    for (url <- ui.predefBaseUrls) {
      urls.addItem(url._1)
    }
    urls.setPreferredSize(new Dimension(200, 30))
    if ui.predefBaseUrls.isEmpty then urls.setEnabled(false)

    val home = JButton("Home")
    home.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val idx = urls.getSelectedIndex
        val base = ui.predefBaseUrls(urls.getItemAt(idx))
        println("Not implemented yet: " + base)

        try {
          import java.net.URI
          println(base)
          Desktop.getDesktop.browse(URI(base))
        }
        catch {
          case ex: Exception =>
            ex.printStackTrace()
        }
      }
    })
    home.setEnabled(false)
    home.setPreferredSize(new Dimension(200, 100))

    val goto = JButton("Goto")
    goto.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val path = currentFrm.path
        val idx = urls.getSelectedIndex
        val base = ui.predefBaseUrls(urls.getItemAt(idx))
        println("Not implemented yet: " + base)
        try {
          import java.net.URI
          println(base + currentFrm.path)
          Desktop.getDesktop.browse(URI(base + currentFrm.path))
        }
        catch {
          case ex: Exception =>
            ex.printStackTrace()
        }
      }
    })
    goto.setPreferredSize(new Dimension(200, 100))
    goto.setEnabled(false)


    // atoms
    val atomsModel = DefaultListModel[String]()
    val atoms = JList(atomsModel)
    atomsModel.add(0, "Nothing selected")
    //  for entry <- ui.frms.keySet.toList.sorted.zipWithIndex yield{
    //    frmsModel.add(entry._2, entry._1)
    //  }
    //atoms.setPreferredSize(new Dimension(200, 400))
    frmModel.add(atoms, BorderLayout.CENTER)

    val text = JTextArea()
    text.setFont(new Font("Monospaced", Font.BOLD, 14))
    val southPanel = JPanel()
    southPanel.setPreferredSize(new Dimension(200, 400))
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS)); // Vertical alignment
    def mkSouthScroll = {
      val res = JScrollPane(text)
      res.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED)
      res.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED)
      res
    }
    southPanel.add(mkSouthScroll, BorderLayout.SOUTH)
    frmModel.add(southPanel, BorderLayout.SOUTH)

    val add = JButton("Add Gherkin")
    add.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkAdd
        text.setText(txt)
      }
    })
    add.setToolTipText("create an Add block to be used in Gherkin")

    val edit = JButton("Edit Gherkin")
    edit.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkEdit
        text.setText(txt)
      }
    })
    edit.setToolTipText("create an Edit block to be used in Gherkin")

    val next = JButton("Next Gherkin")
    next.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkNext
        text.setText(txt)
      }
    })
    next.setToolTipText("create an Next block to be used in Gherkin")

    val set = JButton("Set Gherkin")
    set.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkSet
        text.setText(txt)
      }
    })
    set.setToolTipText("create a Set block to be used in Gherkin")

    val get = JButton("Get Gherkin")
    get.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkGet
        text.setText(txt)
      }
    })
    get.setToolTipText("create a Get block to be used in Gherkin")

    val chk = JButton("Check Gherkin")
    chk.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkChk
        text.setText(txt)
      }
    })
    chk.setToolTipText("create a Chk block to be used in Gherkin")

    val act = JButton("Actions Gherkin")
    act.setToolTipText("create an Act block to be used in Gherkin")
    act.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkAct
        text.setText(txt)
      }
    })


    val doc = JButton("Asciidoc Snippet")
    doc.setToolTipText("create a Asciidoc snippet")
    doc.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkDoc
        text.setText(txt)
      }
    })


    val cs = JButton("C # Definition")
    cs.setToolTipText("create C # definition for that frame")
    cs.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkCs
        text.setText(txt)
      }
    })

    val ts = JButton("TypeScript Definition")
    ts.setToolTipText("create TypeScript definition for that frame")
    ts.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkTs
        text.setText(txt)
      }
    })

    val help = JButton("Help")
    help.setToolTipText("Not implemented yet")
    help.addActionListener(new ActionListener {
      def actionPerformed(e: ActionEvent): Unit = {
        val txt = currentFrm.mkCs
        text.setText("here we have to define out help text")
      }
    })

    val btlist = scala.collection.immutable.List[JComponent](add, edit, next, set, get, chk, act, doc, cs, ts, help)

    // Create a panel for the EAST region
    val eastPanel = JPanel()
    eastPanel.setPreferredSize(new Dimension(200, 400))
    eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS)); // Vertical alignment

    // Add 3 buttons to the panel
    for (b <- btlist) eastPanel.add(b)

    def disableButtons(): Unit = {
      for (b <- btlist) {
        b.setEnabled(false)
      }
      home.setEnabled(false)
    }

    def enableButtons(): Unit = {
      for (b <- btlist) {
        b.setEnabled(true)
      }
    }

    frmModel.add(eastPanel, BorderLayout.EAST)
    disableButtons()


    // frames
    val frmsModel = DefaultListModel[String]()
    val frms = JList(frmsModel)

    def selectFrms(s: String) = {
      frmsModel.clear()
      for entry <- ui.full.keySet.filter(_.contains(s)).toList.sorted.zipWithIndex yield {
        frmsModel.add(entry._2, entry._1)
      }
      disableButtons()
    }

    selectFrms("")

    frms.addListSelectionListener(new ListSelectionListener {
      override def valueChanged(e: ListSelectionEvent): Unit = {
        atomsModel.clear()
        val item = frms.getSelectedValue

        if (ui.full.contains(item)) {
          currentFrm = ui.full(item)

          println(item + " " + currentFrm.path)
          enableButtons()
          home.setEnabled(true)
          if (currentFrm.path.isEmpty) {
            goto.setEnabled(false)
            goto.setText("Goto")
          } else
            goto.setEnabled(true)
            goto.setText("../" + currentFrm.path)

          val frm = ui.full(item)
          for atom <- frm.atoms.keySet.toList.sorted.zipWithIndex yield {
            atomsModel.add(atom._2, atom._1)
          }
        }
      }
    })

    val westPanel = JPanel()

    def mkWestScroll = {
      val res = JScrollPane(frms)
      res.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED)
      res.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED)
      res
    }

    westPanel.setPreferredSize(new Dimension(200, 400))
    westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS)); // Vertical alignment
    westPanel.add(mkWestScroll, BorderLayout.WEST)
    frmModel.add(westPanel, BorderLayout.WEST)

    //frms.setPreferredSize(new Dimension(200, 300))

    val filter = JTextField("")
    filter.setToolTipText("not implemented yet filter for displayed elements")
    filter.setPreferredSize(new Dimension(200, 100))
    // Add DocumentListener to detect changes
    filter.getDocument.addDocumentListener(new DocumentListener() {
      @Override
      override def insertUpdate(e: DocumentEvent): Unit = {
        selectFrms(filter.getText())
      }

      @Override
      override def removeUpdate(e: DocumentEvent): Unit = {
        selectFrms(filter.getText())
      }

      @Override
      override def changedUpdate(e: DocumentEvent): Unit = {
        // Usually for styled text; not used in plain JTextField
      }
    })
    northPanel.add(filter)
    northPanel.add(urls)
    northPanel.add(home)
    northPanel.add(goto)

  }

  private def mkGen(): Unit = {
    val layout = BorderLayout()
    frmGen.setLayout(layout);

    // button
    val northPanel = JPanel();
    northPanel.setPreferredSize(new Dimension(200, 50))
    northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS)); // Vertical alignment
    val name = JTextField("MyFrm_")
    northPanel.add(name)
    frmGen.add(northPanel, BorderLayout.NORTH)

    val westPanel = JPanel();
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

    val southPanel = JPanel();
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

  //////
  mkUI()
  mkGen()

  mainFrame.pack()
  mainFrame.setVisible(true)


}
