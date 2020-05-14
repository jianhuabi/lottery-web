
package com.sinodata.lottery.webexample;

import io.micrometer.core.instrument.MeterRegistry;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class Application {
    public static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        //具体操作看官网。官网也提供了spring的方法
//        Counter requests = Counter.build()
//                                  .name("my_library_requests_total")
//                                  .help("Total requests.")
//                                  .labelNames("path")
//                                  .register();
//        try {
//            InetSocketAddress address = new InetSocketAddress(5566);
//            CollectorRegistry registry = new CollectorRegistry(true);
//            registry.register(requests);
//            HTTPServer server2 = new HTTPServer(address, registry);
//            requests.labels("get").inc();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        SpringApplication.run(Application.class, args);
    }

    // 监控 jvm
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName){
        return registry -> registry.config().commonTags("application",applicationName);
    }

    //从kafka demo topic上消费消息
    //private final CountDownLatch latch = new CountDownLatch(3);
    /*@KafkaListener(topics = "demo")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info(cr.toString());
        //latch.countDown();
    }*/

}
