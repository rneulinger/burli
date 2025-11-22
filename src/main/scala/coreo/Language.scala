package coreo

object Language:
  //tag::env[]

  /**
   * environmental scopes for our test.
   */
  enum env:
    case
    // ------------------------- no context required <.>
    `*`,
    // ------------------------- hardware resources <.>
    server,
    host,
    tank,
    lan,
    wan,
    broadcast,
    // ...tbd
    // ------------------------ game controller <.>
    joystick,
    trackball,
    // ------------------------ touch devices <.>
    mobile,
    tablet,
    convertible,
    // ------------------------ input devices <.>
    keyboard,
    // ------------------------ pointing device <.>
    mouse,
    // ------------------------ software related sources <.>
    os,
    file,
    // ------------------------- browser <.>
    browser,
    // ------------------------ application <.>
    button,
    text,
    checkbox,
    combobox,
    listbox,
    table,
    tab,
    tree,
    menu, menuitem,
    view,
    window
  end env
  //end::env[]

  //tag::cause[]

  /**
   * basic operations on UI Elements
   */
  enum cause:
    case
    //tag::free-cause[]
    // -----------------  free context utilities <.>
    `wait?`, // requires microlanguage ? PRIO:-
    flash, // flash an element on UI // Prio: +??
    screenshot, // perform screenshot Prio++
    screenshotUpdate, // perform optional screeshot PRIO:++
    progress, // tracking of develoment
    breakpoint, //
    dump, // after introduction of variables PRIO: -
    // ..... more to come
    //end::free-cause[]

    //tag::keyboard-cause[]
    // keyboard context
    `type`, // PRIO: ++; ctrl tab enter backspace shift -> a language of its own PRIO: -
    keystroke,
    `press`, // special keystroke Tab Enter Esc ... PRIO: +

    // shortcuts for keystrokes
    _Esc,
    _Home,
    _PageUp,
    _PageDown,
    _Tab,
    _Shift_Tab,
    _Return,
    _Delete,
    // ....
    _Ctrl,
    _Shift,
    _Alt,
    _Ctrl_Shift,
    _Copy,
    _Paste,
    _Undo, // PRIO: -
    _Redo, // PRIO: -
    // ... more to come
    //end::keyboard-cause[]

    // context UI
    `await`, `awaitNot`,
    focus, touch, //
    set, // PRIO +++
    get, // PRIO --
    insert, // PRIO --
    append, // PRIO --
    replace, // PRIO --
    assign, let, // introduction of variables PRIO: -
    click, // maybe with parameter count = 1 // PRIO: ++
    select, // in lists, PRIO: ++
    rightClick, // maybe with parameter count = 1
    doubleClick, // convenience
    copy, // PRIO:- paste,// PRIO:-
    expand, collapse,
    drag, drop,
    // decorators for objects
    help,
    msg, // Prio +
    popup,

    // only usefull in context of checkbox
    check,
    uncheck,
    toggle, // -> same as click 

    goto, `navigate to`, `open path`, `open url`, // PRIO:++

    // Problem domain PRIO:-
    open,
    download,
    save,
    load,
    send,
    talk
  end cause

  //end::cause[]

  //tag::effect[]

  enum effect:
    case
    check, chk,
    shows,
    displays,

    // string match
    contains,
    matches,

    ==, !=,
    =~, !~,
    >, >=, <=, <,

    // numeric match, @ is a proposal, PRIO: -
    `.==`, `.!=`,
    `.=~`, `.!~`,
    `.>`, `.>=`, `.<=`, `.<`,

    // date match, @ is a proposal PRIO: --
    `@==`, `@!=`,
    `@=~`, `@!~`,
    `@>`, `@>=`, `@<=`, `@<`,

    // date shift, # is a proposal microlang required PRIO: --
    `#==`, `#!=`,
    `#=~`, `#!~`,
    `#>`, `#>=`, `#<=`, `#<`,

    // --- boolean mapping
    `true`, `false`,
    visible, hidden,
    enabled, disabled,
    isEmpty, nonEmpty,

    isChecked, unChecked,
    on, off,
    yes, no,
    passed, failed
    // .... many more sum-type mappings (enum ....)
    //
  end effect
  //end::effect[]

  //tag::crud[]
  enum crud:
    case
    add, create,
    show, read,
    edit, update,
    delete,
    filter, sort,
    view, query
  end crud
  //end::crud[]

  //tag::abrev[]
  val OBJ = Set(BTN, TXT);
  val `*` = env.`*` // no context required
  val KDB = env.keyboard
  val BTN = env.button
  val TXT = env.text
  val CHK = env.checkbox
  val CBX = env.combobox
  val LST = env.listbox
  val TAB = env.tab
  val TBL = env.table
  val TRE = env.tree
  val MNU = env.menu
  val MIT = env.menuitem
//end::abrev[]

//tag::cli[]
object Cli:
  def main(args: Array[String]): Unit =

    val greet =
      s"""
         |Hello, Word from language
         |""".stripMargin

    println(greet)
//end::cli[]

object lab:
  enum decorator:
    enum k {
      case
      x,
      y,
      z
    }

    case
    a,
    b,
    k

  var res: decorator = decorator.k
  res = decorator.a
  res = decorator.b
  res = decorator.k
  res.k.x

end lab