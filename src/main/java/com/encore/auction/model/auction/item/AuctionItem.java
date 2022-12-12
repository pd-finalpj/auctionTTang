package com.encore.auction.model.auction.item;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import com.encore.auction.controller.auction.requests.AuctionUpdateRequest;
import com.encore.auction.model.BaseEntity;
import com.encore.auction.model.address.Address;
import com.encore.auction.model.manager.Manager;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "state = false")
public class AuctionItem extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "manager_id")
	private Manager manager;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "address_code")
	private Address address;

	@Column(nullable = false, length = 20)
	private String auctionItemCaseNumber;

	@Column(nullable = false, length = 100)
	private String auctionItemName;

	@Column(nullable = false, length = 10)
	private String location;

	@Column(nullable = false, length = 8)
	private String lotNumber;

	@Column(length = 50)
	private String addressDetail;

	@Column(nullable = false)
	private Long appraisedValue;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime auctionStartDate;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime auctionEndDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ItemCategory itemCategory;

	@Column(nullable = false)
	private Double areaSize;

	@Column(nullable = false, columnDefinition = "tinyint default 0")
	private Integer auctionFailedCount;

	@Column(nullable = false, columnDefinition = "int default 0")
	private Integer bookmarkCount;

	@Column(nullable = false, columnDefinition = "int default 0")
	private Integer hit;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ItemSoldState itemSoldState;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public AuctionItem(Long id, Manager manager, Address address, String auctionItemCaseNumber, String auctionItemName,
		String location, String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate, ItemCategory itemCategory, Double areaSize, Integer auctionFailedCount,
		Integer bookmarkCount, Integer hit, ItemSoldState itemSoldState, Boolean state) {
		this.id = id;
		this.manager = manager;
		this.address = address;
		this.auctionItemCaseNumber = auctionItemCaseNumber;
		this.auctionItemName = auctionItemName;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.appraisedValue = appraisedValue;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.itemCategory = itemCategory;
		this.areaSize = areaSize;
		this.auctionFailedCount = auctionFailedCount;
		this.bookmarkCount = bookmarkCount;
		this.hit = hit;
		this.itemSoldState = itemSoldState;
		this.state = state;
	}

	@Override
	public String toString() {
		return "AuctionItem{" +
			"id=" + id +
			", auctionItemCaseNumber='" + auctionItemCaseNumber + '\'' +
			", auctionItemName='" + auctionItemName + '\'' +
			", location='" + location + '\'' +
			", lotNumber='" + lotNumber + '\'' +
			", addressDetail='" + addressDetail + '\'' +
			", appraisedValue=" + appraisedValue +
			", auctionStartDate=" + auctionStartDate +
			", auctionEndDate=" + auctionEndDate +
			", itemCategory=" + itemCategory +
			", areaSize=" + areaSize +
			", auctionFailedCount=" + auctionFailedCount +
			", bookmarkCount=" + bookmarkCount +
			", hit=" + hit +
			", itemSoldState=" + itemSoldState +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AuctionItem that = (AuctionItem)o;
		return Objects.equals(id, that.id) && Objects.equals(manager, that.manager)
			&& Objects.equals(address, that.address) && Objects.equals(auctionItemCaseNumber,
			that.auctionItemCaseNumber) && Objects.equals(auctionItemName, that.auctionItemName)
			&& Objects.equals(location, that.location) && Objects.equals(lotNumber, that.lotNumber)
			&& Objects.equals(addressDetail, that.addressDetail) && Objects.equals(appraisedValue,
			that.appraisedValue) && Objects.equals(auctionStartDate, that.auctionStartDate)
			&& Objects.equals(auctionEndDate, that.auctionEndDate) && itemCategory == that.itemCategory
			&& Objects.equals(areaSize, that.areaSize) && Objects.equals(auctionFailedCount,
			that.auctionFailedCount) && Objects.equals(bookmarkCount, that.bookmarkCount)
			&& Objects.equals(hit, that.hit) && itemSoldState == that.itemSoldState && Objects.equals(
			state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, auctionItemCaseNumber, auctionItemName, location, lotNumber, addressDetail,
			appraisedValue,
			auctionStartDate, auctionEndDate, itemCategory, areaSize, auctionFailedCount, bookmarkCount, hit,
			itemSoldState,
			state);
	}

	public void deleteAuctionItem() {
		this.state = true;
	}

	public void increaseHit() {
		this.hit++;
	}

	public void updateAuctionItem(AuctionUpdateRequest auctionupdateRequest, Manager manager, Address address) {
		this.address = address;
		this.manager = manager;
		this.auctionItemName = auctionupdateRequest.getAuctionItemName();
		this.location = auctionupdateRequest.getLocation();
		this.lotNumber = auctionupdateRequest.getLotNumber();
		this.addressDetail = auctionupdateRequest.getAddressDetail();
		this.appraisedValue = auctionupdateRequest.getAppraisedValue();
		this.auctionStartDate = auctionupdateRequest.getAuctionStartDate();
		this.auctionEndDate = auctionupdateRequest.getAuctionEndDate();
		this.itemCategory = auctionupdateRequest.getItemCategory();
		this.areaSize = auctionupdateRequest.getAreaSize();
	}

	public void updateItemSoldState(ItemSoldState state) {
		this.itemSoldState = state;
	}

	public void increaseFailCount() {
		this.auctionFailedCount++;
	}

	public void increaseBookmark() {
		this.bookmarkCount++;
	}

	public void decreaseBookmark() {
		this.bookmarkCount++;
	}
}
