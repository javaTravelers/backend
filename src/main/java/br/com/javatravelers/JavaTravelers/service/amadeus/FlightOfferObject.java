package br.com.javatravelers.JavaTravelers.service.amadeus;

import com.amadeus.resources.Resource;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
public class FlightOfferObject extends Resource {
	  protected FlightOfferObject() {}

	  private String type;
	  private String id;
	  private String source;
	  private boolean instantTicketingRequired;
	  private boolean nonHomogeneous;
	  private boolean oneWay;
	  private String lastTicketingDate;
	  private int numberOfBookableSeats;
	  private Itinerary[] itineraries;
	  private SearchPrice price;
	  private PricingOptions pricingOptions;
	  private String[] validatingAirlineCodes;
	  private TravelerPricing[] travelerPricings;
	  private String choiceProbability;

	  @ToString
	  public class Itinerary {
	    protected Itinerary() {
	    }

	    private String duration;
	    private SearchSegment[] segments;

	  }

	  @ToString
	  public class SearchSegment {
	    protected SearchSegment() {
	    }

	    private AirportInfo departure;
	    private AirportInfo arrival;
	    private String carrierCode;
	    private String number;
	    private Aircraft aircraft;
	    private String duration;
	    private String id;
	    private int numberOfStops;
	    private boolean blacklistedInEU;
	    private Co2Emissions[] co2Emissions;
	  }

	  @ToString
	  public class Co2Emissions {
	    protected Co2Emissions() {
	    }

	    private int weight;
	    private String weightUnit;
	    private String cabin;
	  }

	  @ToString
	  public class AirportInfo {
	    protected AirportInfo() {
	    }

	    private String iataCode;
	    private String terminal;
	    private String at;
	  }

	  @ToString
	  public class Aircraft {
	    protected Aircraft() {
	    }

	    private String code;
	  }

	  @ToString
	  public class SearchPrice {
	    protected SearchPrice() {
	    }

	    private String currency;
	    private double total;
	    private double base;
	    private Fee[] fees;
	    private double grandTotal;
	  }

	  @ToString
	  public class Fee {
	    protected Fee() {
	    }

	    private double amount;
	    private String type;
	  }

	  @ToString
	  public class PricingOptions {
	    private boolean includedCheckedBagsOnly;
	    private String[] fareType;
	    private String[] corporateCodes;
	    private boolean refundableFare;
	    private boolean noRestrictionFare;
	    private boolean noPenaltyFare;
	  }

	  @ToString
	  public class TravelerPricing {
	    protected TravelerPricing() {
	    }

	    private String travelerId;
	    private String fareOption;
	    private String travelerType;
	    private SearchPrice price;
	    private FareDetailsBySegment[] fareDetailsBySegment;
	  }

	  @ToString
	  public class FareDetailsBySegment {
	    protected FareDetailsBySegment() {
	    }

	    private String segmentId;
	    private String cabin;
	    private String fareBasis;
	    @SerializedName("class")
	    private String segmentClass;
	    private IncludedCheckedBags includedCheckedBags;

	  }

	  @ToString
	  public class IncludedCheckedBags {
	    protected IncludedCheckedBags() {
	    }

	    private int weight;
	    private String weightUnit;
	  }
	}
