package org.bsm.sphinx;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class test {
    //设置APPID/AK/SK
    public static final String APP_ID = "18595904";
    public static final String API_KEY = "1OlEjVz6Zck7h4kdCpSu2GDX";
    public static final String SECRET_KEY = "1gylrnGUjKdUbFSqUyGd54LwUCCUStno";
    private static int num = 0;
//    public static void main(String[] args) {
////        baiduaip();
//        final Webcam webcam = Webcam.getDefault();
//        webcam.setViewSize(WebcamResolution.VGA.getSize());
//        WebcamPanel panel = new WebcamPanel(webcam);
//        panel.setFPSDisplayed(true);
//        panel.setDisplayDebugInfo(true);
//        panel.setImageSizeDisplayed(true);
//        panel.setMirrored(true);
//
//        final JFrame window = new JFrame("摄像头");
//        window.addWindowListener(new WindowAdapter() {
//
//            @Override
//            public void windowClosed(WindowEvent e)
//            {
//                webcam.close();
//                window.dispose();
//            }
//        });
//        // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        final JButton button = new JButton("截图");
//        window.add(panel, BorderLayout.CENTER);
//        window.add(button, BorderLayout.SOUTH);
//        window.setResizable(true);
//        window.pack();
//        window.setVisible(true);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e)
//            {
//                button.setEnabled(false);
//                String fileName = "D://" + num;
//                WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
//                SwingUtilities.invokeLater(new Runnable() {
//
//                    @Override
//                    public void run()
//                    {
//                        JOptionPane.showMessageDialog(null, "截图成功");
//                        button.setEnabled(true);
//                        num++;
//                        return;
//                    }
//                });
//            }
//        });
//    	
//    }

    private static void baiduaip() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "log4j.properties");

        // 调用接口
        String path = "D:/123.png";
        HashMap<String, String> map = new HashMap<>();
        JSONObject res = client.basicGeneral(path, map);
        System.out.println(res.toString(2));
    }
}