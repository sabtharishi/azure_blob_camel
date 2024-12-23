package com.rishilearnings.azstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AzstorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzstorageApplication.class, args);
	}

//
//	@Bean
//	public Consumer<Message<String>> consume() {
//		return message -> {
//			List<EventGridEvent> eventData = EventGridEvent.fromString(message.getPayload());
//			eventData.forEach(event -> {
//				log.info("New event received: '{}'", event.getData());
//			});
//		};
//	}
}


