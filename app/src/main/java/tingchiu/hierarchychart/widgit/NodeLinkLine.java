package tingchiu.hierarchychart.widgit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import static tingchiu.hierarchychart.GlobalConstant.BOTTOM_LEVEL_TOTAL_CHILDREN;
import static tingchiu.hierarchychart.GlobalConstant.LINE_COLOR;
import static tingchiu.hierarchychart.GlobalConstant.LINE_WIDTH;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HEIGHT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HORIZONTAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_VERTICAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_WIDTH;
import static tingchiu.hierarchychart.GlobalConstant.TOTAL_LEVEL;
import static tingchiu.hierarchychart.GlobalConstant.getLevelChildNumber;
import static tingchiu.hierarchychart.GlobalConstant.getLevelChildrenTotal;

/**
 * Created by ting.chiu on 2016/10/5.
 */

//TODO: not finish, only top and bottom
public class NodeLinkLine extends View {

    View nodeView;
    boolean showTopParent;
    int level;

    Paint paint;
    Paint dashPaint;
    Canvas canvas;

    public NodeLinkLine(Context context, View nodeView, boolean showTopParent, int level) {
        super(context);

        this.nodeView = nodeView;
        this.showTopParent = showTopParent;
        this.level = level;

        paint = new Paint();
        dashPaint = new Paint();
        canvas = new Canvas();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor(LINE_COLOR));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setAntiAlias(true);


        int layoutLevel = level;
        int totalLevel = TOTAL_LEVEL;

        int totalWidth = BOTTOM_LEVEL_TOTAL_CHILDREN * NODE_WIDTH + (BOTTOM_LEVEL_TOTAL_CHILDREN - 1) * NODE_HORIZONTAL_MARGIN;

        //top vertical line
        if (layoutLevel != 0) {
            canvas.drawLine(nodeView.getLeft() + nodeView.getWidth() / 2, nodeView.getTop() - NODE_VERTICAL_MARGIN / 2,
                    nodeView.getLeft() + nodeView.getWidth() / 2, nodeView.getTop(),
                    paint);
        }

        //bottom vertical line
        if (layoutLevel != totalLevel - 1) {
            canvas.drawLine(nodeView.getLeft() + nodeView.getWidth() / 2, nodeView.getTop() + NODE_HEIGHT,
                    nodeView.getLeft() + nodeView.getWidth() / 2, nodeView.getTop() + NODE_HEIGHT + NODE_VERTICAL_MARGIN / 2,
                    paint);
        }

        //bottom horizontal line
        if (layoutLevel == 0) {
            canvas.drawLine(
                    nodeView.getLeft() + nodeView.getWidth() / 2 - totalWidth / getLevelChildrenTotal(level) / 2 * (getLevelChildNumber(level - 1) - 1) - LINE_WIDTH + NODE_HORIZONTAL_MARGIN / 2,
                    nodeView.getTop() + NODE_HEIGHT + NODE_VERTICAL_MARGIN / 2,
                    nodeView.getLeft() + nodeView.getWidth() / 2 + totalWidth / getLevelChildrenTotal(level) / 2 * (getLevelChildNumber(level - 1) - 1) + LINE_WIDTH - NODE_HORIZONTAL_MARGIN / 2,
                    nodeView.getTop() + NODE_HEIGHT + NODE_VERTICAL_MARGIN / 2,
                    paint);
        } else if (layoutLevel != totalLevel - 1) {
            canvas.drawLine(
                    nodeView.getLeft() + nodeView.getWidth() / 2 - totalWidth / getLevelChildrenTotal(level) / 2 * (getLevelChildNumber(level - 1) - 1) - LINE_WIDTH,
                    nodeView.getTop() + NODE_HEIGHT + NODE_VERTICAL_MARGIN / 2,
                    nodeView.getLeft() + nodeView.getWidth() / 2 + totalWidth / getLevelChildrenTotal(level) / 2 * (getLevelChildNumber(level - 1) - 1) + LINE_WIDTH,
                    nodeView.getTop() + NODE_HEIGHT + NODE_VERTICAL_MARGIN / 2,
                    paint);
        }

    }

}
