package com.dal.mycareer.DTOMapper;

import java.util.HashMap;
import java.util.Map;

public class DTOMapper
{
    public static Map <String, IDTOMapper> dtoMap = new HashMap() 
    {{
        put("jobDetailsMapper", new JobDetailsMapper());
    }};
}