package com._1forge.java;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

class WebSocketClient implements WebSocket.Listener {
    java.net.http.WebSocket ws;
    private SocketClientListener listener;

    public WebSocketClient(final SocketClientListener listener) {
        this.listener = listener;
        ws = HttpClient.newHttpClient().newWebSocketBuilder()
                .buildAsync(URI.create("wss://sockets.1forge.com/socket"), this).join();
    }

    @Override
    public void onOpen(final WebSocket webSocket) {
        // System.out.println("onOpen using subprotocol " + webSocket.getSubprotocol());
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(final WebSocket webSocket, final CharSequence data, final boolean last) {
        String[] parts = data.toString().split("\\|");
        // System.out.println("onText received " + data.toString());
        if (parts.length > 0) {
            switch (parts[0]) {
                case "post_login_success":
                    this.listener.onLoginSuccessful();
                case "update":
                    this.listener.onUpdate(parts[1]);
                default:
                    // System.out.println("Data: " + data.toString());

            }
        }
        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    @Override
    public void onError(final WebSocket webSocket, final Throwable error) {
        // System.out.println("Bad day! " + error.toString());
        WebSocket.Listener.super.onError(webSocket, error);
    }

    public void login(final String apiKey) {
        ws.sendText("login|" + apiKey, true);
    }

    public void subscribeTo(final String pair) {
        ws.sendText("subscribe_to|" + pair, true);
    }

    public void subscribeToAll() {
        ws.sendText("subscribe_to_all", true);
    }

    public void unsubscribeFrom(final String pair) {
        ws.sendText("unsubscribe_from|" + pair, true);
    }

    public void unsubscribeFromAll() {
        ws.sendText("unsubscribe_from_all", true);
    }
}
