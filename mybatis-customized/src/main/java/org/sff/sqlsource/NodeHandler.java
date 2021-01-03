package org.sff.sqlsource;

import org.dom4j.Element;
import org.sff.sqlnode.SqlNode;

import java.util.List;

/**
 * 处理不同的 SqlNode
 */
public interface NodeHandler {

    /**
     * 处理非文本节点
     *
     * @param nodeHandlerElement 待处理子节点
     * @param sqlNodes           处理之后的节点集合
     */
    void handleNode(Element nodeHandlerElement, List<SqlNode> sqlNodes);
}
