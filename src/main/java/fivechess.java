import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fivechess
{
    AI ai;

    Fivechess f = this;
    Frame window = new Frame("五子棋游戏");
    BufferedImage table;
    BufferedImage black;
    BufferedImage white;
    BufferedImage select;
    int level = -1;
    final int TABLE_WIDTH = 535;
    final int TABLE_HEIGHT = 536;
    final int BOARD_SIZE = 15;

    final int RATE = TABLE_WIDTH / BOARD_SIZE;
    //棋子x方向和y方向的偏移量
    final int X_OFFSET = 5;
    final int Y_OFFSET = 6;

    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];//0没有棋子，1为白，-1为黑

    int SELECT_X = -1;
    int SELECT_Y = -1;

    ChessBoard chess_board = new ChessBoard(this);

    int board_type = 1;

    JPanel jpanel = new JPanel();
    JLabel label = new JLabel("游戏类型:");
    String[] mode = {"人人对战", "人机对战"};
    JComboBox comboBox = new JComboBox(mode);


    JButton b1 = new JButton("白棋");
    JButton b2 = new JButton("黑棋");
    JButton b3 = new JButton("悔棋");
    JButton b4 = new JButton("重新开始");

    public void Reflashcolor(Color b1color, Color b2color, Color b3color, Color b4color)
    {
        b1.setBackground(b1color);
        b2.setBackground(b2color);
        b3.setBackground(b3color);
        b4.setBackground(b4color);

    }

    private void init()
    {
        Reflashcolor(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY);
        b1.addActionListener(e -> {
            board_type = 1;
            Reflashcolor(Color.PINK, Color.GRAY, Color.GRAY, Color.GRAY);

        });
        b2.addActionListener(e -> {
            board_type = -1;
            Reflashcolor(Color.GRAY, Color.PINK, Color.GRAY, Color.GRAY);


        });
        b3.addActionListener(e -> {
            board_type = 0;
            Reflashcolor(Color.GRAY, Color.GRAY, Color.PINK, Color.GRAY);
        });
        b4.addActionListener(e -> {

            Reflashcolor(Color.GRAY, Color.GRAY, Color.GRAY, Color.PINK);
            for (int i = 0; i < BOARD_SIZE; i++)
            {
                for (int j = 0; j < BOARD_SIZE; j++)
                {
                    board[i][j] = 0;
                }
            }
            chess_board.repaint();
        });


        jpanel.add(b2);
        jpanel.add(b1);
        jpanel.add(b3);
        jpanel.add(b4);
        jpanel.add(label);
        jpanel.add(comboBox);

        try
        {
            table = ImageIO.read(Fivechess.class.getClassLoader().getResourceAsStream("image/board.jpg"));
            black = ImageIO.read(Fivechess.class.getClassLoader().getResourceAsStream("image/black.gif"));
            white = ImageIO.read(Fivechess.class.getClassLoader().getResourceAsStream("image/white.gif"));
            select = ImageIO.read(Fivechess.class.getClassLoader().getResourceAsStream("image/selected.gif"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        chess_board.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        chess_board.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                SELECT_X = (e.getX() - X_OFFSET) / RATE;
                SELECT_Y = (e.getY() - Y_OFFSET) / RATE;

                chess_board.repaint();
            }
        });

        chess_board.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int x = (e.getX() - X_OFFSET) / RATE;
                int y = (e.getY() - Y_OFFSET) / RATE;
                board[x][y] = board_type;
                chess_board.repaint();
                new Win(board).isWin(x, y);

                if (level != -1)
                {
                    int[] m = ai.return_positon();
                    board[m[0]][m[1]] = -board_type;
                    chess_board.repaint();
                    new Win(board).isWin(m[0], m[1]);
                }
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                SELECT_X = -1;
                SELECT_Y = -1;
                chess_board.repaint();
            }
        });

        comboBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getStateChange() != ItemEvent.SELECTED)
                    return;

                if (comboBox.getSelectedItem().equals("人机对战"))
                {
                    Object[] possibleValues = {"简单", "困难"};
                    level = JOptionPane.showOptionDialog(null, "请选择对战难度", "难度选择", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, possibleValues, possibleValues[0]);
                }

                if (level == 0)
                {
                    ai = new AIEasy(f);
                }
            }
        });

        window.add(chess_board);
        window.add(jpanel, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
        window.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args)
    {
        new Fivechess().init();
    }
}
