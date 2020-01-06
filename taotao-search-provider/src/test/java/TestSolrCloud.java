import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * Created by 孙建荣 on 17-6-16.下午5:43
 */
public class TestSolrCloud {

//    @Test
    public void testSolrCloundAdDocument() throws IOException, SolrServerException {
        // 创建一个CloudSolrServer对象，构建方法中需要指定zookeeper的地址列表
        CloudSolrServer solrServer = new CloudSolrServer("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
        // 需要配置默认的
        solrServer.setDefaultCollection("collection1");
        // 创建SolrDocument对象
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        // 向文档中添加域
        solrInputDocument.addField("item_title", "测试商品");
        solrInputDocument.addField("item_price", "100");
        solrInputDocument.addField("id", "test001");
        // 把文档对象写入索引库
        solrServer.add(solrInputDocument);
        // 提交
        solrServer.commit();
    }
}












