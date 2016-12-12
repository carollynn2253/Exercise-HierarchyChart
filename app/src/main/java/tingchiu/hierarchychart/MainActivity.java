package tingchiu.hierarchychart;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tingchiu.hierarchychart.widgit.NodeItem;

import static tingchiu.hierarchychart.GlobalConstant.LEVEL_0_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.LEVEL_1_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.LEVEL_2_CHILD_NUMBER;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAIN";

    @Bind(R.id.vertical_scroll_view)
    ScrollView verticalScrollView;
    @Bind(R.id.horizontal_scroll_view)
    HorizontalScrollView horizontalScrollView;
    @Bind(R.id.tree_layout)
    FrameLayout treeLayout;

    private boolean hasTopParent;
    private TreeInfo treeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setData();
        initView();
    }

    private void setData() {
        hasTopParent = false;

        List<NodeInfo> treeNodeInfoList = new ArrayList<>();
        treeNodeInfoList.add(new NodeInfo("NRoot", 0, 0, "Node1", "http://www.jrtstudio.com/sites/default/files/ico_android.png"));
        treeNodeInfoList.add(new NodeInfo("Node1", 1, 1, "Node2", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node1", 2, 1, "Node3", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node2", 1, 2, "Node4", "http://www.jrtstudio.com/sites/default/files/ico_android.png"));
        treeNodeInfoList.add(new NodeInfo("Node2", 2, 2, "Node5", "http://www.jrtstudio.com/sites/default/files/ico_android.png"));
        treeNodeInfoList.add(new NodeInfo("Node3", 1, 2, "Node6", "http://www.jrtstudio.com/sites/default/files/ico_android.png"));
        treeNodeInfoList.add(new NodeInfo("Node3", 2, 2, "Node7", "http://www.jrtstudio.com/sites/default/files/ico_android.png"));
        treeNodeInfoList.add(new NodeInfo("Node4", 1, 3, "Node8", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node4", 2, 3, "Node9", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node5", 1, 3, "Node10", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node5", 2, 3, "Node11", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node6", 1, 3, "Node12", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node6", 2, 3, "Node13", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node7", 1, 3, "Node14", "http://www.swashtech.com/images/mobile.jpg"));
        treeNodeInfoList.add(new NodeInfo("Node7", 2, 3, "Node15", "http://www.swashtech.com/images/mobile.jpg"));
        treeInfo = new TreeInfo(treeNodeInfoList);
    }

    private void initView() {
        treeLayout.removeAllViews();

        //level 3
        for (int i = 0; i < LEVEL_0_CHILD_NUMBER * LEVEL_1_CHILD_NUMBER * LEVEL_2_CHILD_NUMBER; i++) {
            NodeItem nodeItem = new NodeItem(this, treeLayout, hasTopParent, 3, i, treeInfo.getInfoListLevel3().get(i));
            nodeItem.setOnItemClickListener(new OnItemClickListener());
        }

        //level 2
        for (int i = 0; i < LEVEL_0_CHILD_NUMBER * LEVEL_1_CHILD_NUMBER; i++) {
            NodeItem nodeItem = new NodeItem(this, treeLayout, hasTopParent, 2, i, treeInfo.getInfoListLevel2().get(i));
            nodeItem.setOnItemClickListener(new OnItemClickListener());
        }

        //level 1
        for (int i = 0; i < LEVEL_0_CHILD_NUMBER; i++) {
            NodeItem nodeItem = new NodeItem(this, treeLayout, hasTopParent, 1, i, treeInfo.getInfoListLevel1().get(i));
            nodeItem.setOnItemClickListener(new OnItemClickListener());
        }

        //level 0
        NodeItem nodeItem = new NodeItem(this, treeLayout, hasTopParent, 0, 0, treeInfo.getInfoListLevel0());
        nodeItem.setOnItemClickListener(new OnItemClickListener());

        //scroll to top of view and center configuration chart
        verticalScrollView.scrollTo(0, 0);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                horizontalScrollView.scrollTo(horizontalScrollView.getChildAt(0).getWidth() / 2 - getResources().getDisplayMetrics().widthPixels / 2, 0);
            }
        });
    }

    class OnItemClickListener implements NodeItem.OnItemClickListener {
        @Override
        public void onClick(NodeInfo nodeData) {
            Log.d(TAG, hasTopParent + ", " + Integer.parseInt(Long.toString(nodeData.getLevel())));
            Log.d(TAG, "nodeData:" + new Gson().toJson(nodeData));

            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Level " + nodeData.getLevel())
                    .setMessage(nodeData.getNodeId())
                    .setPositiveButton(R.string.g_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create();
            dialog.show();
        }
    }
}
