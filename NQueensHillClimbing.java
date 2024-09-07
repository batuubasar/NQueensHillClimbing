import java.util.Random;

public class NQueensHillClimbing {

    private static final int SIZE = 8; // Satranç tahtası boyutu
    private static int TEST_NUMBER  = 15; // Maksimum random restart sayısı

    public static void main(String[] args) {
        while(TEST_NUMBER >0){
        int[] board = solve();
        printBoard(board);
        TEST_NUMBER--;
        }

    }

    public static int[] solve() {
        int[] board = new int[SIZE];
        int[] tempBoard = new int[SIZE];
        int conflicts, tempConflicts;
        int moves = 0;
        int restarts = 0;
        long startTime = System.currentTimeMillis();

        while (true) {
            conflicts = countConflicts(board);

            if (conflicts == 0) {
                break; // Çözüm bulundu
            }

            tempBoard = board.clone();
            tempConflicts = conflicts;

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i] != j) {
                        tempBoard[i] = j;
                        int newConflicts = countConflicts(tempBoard);
                        moves++;

                        if (newConflicts < tempConflicts) {
                            board[i] = tempBoard[i];
                            tempConflicts = newConflicts;
                            break;
                        }
                        tempBoard[i] = board[i];
                    }
                }
            }

            if (tempConflicts >= conflicts) {
                // Yerel optimuma ulaşıldı, random restart
                board = generateRandomBoard();
                restarts++;
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Place Changes | Random Restarts | Time (ms)");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%13d | %15d | %12d\n", moves, restarts, elapsedTime);

        return board;
    }

    public static int countConflicts(int[] board) {
        int conflicts = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = i + 1; j < SIZE; j++) {
                if (board[i] == board[j] || Math.abs(board[i] - board[j]) == j - i) {
                    conflicts++;
                }
            }
        }

        return conflicts;
    }

    public static int[] generateRandomBoard() {
        Random random = new Random();
        int[] board = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            board[i] = random.nextInt(SIZE);
        }

        return board;
    }

    public static void printBoard(int[] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
            for (int j = 0; j < SIZE; j++) {
                if (board[i] == j) {
                    System.out.print("| Q ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        for (int j = 0; j < SIZE; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
