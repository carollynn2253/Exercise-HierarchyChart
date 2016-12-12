package tingchiu.hierarchychart;


public class NodeInfo {

    int level;
    String parentNodeId = "";
    int childSeq;
    String iconUrl = "";
    String nodeId = "";

    public NodeInfo() {
    }

    public NodeInfo(String parentNodeId, int childSeq, int level, String nodeId, String icon) {
        this.parentNodeId = parentNodeId;
        this.childSeq = childSeq;
        this.level = level;
        this.iconUrl = icon;
        this.nodeId = nodeId;
    }

    public int getLevel() {
        return level;
    }

    public String getParentNodeId() {
        return parentNodeId != null ? parentNodeId : "";
    }

    public int getChildSeq() {
        return childSeq;
    }

    public String getIconUrl() {
        return iconUrl != null ? iconUrl : "http://www.jrtstudio.com/sites/default/files/ico_android.png";
    }

    public String getNodeId() {
        return nodeId != null ? nodeId : "";
    }
}
