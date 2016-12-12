package tingchiu.hierarchychart.widgit;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import tingchiu.hierarchychart.MyApplication;
import tingchiu.hierarchychart.NodeInfo;
import tingchiu.hierarchychart.R;

import static tingchiu.hierarchychart.GlobalConstant.BOTTOM_LEVEL_TOTAL_CHILDREN;
import static tingchiu.hierarchychart.GlobalConstant.LEVEL_0_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.LEVEL_1_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HEIGHT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HORIZONTAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_VERTICAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_WIDTH;

/**
 * Created by ting.chiu on 2016/10/5.
 */


public class NodeItem {

    private View nodeView;

    @Bind(R.id.node_layout)
    RelativeLayout nodeLayout;
    @Bind(R.id.icon)
    ImageView icon;
    @Bind(R.id.node_id)
    TextView nodeId;

    private OnItemClickListener onItemClickListener;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public NodeItem(Context context, FrameLayout parentLayout, boolean showTopParent, int level, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nodeView = inflater.inflate(R.layout.view_node, null);
        ButterKnife.bind(this, nodeView);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(NODE_WIDTH, NODE_HEIGHT);
        layoutParams.topMargin = (NODE_HEIGHT + NODE_VERTICAL_MARGIN) * level;
        if (level == 0) {
            layoutParams.leftMargin =
                    (NODE_HORIZONTAL_MARGIN + NODE_WIDTH) * BOTTOM_LEVEL_TOTAL_CHILDREN / 2 - NODE_WIDTH / 2;
        } else if (level == 1) {
            layoutParams.leftMargin =
                    (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_LEVEL_TOTAL_CHILDREN / LEVEL_0_CHILD_NUMBER / 2 - NODE_WIDTH / 2
                            + (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_LEVEL_TOTAL_CHILDREN / LEVEL_0_CHILD_NUMBER * position;
        } else if (level == 2) {
            layoutParams.leftMargin =
                    (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_LEVEL_TOTAL_CHILDREN / (LEVEL_0_CHILD_NUMBER * LEVEL_1_CHILD_NUMBER) / 2 - NODE_WIDTH / 2
                            + (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_LEVEL_TOTAL_CHILDREN / (LEVEL_0_CHILD_NUMBER * LEVEL_1_CHILD_NUMBER) * position;
        } else {
            layoutParams.leftMargin = (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * position + NODE_HORIZONTAL_MARGIN / 2;
        }
        parentLayout.addView(nodeView, layoutParams);

        nodeLayout.setGravity(Gravity.CENTER);

        //draw link line
        NodeLinkLine nodeLinkLine = new NodeLinkLine(context, nodeView, showTopParent, level);
        parentLayout.addView(nodeLinkLine);
    }

    public NodeItem(Context context, FrameLayout parentLayout, boolean hasTopParent, final int level, final int position, NodeInfo nodeData) {
        this(context, parentLayout, hasTopParent, level, position);  //set position and draw link lines

        nodeId.setText(nodeData.getNodeId());
        imageLoader.displayImage(nodeData.getIconUrl(), icon, MyApplication.ImageLoaderCacheOptions);

        //set click listener
        nodeLayout.setOnClickListener(new OnItemClick(nodeData));
    }

    //when click nodeLayout
    public interface OnItemClickListener {
        void onClick(NodeInfo nodeData);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private class OnItemClick implements View.OnClickListener {
        NodeInfo nodeData;

        public OnItemClick(NodeInfo nodeData) {
            this.nodeData = nodeData;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(nodeData);
            }
        }
    }

}
