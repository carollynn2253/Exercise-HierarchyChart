package tingchiu.hierarchychart;

/**
 * Created by ting.chiu on 2016/10/5.
 */

public class GlobalConstant {
    public static final int LINE_WIDTH = 4;

    public static int NODE_WIDTH;
    public static int NODE_HEIGHT;
    public static final int NODE_HORIZONTAL_MARGIN = 10;  //each node's right and left margin
    public static final int NODE_VERTICAL_MARGIN = 60;  //each node's top and bottom margin

    public static final int TOTAL_LAYER = 4;

    public static final int TOP_LAYER_CHILD_NUMBER = 2;
    public static final int SECOND_LAYER_CHILD_NUMBER = 2;
    public static final int THIRD_LAYER_CHILD_NUMBER = 2;

    public static final int BOTTOM_CHILDREN_COUNT = TOP_LAYER_CHILD_NUMBER * SECOND_LAYER_CHILD_NUMBER * THIRD_LAYER_CHILD_NUMBER;

    public static int getChildrenCount(int layer) {
        int count = 0;
        switch (layer) {
            case 0:
                count = TOP_LAYER_CHILD_NUMBER;
                break;

            case 1:
                count = TOP_LAYER_CHILD_NUMBER * SECOND_LAYER_CHILD_NUMBER;
                break;

            case 2:
                count = TOP_LAYER_CHILD_NUMBER * SECOND_LAYER_CHILD_NUMBER * THIRD_LAYER_CHILD_NUMBER;
                break;
        }
        return count;
    }

    public static int getChildNumber(int layer) {
        int count = 0;
        switch (layer) {
            case 0:
                count = TOP_LAYER_CHILD_NUMBER;
                break;

            case 1:
                count = SECOND_LAYER_CHILD_NUMBER;
                break;

            case 2:
                count = THIRD_LAYER_CHILD_NUMBER;
                break;
        }
        return count;
    }
}
