package lab.mars.network;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import lab.mars.m2m.protocol.http.HeartBeat;
import lab.mars.m2m.protocol.http.M2MHttpBindings;
import lab.mars.m2m.protocol.http.MissingContentBodyException;
import lab.mars.m2m.protocol.http.MissingParameterException;
import lab.mars.m2m.protocol.primitive.m2m_rsp;
import lab.mars.m2m.protocol.resource.m2m_resource;
import lab.mars.util.network.HttpClient;
import lab.mars.util.network.NetworkEvent;
import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * Author:yaoalong.
 * Date:2016/6/13.
 * Email:yaoalong@foxmail.com
 */
public class Network {

    private static HttpClient client = new HttpClient();

    protected static m2m_resource testRetrieve(String path, String contentFilePath, HttpResponseStatus statusCode) throws Exception {
        m2m_rsp m_rsp = testRequest(HttpMethod.GET, path, statusCode, contentFilePath);
        if (m_rsp.pc != null && m_rsp.pc.value instanceof m2m_resource)
            return ((m2m_resource) m_rsp.pc.value);
        return null;
    }

    public static m2m_rsp testRequest(HttpMethod method, String path, HttpResponseStatus statusCode, String contentPath) throws Exception {

        String[][] req_headers = new String[][]{
                {"Host", "/cse01"},
                {"Accept", "application/onem2m-resource+xml"},
                {"Content-type", "application/onem2m-resource+xml"},
                {"From", "/AE01"},
                {"X-M2M-RI", "00001"},
        };
        String requestBody = contentPath != null ? IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource(contentPath)) : null;
        String[][] rsp_headers = new String[][]{
//				{"X-M2M-RI", "00001"},
        };

        return testRequest(method, path, req_headers, requestBody, statusCode, rsp_headers);
    }

    protected static m2m_rsp testRequest(
            HttpMethod method,
            String path,
            String[][] req_headers,
            String requestBody,
            HttpResponseStatus status,
            String[][] rsp_headers) throws InterruptedException, IOException, URISyntaxException {

        CountDownLatch latchNami = new CountDownLatch(1);
        URI uri = new URI("http://localhost:8081");
        HttpRequest httpRequest = HttpClient.makeRequest(method, path, req_headers, requestBody);
        m2m_rsp m_rsp[] = new m2m_rsp[1];
        client.requestAsync(uri, httpRequest)
                .<NetworkEvent<FullHttpResponse>>then(resp -> {

                    try {
                        //      System.out.println(resp.msg.content().toString(Charset.forName("utf-8")));
                        m_rsp[0] = M2MHttpBindings.decodeResponse(resp.msg);
                    } catch (JAXBException | MissingParameterException | MissingContentBodyException e) {
                        e.printStackTrace();
                    }
                    latchNami.countDown();
                });

        latchNami.await();
        return m_rsp[0];
    }

    protected static HeartBeat test2Request(
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

    public static HeartBeat test2Request(HttpMethod method, String path, HttpResponseStatus statusCode, String contentPath) throws Exception {

        String[][] req_headers = new String[][]{
                {"HeartBeat", "true"},
        };
        String requestBody = contentPath != null ? IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource(contentPath)) : null;
        String[][] rsp_headers = new String[][]{
//				{"X-M2M-RI", "00001"},
        };

        return test2Request(method, path, req_headers, requestBody, statusCode, rsp_headers);
    }

}
