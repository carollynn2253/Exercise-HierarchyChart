package tingchiu.hierarchychart.widgit;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tingchiu.hierarchychart.GlobalConstant;
import tingchiu.hierarchychart.R;

import static tingchiu.hierarchychart.GlobalConstant.BOTTOM_CHILDREN_COUNT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HEIGHT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HORIZONTAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_VERTICAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_WIDTH;
import static tingchiu.hierarchychart.GlobalConstant.SECOND_LAYER_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.TOP_LAYER_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.TOTAL_LAYER;

/**
 * Created by ting.chiu on 2016/10/5.
 */


public class Node {

    View node;

    @Bind(R.id.node_layout)
    RelativeLayout nodeLayout;
    @Bind(R.id.name)
    TextView title;
    @Bind(R.id.dollar)
    TextView subTitle;

    public Node(Context context, FrameLayout parentLayout, final int layer, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        node = inflater.inflate(R.layout.node, null);
        ButterKnife.bind(this, node);

        //set topMargin, leftMargin
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(NODE_WIDTH, NODE_HEIGHT);
        layoutParams.topMargin = (NODE_HEIGHT + NODE_VERTICAL_MARGIN) * layer;
        if (layer == 0) {
            layoutParams.leftMargin =
                    (NODE_HORIZONTAL_MARGIN + NODE_WIDTH) * BOTTOM_CHILDREN_COUNT / 2 - NODE_WIDTH / 2;
        } else if (layer == 1) {
            layoutParams.leftMargin =
                    (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_CHILDREN_COUNT / TOP_LAYER_CHILD_NUMBER / 2 - NODE_WIDTH / 2
                            + (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_CHILDREN_COUNT / TOP_LAYER_CHILD_NUMBER * position;
        } else if (layer == 2) {
            layoutParams.leftMargin =
                    (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_CHILDREN_COUNT / (TOP_LAYER_CHILD_NUMBER * SECOND_LAYER_CHILD_NUMBER) / 2 - NODE_WIDTH / 2
                            + (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_CHILDREN_COUNT / (TOP_LAYER_CHILD_NUMBER * SECOND_LAYER_CHILD_NUMBER) * position;
        } else {
            layoutParams.leftMargin = (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * position + NODE_HORIZONTAL_MARGIN / 2;
        }
        parentLayout.addView(node, layoutParams);

        nodeLayout.setGravity(Gravity.CENTER);


        //set click listener
//        if (layer != TOTAL_LAYER - 1) {
//            node.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("tttttt", "layer:" + layer + ", position:" + position);
//                }
//            });
//        } else {
//            node.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("tttttt", "bottom layer at position:" + position);
//                }
//            });
//        }


        //draw link line
        NodeLinkLine nodeLinkLine1 = new NodeLinkLine(context, node, layer);
        parentLayout.addView(nodeLinkLine1);
    }

}
