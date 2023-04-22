package com.uet.quangnv.service;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

public interface Constant {
    String LOCATION = "D:/historyIntroductionData";

    interface FileType {
        Integer IMAGE = 0;
        String IMAGE_NAME = "/IMAGE";
        Integer SOUND = 1;
        String SOUND_NAME = "/SOUND";
        Integer VIDEO = 2;
        String VIDEO_NAME = "/VIDEO";

    }

    interface FileReferenceType {
        Integer USER = 0;
        String USER_NAME = "/User_";

        Integer ARTICLE = 1;
        String ARTICLE_NAME = "/Article_";
    }
}
