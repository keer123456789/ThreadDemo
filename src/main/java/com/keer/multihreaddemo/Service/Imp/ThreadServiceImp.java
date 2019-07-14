package com.keer.multihreaddemo.Service.Imp;

import com.keer.multihreaddemo.Service.IThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: multihreaddemo
 * @BelongsPackage: com.keer.multihreaddemo.Service.Imp
 * @Author: keer
 * @CreateTime: 2019-07-14 22:48
 * @Description: 线程方法实现
 */
@Service
public class ThreadServiceImp implements IThreadService {
    private static Logger logger = LoggerFactory.getLogger(ThreadServiceImp.class);

    @Async("asyncServiceExecutor")
    @Override
    public void executeAsync(String info) {
        //线程方法，可自行定义逻辑
        logger.info("start executeAsync");
        try{
            logger.info("###"+info+"###");
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }
}
