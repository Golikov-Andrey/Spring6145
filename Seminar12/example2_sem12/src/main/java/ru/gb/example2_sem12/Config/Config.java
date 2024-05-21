package ru.gb.example2_sem12.Config;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.feed.dsl.Feed;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.AbstractPayloadTransformer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class Config {

    @Bean
    public IntegrationFlow feedFlow() throws MalformedURLException{
        return IntegrationFlow.from(Feed.inboundAdapter(new URL("https://lenta.ru/rss"), "news"), e -> e.poller(p -> p.fixedDelay(5000)))
                .transform(extractorFeed())
                .handle(myHandler())
                .get();
    }


    @Bean
    public AbstractPayloadTransformer<SyndEntry, String> extractorFeed() {
        return new AbstractPayloadTransformer<SyndEntry, String>() {
            @Override
            protected String transformPayload(SyndEntry payload) {
                return payload.getTitle() + " " + payload.getAuthor() + " " + payload.getLink();
            }
        };
    }

    @Bean
    public FileWritingMessageHandler myHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/home/alex/Загрузки/example2_sem12/src/main/java/ru/gb"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        handler.setAutoCreateDirectory(true);
        handler.setCharset("UTF8");
        return handler;
    }
}


