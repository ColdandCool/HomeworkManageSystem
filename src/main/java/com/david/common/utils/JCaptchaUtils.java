package com.david.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Verification utils
 *
 * @author david.cn
 * @version 1.0
 */
public class JCaptchaUtils {

    private static final char[] CH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final Random RANDOM = new Random();

    public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse) {
        if (request.getSession(false) == null) return false;

        boolean validated = false;
        boolean jcaptchaEbabled = (boolean) request.getAttribute("jcaptchaEbabled");
        if (jcaptchaEbabled) {
            String old = (String) request.getSession().getAttribute("piccode");
            if (old.equalsIgnoreCase(userCaptchaResponse)) {
                return true;
            }
        }
        return validated;
    }

    /**
     * generate
     *
     * @param request
     * @return
     */
    public static BufferedImage getImageChallengeForID(HttpServletRequest request) {
        BufferedImage bi = new BufferedImage(100, 36, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(211, 211, 211);
        g.setColor(c);
        g.fillRect(0, 0, 100, 36);

        int len = CH.length, index;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = RANDOM.nextInt(len);
            g.setColor(new Color(RANDOM.nextInt(88), RANDOM.nextInt(188), RANDOM.nextInt(255)));
            g.drawString(CH[index] + "", (i * 24) + 4, 25);
            sb.append(CH[index]);
        }
        request.getSession().setAttribute("piccode", sb.toString());
        return bi;
    }

    /**
     * To determine whether there is a verification code
     *
     * @param request
     * @param userCaptchaResponse
     * @return
     */
    public static boolean hasCaptcha(HttpServletRequest request, String userCaptchaResponse) {
        if (request.getSession(false) == null) return false;
        boolean validated = false;
//        try {
//            String id = request.getSession().getId();
//            validated = captchaService.hasCapcha(id, userCaptchaResponse);
//        } catch (CaptchaServiceException e) {
//            e.printStackTrace();
//        }
        return validated;
    }

}
