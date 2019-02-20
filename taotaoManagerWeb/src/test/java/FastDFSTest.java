import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 孙建荣 on 17-6-10.下午6:01
 */
public class FastDFSTest {



    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void testFastFds() throws Exception {

        //       1、加载配置文件，配置文件中的内容就是tracker服务的地址。
        ClientGlobal.init("/home/sunjianrong/IdeaProjects/shoppingMall/taotaoManagerWeb/src/main/resources/resource/client.conf");
        //       配置文件内容：tracker_server=192.168.25.133:22122
        //       2、创建一个TrackerClient对象。直接new一个。
        TrackerClient trackerClient = new TrackerClient();
        //       3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        //       4、创建一个StorageServer的引用，值为null
        StorageServer storageServer = null;
        //       5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //       6、使用StorageClient对象上传图片。
        String[] result = storageClient.upload_file("/home/sunjianrong/IdeaProjects/shoppingMall/taotaoManagerWeb/src/main/webapp/WEB-INF/jsp/item-param-list.jsp", "jsp", null);
        //       7、返回数组。包含组名和图片的路径。
        for (String s : result) {
            System.out.println(s);
        }
    }



}











