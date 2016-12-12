package tingchiu.hierarchychart;

/**
 * Created by ting.chiu on 2016/10/5.
 */

public class GlobalConstant {
    public static final int LINE_WIDTH = 3;
    public static final String LINE_COLOR = "#d8d8d8";

    public static int NODE_WIDTH = MyApplication.getDisplayWidth() * 25 / 100;
    public static int NODE_HEIGHT = MyApplication.getDisplayWidth() * 30 / 100;
    public static final int NODE_HORIZONTAL_MARGIN = 20;  //each node's right and left margin
    public static final int NODE_VERTICAL_MARGIN = 60;  //each node's top and bottom margin

    public static final int TOTAL_LEVEL = 4;

    public static final int LEVEL_0_CHILD_NUMBER = 2;
    public static final int LEVEL_1_CHILD_NUMBER = 2;
    public static final int LEVEL_2_CHILD_NUMBER = 2;

    public static final int BOTTOM_LEVEL_TOTAL_CHILDREN = LEVEL_0_CHILD_NUMBER * LEVEL_1_CHILD_NUMBER * LEVEL_2_CHILD_NUMBER;

    public static int getLevelChildrenTotal(int level) {
        int total = 0;
        switch (level) {
            case 0:
                total = LEVEL_0_CHILD_NUMBER;
                break;

            case 1:
                total = LEVEL_0_CHILD_NUMBER * LEVEL_1_CHILD_NUMBER;
                break;

            case 2:
                total = LEVEL_0_CHILD_NUMBER * LEVEL_1_CHILD_NUMBER * LEVEL_2_CHILD_NUMBER;
                break;

            default:
                total = LEVEL_0_CHILD_NUMBER;
                break;
        }

        return total;
    }

    public static int getLevelChildNumber(int level) {
        int count = 0;
        switch (level) {
            case 0:
                count = LEVEL_0_CHILD_NUMBER;
                break;

            case 1:
                count = LEVEL_1_CHILD_NUMBER;
                break;

            case 2:
                count = LEVEL_2_CHILD_NUMBER;
                break;
        }
        return count;
    }
}
