public class WeworkConfig {
    public String agentId="3010040";
    public String secret="szDWP5SehZZPPQk1zFxS7Q6R0S3_dTlN0aV7MYFE";
    public String corpid="ww416484273c17f7d7";
    public String contactSecret="RNQ2v0-FQL9uMIo6LlNKDUgyPrlYnYrp9ZllYP4L76c";

    public static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if (weworkConfig==null){
            weworkConfig=new WeworkConfig();
        }
        return weworkConfig;
    }
}
