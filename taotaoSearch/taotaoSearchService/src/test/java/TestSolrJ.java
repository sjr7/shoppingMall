import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by 孙建荣 on 17-6-15.下午8:26
 */
public class TestSolrJ {

    @Test
    public void test() throws IOException, SolrServerException {
        // 创建SolrServer对象连接，创建一个HttpSolrServer
        SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:9000/solr/collection1");
        // 需要指定solr服务的url
        // 创建一个文档对象SOlrInputDocument对象
        SolrInputDocument document = new SolrInputDocument();
        // 向文档中添加域，必须有id域，域的名称必须在schema.xml中定义
        document.addField("id", "test001");
        document.addField("item_title", "测试商品1");
        document.addField("item_price", 1000);
        // 把文档对象写入索引库
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }
}
