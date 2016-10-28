package tingchiu.hierarchychart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tingchiu.hierarchychart.widgit.Node;
import tingchiu.hierarchychart.widgit.ZoomableLayout;

import static tingchiu.hierarchychart.GlobalConstant.NODE_HEIGHT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_WIDTH;
import static tingchiu.hierarchychart.GlobalConstant.SECOND_LAYER_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.THIRD_LAYER_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.TOP_LAYER_CHILD_NUMBER;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.scroll_layout)
    HorizontalScrollView scrollLayout;
    @Bind(R.id.zoomable_layout)
    ZoomableLayout zoomableLayout;

    @Nullable
    @Bind(R.id.layout)
    FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set node size by screen size
//        NODE_WIDTH = getResources().getDisplayMetrics().widthPixels / 2;
//        NODE_HEIGHT = getResources().getDisplayMetrics().widthPixels/ 2;
        NODE_WIDTH = 200;
        NODE_HEIGHT = 200;

        //TODO:set height and width of zoomableLayout
//        c.setLayoutParams(
//                new FrameLayout.LayoutParams(
//                        (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_CHILDREN_COUNT + NODE_HORIZONTAL_MARGIN / 2,
//                        (NODE_HEIGHT + NODE_VERTICAL_MARGIN) * TOTAL_LAYER));
//        layout.setLayoutParams(
//                new FrameLayout.LayoutParams(
//                        (NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_CHILDREN_COUNT + NODE_HORIZONTAL_MARGIN / 2,
//                        (NODE_HEIGHT + NODE_VERTICAL_MARGIN) * TOTAL_LAYER));

//        zoomableLayout.setLayoutParams(new FrameLayout.LayoutParams(
//                getResources().getDisplayMetrics().widthPixels * 3, ViewGroup.LayoutParams.MATCH_PARENT));
//        layout.setLayoutParams(
//                new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels * 3, ViewGroup.LayoutParams.MATCH_PARENT));


        //layer 4
        for (int i = 0; i < TOP_LAYER_CHILD_NUMBER * SECOND_LAYER_CHILD_NUMBER * THIRD_LAYER_CHILD_NUMBER; i++) {
            new Node(this, layout, 3, i);
        }

        //layer 3
        for (int i = 0; i < TOP_LAYER_CHILD_NUMBER * SECOND_LAYER_CHILD_NUMBER; i++) {
            new Node(this, layout, 2, i);
        }

        //layer 2
        for (int i = 0; i < TOP_LAYER_CHILD_NUMBER; i++) {
            new Node(this, layout, 1, i);
        }

        //layer 1
        new Node(this, layout, 0, 0);


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        //init scroll to center
//        double centerX = getResources().getDisplayMetrics().widthPixels * 1.5;
//        scrollLayout.scrollTo((int) centerX, 0);
//    }

}
