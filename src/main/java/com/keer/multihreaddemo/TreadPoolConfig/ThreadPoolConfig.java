package com.keer.multihreaddemo.TreadPoolConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @BelongsProject: multihreaddemo
 * @BelongsPackage: com.keer.multihreaddemo.TreadPoolConfig
 * @Author: keer
 * @CreateTime: 2019-07-14 22:47
 * @Description: 线程池配置类
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);

    @Value("${CorePoolSize}")
    private int CorePoolSize;

    @Value("${MaxPoolSize}")
    private int MaxPoolSize;

    @Value("${QueueCapacity}")
    private int QueueCapacity;

    @Bean
    public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(CorePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(MaxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(QueueCapacity);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
