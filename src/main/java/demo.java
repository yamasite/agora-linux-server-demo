import io.agora.rtc.SDK;
import io.agora.rtc.AgoraRtcConn;
import io.agora.rtc.AgoraService;
import io.agora.rtc.AgoraServiceConfig;
import io.agora.rtc.DefaultRtcConnObserver;
import io.agora.rtc.RtcConnInfo;

public class demo {
    public static class ConnObserver extends DefaultRtcConnObserver {
        @Override
        public void onConnected(AgoraRtcConn conn, RtcConnInfo rtcConnInfo, int reason) {
            System.out.println("join success");
        }
    }

    public static void main(String[] args) throws Exception {
        String token = null;
        SDK.load(); // ensure JNI library load
        AgoraService service = new AgoraService();
        AgoraServiceConfig config = new AgoraServiceConfig();
        config.setEnableAudioProcessor(1);
        config.setEnableAudioDevice(0);
        config.setEnableVideo(0);
        config.setAppId("");
        service.initialize(config);

        AgoraRtcConn conn = service.agoraRtcConnCreate(null);

        conn.registerObserver(new ConnObserver());

        conn.connect(token, "test_channel", "1");

        Thread.sleep(2000);
        conn.disconnect();
        conn.destroy();
        service.destroy();
    }

}
