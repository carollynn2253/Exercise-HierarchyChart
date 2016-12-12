package tingchiu.hierarchychart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ting.chiu on 2016/12/12.
 */

public class TreeInfo {
    List<NodeInfo> nodeInfoList;

    NodeInfo infoListLayer0 = new NodeInfo();
    NodeInfo[] infoListLayer1 = new NodeInfo[2];
    NodeInfo[] infoListLayer2 = new NodeInfo[4];
    NodeInfo[] infoListLayer3 = new NodeInfo[8];

    public TreeInfo(List<NodeInfo> nodeInfoList) {
        this.nodeInfoList = nodeInfoList != null ? nodeInfoList : new ArrayList<NodeInfo>();
        setInfoListLayer0();
        setInfoListLayer1();
        setInfoListLayer2();
        setInfoListLayer3();
    }

    public int getTreeNodeListSize() {
        return nodeInfoList.size();
    }

    private void setInfoListLayer0() {
        if (nodeInfoList != null) {
            for (int i = 0; i < nodeInfoList.size(); i++) {
                if (nodeInfoList.get(i).getLevel() == 0) {
                    infoListLayer0 = nodeInfoList.get(i);
                }
            }
        }
    }

    private void setInfoListLayer1() {
        if (nodeInfoList != null) {
            for (int i = 0; i < nodeInfoList.size(); i++) {
                if (nodeInfoList.get(i).getLevel() == 1) {
                    int position = nodeInfoList.get(i).getChildSeq() - 1;
                    infoListLayer1[position] = nodeInfoList.get(i);
                }
            }
        }
    }

    private void setInfoListLayer2() {
        if (nodeInfoList != null) {
            for (int i = 0; i < nodeInfoList.size(); i++) {
                if (nodeInfoList.get(i).getLevel() == 2) {
                    int parentPosition = getPositionOfListLayer(nodeInfoList.get(i).getParentNodeId(), infoListLayer1);
                    if (parentPosition != -1) {
                        int position = parentPosition * 2 + nodeInfoList.get(i).getChildSeq() - 1;
                        infoListLayer2[position] = nodeInfoList.get(i);
                    }
                }
            }
        }
    }

    private void setInfoListLayer3() {
        if (nodeInfoList != null) {
            for (int i = 0; i < nodeInfoList.size(); i++) {
                if (nodeInfoList.get(i).getLevel() == 3) {
                    int parentPosition = getPositionOfListLayer(nodeInfoList.get(i).getParentNodeId(), infoListLayer2);
                    if (parentPosition != -1) {
                        int position = parentPosition * 2 + nodeInfoList.get(i).getChildSeq() - 1;
                        infoListLayer3[position] =nodeInfoList.get(i);
                    }
                }
            }
        }
    }

    private int getPositionOfListLayer(String data, NodeInfo[] dataList) {
        int position = -1;

        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i] != null) {
                if (dataList[i].getNodeId().equals(data)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }

    public NodeInfo getInfoListLevel0() {
        return infoListLayer0 != null ? infoListLayer0 : new NodeInfo();
    }

    public List<NodeInfo> getInfoListLevel1() {
        List<NodeInfo> dataList = new ArrayList<>();
        for (int i = 0; i < infoListLayer1.length; i++) {
            dataList.add(i, infoListLayer1[i] != null ? infoListLayer1[i] : new NodeInfo());
        }
        return dataList;
    }

    public List<NodeInfo> getInfoListLevel2() {
        List<NodeInfo> dataList = new ArrayList<>();
        for (int i = 0; i < infoListLayer2.length; i++) {
            dataList.add(i, infoListLayer2[i] != null ? infoListLayer2[i] : new NodeInfo());
        }
        return dataList;
    }

    public List<NodeInfo> getInfoListLevel3() {
        List<NodeInfo> dataList = new ArrayList<>();
        for (int i = 0; i < infoListLayer3.length; i++) {
            dataList.add(i, infoListLayer3[i] != null ? infoListLayer3[i] : new NodeInfo());
        }
        return dataList;
    }
}
