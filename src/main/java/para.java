public interface Para
{
    final int TABLE_WIDTH=535;
    final int TABLE_HEIGHT=536;

    final int BOARD_SIZE=15;


    final int RATE=TABLE_WIDTH/BOARD_SIZE;
    //棋子x方向和y方向的偏移量
    final int X_OFFSET=5;
    final int Y_OFFSET=6;

    int[][] board=new int[BOARD_SIZE][BOARD_SIZE];//0没有棋子，1为白，2为黑

    int SELECT_X=-1;
    int SELECT_Y=-1;
}
