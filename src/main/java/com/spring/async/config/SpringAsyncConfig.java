package com.spring.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

@Configuration
@EnableAsync
public class SpringAsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
        threadPoolTaskExecutor.setThreadFactory(virtualThreadFactory);
        threadPoolTaskExecutor.setCorePoolSize(5); // Minimum number of threads in the pool
        threadPoolTaskExecutor.setMaxPoolSize(300); // Maximum number of threads in the pool
        threadPoolTaskExecutor.setQueueCapacity(500); // Capacity of the queue for holding submitted tasks
        threadPoolTaskExecutor.setThreadNamePrefix("MyAsyncTask-"); // Prefix for the names of threads in the pool
        threadPoolTaskExecutor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy()); // Example: use CallerRunsPolicy
        threadPoolTaskExecutor.initialize(); // Initializes the executor
        // Configure other properties like corePoolSize, maxPoolSize, queueCapacity if needed
         return threadPoolTaskExecutor;
    }
}
