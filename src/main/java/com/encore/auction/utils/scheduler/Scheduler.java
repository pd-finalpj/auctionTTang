package com.encore.auction.utils.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.encore.auction.controller.auction.responses.AuctionOverDateResponse;
import com.encore.auction.repository.AuctionOverRepository;
import com.encore.auction.service.auction.AuctionOverService;

@Component
public class Scheduler {

	private final AuctionOverRepository auctionOverRepository;
	private final AuctionOverService auctionOverService;
	private int THREADS = 0;

	public Scheduler(AuctionOverRepository auctionOverRepository, AuctionOverService auctionOverService) {
		this.auctionOverRepository = auctionOverRepository;
		this.auctionOverService = auctionOverService;
	}

	@Scheduled(cron = "0 0 0/1 * * *")
	public void auctionOverSchedule() {
		LocalDateTime now = LocalDateTime.now();
		now = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), 0, 0);

		List<AuctionOverDateResponse> auctionOverDateResponses = auctionOverRepository.auctionOverListByStartDateAndEndDate(
			now);

		THREADS = auctionOverDateResponses.size();

		for (int i = 0; i < THREADS; ++i) {
			AuctionOverDateResponse auctionOverDateResponse = auctionOverDateResponses.get(i);

			AuctionOverSleepRunnable runnable = new AuctionOverSleepRunnable(auctionOverDateResponse, now);

			runnable.setAuctionOverService(auctionOverService);

			new Thread(runnable).start();
		}
	}

}
