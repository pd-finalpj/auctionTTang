package com.encore.auction.service.auction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.model.auction.failed.log.AuctionFailedLog;
import com.encore.auction.model.auction.failed.log.AuctionFailedLogDetails;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.auction.item.ItemSoldState;
import com.encore.auction.model.bidding.aftbidding.AftBidding;
import com.encore.auction.model.bidding.aftbidding.BiddingResult;
import com.encore.auction.model.bidding.bidding.Bidding;
import com.encore.auction.model.biddingdetails.BiddingDetails;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.auction.AuctionFailedLogRepository;
import com.encore.auction.repository.auction.AuctionItemRepository;
import com.encore.auction.repository.bidding.aftbidding.AftBiddingRepository;
import com.encore.auction.repository.bidding.bidding.BiddingRepository;
import com.encore.auction.repository.mongodb.AuctionFailedLogMongoDBRepository;
import com.encore.auction.repository.mongodb.BiddingDetailsMongoDBRepository;
import com.encore.auction.repository.user.UserRepository;
import com.encore.auction.utils.mapper.AuctionMapper;
import com.encore.auction.utils.mapper.BiddingMapper;

@Service
public class AuctionOverService {

	private final AuctionItemRepository auctionItemRepository;
	private final BiddingRepository biddingRepository;
	private final AftBiddingRepository aftBiddingRepository;
	private final AuctionFailedLogRepository auctionFailedLogRepository;
	private final BiddingDetailsMongoDBRepository biddingDetailsMongoDBRepository;
	private final UserRepository userRepository;
	private final AuctionFailedLogMongoDBRepository auctionFailedLogMongoDBRepository;

	public AuctionOverService(AuctionItemRepository auctionItemRepository, BiddingRepository biddingRepository,
		AftBiddingRepository aftBiddingRepository, AuctionFailedLogRepository auctionFailedLogRepository,
		BiddingDetailsMongoDBRepository biddingDetailsMongoDBRepository, UserRepository userRepository,
		AuctionFailedLogMongoDBRepository auctionFailedLogMongoDBRepository) {
		this.auctionItemRepository = auctionItemRepository;
		this.biddingRepository = biddingRepository;
		this.aftBiddingRepository = aftBiddingRepository;
		this.auctionFailedLogRepository = auctionFailedLogRepository;
		this.biddingDetailsMongoDBRepository = biddingDetailsMongoDBRepository;
		this.userRepository = userRepository;
		this.auctionFailedLogMongoDBRepository = auctionFailedLogMongoDBRepository;
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

		saveLogDataToMongoDB(auctionItem, maxAmountBidding, aftSuccessBidding);

		biddingList.remove(maxAmountBidding);

		List<AftBidding> failBiddingList = biddingList.stream()
			.map(e -> BiddingMapper.of().biddingEntityToAftBidding(e, BiddingResult.FAIL))
			.collect(Collectors.toList());

		List<AftBidding> aftBiddings = aftBiddingRepository.saveAll(failBiddingList);

		for (int i = 0; i < biddingList.size(); i++) {
			saveLogDataToMongoDB(auctionItem, biddingList.get(i), aftBiddings.get(i));
		}

		auctionItem.updateItemSoldState(ItemSoldState.SOLD);
	}

	private void auctionFail(AuctionItem auctionItem) {
		AuctionFailedLog auctionFailedLog = AuctionMapper.of().entityToAuctionFailedLog(auctionItem);

		auctionItem.updateItemSoldState(ItemSoldState.FAIL);

		auctionItem.increaseFailCount();

		AuctionFailedLog savedAuctionFailedLog = auctionFailedLogRepository.save(auctionFailedLog);

		saveAuctionFailLogDataToMongoDB(auctionItem, savedAuctionFailedLog);
	}

	private void saveLogDataToMongoDB(AuctionItem auctionItem, Bidding bidding, AftBidding aftBidding) {
		User user = userRepository.findById(bidding.getUser().getId())
			.orElseThrow(() -> new NonExistResourceException("user does not exist"));

		BiddingDetails biddingDetails = new BiddingDetails(bidding.getId(), user.getId(), user.getBirth(),
			bidding.getBiddingDate(), bidding.getAmount(), aftBidding.getDecideDate(), aftBidding.getBiddingResult(),
			AuctionMapper.of().entityToAuctionDetailsResponse(auctionItem));

		biddingDetailsMongoDBRepository.save(biddingDetails);
	}

	private void saveAuctionFailLogDataToMongoDB(AuctionItem auctionItem, AuctionFailedLog auctionFailedLog) {
		AuctionFailedLogDetails auctionFailedLogDetails = new AuctionFailedLogDetails(auctionFailedLog.getId(),
			AuctionMapper.of()
				.entityToAuctionDetailsResponse(auctionItem));

		auctionFailedLogMongoDBRepository.save(auctionFailedLogDetails);
	}
}
