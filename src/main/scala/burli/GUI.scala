package burli

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}

/**
 * explorer
 *
 * @param pwRoot
 */
class GUI(ui: PwRoot) {

  import javax.swing.*, java.awt.event.*, java.awt.*
  val frame = new JFrame(s"Coreo Basar ${ui.nameOfApp}")
  val layout = BorderLayout()
  frame.setLayout(layout);

  // button
  val northPanel = JPanel();
  northPanel.setPreferredSize(new java.awt.Dimension(200, 100))
  northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS)); // Vertical alignment
  frame.add(northPanel, BorderLayout.NORTH)

  val filter = JTextArea("<Filter>")
  filter.setToolTipText("not implemented yet filter for displayed elements")
  filter.setPreferredSize(new java.awt.Dimension(200, 100))
  northPanel.add(filter)

  val urls = JComboBox[String]()
  for( url <- ui.predefBaseUrls ){
    urls.addItem(url._1)
  }
  urls.setPreferredSize(new java.awt.Dimension(200, 100))
  northPanel.add(urls)
  if ui.predefBaseUrls.isEmpty then urls.setEnabled(false)

  val home = JButton("Home")
  home.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      val idx = urls.getSelectedIndex
      val base = ui.predefBaseUrls(urls.getItemAt(idx))
      println("Not implemented yet: " + base)

      try {
        import java.net.URI;
        println( base )
        Desktop.getDesktop.browse(URI(base))
      }
      catch {
        case ex: Exception =>
          ex.printStackTrace()
      }
    }
  })
  home.setEnabled(false)
  home.setPreferredSize(new java.awt.Dimension(200, 100))
  northPanel.add(home)

  val goto = JButton("Goto")
  goto.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      val path = currentFrm.path
      val idx = urls.getSelectedIndex
      val base = ui.predefBaseUrls(urls.getItemAt(idx))
      println("Not implemented yet: " + base)
      try {
        import java.net.URI;
        println( base + currentFrm.path)
        Desktop.getDesktop.browse(URI(base + currentFrm.path))
      }
      catch {
        case ex: Exception =>
          ex.printStackTrace()
      }
    }
  })
  goto.setPreferredSize(new java.awt.Dimension(200, 100))
  northPanel.add(goto)
  goto.setEnabled(false)


  // frames
  val frmsModel = DefaultListModel[String]()
  val frms = JList(frmsModel)
  for entry <- ui.frms.keySet.toList.sorted.zipWithIndex yield{
    frmsModel.add(entry._2, entry._1)
  }

  var currentFrm:FRM = _

  frms.addListSelectionListener( new ListSelectionListener {
    override def valueChanged(e: ListSelectionEvent): Unit = {
      atomsModel.clear()
      val item = frms.getSelectedValue()

      if (ui.frms.contains(item)){
        currentFrm = ui.frms(item)
        println(item + " " + currentFrm.path)
        enableButtons()
        home.setEnabled(true)
        if ( currentFrm.path.isEmpty) {
          goto.setEnabled(false)
          goto.setText("Goto")
        } else
          goto.setEnabled(true)
          goto.setText("../"+currentFrm.path)

        val frm = ui.frms(item)
        for atom <- frm.atoms.keySet.toList.sorted.zipWithIndex yield{
          atomsModel.add(atom._2, atom._1)
        }
      }
    }
  })

  frms.setPreferredSize(new java.awt.Dimension(200, 300))
  frame.add(JScrollPane(frms), BorderLayout.WEST)

  // atoms
  val atomsModel = DefaultListModel[String]()
  val atoms = JList(atomsModel)
  atomsModel.add(0, "Nothing selected")
  //  for entry <- ui.frms.keySet.toList.sorted.zipWithIndex yield{
  //    frmsModel.add(entry._2, entry._1)
  //  }
  atoms.setPreferredSize(new java.awt.Dimension(200, 400))
  frame.add(atoms, BorderLayout.CENTER)

  val set = JButton("Set Gherkin")
  set.addActionListener(new ActionListener{
    def actionPerformed(e: ActionEvent) = {
      val txt = currentFrm.mkSet
      text.setText(txt)
    }
  })
  set.setToolTipText("create a Set block to be used in Gherkin");

  val get = JButton("Get Gherkin")
  get.addActionListener(new ActionListener{
    def actionPerformed(e: ActionEvent) = {
      val txt = currentFrm.mkGet
      text.setText(txt)
    }
  })
  get.setToolTipText("create a Get block to be used in Gherkin");

  val chk = JButton("Check Gherkin")
  chk.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      val txt = currentFrm.mkChk
      text.setText(txt)
    }
  })
  chk.setToolTipText("create a Chk block to be used in Gherkin");

  val act = JButton("Actions Gherkin")
  act.setToolTipText("create an Act block to be used in Gherkin");
  act.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      val txt = currentFrm.mkSet
      text.setText(txt)
    }
  })


  val doc = JButton("Asciidoc Snippet")
  doc.setToolTipText("create a Asciidoc snippet");
  doc.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      val txt = currentFrm.mkDoc
      text.setText(txt)
    }
  })


  val cs = JButton("C # Definition")
  cs.setToolTipText("create a C # definition for that frame");
  cs.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      val txt = currentFrm.mkCs
      text.setText(txt)
    }
  })


  val help = JButton("Help")
  help.setToolTipText("Not implemented yet");
  help.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      val txt = currentFrm.mkCs
      text.setText("here we have to define out help text")
    }
  })

  val btlist = scala.collection.immutable.List[JComponent](set, get, chk,act, doc,cs, help)

  // Create a panel for the EAST region
  val eastPanel = JPanel();
  eastPanel.setPreferredSize(new java.awt.Dimension(200, 400))
  eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS)); // Vertical alignment

  // Add 3 buttons to the panel
  for( b <- btlist) eastPanel.add(b);

  def disableButtons(): Unit = {
    for( b <- btlist) { b.setEnabled(false)}
  }
  def enableButtons(): Unit = {
    for( b <- btlist) { b.setEnabled(true)}
  }
  frame.add(eastPanel, BorderLayout.EAST)
  disableButtons()
  val text = JTextArea()
  text.setFont(new Font("Monospaced", Font.BOLD , 14));
  text.setPreferredSize(new java.awt.Dimension(300, 200))

  frame.add(JScrollPane(text), BorderLayout.SOUTH)


  frame.pack()
  frame.setVisible(true)


}
