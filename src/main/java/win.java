import javax.swing.*;

public class Win implements Para
{
    private int[][] chess;

    public Win(int[][] chess)
    {
        this.chess = chess;
    }

    public void isWin(int x, int y)
    {
        if (checkLine(x, y) >= 5 || checkVerti(x, y) >= 5 || checkR(x, y) >= 5 || checkL(x, y) >= 5)
        {
            if (chess[x][y] == 1)
            {
                JOptionPane.showMessageDialog(null, "白棋赢！");
            }
            if (chess[x][y] == -1)
            {
                JOptionPane.showMessageDialog(null, "黑棋赢！");
            }
        }
    }

    //斜右
    private int checkR(int x, int y)
    {
        int count = 1;
        for (int n = 1; n <= 4 && x - n < BOARD_SIZE && x - n >= 0 && y - n < BOARD_SIZE && y - n >= 0; n++)
        {
            if (chess[x][y] == chess[x - n][y - n])
                count++;
            else
                break;

        }
        for (int i = 1; i <= 4 && x + i >= 0 && x + i < BOARD_SIZE && y + i >= 0 && y + i < BOARD_SIZE; i++)
        {
            if (chess[x][y] == chess[x + i][y + i])
                count++;
            else
                break;
        }
        return count;
    }

    //斜左
    private int checkL(int x, int y)
    {
        int count = 1;
        for (int n = 1; n <= 4 && x + n >= 0 && x + n < BOARD_SIZE && y - n >= 0 && y - n < BOARD_SIZE; n++)
        {
            if (chess[x][y] == chess[x + n][y - n])
                count++;
            else
                break;

        }
        for (int i = 1; i <= 4 && y + i >= 0 && y + i < BOARD_SIZE && x - i >= 0 && x - i < BOARD_SIZE; i++)
        {
            if (chess[x][y] == chess[x - i][y + i])
                count++;
            else
                break;
        }
        return count;
    }

    //同一列
    private int checkVerti(int x, int y)
    {
        int count = 1;
        for (int n = 1; n <= 4 && y + n >= 0 && y + n < BOARD_SIZE; n++)
        {
            if (chess[x][y] == chess[x][y + n])
                count++;
            else
                break;

        }
        for (int i = 1; i <= 4 && y - i < BOARD_SIZE && y - i >= 0; i++)
        {
            if (chess[x][y] == chess[x][y - i])
                count++;
            else
                break;
        }
        return count;
    }

    //同一排是否有五个棋子
    private int checkLine(int x, int y)
    {
        int count = 1;
        for (int n = 1; n <= 4 && x + n >= 0 &&  x + n < BOARD_SIZE; n++)
        {
            if (chess[x][y] == chess[x + n][y])
                count++;
            else
                break;
        }

        for (int i = 1; i <= 4 && x - i >= 0 && x - i < BOARD_SIZE; i++)
        {
            if (chess[x][y] == chess[x - i][y])
                count++;
            else
                break;
        }
        return count;
    }
}
