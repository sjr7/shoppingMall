import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Created by 孙建荣 on 17-6-15.下午8:26
 */
public class TestSolrJ {

    //    @Test
    public void testAddDocument() throws IOException, SolrServerException {
      /*  // 创建SolrServer对象连接，创建一个HttpSolrServer
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
        solrServer.commit();*/
    }

    //    @Test
    public void testDelDocument() throws IOException, SolrServerException {
       /* SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:9000/solr/collection1");
        solrServer.deleteById("test001");
        solrServer.commit();*/
    }

    //    @Test
    public void testDelByQuery() throws IOException, SolrServerException {
       /* SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:9000/solr/collection1");
        solrServer.deleteByQuery("");
        solrServer.commit();*/
    }

    //    @Test
    public void searchDocument() throws SolrServerException {
        /*// 创建SOlrServer对象
        SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:9000/solr/collection1");
        // 创建SolrQuery对象
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询条件
//        solrQuery.set("q", "*:*");
        solrQuery.setQuery("手机");
        // 分页条件
        solrQuery.setStart(30);
        solrQuery.setRows(20);
        // 设置搜索域
        solrQuery.set("df", "item_keywords");
        // 执行查询，得到Response对象

        // 得到总记录数
        solrQuery.setHighlight(true);
        // 高粱显示
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<div>");
        solrQuery.setHighlightSimplePost("</div>");
        // 得到查询结果
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        for (SolrDocument solrDocument : results) {
            System.out.println(solrDocument.get("id"));
            // 取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTIle;
            if (list != null && list.size() > 0) {
                itemTIle = list.get(0);
            } else {
                itemTIle = (String) solrDocument.get("item_title");
            }
            System.out.println(itemTIle);
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));
        }*/
    }

}
