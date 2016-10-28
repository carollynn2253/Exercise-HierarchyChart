package tingchiu.hierarchychart;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import tingchiu.hierarchychart.widgit.Node;
import tingchiu.hierarchychart.widgit.ZoomableLayout;

import static tingchiu.hierarchychart.GlobalConstant.BOTTOM_CHILDREN_COUNT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HEIGHT;
import static tingchiu.hierarchychart.GlobalConstant.NODE_HORIZONTAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_VERTICAL_MARGIN;
import static tingchiu.hierarchychart.GlobalConstant.NODE_WIDTH;
import static tingchiu.hierarchychart.GlobalConstant.SECOND_LAYER_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.THIRD_LAYER_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.TOP_LAYER_CHILD_NUMBER;
import static tingchiu.hierarchychart.GlobalConstant.TOTAL_LAYER;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.zoomable_layout)
    ZoomableLayout zoomableLayout;
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

        zoomableLayout.setLayoutParams(new FrameLayout.LayoutParams(
                getResources().getDisplayMetrics().widthPixels * 3, ViewGroup.LayoutParams.MATCH_PARENT));
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

    //右上角按鈕
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save:
//                saveToPng();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

//    public void saveToPng() {
//
//        layout.setDrawingCacheEnabled(true);
//        Bitmap bitmap = Bitmap.createBitmap((NODE_WIDTH + NODE_HORIZONTAL_MARGIN) * BOTTOM_CHILDREN_COUNT + NODE_HORIZONTAL_MARGIN / 2, layout.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        canvas.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
//        canvas.measure(
//                RelativeLayout.MeasureSpec.makeMeasureSpec(canvas.getLayoutParams().width,
//                        RelativeLayout.MeasureSpec.EXACTLY),
//                RelativeLayout.MeasureSpec.makeMeasureSpec(canvas.getLayoutParams().height,
//                        RelativeLayout.MeasureSpec.EXACTLY));
//        canvas.layout(0, 0, drawingCanvas.getMeasuredWidth(),
//                canvas.getMeasuredHeight());
//        layout.draw(canvas);
//
//        try {
//            FileOutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory() + "/DCIM/aaaaaa_image.png");
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
//            output.close();
//            Log.d("IMG", "created.");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


}
