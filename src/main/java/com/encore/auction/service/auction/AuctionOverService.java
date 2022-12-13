package com.encore.auction.service.auction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.model.auction.failed.log.AuctionFailedLog;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.auction.item.ItemSoldState;
import com.encore.auction.model.bidding.aftbidding.AftBidding;
import com.encore.auction.model.bidding.aftbidding.BiddingResult;
import com.encore.auction.model.bidding.bidding.Bidding;
import com.encore.auction.repository.AftBiddingRepository;
import com.encore.auction.repository.AuctionFailedLogRepository;
import com.encore.auction.repository.AuctionItemRepository;
import com.encore.auction.repository.BiddingRepository;
import com.encore.auction.utils.mapper.AuctionMapper;
import com.encore.auction.utils.mapper.BiddingMapper;

@Service
public class AuctionOverService {

	private final AuctionItemRepository auctionItemRepository;
	private final BiddingRepository biddingRepository;
	private final AftBiddingRepository aftBiddingRepository;
	private final AuctionFailedLogRepository auctionFailedLogRepository;

	public AuctionOverService(AuctionItemRepository auctionItemRepository, BiddingRepository biddingRepository,
		AftBiddingRepository aftBiddingRepository, AuctionFailedLogRepository auctionFailedLogRepository) {
		this.auctionItemRepository = auctionItemRepository;
		this.biddingRepository = biddingRepository;
		this.aftBiddingRepository = aftBiddingRepository;
		this.auctionFailedLogRepository = auctionFailedLogRepository;
	}

	@Transactional
	public void setAuctionOverResult(Long auctionItemId) {
		List<Bidding> biddingList = biddingRepository.findByAuctionItemId(auctionItemId);

		AuctionItem auctionItem = auctionItemRepository.findById(auctionItemId)
			.orElseThrow(() -> new NonExistResourceException("Auction Item does not exist"));

		if (biddingList.isEmpty()) {
			auctionFail(auctionItem);
		} else {
			auctionSuccess(auctionItem, biddingList);
		}
	}

	private void auctionSuccess(AuctionItem auctionItem, List<Bidding> biddingList) {
		Bidding maxAmountBidding = biddingList.stream().max(Comparator.comparingLong(Bidding::getAmount)).get();

		AftBidding aftSuccessBidding = BiddingMapper.of().biddingEntityToAftBidding(maxAmountBidding,
			BiddingResult.SUCCESS);

		aftBiddingRepository.save(aftSuccessBidding);

		biddingList.remove(maxAmountBidding);

		List<AftBidding> failBiddingList = biddingList.stream()
			.map(e -> BiddingMapper.of().biddingEntityToAftBidding(e, BiddingResult.FAIL))
			.collect(Collectors.toList());

		aftBiddingRepository.saveAll(failBiddingList);

		auctionItem.updateItemSoldState(ItemSoldState.SOLD);
	}

	private void auctionFail(AuctionItem auctionItem) {
		AuctionFailedLog auctionFailedLog = AuctionMapper.of().entityToAuctionFailedLog(auctionItem);

		auctionItem.updateItemSoldState(ItemSoldState.FAIL);

		auctionItem.increaseFailCount();

		auctionFailedLogRepository.save(auctionFailedLog);
	}
}
