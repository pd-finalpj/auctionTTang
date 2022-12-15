package com.encore.auction.utils.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;

import com.encore.auction.controller.auction.responses.AuctionOverDateResponse;
import com.encore.auction.exception.SchedulerThreadException;
import com.encore.auction.service.auction.AuctionOverService;

public class AuctionOverSleepRunnable implements Runnable {

	private AuctionOverService auctionOverService;

	private Long auctionItemId = 0L;
	private long delay;

	public AuctionOverSleepRunnable(AuctionOverDateResponse auctionOverDateResponse, LocalDateTime now) {
		this.auctionItemId = auctionOverDateResponse.getAuctionItemId();
		this.delay = Duration.between(now, auctionOverDateResponse.getAuctionEndDate()).getSeconds() * 1000;
	}

	public void setAuctionOverService(AuctionOverService auctionOverService) {
		this.auctionOverService = auctionOverService;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			throw new SchedulerThreadException(e.getMessage());
		}
		auctionOverService.setAuctionOverResult(auctionItemId);
	}
}
