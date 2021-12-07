package com.innodiff.api.ftx4j;

import com.google.gson.Gson;
import com.innodiff.api.ftx4j.ws.FtxWebSocketListener;
import com.innodiff.api.ftx4j.ws.messages.WsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WebSocketTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketTest.class);

    private static String FTX_WSS_URL ="wss://ftx.com/ws/";

    private static String FTX_AUTH_APIKEY_READONLY="SuIFCXbAagS_fOo1KE2GG2cQtgs3JedTADgQ4Qf6";
    private static String FTX_AUTH_SECRET_READONLY="YWKcBB_vnn7KnEhVYYkk7PluIZ1O8GHluPwLP352";

    private static String FTX_AUTH_APIKEY="0QVG_HiIEBjR0BvvixqK1wCpS7wbaRIprDGpXwlq";
    private static String FTX_AUTH_SECRET="65hoKLq3xaZ0SoMrkaEaQeet5DBrL-nN2N3XFVQm";

    private static Gson gson = new Gson();

    public static void main (String[] args) throws InterruptedException, TimeoutException, ExecutionException {

        var client = HttpClient.newHttpClient();
        var wsListener=new FtxWebSocketListener();
        WebSocket webSocket = client.newWebSocketBuilder()
                .buildAsync(URI.create(FTX_WSS_URL), wsListener).join();
        WsRequest req=new WsRequest("subscribe","trades","BTC-PERP");
        CompletableFuture<WebSocket>  res=webSocket.sendText(gson.toJson(req), true);

        System.out.println("Ret : " + res.get(5, TimeUnit.SECONDS));

        TimeUnit.SECONDS.sleep(30);
        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok");
    }
}
