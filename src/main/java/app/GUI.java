package app;

import app.enums.PileType;
import app.listeners.GuiActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame implements MouseListener, MouseMotionListener {
    private JMenuBar menuBar;
    private Map<String, String> menuOptions;
    private JPanel gameArea;
    private JPanel columns;
    private JPanel topColumns;
    private JLayeredPane layeredPane;
    private Engine game;
    private Pile tempPile;
    private Point mouseOffset;
    private GuiActionListener actionListener;

    public GUI (Engine game) {
        this.game = game;
        this.actionListener = new GuiActionListener(this);
        initializeGui();
        initializeCardPositions();
    }

    private void initializeGui() {
        createTextMap();

        setTitle("Solitaire");
        setSize(900, 700);

        try {
            setContentPane((new JPanelWithBackground("/background.jpg", this)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        gameArea = new JPanel();
        gameArea.setOpaque(false);
        gameArea.setLayout(new BoxLayout(gameArea, BoxLayout.PAGE_AXIS));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //createTopMenu();

        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        flow.setAlignOnBaseline(true);

        columns = new JPanel();
        columns.setOpaque(false);
        columns.setLayout(flow);
        columns.setMinimumSize(new Dimension(200, 900));

        FlowLayout topFlow = new FlowLayout(FlowLayout.LEFT);
        topFlow.setAlignOnBaseline(true);

        topColumns = new JPanel();
        topColumns.setOpaque(false);
        topColumns.setLayout(topFlow);


        gameArea.add(topColumns);
        gameArea.add(columns);

        add(gameArea);

        layeredPane = getLayeredPane();
        setVisible(true);

        mouseOffset = new Point(0, 0);
    }

    private void initializeCardPositions() {
        topColumns.removeAll();
        columns.removeAll();

        for(Card card: game.getDeck().getCards()) {
            card.addMouseListener(this);
            card.addMouseMotionListener(this);
        }

        game.setupGame();
        for(Pile pile : game.getPiles()) {
            columns.add(pile);
        }

        topColumns.add(game.getDrawPile());
        topColumns.add(game.getGetPile());

        for(Pile pile : game.getFinalPiles()) {
            topColumns.add(pile);
        }

        validate();
    }

    public void reset() {
        game.resetCards();
        initializeCardPositions();
        repaint();
    }

    private void createTextMap() {
        menuOptions = new HashMap();

        menuOptions.put("File", "File");
        menuOptions.put("New", "New");
        menuOptions.put("Exit", "Exit");
    }

    /*
    private void createTopMenu() {
        menuBar = new JMenuBar();

        JMenu FileMenu = new JMenu("File");
        FileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(FileMenu);

        MenuOption[] fileOptions = new MenuOption[] {
                new MenuOption(menuOptions.get("New"), KeyEvent.VK_N),
                new MenuOption(menuOptions.get("Exit"), KeyEvent.VK_X)
        };

        for(MenuOption option: fileOptions) {
            JMenuItem opt = new JMenuItem(option.name);
            if(option.shortcut != 0) opt.setMnemonic(option.shortcut);

            opt.addActionListener(actionListener);
            FileMenu.add(opt);
        }

        setJMenuBar(menuBar);
    }
*/

    @Override
    public void mouseDragged(MouseEvent e) {
        if(tempPile != null) {

            Point pos = getLocationOnScreen();
            pos.x = e.getLocationOnScreen().x - pos.x - mouseOffset.x;
            pos.y = e.getLocationOnScreen().y - pos.y - mouseOffset.y;

            tempPile.setLocation(pos);
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent() instanceof Card) {
            Card c = (Card)e.getComponent();
            Pile p = (Pile)c.getParent();

            switch(p.getType()) {
                case DRAW:
                    game.drawCard();
                    break;
                case NORMAL:
                    game.clickPile(p);
                    break;
                case GET:
                    game.turnGetPile();
                    break;
            }
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getComponent() instanceof Card) {
            Card c = (Card)e.getComponent();

            if(c.isReversed())
                return;

            Pile p  = (Pile)c.getParent();

            if(p.getCards().isEmpty() || p.getType() == PileType.FINAL) return;

            tempPile = p.split(c);


            layeredPane.add(tempPile, JLayeredPane.DRAG_LAYER);

            Point pos = getLocationOnScreen();
            mouseOffset = e.getPoint();
            pos.x = e.getLocationOnScreen().x - pos.x - mouseOffset.x;
            pos.y = e.getLocationOnScreen().y - pos.y - mouseOffset.y;

            tempPile.setLocation(pos);

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(tempPile != null) {

            Point mousePos = e.getLocationOnScreen();
            boolean match = false;

            ArrayList<Pile> droppable = new ArrayList(game.getPiles());
            droppable.addAll(game.getFinalPiles());

            for(Pile p: droppable) {
                Point pilePos = p.getLocationOnScreen();
                Rectangle r = p.getBounds();
                r.x = pilePos.x;
                r.y = pilePos.y;

                if(r.contains(mousePos) && p.acceptsPile(tempPile)) {
                    p.merge(tempPile);
                    match = true;
                    break;
                }
            }

            if(!match) tempPile.getParentPile().merge(tempPile);

            layeredPane.remove(tempPile);
            tempPile = null;

            repaint();

            if(game.checkWin()) {
                JOptionPane.showMessageDialog(this, "You won! Congrats!");
                reset();
            }
        }
    }

    public Map<String, String> getMenuOptions() {
        return menuOptions;
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {}
    @Override
    public void mouseExited(MouseEvent arg0) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}
