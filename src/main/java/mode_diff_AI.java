import java.util.HashMap;

public class mode_diff_AI implements para, AI {
    public int board_type;
    public fivechess f;
    HashMap<String, Integer> hm = new HashMap<String, Integer>();

    public mode_diff_AI(int board_type, fivechess f) {
        this.board_type = board_type;
        this.f = f;
    }

    public int AlphaBata(int nPly, int nAlpha, int nBta) {
        boolean gameover = false;
        int Evluaion = 0;
        int score;

//        Evluaion = getEvluaion();

        hm.put("011100", 720);
        hm.put("001110", 720);
        hm.put("011010", 720);
        hm.put("010110", 720);
        hm.put("11110", 720);
        hm.put("01111", 720);
        hm.put("11011", 720);
        hm.put("10111", 720);
        hm.put("11101", 720);
        hm.put("001100", 120);
        hm.put("001010", 120);
        hm.put("010100", 120);
        hm.put("000100", 20);
        hm.put("001000", 20);
        hm.put("011110", 4320);
        hm.put("11111", 50000);

        hm.put("022200", -720);
        hm.put("002220", -720);
        hm.put("022020", -720);
        hm.put("020220", -720);
        hm.put("22220", -720);
        hm.put("02222", -720);
        hm.put("22022", -720);
        hm.put("20222", -720);
        hm.put("22202", -720);
        hm.put("002200", -120);
        hm.put("002020", -120);
        hm.put("020200", -120);
        hm.put("000200", -20);
        hm.put("002000", -20);
        hm.put("022220",-24320);
        hm.put("22222", -50000);

        if (gameover) {
            return Evluaion;
        }
        if (nPly == 0) {
            return Evluaion;
        }
        if (board_type == 1) {
            for (int i = 0; i <= BOARD_SIZE; i++) {
                for (int j = 0; j <= BOARD_SIZE; j++) {
                    if (f.board[i][j] == 0) {
                        f.board[i][j] = board_type;
                        score = AlphaBata(nPly - 1, nAlpha, nBta);
                        f.board[i][j] = 0;
                        if (score > nAlpha) {
                            nAlpha = score;
                        }
                        if (nAlpha >= nBta) {
                            return nBta;
                        }

                    }
                    return nAlpha;

                }
            }
        } else {
            for (int i = 0; i <= BOARD_SIZE; i++) {
                for (int j = 0; j <= BOARD_SIZE; j++) {
                    if (f.board[i][j] == 0) {
                        f.board[i][j] = board_type;
                        score = AlphaBata(nPly - 1, nAlpha, nBta);
                        f.board[i][j] = 0;
                        if (score < nBta) {
                            nBta = score;
                        }
                        if (nAlpha >= nBta) {
                            return nAlpha;
                        }

                    }
                    return nBta;

                }
            }

        }
    return Evluaion;
    }

    @Override
    public int[] return_positon() {
        return new int[0];
    }

}

