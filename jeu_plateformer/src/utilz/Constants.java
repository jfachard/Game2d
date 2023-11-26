package src.utilz;

public class Constants {

    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    
    public static class PlayerConstants{

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int DYNG = 3;
        public static final int HIT = 4;

        public static int getSpriteAmount(int player_action){

            switch (player_action) {
                case RUNNING:
                    return 8;
                case IDLE:
                    return 6;
                case JUMP:
                    return 7;
                case DYNG:
                    return 9;
                case HIT:
                    return 4;
                default:
                    return 1;
            
            }
        }
    }
}
