import javax.swing.*;

public class win implements para{
    private int[][] chess;

    public win(int[][] chess) {
        this.chess = chess;
    }
    public void isWin(int x,int y) {
        if (checkline(x,y) >= 5||checkverti(x,y) >= 5||checkR(x,y)>=5||checkL(x,y)>=5) {
            if (chess[x][y] == 1) {
                JOptionPane.showMessageDialog(null, "白棋赢！");
            }
            if (chess[x][y] == -1) {
                JOptionPane.showMessageDialog(null, "黑棋赢！");
            }
        }
    }
//斜右
    private int checkR(int x, int y) {
        int n=1;
        int i=1;
        int count=1;
        for(;n<=4&&(x-n>=0 )&&(y-n>=0);n++){
            if(chess[x][y]==chess[x-n][y-n]){
                count++;
            }
            else break;

        }
        for(;i<=4&&(x+i<BOARD_SIZE)&&(y+i>=0);i++){
            if(chess[x][y]==chess[x+i][y+i]){
                count++;
            }
            else break;
        }
        return count;
    }

//斜左
    private int checkL(int x, int y) {
        int n=1;
        int i=1;
        int count=1;
        for(;n<=4&&(x+n<BOARD_SIZE)&&(y-n>=0);n++){
            if(chess[x][y]==chess[x+n][y-n]){
                count++;
            }
            else break;

        }
        for(;i<=4&&(y+i<BOARD_SIZE)&&(x-i>=0);i++){
            if(chess[x][y]==chess[x-i][y+i]){
                count++;
            }
            else break;
        }
        return count;
    }
//同一列
    private int checkverti(int x, int y) {
        int n=1;
        int i=1;
        int count=1;
        for(;n<=4&&(y+n<BOARD_SIZE);n++){
            if(chess[x][y]==chess[x][y+n]){
                count++;
            }
            else break;

        }
        for(;i<=4&&(y-i>=0);i++){
            if(chess[x][y]==chess[x][y-i]){
                count++;
            }
            else break;
        }
        return count;
    }
    //同一排是否有五个棋子
    private int checkline(int x, int y) {
        int n=1;
        int i=1;
        int count = 1;
        for(;n<=4&&(x+n)<BOARD_SIZE;n++){
            if(chess[x][y]==chess[x+n][y]){
                count++;
                System.out.println(count);
            }
            else break;
        }

//        count = 0;
        for(;i<=4&&(x-i)>=0;i++){
            if(chess[x][y]==chess[x-i][y]){
                count++;
            }
            else break;
        }
       return count;

    }
}
