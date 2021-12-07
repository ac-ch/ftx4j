package com.innodiff.api.ftx4j.ws.messages;

public class WsRequest {
    private final String op;
    private final String channel;
    private final String market;


    public WsRequest(String op, String channel, String market) {
        this.op = op;
        this.channel = channel;
        this.market = market;
    }

}
