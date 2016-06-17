package lab.mars.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.*;
import lab.mars.m2m.protocol.common.m2m_AnyURIList;
import lab.mars.m2m.protocol.common.m2m_childResourceRef;
import lab.mars.m2m.protocol.common.m2m_eventNotificationCriteria;
import lab.mars.m2m.protocol.enumeration.m2m_resourceStatus;
import lab.mars.m2m.protocol.http.HeartBeat;
import lab.mars.m2m.protocol.http.M2MHttpBindings;
import lab.mars.m2m.protocol.http.MissingContentBodyException;
import lab.mars.m2m.protocol.http.MissingParameterException;
import lab.mars.m2m.protocol.primitive.m2m_primitiveContentType;
import lab.mars.m2m.protocol.primitive.m2m_req;
import lab.mars.m2m.protocol.primitive.m2m_rsp;
import lab.mars.m2m.protocol.resource.m2m_AE;
import lab.mars.m2m.protocol.resource.m2m_Container;
import lab.mars.m2m.protocol.resource.m2m_Subscription;
import lab.mars.m2m.protocol.resource.m2m_resource;
import lab.mars.util.async.AsyncStream;
import lab.mars.util.network.HttpClient;
import lab.mars.util.network.HttpServer;
import lab.mars.util.network.NetworkEvent;
import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static lab.mars.m2m.protocol.enumeration.m2m_resourceType.*;
import static lab.mars.mapper.MachineMapper.CSEBASE;

/**
 * Author:yaoalong.
 * Date:2016/6/13.
 * Email:yaoalong@foxmail.com
 */
public class WebNetwork {
    public static final int ASYNC = 1;
    public static final int SYNC = 0;
    protected final String myIp = "192.168.10.131";
    private final String serverIp = "192.168.10.131";
    public ThreadLocal<Marshaller> marshaller;
    private JAXBContext jc = null;
    private ThreadLocal<Unmarshaller> unmarshaller;
    private HttpClient client;
    private HttpServer server;

    public void init() {
        client = new HttpClient();
        server = new HttpServer();
        server.bindAsync("localhost", 9010)
                .then(future -> {
                    System.out.println("server has started@9010");
                })
                .<NetworkEvent<FullHttpRequest>>loop(m -> {
                    //System.out.println("接收到");
                    ByteBuf data = m.msg.content();
                    try {
                        m2m_primitiveContentType pc = (m2m_primitiveContentType) unmarshaller.get().unmarshal(new ByteBufInputStream(data));
                        m2m_childResourceRef ref = (m2m_childResourceRef) pc.value;
                        handleNotify(ref);
                        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                        m.ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

                    } catch (JAXBException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, OK);
                    m.ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                    return true;
                });
        marshaller = new ThreadLocal<Marshaller>() {
            @Override
            public Marshaller initialValue() {
                try {
                    Marshaller marshaller = jc.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    return marshaller;
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        unmarshaller = new ThreadLocal<Unmarshaller>() {
            @Override
            public Unmarshaller initialValue() {
                try {
                    return jc.createUnmarshaller();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        try {
            jc = JAXBContext.newInstance(m2m_primitiveContentType.class, m2m_req.class, m2m_rsp.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public m2m_rsp request(HttpMethod method, String path, HttpResponseStatus statusCode, String content, int flag) throws Exception {

        String[][] req_headers = new String[][]{
                {"Host", "/cse01"},
                {"Accept", "application/onem2m-resource+xml"},
                {"Content-type", "application/onem2m-resource+xml"},
                {"From", "/AE01"},
                {"X-M2M-RI", "00001"},
        };
        String requestBody = content;
        String[][] rsp_headers = new String[][]{
//				{"X-M2M-RI", "00001"},
        };
        if (flag == SYNC) {
            return syncTestRequest(method, path, req_headers, requestBody, statusCode, rsp_headers);
        }
        testRequest(method, path, req_headers, requestBody, statusCode, rsp_headers);
        return null;
    }

    public String testCreate(String parent_path, int ty, String contentFilePath, HttpResponseStatus statusCode, int flag) throws Exception {
        return testCreate(parent_path, ty, null, contentFilePath, statusCode, flag);
    }


    protected String testCreate(String parent_path, int ty, String name, String content, HttpResponseStatus statusCode, int flag) throws Exception {
        m2m_rsp m_rsp = request(HttpMethod.POST, parent_path + "?ty=" + ty + (name == null ? "" : "&rn=" + name), statusCode, content, flag);
        if (flag == ASYNC) {
            return null;
        }
        if (m_rsp.pc != null && m_rsp.pc.value instanceof m2m_childResourceRef)
            return ((m2m_childResourceRef) m_rsp.pc.value).v;
        return null;
    }


    public m2m_resource testRetrieve(String path, String contentFilePath, HttpResponseStatus statusCode, int flag) throws Exception {

        if (flag == SYNC) {
            m2m_rsp m_rsp = request(HttpMethod.GET, path, statusCode, contentFilePath, 0);
            if (m_rsp.pc != null && m_rsp.pc.value instanceof m2m_resource)
                return ((m2m_resource) m_rsp.pc.value);
        }

        request(HttpMethod.GET, path, statusCode, contentFilePath, ASYNC);
        return null;
    }


    protected AsyncStream testRequest(
            HttpMethod method,
            String path,
            String[][] req_headers,
            String requestBody,
            HttpResponseStatus status,
            String[][] rsp_headers) throws InterruptedException, IOException, URISyntaxException {

        URI uri = new URI("http://" + serverIp + ":8081");
        HttpRequest httpRequest = HttpClient.makeRequest(method, path, req_headers, requestBody);
        return client.requestAsync(uri, httpRequest)
                .<NetworkEvent<FullHttpResponse>>then(resp -> {
                }).end();
    }


    public String createSyncAEResource() throws Exception {
        m2m_primitiveContentType m2m_primitiveContentType = new m2m_primitiveContentType();
        StringWriter sw = new StringWriter();
        m2m_AE rsp = new m2m_AE();
        m2m_primitiveContentType.value = rsp;
        marshaller.get().marshal(m2m_primitiveContentType, sw);
        String value = sw.toString();
        return testCreate(CSEBASE, AE, value, OK, SYNC);//创建一个AE
    }


    /**
     * 异步创建Container
     *
     * @throws Exception
     */
    public String createSyncContainer(String aeURI) throws Exception {
        StringWriter sw = new StringWriter();
        m2m_primitiveContentType m2m_primitiveContentType = new m2m_primitiveContentType();
        m2m_Container m2m_container = new m2m_Container();
        m2m_primitiveContentType.value = m2m_container;
        marshaller.get().marshal(m2m_primitiveContentType, sw);
        return testCreate(aeURI, container, sw.toString(), OK, SYNC);//创建一个container
    }


    public void createAsyncSubScriptions(String cntURI) throws Exception {
        StringWriter sw = new StringWriter();
        m2m_primitiveContentType m2m_primitiveContentType = new m2m_primitiveContentType();
        m2m_Subscription m2m_subscription = new m2m_Subscription();
        m2m_eventNotificationCriteria m2m_eventNotificationCriteria = new m2m_eventNotificationCriteria();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(m2m_resourceStatus.childCreated);
        m2m_eventNotificationCriteria.rss = integerList;
        m2m_subscription.enc = m2m_eventNotificationCriteria;
        m2m_AnyURIList m2m_anyURIList = new m2m_AnyURIList();
        List<String> reference = new ArrayList<>();
        reference.add("http://" + "localhost" + ":9010/");
        m2m_anyURIList.reference = reference;
        m2m_subscription.nu = m2m_anyURIList;
        m2m_primitiveContentType.value = m2m_subscription;
        marshaller.get().marshal(m2m_primitiveContentType, sw);
        testCreate(cntURI, subscription, sw.toString(), OK, ASYNC);
    }


    protected m2m_rsp syncTestRequest(
            HttpMethod method,
            String path,
            String[][] req_headers,
            String requestBody,
            HttpResponseStatus status,
            String[][] rsp_headers) throws InterruptedException, IOException, URISyntaxException {

        CountDownLatch latchNami = new CountDownLatch(1);
        URI uri = new URI("http://" + serverIp + ":8081");
        HttpRequest httpRequest = HttpClient.makeRequest(method, path, req_headers, requestBody);
        m2m_rsp m_rsp[] = new m2m_rsp[1];
        client.requestAsync(uri, httpRequest)
                .<NetworkEvent<FullHttpResponse>>then(resp -> {
                    try {
                        m_rsp[0] = M2MHttpBindings.decodeResponse(resp.msg);

                    } catch (JAXBException | MissingParameterException | MissingContentBodyException e) {
                        e.printStackTrace();
                    }
                    latchNami.countDown();
                });

        latchNami.await();
        return m_rsp[0];
    }


    protected HeartBeat test2Request(
            HttpMethod method,
            String path,
            String[][] req_headers,
            String requestBody,
            HttpResponseStatus status,
            String[][] rsp_headers) throws InterruptedException, IOException, URISyntaxException {

        CountDownLatch latchNami = new CountDownLatch(1);
        URI uri = new URI("http://localhost:8081");
        HttpRequest httpRequest = HttpClient.makeRequest(method, path, req_headers, requestBody);
        HeartBeat m_rsp[] = new HeartBeat[1];
        client.requestAsync(uri, httpRequest)
                .<NetworkEvent<FullHttpResponse>>then(resp -> {

                    try {
                        m_rsp[0] = M2MHttpBindings.decodeHeartBeat(resp.msg);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    latchNami.countDown();
                });

        latchNami.await();
        return m_rsp[0];
    }

    /**
     * 发送心跳检测包
     */
    public HeartBeat test2Request(HttpMethod method, String path, HttpResponseStatus statusCode, String contentPath) throws Exception {

        String[][] req_headers = new String[][]{
                {"HeartBeat", "true"},
        };
        String requestBody = contentPath != null ? IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource(contentPath)) : null;
        String[][] rsp_headers = new String[][]{
        };

        return test2Request(method, path, req_headers, requestBody, statusCode, rsp_headers);
    }


    public void handleNotify(m2m_childResourceRef resourceRef) {
        //MachineMapper.update(resourceRef.v);
    }
}
