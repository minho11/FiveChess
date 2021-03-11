import java.util.*;

public class AIEasy implements Para, AI
{
    int[][] value = new int[15][15];
    public Fivechess f;
    int x;
    int y;

    public AIEasy(Fivechess f)
    {
        this.f = f;
    }

    public void cal_empty_value()
    {
        int all_value = 0;
        for (int x = 0; x < BOARD_SIZE; x++)
        {
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                if (f.board[x][y] == 0)
                {
                    all_value = value_line(x, y) + value_l(x, y) + value_r(x, y) + value_row(x, y);
                    value[x][y] = all_value;
                }
                else
                    value[x][y] = -Integer.MAX_VALUE;
            }
        }

    }

//    int[] s = {7, 35, 800, 15000, 800000, 15, 400, 1800, 100000, 0, 0};

    private int valuate(int count, int count_black, int count_white)
    {
        int value = 0;

        if (count == 5)
        {
            if (count_black == 0 && count_white == 0)
            {
                value += 7;
            }
            else if (count_black == 1 && count_white == 0)
            {
                value += 35;
            }
            else if (count_black == 2 && count_white == 0)
            {
                value += 800;
            }
            else if (count_black == 3 && count_white == 0)
            {
                value += 15000;
            }
            else if (count_black == 4 && count_white == 0)
            {
                value += 80000;
            }
            else if (count_black == 0 && count_white == 1)
            {
                value += 15;
            }
            else if (count_black == 0 && count_white == 2)
            {
                value += 400;
            }
            else if (count_black == 0 && count_white == 3)
            {
                value += 1800;
            }
            else if (count_black == 0 && count_white == 4)
            {
                value += 100000;
            }
        }

        return value;
    }

    //一行上
    public int value_line(int x, int y)
    {
        int count_black = 0;
        int count_white = 0;
        int value = 0;
        int count = 0;

        for (int i = 0; i < 5 && x - i >= 0; i++)
        {
            count = 0;
            for (int j = 0; j < 5 && (x - i + j) < BOARD_SIZE && (x - i + j) >= 0; j ++)
            {
                if (f.board[x - i + j][y] == -1)
                    count_black++;

                if (f.board[x - i + j][y] == 1)
                    count_white++;

                count ++;
            }

            // valuate
            value += valuate(count, count_black, count_white);
        }

        return value;
    }

    //一列上
    public int value_row(int x, int y)
    {
        int count_black = 0;
        int count_white = 0;
        int value = 0;
        int count = 0;

        for (int i = 0; i < 5 && x - i >= 0; i++)
        {
            count = 0;
            for (int j = 0; j < 5 && (y - i + j) < BOARD_SIZE && (y - i + j) >= 0; j ++)
            {
                if (f.board[x][y - i + j] == -1)
                    count_black++;

                if (f.board[x][y - i + j] == 1)
                    count_white++;

                count ++;
            }

            // valuate
            value += valuate(count, count_black, count_white);
        }

        return value;
    }

    //右上
    public int value_r(int x, int y)
    {
        int count_black = 0;
        int count_white = 0;
        int value = 0;
        int count = 0;

        for (int i = 0; i < 5 && x - i >= 0; i++)
        {
            count = 0;
            for (int j = 0; j < 5 && (x - i + j) < BOARD_SIZE && (x - i + j) >= 0
                    && (y + i - j) < BOARD_SIZE && (y + i - j) >= 0; j ++)
            {
                if (f.board[x - i + j][y + i - j] == -1)
                    count_black++;

                if (f.board[x - i + j][y + i - j] == 1)
                    count_white++;

                count ++;
            }

            // valuate
            value += valuate(count, count_black, count_white);
        }

        return value;
    }

    //左上
    public int value_l(int x, int y)
    {
        int count_black = 0;
        int count_white = 0;
        int value = 0;
        int count = 0;

        for (int i = 0; i < 5 && x - i >= 0; i++)
        {
            count = 0;
            for (int j = 0; j < 5 && (x - i + j) < BOARD_SIZE && (x - i + j) >= 0
                    && (y - i + j) < BOARD_SIZE && (y - i + j) >= 0; j ++)
            {
                if (f.board[x - i + j][y - i + j] == -1)
                    count_black++;

                if (f.board[x - i + j][y - i + j] == 1)
                    count_white++;

                count ++;
            }

            // valuate
            value += valuate(count, count_black, count_white);
        }

        return value;
    }

    public int[] return_positon()
    {
        cal_empty_value();

        int[] values = new int[255];
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                values[15 * j + i] = value[i][j];

        Arrays.sort(values);

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                if (value[i][j] == values[254])
                {
                    x = i;
                    y = j;
                }

        return new int[]{x, y};
    }
}