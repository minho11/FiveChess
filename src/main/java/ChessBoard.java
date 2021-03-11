import java.awt.*;

class ChessBoard extends Canvas {
    private final Fivechess fivechess;

    public ChessBoard(Fivechess fivechess) {
        this.fivechess = fivechess;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(fivechess.table,0,0,null);
        if(fivechess.SELECT_X>=0&& fivechess.SELECT_Y>=0) {
            g.drawImage(fivechess.select, fivechess.SELECT_X* fivechess.RATE + fivechess.X_OFFSET, fivechess.SELECT_Y* fivechess.RATE + fivechess.Y_OFFSET, null);
        }
        for (int i = 0; i < fivechess.BOARD_SIZE; i++) {
            for (int j = 0; j < fivechess.BOARD_SIZE; j++) {
                if(fivechess.board[i][j]==-1){
                    g.drawImage(fivechess.black,i* fivechess.RATE+ fivechess.X_OFFSET,j* fivechess.RATE+ fivechess.Y_OFFSET,null);
                }
                else if(fivechess.board[i][j]==1){
                    g.drawImage(fivechess.white,i* fivechess.RATE+ fivechess.X_OFFSET,j* fivechess.RATE+ fivechess.Y_OFFSET,null);
                }

            }

        }

    }
}
