package com.istateca.app.istateca.utilidades;


import com.squareup.okhttp.*;
import org.json.JSONObject;

import java.io.IOException;

public class NotificacionDevice {
    private static final String FCM_API = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AAAA_Ah6vzo:APA91bHA_7fqwTMY4DQhZxeixNL5hQPRSW_TIzEgPhElWY8riZDJMF8vLeqRtBBkt9V6tD77rF78VKN-dAd096ngE0p4OQGlq5hkjg8jPlOSVDVBpPruMW1-Cglr0jltAB7AKmqRKIkL";

    public static void enviarNotificacion(String deviceToken, String titulo, String cuerpo) {
        OkHttpClient cliente = new OkHttpClient();
        System.out.println("Entro a notificar");
        JSONObject notificacion = new JSONObject();
        JSONObject data = new JSONObject();

        try {
            notificacion.put("title", titulo);
            notificacion.put("body", cuerpo);
            notificacion.put("click_action", "FLUTTER_NOTIFICATION_CLICK");
            notificacion.put("priority", "high");

            data.put("custom_key", "custom_value");

            JSONObject request = new JSONObject();
            request.put("to", deviceToken);
            request.put("notification", notificacion);
            request.put("data", data);

            RequestBody cuerpoSolicitud = RequestBody.create(MediaType.parse("application/json"), request.toString());

            Request solicitud = new Request.Builder()
                    .url(FCM_API)
                    .post(cuerpoSolicitud)
                    .addHeader("Authorization", "key=" + SERVER_KEY)
                    .addHeader("Content-Type", "application/json")
                    .build();

            cliente.newCall(solicitud).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    System.out.println("notificacion fallida "+e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    System.out.println("notificacion enviada con exito");
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
