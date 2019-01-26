package com.wayne.utils;

import java.util.Collections;
import java.util.List;

public class CollectionUtils {
    private CollectionUtils(){};

    public static boolean isNotEmpty(List l){
        return null!= l && l.size()>0;
    }

}
