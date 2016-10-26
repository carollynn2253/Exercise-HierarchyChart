package tingchiu.hierarchychart.widgit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import tingchiu.hierarchychart.GlobalConstant;

import static tingchiu.hierarchychart.GlobalConstant.BOTTOM_CHILDREN_COUNT;
import static tingchiu.hierarchychart.GlobalConstant.LINE_WIDTH;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HEIGHT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HORIZONTAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_WIDTH;
import static tingchiu.hierarchychart.GlobalConstant.TOTAL_LAYER;
import static tingchiu.hierarchychart.GlobalConstant.getChildNumber;
import static tingchiu.hierarchychart.GlobalConstant.getChildrenCount;

/**
 * Created by ting.chiu on 2016/10/5.
 */

//TODO: not finish, only top and bottom
public class NodeLinkLine extends View {

    View node;
    int layer;

    Paint paint;
    Canvas canvas;

    public NodeLinkLine(Context context, View node, int layer) {
        super(context);

        this.node = node;
        this.layer = layer;

        paint = new Paint();
        canvas = new Canvas();
    }

    public NodeLinkLine(Context context, View node, int layer, int position) {
        super(context);

        this.node = node;
        this.layer = layer;

        paint = new Paint();
        canvas = new Canvas();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setAntiAlias(true);

        //top vertical line
        if (layer != 0) {
            canvas.drawLine(node.getLeft() + node.getWidth() / 2, node.getTop() - GlobalConstant.NODE_VERTICAL_MARGIN / 2,
                    node.getLeft() + node.getWidth() / 2, node.getTop(),
                    paint);
        }

        paint.setColor(Color.BLUE);

        //bottom vertical line
        if (layer != TOTAL_LAYER - 1) {
            canvas.drawLine(node.getLeft() + node.getWidth() / 2, node.getTop() + NODE_HEIGHT,
                    node.getLeft() + node.getWidth() / 2, node.getTop() + NODE_HEIGHT + GlobalConstant.NODE_VERTICAL_MARGIN / 2,
                    paint);
        }

        paint.setColor(Color.GREEN);

        //bottom horizontal line
        int totalWidth = BOTTOM_CHILDREN_COUNT * NODE_WIDTH + (BOTTOM_CHILDREN_COUNT - 1) * NODE_HORIZONTAL_MARGIN;
        if (layer != TOTAL_LAYER - 1) {
            canvas.drawLine(
                    node.getLeft() + node.getWidth() / 2 - totalWidth / getChildrenCount(layer) / 2 * (getChildNumber(layer) - 1) - LINE_WIDTH,
                    node.getTop() + NODE_HEIGHT + GlobalConstant.NODE_VERTICAL_MARGIN / 2,
                    node.getLeft() + node.getWidth() / 2 + totalWidth / getChildrenCount(layer) / 2 * (getChildNumber(layer) - 1) + LINE_WIDTH,
                    node.getTop() + NODE_HEIGHT + GlobalConstant.NODE_VERTICAL_MARGIN / 2,
                    paint);
        }

    }

}
